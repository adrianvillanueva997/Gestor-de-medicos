package Control;
import java.awt.Desktop;
/**
 * @author Sofía Alejandra López Fern�ndez
 * @version Entrega GUI de inicio - 12/02/2017
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;

import Vista.VentanaAbout;

public class ControladorAbout implements ActionListener, KeyListener {
	
		public VentanaAbout ventanaControlada;
		
		//Funci�n controladora de la ventana de About
		public ControladorAbout(VentanaAbout win){
			ventanaControlada = win;
		}

		//Funci�n que indica las acciones que realizan los distintos objetos de la ventana
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(ventanaControlada.btn_About_Aceptar)){	//analiaza la acci�n en la ventana
				ventanaControlada.frmAbout.setVisible(false);	//deja de hacerse visible la ventana cuando se pulsa el bot�n
			} else if(e.getSource().equals(ventanaControlada.btn_About_mailto)){
		        mailto();
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

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				ventanaControlada.frmAbout.setVisible(false);	//deja de hacerse visible la ventana cuando se pulsa el bot�n
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
