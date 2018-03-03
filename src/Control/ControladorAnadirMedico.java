package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;

public class ControladorAnadirMedico implements ActionListener{
	
	VentanaAnadirMedico ventanaControlada;
	public VentanaAdmin ventanaAdmin;
	JFrame frm = null;
	public Administrador usuario = null;
	public static Usuario us;

	
	public ControladorAnadirMedico (VentanaAnadirMedico win, Administrador usuario){
		ventanaControlada = win;
		this.usuario = usuario;
		us = new Usuario();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_AM_Aceptar)){
			try {
				datosMedico();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		} else if (e.getSource().equals(ventanaControlada.btn_AM_Cancelar)){
			ventanaControlada.frmAMed.dispose();
		}
		
	}
	
	public void abrirVentanaAdmin(Administrador usuario) throws Exception{
		VentanaAdmin va = new VentanaAdmin();
		ControladorAdmin cm = new ControladorAdmin(va, usuario);
		va.addController(cm);
		cm.refrescarDatos();
		va.crearVentana(usuario);
	}
	
	public void datosMedico() throws Exception{
		Fichero fich = new Fichero();
		
		String dni = ventanaControlada.txt_AM_Dni.getText();
		String nombre = ventanaControlada.txt_AM_Nombre.getText();
		String apellidos = ventanaControlada.txt_AM_Apellidos.getText();
		String clinica = ventanaControlada.txt_AM_Clinica.getText();
		String email = ventanaControlada.txt_AM_Email.getText();
		
		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || clinica.isEmpty() || email.isEmpty()){
			JOptionPane.showMessageDialog(frm, "Faltan campos por rellenar.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!email.contains("@")){
			JOptionPane.showMessageDialog(frm, "El email no tiene el formato correcto.", "Error", JOptionPane.ERROR_MESSAGE);
		}	else {
			int dialogo = JOptionPane.showConfirmDialog(frm, "¿Guardar cambios?", "Guardar cambios", JOptionPane.YES_NO_OPTION);
			if(dialogo == JOptionPane.YES_OPTION){
				try {
					fich.guardarUsuario(dni, 1);
					fich.guardarPass(dni, dni);
					fich.guardarMedico(dni, nombre, apellidos, email, clinica);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ventanaControlada.frmAMed.dispose();
				ventanaAdmin.frmAdmin.dispose();
				us = Fichero.comprobarUsuario(usuario.getNombreUsuario(), usuario.getContrasena());
				usuario = Fichero.busquedaAdministrador(us);
				abrirVentanaAdmin(usuario);
			}
		}
		
	}
	
}
