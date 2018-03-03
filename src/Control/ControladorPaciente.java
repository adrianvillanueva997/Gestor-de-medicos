package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;


public class ControladorPaciente implements ActionListener{
	
	JFrame frmDialogo;
	
	public VentanaPaciente ventanaControlada;
	public Paciente usuarioPaciente;
	
	//Funci�n controladora de la ventana de Paciente
	public ControladorPaciente(VentanaPaciente win, Paciente usuarioPaciente){
		ventanaControlada = win;
		this.usuarioPaciente = usuarioPaciente;
		
	}

	//Funci�n que indica las acciones que realizan los distintos objetos de la ventana
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_Paciente_Atras)){
			int respuesta = JOptionPane.showConfirmDialog( frmDialogo, "�Desea cerrar sesi�n?", "Cerrar Sesi�n", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				abrirVentanaIndex();
			}
		} else if(e.getSource().equals(ventanaControlada.btn_Paciente_HistorialSesiones)){
			try {
				abrirVentanaCalendario(usuarioPaciente);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(ventanaControlada.btn_Paciente_SubirSesion)) {
			abrirFormularioSesion();
		} else if (e.getSource().equals(ventanaControlada.btn_Paciente_Editar)){
			abrirFormularioModificarDatos(usuarioPaciente);
		} else if (e.getSource().equals(ventanaControlada.btn_CambiarPass)){
			abrirCambiarPass();
		}
	}
	
	public void abrirVentanaIndex(){
		ventanaControlada.frmPaciente.setVisible(false);
		VentanaIndex mainframe=new VentanaIndex();
		ControladorIndex mc=new ControladorIndex(mainframe);
		mainframe.addController(mc);
		mainframe.crearVentana();
	}
	
	public void abrirVentanaCalendario(Paciente usuarioPaciente) throws IOException{
		  ventanaControlada.frmPaciente.dispose();
		  VentanaCalendarioPaciente vs = new VentanaCalendarioPaciente();
		  Fichero fich=new Fichero();
		  CalendarControllerPaciente cs = new CalendarControllerPaciente(vs,fich.sesionesPaciente(usuarioPaciente),usuarioPaciente);
		  vs.addController(cs);
		  vs.crearVentana(fich.sesionesPaciente(usuarioPaciente));
		 }
	
	public void abrirFormularioSesion() {
		ventanaControlada.frmPaciente.setEnabled(false);
		VentanaFormularioSesion vfs = new VentanaFormularioSesion();
		ControladorFormularioSesion cs = new ControladorFormularioSesion(vfs, ventanaControlada, usuarioPaciente);
		vfs.addController(cs);
		vfs.crearVentana();
		
	}
	
	public void abrirFormularioModificarDatos(Paciente paciente){
		ventanaControlada.frmPaciente.setEnabled(false);
		VentanaModPac vm = new VentanaModPac();
		ControladorModPac cm = new ControladorModPac(vm, ventanaControlada, paciente);
		vm.addController(cm);
		vm.crearVentana(paciente);
	}
	
	public void abrirCambiarPass(){
		VentanaCambioPass vp = new VentanaCambioPass();
		ControladorCambioPass cp = new ControladorCambioPass(vp, usuarioPaciente);
		vp.addController(cp);
		vp.CrearVentana(usuarioPaciente);
	}
}

