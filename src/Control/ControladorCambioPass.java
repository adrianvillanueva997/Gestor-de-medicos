package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;

public class ControladorCambioPass implements ActionListener{
	VentanaCambioPass ventanaControlada;
	public Usuario user;
	public String usuario;
	public String password;
	
	public ControladorCambioPass(VentanaCambioPass win, Usuario user){
		ventanaControlada = win;
		this.user = user;
		this.usuario = user.getNombreUsuario();
		this.password = user.getContrasena();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ventanaControlada.btn_CP_Cancelar)){
			if(usuario.equals(password)){
				Object frame = null;	//crea un objeto ventana
				JOptionPane.showMessageDialog((Component) frame, "Por motivos de seguridad, debe cambiar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				ventanaControlada.frmCambioPass.dispose();
			}
		} if (e.getSource().equals(ventanaControlada.btn_CP_Aceptar)){
			try {
				cambioPass();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void cambioPass () throws Exception{
		char caracteres1[] = ventanaControlada.ptxt_CP_Password.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
		String password = String.valueOf(caracteres1);
		char caracteres2[] = ventanaControlada.ptxt_CP_VPassword.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
		String password2 = String.valueOf(caracteres2);
		if(password.equals(password2)){
			Fichero fich = new Fichero();
			
			int respuesta = JOptionPane.showConfirmDialog(ventanaControlada, "¿Guardar Cambios?", "Cambiar Contraseña", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				fich.updatePassword(user.getId_usuario(), password);
				ventanaControlada.frmCambioPass.dispose();	
			}
			
		} else {
			Object frame = null;	//crea un objeto ventana
			JOptionPane.showMessageDialog((Component) frame, "Los campos no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void abrirVentanaPaciente(Paciente usuarioPaciente) throws Exception{
		/**
		 * Creaciï¿½n de la ventana Paciente
		 */
		ventanaControlada.frmCambioPass.setVisible(false);	//Cierra la ventana de inicio
		VentanaPaciente vp = new VentanaPaciente();	//crea nueva ventana
		ControladorPaciente cp = new ControladorPaciente(vp, usuarioPaciente);	//crea nuevo controlador de ventana
		vp.addController(cp);	//asigna el controlador a la ventana creada
		vp.crearVentana(usuarioPaciente);	//crea los elementos de la ventana
	}
	
	public void abrirVentanaMedico(Medico usuario) throws Exception{
		/**
		 * Creaciï¿½n de la ventana Mï¿½dico
		 */
		ventanaControlada.frmCambioPass.setVisible(false);	//Cierra la ventana de inicio
		VentanaMedico vm = new VentanaMedico();	//crea nueva ventana
		ControladorMedico cm = new ControladorMedico(vm,usuario);	//crea nuevo controlador de ventana
		vm.addController(cm);	//asigna el controlador a la ventana creada
		vm.crearVentana(usuario);	//crea los elementos de la ventana
	}
	
	public void abrirVentanaAdmin(Administrador usuario) throws Exception{
		ventanaControlada.frmCambioPass.setVisible(false);
		VentanaAdmin va = new VentanaAdmin();
		ControladorAdmin cm = new ControladorAdmin(va, usuario);
		va.addController(cm);
		va.crearVentana(usuario);
	}
}
