package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Vista.VentanaComentarioSesion;

public class ControladorComentarioSesiones implements ActionListener, KeyListener{

	public VentanaComentarioSesion ventanaControlada;
	
	//Funci�n controladora de la ventana de Médico
	public ControladorComentarioSesiones (VentanaComentarioSesion win){
		ventanaControlada = win;
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(ventanaControlada.btn_Comentario_Sesiones_Aceptar)){	//analiaza la acci�n en la ventana
			ventanaControlada.frmComentarioSesion.dispose();	//deja de hacerse visible la ventana cuando se pulsa el bot�n
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			ventanaControlada.frmComentarioSesion.dispose();	//deja de hacerse visible la ventana cuando se pulsa el bot�n
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
}
