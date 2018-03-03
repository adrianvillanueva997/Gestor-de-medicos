package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;


public class ControladorModPac implements ActionListener{

	public VentanaModPac ventanaControlada;
	 public VentanaPaciente ventanaPaciente;
	 boolean fotoComprobada = false;
	 public String dniMedico = null;
	 public Paciente paciente;
	 Usuario us;
	 
	 
	 JFrame frmDialogo;
	 File selectedFile = null;
	
	//Funciï¿½n controladora de la ventana de Mï¿½dico
	public ControladorModPac(VentanaModPac win, VentanaPaciente win2, Paciente paciente){
		ventanaControlada = win;
		ventanaPaciente = win2;
		this.paciente = paciente;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(ventanaControlada.btn_MP_Cancelar)){ //analiaza la acciï¿½n en la ventana
			   ventanaControlada.frmModPac.dispose();//deja de hacerse visible la ventana cuando se pulsa el botï¿½n
			   ventanaPaciente.frmPaciente.setEnabled(true);
		} else if(e.getSource().equals(ventanaControlada.btn_MP_Aceptar)){
			try {
				datosVentanaMPaciente();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void abrirVentanaPaciente(Paciente usuarioPaciente) throws Exception{
		VentanaPaciente vp = new VentanaPaciente();
		ControladorPaciente cp = new ControladorPaciente(vp, usuarioPaciente);
		vp.addController(cp);
		vp.crearVentana(usuarioPaciente);
	}
	
	public void datosVentanaMPaciente () throws Exception{
		String dni = ventanaControlada.txt_MP_Dni.getText();
		String nombre = ventanaControlada.txt_MP_Nombre.getText();
		String apellidos = ventanaControlada.txt_MP_Apellidos.getText();
		String email = ventanaControlada.txt_MP_Email.getText();
		String sexo = ventanaControlada.cb_AP_Sexo.getActionCommand();
		String altura = ventanaControlada.cb_AP_Altura.getSelectedItem().toString();
		String cent = ventanaControlada.cb_AP_Cent.getSelectedItem().toString();
		String calle = ventanaControlada.txt_MP_Direccion.getText();
		String provincia = ventanaControlada.txt_MP_Provincia.getText();
		String localidad = ventanaControlada.txt_MP_Localidad.getText();
		String cp = ventanaControlada.txt_MP_CP.getText();
		String telefono = ventanaControlada.txt_MP_Telefono.getText();
		//String foto = ventanaControlada.txt_MP_BuscarImagen.getText();
 		int CP = 0;
		
		try{
	    	CP = Integer.parseInt(cp);
		} catch(Exception e){
				JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono y el CP deben tener números no letras ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || calle.isEmpty() || cp.isEmpty() || localidad.isEmpty() || provincia.isEmpty() || telefono.isEmpty() || 
				email.isEmpty() || sexo.isEmpty()){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Faltan campos por rellenar.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!email.contains("@")){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Correo electronico no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cp.length()>5 || cp.length()<5){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Codigo postal no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(telefono.length()>9 || telefono.length()<9){
			JOptionPane.showMessageDialog((Component) frmDialogo, "Telefono no valido.", "Error", JOptionPane.ERROR_MESSAGE);
		} else{
			int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "¿Guardar Cambios?", "Modificar datos", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION){
				Fichero nuevoPaciente = new Fichero();
				String altu = altura + cent;
				double alt = Double.parseDouble(altu);
				//TIPO 0: PACIENTE
				nuevoPaciente.updatePac( paciente.getId_usuario(), dni, nombre, apellidos, calle, cp, localidad, provincia, telefono, email, sexo, altura);
	
				ventanaControlada.frmModPac.dispose();
				ventanaPaciente.frmPaciente.dispose();
				us = Fichero.comprobarUsuario(paciente.getNombreUsuario(), paciente.getContrasena());
				paciente = Fichero.busquedaPaciente(us);
				abrirVentanaPaciente(paciente);
			}
		}
	}
}
