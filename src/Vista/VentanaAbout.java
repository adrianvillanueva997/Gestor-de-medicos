package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Control.ControladorAbout;

public class VentanaAbout extends JFrame{
	public JFrame frmAbout;
	ControladorAbout controlador;
	public JLabel lbl_About_Logo;
	public JLabel lbl_About;
	public JButton btn_About_Aceptar;
	public JButton btn_About_mailto;
	public JLabel lbl_Licencia;
	
	public void addController(ControladorAbout mc){
		controlador=mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(){
		
		frmAbout = new JFrame();
		frmAbout.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img" + File.separator + "apus_logo.jpg"));
		frmAbout.getContentPane().setBackground(Color.WHITE);	//Se establece como color de la ventana el blanco
		frmAbout.setTitle("About");	//Titulo de la ventana
		frmAbout.setBounds(100, 100, 450, 731);	//Se establece el tama�o
		frmAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Se establece su funcion por defecto al cerrar con la "X"
		frmAbout.getContentPane().setLayout(null);	//Obtiene el del contenido del JFrame y no establece ning�n tipo de Dise�o(Layout)
		frmAbout.addKeyListener(controlador);
		frmAbout.setFocusable(true);
		
		//Crea la etiqueta
		lbl_About = new JLabel("<html>Programa realizado por:<br>Sofia Alejandra Lopez Fernandez<br>Alejandro Abad Martinez<br>Adrian Villanueva Martinez<br>Adrian Gallego Sanchez</html>");
		//Se introduce el texto que va a salir por pantalla
		lbl_About.setBounds(39, 320, 381, 224);	//Se establece el tama�o
		lbl_About.setFont(new Font("Tahoma", Font.PLAIN, 20));	//Establece el tama�o y t  ipo de letra
		frmAbout.getContentPane().add(lbl_About);	//Se a�ade el elemento al JFrame
		
		//Crea la etiqueta para la imagen
		lbl_About_Logo = new JLabel("");
		ImageIcon logo = new ImageIcon(("."+ File.separator + "img" + File.separator + "apus_logo.jpg"));	//Crea el objeto imagen y lo vincula a la imagen apus_logo.jpg guardada en la carpeta img del proyecto
		lbl_About_Logo.setIcon(logo); 
		lbl_About_Logo.setBounds(130, 200, 183, 152);	//Establece el tama�o de la ventana
		frmAbout.getContentPane().add(lbl_About_Logo);	//Se a�ade el elemento al JFrame
		
		//Crea el boton
		btn_About_Aceptar = new JButton("Aceptar");	//Indica qu� est� escrito
		btn_About_Aceptar.setFont(new Font("Tahoma", Font.BOLD, 21));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_About_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_About_Aceptar.setOpaque(true);
		btn_About_Aceptar.setBorderPainted(false);	//Establece el color del bot�n
		btn_About_Aceptar.setForeground(Color.WHITE);	//Establece el color de la fuente
		btn_About_Aceptar.setBounds(280, 632, 140, 39);	//Establece el tama�o del bot�n
		frmAbout.getContentPane().add(btn_About_Aceptar);	//Se a�ade el elemento al JFrame
		btn_About_Aceptar.addActionListener(controlador);
		
		//label licencia
		lbl_Licencia = new JLabel("");
		lbl_Licencia.setBounds(AnchoRelativo(12), AltoRelativo(13), AnchoRelativo(408), AltoRelativo(152));
		Image licencia = new ImageIcon(("."+File.separator+"img"+File.separator+"licencia.png")).getImage();
		licencia = licencia.getScaledInstance(lbl_Licencia.getWidth(), lbl_Licencia.getHeight(), Image.SCALE_SMOOTH);
		lbl_Licencia.setIcon(new ImageIcon(licencia)); 
  		frmAbout.getContentPane().add(lbl_Licencia);
  		
  		
	    btn_About_mailto = new JButton("");
	    btn_About_mailto.setBounds(AnchoRelativo(12), AltoRelativo(543), AnchoRelativo(128), AltoRelativo(128));  //Establece el tama�o de la ventana
	    Image mail = new ImageIcon(("."+File.separator+"img"+File.separator+"correo.png")).getImage();
	    mail = mail.getScaledInstance(btn_About_mailto.getWidth(), btn_About_mailto.getHeight(), Image.SCALE_SMOOTH);
  		btn_About_mailto.setIcon(new ImageIcon(mail));
	    btn_About_mailto.setBackground(Color.WHITE);
	    btn_About_mailto.setOpaque(true);
	    btn_About_mailto.addActionListener(controlador);
	    btn_About_mailto.setBorderPainted(false);
	    frmAbout.getContentPane().add(btn_About_mailto);  //Se a�ade el elemento al JFrame
		
		frmAbout.setVisible(true);	//Se hace visible la ventana
	}
	
	public int AltoRelativo(int altura) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AltoRelat = (screen.height*altura)/1080;
        return AltoRelat;
    }
    
    public int AnchoRelativo(int ancho) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AnchoRelat = (screen.width*ancho)/1920;
        return AnchoRelat;
    }
    
    public int fuenteRelativa(int fuenteActual){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int fuenteBuena = (screen.width*fuenteActual)/1920;
        return fuenteBuena;
    }
    
}
