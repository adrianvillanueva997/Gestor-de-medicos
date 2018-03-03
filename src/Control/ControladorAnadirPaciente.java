package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Vista.*;
import Modelo.*;

public class ControladorAnadirPaciente implements ActionListener{
	
	public VentanaAnadirPaciente ventanaControlada;
	 public VentanaMedico ventanaMedico;
	 boolean fotoComprobada = false;
	 public static Medico dniMedico = null;
	 public static Usuario us;
	 
	 JFrame frmDialogo;
	 File selectedFile = null;
	
	//Funci�n controladora de la ventana de M�dico
	public ControladorAnadirPaciente(VentanaAnadirPaciente win, VentanaMedico win2, Medico usuarioMed) throws IOException{
		ventanaControlada = win;
		ventanaMedico = win2;
		dniMedico=usuarioMed;
		us = new Usuario();
	}

	//Funci�n que indica las acciones que realizan los distintos objetos de la ventana
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(ventanaControlada.btn_AP_Cancelar)){ //analiaza la acci�n en la ventana
			   ventanaControlada.frmAnadirPaciente.dispose();//deja de hacerse visible la ventana cuando se pulsa el bot�n
			   ventanaMedico.frmMedico.setEnabled(true);
		}/* else if (e.getSource().equals(ventanaControlada.btn_AP_BuscarImagen)) {
			final JFileChooser fc = new JFileChooser();
			int result = fc.showOpenDialog(ventanaControlada.btn_AP_BuscarImagen);
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fc.getSelectedFile();
				if(Comprobar_Foto(selectedFile)==true){
					File archivoOrigen = new File(selectedFile.getPath());
			        fotoComprobada=true;
			        ventanaControlada.txt_AP_BuscarImagen.setText(archivoOrigen.getPath());
				}
			}
		} */else if(e.getSource().equals(ventanaControlada.btn_AP_Aceptar)){
			try {
				datosVentanaAPaciente();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	private String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
		         return "";
		}
	}
		 
	public boolean Comprobar_Foto(File selectedFile) {
		boolean comprobado = false;
		if (selectedFile.isDirectory()) {
		   comprobado = true;
		}
		String extension = getFileExtension(selectedFile);
		if(extension.equals("png")||extension.equals("jpg")||extension.equals("bmp")||extension.equals("jpeg")){
			comprobado = true;
		 }else{
			 JFrame frame = new JFrame();
			 JOptionPane.showMessageDialog((Component) frame, "Por favor, selecciona otra imagen.", "Error", JOptionPane.ERROR_MESSAGE);
		 }
			 return comprobado;
	}
	
	public void abrirVentanaMedico(Medico usuarioMedico) throws IOException{
		VentanaMedico vm = new VentanaMedico();	//crea nueva ventana
		ControladorMedico cm = new ControladorMedico(vm,usuarioMedico);	//crea nuevo controlador de ventana
		vm.addController(cm);	//asigna el controlador a la ventana creada
		try {
			vm.crearVentana(usuarioMedico);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void datosVentanaAPaciente () throws Exception{
		int tlf = 0, CP = 0;
		String dni = ventanaControlada.txt_AP_Dni.getText();
		String nombre = ventanaControlada.txt_AP_Nombre.getText();
		String apellidos = ventanaControlada.txt_AP_Apellidos.getText();
		String email = ventanaControlada.txt_AP_Email.getText();
		String altura = ventanaControlada.cb_AP_Altura.getSelectedItem().toString();
		String cent = ventanaControlada.cb_AP_Cent.getSelectedItem().toString();
		String sexo = ventanaControlada.cb_AP_Sexo.getSelectedItem().toString();
		String calle = ventanaControlada.txt_AP_Direccion.getText();
		String provincia = ventanaControlada.txt_AP_Provincia.getText();
		String localidad = ventanaControlada.txt_AP_Localidad.getText();
		String cp = ventanaControlada.txt_AP_CP.getText();
		String telefono = ventanaControlada.txt_AP_Telefono.getText();
		
		try{
			tlf = Integer.parseInt(telefono);
	    	CP = Integer.parseInt(cp);
		} catch(Exception e){
				JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono y el CP deben tener números no letras ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || calle.isEmpty() || cp.isEmpty() || localidad.isEmpty() || provincia.isEmpty() || telefono.isEmpty() || 
				email.isEmpty() || sexo.isEmpty()){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Faltan campos por rellenar.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!email.contains("@")){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Correo electronico no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(telefono.length()>9 || telefono.length()<9){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Numero de telefono no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cp.length()>5 || cp.length()<5){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Codigo Postal no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			int dialogo = JOptionPane.showConfirmDialog(frmDialogo, "¿Guardar Cambios?", "Guardar nuevo paciente", JOptionPane.YES_NO_OPTION);
			if (dialogo == JOptionPane.YES_OPTION){
				String altu = altura + cent;
				double alt = Double.parseDouble(altu);
				
				String añoNac = Integer.toString(ventanaControlada.dateChooser_AP_Nacimiento.getCalendar().get(java.util.Calendar.YEAR));
				int mesNac = ventanaControlada.dateChooser_AP_Nacimiento.getCalendar().get(java.util.Calendar.MONTH);
				int diaNac = ventanaControlada.dateChooser_AP_Nacimiento.getCalendar().get(java.util.Calendar.DATE);
				
				String diaDef = null;
				if (diaNac < 10){
			    	diaDef = "0" + Integer.toString(diaNac);
			    } else {
			    	diaDef = Integer.toString(diaNac);
			    }
				String mesDef = null;
				if(mesNac < 10){
					mesDef = "0" + Integer.toString(mesNac);
				} else {
					mesDef = Integer.toString(mesNac);
				}
				
			    String fechaN=diaDef+"-"+mesDef+"-"+añoNac;
				
				Date inicio = ventanaControlada.dateChooser_AP_Inicio.getDate();
				String fechaI = DateFormat.getDateInstance().format(inicio);
	
				Fichero nuevoPaciente = new Fichero();
				//TIPO 0: PACIENTE
				try {
					nuevoPaciente.guardarUsuario(dni, 0);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				nuevoPaciente.guardarPass(dni, dni);						
				nuevoPaciente.guardarPaciente(dni, nombre, apellidos, calle, CP, localidad, provincia, tlf, email,
						sexo, alt, fechaI, fechaN);
				nuevoPaciente.guardarMedicoPaciente(dni, dniMedico.getId_usuario());
	
				ventanaControlada.frmAnadirPaciente.dispose();
				ventanaMedico.frmMedico.dispose();
				us = Fichero.comprobarUsuario(dniMedico.getNombreUsuario(), dniMedico.getContrasena());
				dniMedico = Fichero.busquedaMedico(us);
				abrirVentanaMedico(dniMedico);

			}
		}
	}
}


