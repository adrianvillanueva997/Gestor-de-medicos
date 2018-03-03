package Control;
/**
 * @author Sof�a Alejandra L�pez Fern�ndez
 * @version Entrega GUI de inicio - 12/02/2017
 */

import java.awt.Component;
import java.awt.Desktop;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.*;
import Vista.*;


public class ControladorIndex implements ActionListener, KeyListener {	
	/**
	 * Funciones de la VentanaIndex
	 * @see VentanaIndex.java, VentanaMedico.java, ControladorMedico.java, VentanaPaciente.java,  ControladorPaciente.java, VentanaAbout.java,  ControladorAbout.java.
	 */
	
	VentanaIndex ventanaControlada;	
	public JFrame frmDialogo = null;

	
	//Funci�n controladora de la ventana de inicio
	public ControladorIndex(VentanaIndex win){
		ventanaControlada=win;
	}

	//Funci�n que indica las acciones que realizan los distintos objetos de la ventana
	public void actionPerformed(ActionEvent e) {
		/**
		 * Acciones que se llevar�n a cabo en la ventana.
		 */
		if(e.getSource().equals(ventanaControlada.btn_Aceptar)){	//analiza la acci�n que se hace en la ventana y la iguala al btn_Aceptar
			aceptarVentana();
		} else if(e.getSource().equals(ventanaControlada.btn_About)){	//analiza la acci�n que se hace en la ventana y la iguala al btn_About
			abrirVentanaAbout();	//abre la ventana de About
		} else if(e.getSource().equals(ventanaControlada.btn_Recup)){
			//JOptionPane.showMessageDialog(frmDialogo, "Se le ha enviado un correo con una contraseña de recuperación");
			//mailto();	//pendiente de email de adrian
		}
	}
		
	public void abrirVentanaPaciente(Paciente usuarioPaciente) throws Exception{
		/**
		 * Creaci�n de la ventana Paciente
		 */
		ventanaControlada.frmIndex.setVisible(false);	//Cierra la ventana de inicio
		VentanaPaciente vp = new VentanaPaciente();	//crea nueva ventana
		ControladorPaciente cp = new ControladorPaciente(vp, usuarioPaciente);	//crea nuevo controlador de ventana
		vp.addController(cp);	//asigna el controlador a la ventana creada
		vp.crearVentana(usuarioPaciente);	//crea los elementos de la ventana
	}
	
	public void abrirVentanaMedico(Medico usuario) throws Exception{
		/**
		 * Creaci�n de la ventana M�dico
		 */
		ventanaControlada.frmIndex.setVisible(false);	//Cierra la ventana de inicio
		VentanaMedico vm = new VentanaMedico();	//crea nueva ventana
		ControladorMedico cm = new ControladorMedico(vm,usuario);	//crea nuevo controlador de ventana
		vm.addController(cm);	//asigna el controlador a la ventana creada
		vm.crearVentana(usuario);	//crea los elementos de la ventana
	}
	
	public void abrirVentanaAbout(){
		/**
		 * Creaci�n de la ventana About
		 */
		VentanaAbout va = new VentanaAbout();	//crea nueva ventana
		ControladorAbout ca = new ControladorAbout(va);	//crea nuevo controlador de ventana
		va.addController(ca);	//asigna el controlador a la ventana creada
		va.crearVentana();	//crea los elementos de la ventana
	}
	
	public void abrirVentanaAdmin(Administrador usuario) throws Exception{
		ventanaControlada.frmIndex.setVisible(false);
		VentanaAdmin va = new VentanaAdmin();
		ControladorAdmin cm = new ControladorAdmin(va, usuario);
		va.addController(cm);
		va.crearVentana(usuario);
	}
	
	public void abrirVentanaCambioPass(Usuario usuario){
		
		VentanaCambioPass vc = new VentanaCambioPass();
		ControladorCambioPass cc = new ControladorCambioPass(vc, usuario);
		vc.addController(cc);
		vc.CrearVentana(usuario);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			aceptarVentana();
		} else if (e.getKeyCode() == KeyEvent.VK_F1) {
			abrirVentanaAbout();	
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void aceptarVentana () {
		String usuario = ventanaControlada.txt_usuario.getText();	//recoge el contenido del JTextField
		char caracteres[] = ventanaControlada.txt_password.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
		
		String password = String.valueOf(caracteres);	//Convierte los elementos del array en un String
		Fichero comprobar = new Fichero();	//crea un nuevo gestor de ficheros
		
		try {
			Usuario user = Fichero.comprobarUsuario(usuario, password);//comprueba los datos introducidos
			if(user == null){				
				Object frame = null;	//crea un objeto ventana
				JOptionPane.showMessageDialog((Component) frame, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
			}	else if(user.getActivo() == 1){
					char[] pass = password.toCharArray();
					switch(user.getTipoUsuario()){
						case 0:
							Paciente pc = comprobar.busquedaPaciente(user);
							//Abre ventana Paciente
							if(usuario.equals(password)) {
								abrirVentanaCambioPass(user);
								abrirVentanaPaciente(pc);
							} else if(pass.length == 64){
								abrirVentanaCambioPass(user);
								abrirVentanaPaciente(pc);
							} else {
								abrirVentanaPaciente(pc);
							}
							break;
						case 1:
							//Abre ventana M�dico
							Medico med = comprobar.busquedaMedico(user);
							if(usuario.equals(password)){
								abrirVentanaCambioPass(user);
								abrirVentanaMedico(med);
							} else if(pass.length == 64){
								abrirVentanaCambioPass(user);
								abrirVentanaMedico(med);
							} else {
								abrirVentanaMedico(med);
							}
							break;
						case 2:
							Administrador admin = comprobar.busquedaAdministrador(user);

							if(usuario.equals(password)){
								abrirVentanaCambioPass(user);
								abrirVentanaAdmin(admin);
							} else if(pass.length == 64){
								abrirVentanaCambioPass(user);
								abrirVentanaAdmin(admin);
							} else {
								abrirVentanaAdmin(admin);
							}
							break;
						default:
							//En el caso de que no sea ninguno de los dos usuarios.
							Object frame = null;	//crea un objeto ventana
							JOptionPane.showMessageDialog((Component) frame, "Tipo de usuario desconocido.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error
					}
				} else if(user.getActivo()==0){
					JOptionPane.showMessageDialog((Component) frmDialogo, "Su usuario no está activado.", "Error", JOptionPane.ERROR_MESSAGE);
				}
		} catch (Exception e1) {
			e1.printStackTrace();	//imprime el registro de la pila donde se dio la excepci�n
		}
	}
	
	public void mailto(){
	      Desktop desktop;
	      if (Desktop.isDesktopSupported() 
	          && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
	        URI mailto = null;
	      try {
	        mailto = new URI("mailto:contactoapus@gmail.com?subject=Contacto%20Apus");
	        desktop.mail(mailto);
	      } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	      }else {
	        // TODO fallback to some Runtime.exec(..) voodoo?
	        throw new RuntimeException("");
	      }
	}
	
	public static void main(String[]args){	//crea la ventana de inicio
		/**
		 * Creaci�n de la ventana de inicio y llamada a main del proyecto
		 */
		VentanaIndex mainframe=new VentanaIndex();
                VentanaSecundaria vs =  new VentanaSecundaria();
		ControladorIndex mc=new ControladorIndex(mainframe);
		mainframe.addController(mc);
                vs.crearVentana();
		mainframe.crearVentana();
	}


}