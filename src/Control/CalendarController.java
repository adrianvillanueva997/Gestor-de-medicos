package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import Modelo.Fichero;
import Modelo.Medico;
import Modelo.Paciente;
import Modelo.Sesion;
import Vista.VentanaCalendarioMedico;
import Vista.VentanaMedico2;
import Vista.VentanaSesionesMedico;
import Vista.VentanaSesionesPaciente;

public class CalendarController implements ActionListener{
		 Medico usuarioMedico;
		 Vector<Sesion> ses;
		 Paciente usuarioPaciente;
		 VentanaCalendarioMedico ventanaControlada;
 
 	public CalendarController(VentanaCalendarioMedico cal2,Vector<Sesion> ses,Medico usuariomedico,Paciente usuariopaciente){
		 this.ventanaControlada=cal2;
		 this.ses=ses;
		 this.usuarioPaciente=usuariopaciente;
		 this.usuarioMedico=usuariomedico;
	}
 	public void abrirVentanaMedico2(Medico usuarioMedico, Paciente usuarioPaciente) throws IOException{
	    ventanaControlada.frmCalen.dispose();//Cierra la ventana de inicio
	    VentanaMedico2 vp = new VentanaMedico2();  //crea nueva ventana
	    ControladorMedico2 cp = new ControladorMedico2(vp,usuarioMedico, usuarioPaciente);  //crea nuevo controlador de ventana
	    vp.addController(cp);  //asigna el controlador a la ventana creada
	    vp.crearVentana(usuarioMedico, usuarioPaciente);  //crea los elementos de la ventana
 	}

	public void actionPerformed(ActionEvent e) {
	 if(e.getSource().equals(ventanaControlada.btn_Calendario_Atras)){
	  try {
	   try {
	    abrirVentanaMedico2(usuarioMedico,usuarioPaciente);
	   } catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	   }
	  } catch (Exception e1) {
	   // TODO Auto-generated catch block
	   e1.printStackTrace();
	  }
	 
	 }else if(e.getSource().equals(ventanaControlada.btn_Aceptar)){
	  String ano = Integer.toString(ventanaControlada.calen.getCalendar().get(java.util.Calendar.YEAR));
	     String mes = Integer.toString(ventanaControlada.calen.getCalendar().get(java.util.Calendar.MONTH) + 1);
	      int dia = ventanaControlada.calen.getCalendar().get(java.util.Calendar.DATE);
	      String diaDef = null;
	      if (dia < 10){
	       diaDef = "0" + Integer.toString(dia);
	      } else {
	       diaDef = Integer.toString(dia);
	      }
	      String fecha=diaDef+"-"+mesToInt(Integer.parseInt(mes))+"-"+ano;
	      System.out.println(fecha);
	      
	      try {
	    abrirVentanaSesionesFecha(usuarioMedico,usuarioPaciente,fecha);
	   } catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	   }
	      }else if(e.getSource().equals(ventanaControlada.btn_Sesionestotales)){
	   try {
	    abrirVentanaSesiones(usuarioMedico,usuarioPaciente);
	   } catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	   }
	   
	 }
	 
	}
	
	public String mesToInt(int mes){
	            switch (mes) {
	                case 1:
	                    return "ene";
	                case 2:
	                    return "feb";
	                case 3:
	                    return "mar";
	                case 4:
	                    return "abr";
	                case 5:
	                    return "may";
	                case 6:
	                    return "jun";
	                case 7:
	                    return "jul";
	                case 8:
	                    return "ago";
	                case 9:
	                    return "sep";
	                case 10:
	                    return "oct";
	                case 11:
	                    return "nov";
	                case 12:
	                    return "dic";
	                default:
	                    break;
	            }
	 return null;
	}
	public void abrirVentanaSesionesFecha(Medico usuarioMedico, Paciente usuarioPaciente,String fecha) throws Exception{
	 
		 Vector<Sesion>se=Fichero.sesionesFecha(usuarioPaciente, fecha);
		 if(se.size()==0){
			 JOptionPane.showMessageDialog(null, "No hay sesion cargada el dia "+fecha,"ERROR",JOptionPane.ERROR_MESSAGE);
		 }else{
			  ventanaControlada.frmCalen.dispose();
			  VentanaSesionesMedico vs = new VentanaSesionesMedico();
			 ControladorSesionesMedico cs = new ControladorSesionesMedico(vs, usuarioMedico, usuarioPaciente,se);
			 vs.addController(cs, usuarioPaciente,se);
			 vs.crearVentana(usuarioMedico, usuarioPaciente);
		 }
	 
	}
	public void abrirVentanaSesiones(Medico usuarioMedico,Paciente usuarioPaciente) throws IOException{
		 Vector<Sesion>se=Fichero.sesionesPaciente(usuarioPaciente);
		 if(se.size()==0){
			 JOptionPane.showMessageDialog(null, "No hay sesion cargada ","ERROR",JOptionPane.ERROR_MESSAGE);
		 }else{
			  ventanaControlada.frmCalen.dispose();
			  VentanaSesionesMedico vs = new VentanaSesionesMedico();
			 ControladorSesionesMedico cs = new ControladorSesionesMedico(vs, usuarioMedico, usuarioPaciente,se);
			 vs.addController(cs, usuarioPaciente,se);
			 vs.crearVentana(usuarioMedico, usuarioPaciente);
		 }
	}
}

