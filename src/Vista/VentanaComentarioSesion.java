package Vista;

import java.awt.*;
import java.io.*;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Control.ControladorComentarioSesiones;
import Modelo.*;

public class VentanaComentarioSesion {
	
	
	public JFrame frmComentarioSesion;
	public JLabel lbl_Comentario_Sesion_Peso;
	public JLabel lbl_Comentario_Sesion_Animo;
	public JLabel lbl_Comentario_Sesion_Comentario;
	public JButton btn_Comentario_Sesiones_Aceptar;
	public JLabel lbl_ComentarioSesion_Foto;
	public JLabel lbl_Comentario_Sesion_ComentarioLabel;
	public JLabel lbl_Comentario_Sesion_Imc;
	
	public Fichero fich = new Fichero();
	public List<String[]> datosCSV = null;
	
	
	ControladorComentarioSesiones controlador;
	public Sesion sesion;
	
	public void addController(ControladorComentarioSesiones mc, Sesion sesion){
		controlador=mc;
		this.sesion = sesion;
	}
	
	public void crearVentana(Paciente usuarioPaciente, Sesion sesion) throws IOException{

		String[] ses = fich.busquedaSesion(usuarioPaciente, sesion);
		
		frmComentarioSesion = new JFrame();
		frmComentarioSesion.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(550), AltoRelativo(498));
		frmComentarioSesion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmComentarioSesion.getContentPane().setLayout(null);
		frmComentarioSesion.addKeyListener(controlador);
		
		//LABEL PESO
		lbl_Comentario_Sesion_Peso = new JLabel("Peso: " +  ses[2]);
		lbl_Comentario_Sesion_Peso.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(20)));
		lbl_Comentario_Sesion_Peso.setBounds(AnchoRelativo(39), AltoRelativo(61), AnchoRelativo(209), AltoRelativo(25));
		frmComentarioSesion.getContentPane().add(lbl_Comentario_Sesion_Peso);
		
		//LABEL IMC
		lbl_Comentario_Sesion_Imc = new JLabel("IMC: " + ses[6]);
		lbl_Comentario_Sesion_Imc.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(20)));
		lbl_Comentario_Sesion_Imc.setBounds(AnchoRelativo(300), AltoRelativo(61), AnchoRelativo(209), AltoRelativo(25));
		frmComentarioSesion.getContentPane().add(lbl_Comentario_Sesion_Imc);
		
		//LABEL ANIMO
		lbl_Comentario_Sesion_Animo = new JLabel("Estado de animo:");
		lbl_Comentario_Sesion_Animo.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(20)));
		lbl_Comentario_Sesion_Animo.setBounds(AnchoRelativo(39), AltoRelativo(129), AnchoRelativo(189), AltoRelativo(25));
		frmComentarioSesion.getContentPane().add(lbl_Comentario_Sesion_Animo);
		
		//LABEL COMENTARIO
		lbl_Comentario_Sesion_Comentario = new JLabel("Comentario:");
		lbl_Comentario_Sesion_Comentario.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(20)));
		lbl_Comentario_Sesion_Comentario.setBounds(AnchoRelativo(39), AltoRelativo(188), AnchoRelativo(209), AltoRelativo(25));
		frmComentarioSesion.getContentPane().add(lbl_Comentario_Sesion_Comentario);
		
		//BOTON ACEPTAR
		btn_Comentario_Sesiones_Aceptar = new JButton("Aceptar");
		btn_Comentario_Sesiones_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Comentario_Sesiones_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_Comentario_Sesiones_Aceptar.setOpaque(true);
		btn_Comentario_Sesiones_Aceptar.setBorderPainted(false);
		btn_Comentario_Sesiones_Aceptar.setForeground(new Color(255, 255, 255));
		btn_Comentario_Sesiones_Aceptar.setBounds(AnchoRelativo(366), AltoRelativo(406), AnchoRelativo(140), AltoRelativo(32));
		frmComentarioSesion.getContentPane().add(btn_Comentario_Sesiones_Aceptar);
		btn_Comentario_Sesiones_Aceptar.addKeyListener(controlador);
		btn_Comentario_Sesiones_Aceptar.addActionListener(controlador);
		
		//LABEL FOTO DE ESTADO DE ANIMO
		String est = ses[4];
		int estado = Integer.parseInt(est);
		
		lbl_ComentarioSesion_Foto = new JLabel(":)");
		lbl_ComentarioSesion_Foto.setBounds(AnchoRelativo(290), AltoRelativo(100), AnchoRelativo(102), AltoRelativo(89));
		lbl_ComentarioSesion_Foto.setBackground(Color.WHITE);
		if (estado == 0) {
			
			Image logo_CaritaFeliz = new ImageIcon("."+File.separator+"img"+File.separator+"feliz.png").getImage();
			logo_CaritaFeliz = logo_CaritaFeliz.getScaledInstance(lbl_ComentarioSesion_Foto.getWidth(), lbl_ComentarioSesion_Foto.getHeight(), java.awt.Image.SCALE_SMOOTH);
			lbl_ComentarioSesion_Foto.setIcon(new ImageIcon(logo_CaritaFeliz));
		} else if (estado == 1) {
			Image logo_CaritaTriste = new ImageIcon("."+File.separator+"img"+"triste.png").getImage();
			logo_CaritaTriste = logo_CaritaTriste.getScaledInstance(lbl_ComentarioSesion_Foto.getWidth(), lbl_ComentarioSesion_Foto.getHeight(), java.awt.Image.SCALE_SMOOTH);
			lbl_ComentarioSesion_Foto.setIcon(new ImageIcon(logo_CaritaTriste));
		} else if (estado == 2) {
			Image logo_CaritaNeutral = new ImageIcon("."+File.separator+"img"+"neutral.png").getImage();
			logo_CaritaNeutral = logo_CaritaNeutral.getScaledInstance(lbl_ComentarioSesion_Foto.getWidth(), lbl_ComentarioSesion_Foto.getHeight(), java.awt.Image.SCALE_SMOOTH);
			lbl_ComentarioSesion_Foto.setIcon(new ImageIcon(logo_CaritaNeutral));
		}
		frmComentarioSesion.getContentPane().add(lbl_ComentarioSesion_Foto);
		
		//LABEL COMENTARIO DE LA SESION
		lbl_Comentario_Sesion_ComentarioLabel = new JLabel(ses[5]);
	    lbl_Comentario_Sesion_ComentarioLabel.setVerticalAlignment(SwingConstants.TOP);
	    lbl_Comentario_Sesion_ComentarioLabel.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
	    lbl_Comentario_Sesion_ComentarioLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    lbl_Comentario_Sesion_ComentarioLabel.setBorder(new LineBorder(new Color(139, 0, 0), 3));
	    lbl_Comentario_Sesion_ComentarioLabel.setBounds(AnchoRelativo(39), AltoRelativo(226), AnchoRelativo(467), AltoRelativo(151));
	    frmComentarioSesion.getContentPane().add(lbl_Comentario_Sesion_ComentarioLabel);
	    
	    frmComentarioSesion.setIconImage(Toolkit.getDefaultToolkit().getImage("."+ File.separator +"img" + File.separator+"apus_logo.jpg")); 
	    
		frmComentarioSesion.setVisible(true);
	
		}
	  public int AltoRelativo (int altura) {
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
