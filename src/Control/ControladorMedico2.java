package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

import Modelo.*;
import Vista.*;

public class ControladorMedico2 implements ActionListener{
	public VentanaMedico2 ventanaControlada2;
	public VentanaMedico ventanaMedico;
	JFrame frmDialogo;
	public Medico usuarioMedico;
	public Paciente usuarioPaciente;
	Usuario us;
	
	//Funciï¿½n controladora de la ventana de Mï¿½dico
	public ControladorMedico2(VentanaMedico2 win, Medico usuarioMedico, Paciente usuarioPaciente, VentanaMedico win2){
		ventanaControlada2 = win;
		this.usuarioMedico = usuarioMedico;
		this.usuarioPaciente = usuarioPaciente;
		this.ventanaMedico = win2;
	}
	
	public ControladorMedico2(VentanaMedico2 win, Medico usuarioMedico, Paciente usuarioPaciente){
		ventanaControlada2 = win;
		this.usuarioMedico = usuarioMedico;
		this.usuarioPaciente = usuarioPaciente;
	}

	//Funciï¿½n que indica las acciones que realizan los distintos objetos de la ventana
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada2.btn_Medico_Atras)){
			ventanaControlada2.frmMedico2.dispose();
			ventanaMedico.frmMedico.dispose();
			try {
				us = Fichero.comprobarUsuario(usuarioMedico.getNombreUsuario(), usuarioMedico.getContrasena());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			usuarioMedico = Fichero.busquedaMedico(us);
			try {
				abrirVentanaMedico1(usuarioMedico);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource().equals(ventanaControlada2.btn_Medico_HistorialSesiones)){
			try {
				try {
					abrirVentanaCalen(usuarioMedico, usuarioPaciente);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource().equals(ventanaControlada2.btn_Medico_DarAlta)) {
			Fichero fich = new Fichero();
			int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "¿Desea darle el alta?", "Dar el alta", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				fich.updateDelPac(usuarioPaciente.getId_usuario());
				ventanaControlada2.frmMedico2.dispose();
				ventanaMedico.frmMedico.dispose();
				try {
					us = Fichero.comprobarUsuario(usuarioMedico.getNombreUsuario(), usuarioMedico.getContrasena());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				usuarioMedico = Fichero.busquedaMedico(us);
				try {
					abrirVentanaMedico1(usuarioMedico);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(ventanaControlada2.btn_Medico_AddPaciente)){
			Fichero fich = new Fichero();
			int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "¿Quiere añadirlo a su lista de pacientes?", "Añadir paciente", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				fich.updateAddPac(usuarioPaciente.getId_usuario());
				ventanaControlada2.frmMedico2.dispose();
				ventanaMedico.frmMedico.dispose();
				try {
					us = Fichero.comprobarUsuario(usuarioMedico.getNombreUsuario(), usuarioMedico.getContrasena());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				usuarioMedico = Fichero.busquedaMedico(us);
				try {
					abrirVentanaMedico1(usuarioMedico);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
	
	public static void abrirVentanaMedico1(Medico usuario) throws Exception{
		VentanaMedico vp = new VentanaMedico();	//crea nueva ventana
		ControladorMedico cp = new ControladorMedico(vp,usuario);	//crea nuevo controlador de ventana
		vp.addController(cp);	//asigna el controlador a la ventana creada
		vp.crearVentana(usuario);
	}
	
	public void abrirVentanaCalen(Medico usuarioMedico, Paciente usuarioPaciente) throws Exception{
		Vector<Sesion> ses = new Vector<>();
		ses = Fichero.sesionesPaciente(usuarioPaciente);
		if(ses.size() == 0){
			Component frame = null;
			JOptionPane.showMessageDialog((Component) frame , "Este paciente no ha subido ninguna sesión.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			System.out.println("ID usuario: " + usuarioPaciente.getId_usuario());
			ventanaControlada2.frmMedico2.dispose();
			VentanaCalendarioMedico cal = new VentanaCalendarioMedico();
			CalendarController cs = new CalendarController(cal,ses, usuarioMedico, usuarioPaciente);
			cal.addController(cs);
			cal.crearVentana(ses);
		}
	}
}
