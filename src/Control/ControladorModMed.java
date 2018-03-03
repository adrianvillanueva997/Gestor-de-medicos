package Control;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

import Modelo.*;
import Vista.*;


public class ControladorModMed implements ActionListener{
	VentanaModMed ventanaControlada;
	public VentanaMedico ventanaMedico;
	JFrame frm = null;
	public Medico medico;
	public Usuario us;

	
	public ControladorModMed (VentanaModMed win, Medico medico, VentanaMedico win2){
		ventanaControlada = win;
		this.medico = medico;
		this.ventanaMedico = win2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_MM_Aceptar)){
			try {
				datosMedico();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		} else if (e.getSource().equals(ventanaControlada.btn_MM_Cancelar)){
			ventanaControlada.frmAMed.dispose();
			ventanaMedico.frmMedico.setEnabled(true);
		}
		
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
	
	public void datosMedico() throws Exception{
		Fichero fich = new Fichero();
		
		String dni = ventanaControlada.txt_MM_Dni.getText();
		String nombre = ventanaControlada.txt_MM_Nombre.getText();
		String apellidos = ventanaControlada.txt_MM_Apellidos.getText();
		String clinica = ventanaControlada.txt_MM_Clinica.getText();
		String email = ventanaControlada.txt_MM_Email.getText();
		
		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || clinica.isEmpty() || email.isEmpty()){
			JOptionPane.showMessageDialog(frm, "Faltan campos por rellenar", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (!email.contains("@")){
			JOptionPane.showMessageDialog(frm, "El email no tiene el formato esperado.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showConfirmDialog(frm, "¿Guardar cambios?", "Guardar cambios", JOptionPane.YES_NO_OPTION);
			try {
				fich.updateMed(medico.getId_usuario(), nombre, apellidos, clinica, email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ventanaControlada.frmAMed.dispose();
			ventanaMedico.frmMedico.dispose();
			us = Fichero.comprobarUsuario(medico.getNombreUsuario(), medico.getContrasena());
			medico = Fichero.busquedaMedico(us);
			abrirVentanaMedico(medico);

		}
		
	}
	
}
