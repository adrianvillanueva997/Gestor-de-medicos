package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;

public class CalendarControllerPaciente implements ActionListener{
		Vector<Sesion> ses;
		Paciente usuarioPaciente;
		VentanaCalendarioPaciente ventanaControlada;
		
	public CalendarControllerPaciente(VentanaCalendarioPaciente cal2,Vector<Sesion> ses,Paciente usuariopaciente){
		this.ventanaControlada=cal2;
		this.ses=ses;
		this.usuarioPaciente=usuariopaciente;
		
	}
	public void abrirVentanaPaciente(Paciente usuarioPaciente) throws IOException{
		/**
		 * Creaci�n de la ventana Paciente
		 */
		ventanaControlada.frmCalen.setVisible(false);	//Cierra la ventana de inicio
		VentanaPaciente vp = new VentanaPaciente();	//crea nueva ventana
		ControladorPaciente cp = new ControladorPaciente(vp, usuarioPaciente);	//crea nuevo controlador de ventana
		vp.addController(cp);	//asigna el controlador a la ventana creada
		vp.crearVentana(usuarioPaciente);	//crea los elementos de la ventana
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_Calendario_Atras)){
			try {
				try {
					abrirVentanaPaciente(usuarioPaciente);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}else if(e.getSource().equals(ventanaControlada.btn_Aceptar)){
			String año = Integer.toString(ventanaControlada.calen.getCalendar().get(java.util.Calendar.YEAR));
			   String mes = Integer.toString(ventanaControlada.calen.getCalendar().get(java.util.Calendar.MONTH) + 1);
			    int dia = ventanaControlada.calen.getCalendar().get(java.util.Calendar.DATE);
			    String diaDef = null;
			    if (dia < 10){
			    	diaDef = "0" + Integer.toString(dia);
			    } else {
			    	diaDef = Integer.toString(dia);
			    }
			    String fecha=diaDef+"-"+mesToInt(Integer.parseInt(mes))+"-"+año;
			    
			    try {
					abrirVentanaCalendario(usuarioPaciente,fecha);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	public String mesToInt(int mes){
		if(mes==1){
			return "ene";
		}else if(mes==2){
			return "feb";
		}else if(mes==3){
			return "mar";
		}else if(mes==4){
			return "abr";
		}else if(mes==5){
			return "may";
		}else if(mes==6){
			return "jun";
		}else if(mes==7){
			return "jul";
		}else if(mes==8){
			return "ago";
		}else if(mes==9){
			return "sep";
		}else if(mes==10){
			return "oct";
		}else if(mes==11){
			return "nov";
		}else if(mes==12){
			return "dic";
		}
		return null;
	}
		public void abrirVentanaCalendario(Paciente usuarioPaciente,String fecha) throws IOException{
		Vector<Sesion>se=Fichero.sesionesFecha(usuarioPaciente, fecha);
		if(se.size()==0){
			JOptionPane.showMessageDialog(null, "No hay sesion cargada el dia "+fecha,"ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			ventanaControlada.frmCalen.dispose();
			VentanaSesionesPaciente vs = new VentanaSesionesPaciente();
			ControladorSesionesPaciente cs = new ControladorSesionesPaciente(vs, usuarioPaciente, se);
			vs.addController(cs, usuarioPaciente,se);
			vs.crearVentana( usuarioPaciente);
		}
	}
}
