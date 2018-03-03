package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import Control.*;
import Modelo.*;

public class VentanaCambioPass extends JFrame{
	ControladorCambioPass controlador;
	public JFrame frmCambioPass;
	public JLabel lbl_CP_Password;
	public JButton btn_CP_Aceptar;
	public JButton btn_CP_Cancelar;
	public JLabel lbl_CP_Usuario;
	public JLabel lbl_CP_UsuarioDNI;
	public JLabel lbl_CP_VPassword;
	public JPasswordField ptxt_CP_Password;
	public JPasswordField ptxt_CP_VPassword;
	
	public void addController(ControladorCambioPass mc){
		controlador = mc;
	}
	
	public void CrearVentana(Usuario usuario){
		frmCambioPass = new JFrame();
		frmCambioPass.setTitle("Cambiar Contraseña");
		frmCambioPass.getContentPane().setBackground(Color.WHITE);
		frmCambioPass.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"apus_logo.jpg")));
		frmCambioPass.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(424), AltoRelativo(278));
		frmCambioPass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCambioPass.getContentPane().setLayout(null);
		
		btn_CP_Aceptar = new JButton("Aceptar");
		btn_CP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_CP_Aceptar.setForeground(new Color(255, 255, 255));
		btn_CP_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_CP_Aceptar.setOpaque(true);
		btn_CP_Aceptar.setBorderPainted(false);
		btn_CP_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_CP_Aceptar.setBounds(AnchoRelativo(271), AltoRelativo(183), AnchoRelativo(119), AltoRelativo(31));
		frmCambioPass.getContentPane().add(btn_CP_Aceptar);
		btn_CP_Aceptar.addActionListener(controlador);
		
		btn_CP_Cancelar = new JButton("Cancelar");
		btn_CP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_CP_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_CP_Cancelar.setOpaque(true);
		btn_CP_Cancelar.setBorderPainted(false);
		btn_CP_Cancelar.setForeground(new Color(255, 255, 255));
		btn_CP_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_CP_Cancelar.setBounds(AnchoRelativo(108), AltoRelativo(183), AnchoRelativo(126), AltoRelativo(31));
		frmCambioPass.getContentPane().add(btn_CP_Cancelar);
		btn_CP_Cancelar.addActionListener(controlador);
		
		lbl_CP_Usuario = new JLabel("Usuario:");
		lbl_CP_Usuario.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_CP_Usuario.setBounds(AnchoRelativo(12), AltoRelativo(13), AnchoRelativo(85), AltoRelativo(23));
		frmCambioPass.getContentPane().add(lbl_CP_Usuario);
		
		lbl_CP_UsuarioDNI = new JLabel(usuario.getNombreUsuario());
		lbl_CP_UsuarioDNI.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_CP_UsuarioDNI.setToolTipText("");
		lbl_CP_UsuarioDNI.setBounds(AnchoRelativo(217), AltoRelativo(13), AnchoRelativo(146), AltoRelativo(23));
		frmCambioPass.getContentPane().add(lbl_CP_UsuarioDNI);
		
		lbl_CP_VPassword = new JLabel("Verificar contraseña:");
		lbl_CP_VPassword.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_CP_VPassword.setBounds(AnchoRelativo(12), AltoRelativo(130), AnchoRelativo(173), AltoRelativo(23));
		frmCambioPass.getContentPane().add(lbl_CP_VPassword);
		
		ptxt_CP_Password = new JPasswordField();
		ptxt_CP_Password.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		ptxt_CP_Password.setBounds(AnchoRelativo(217), AltoRelativo(69), AnchoRelativo(173), AltoRelativo(25));
		frmCambioPass.getContentPane().add(ptxt_CP_Password);
		
		lbl_CP_Password = new JLabel("Contraseña:");
		lbl_CP_Password.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_CP_Password.setBounds(AnchoRelativo(12), AltoRelativo(69), AnchoRelativo(101), AltoRelativo(23));
		frmCambioPass.getContentPane().add(lbl_CP_Password);
		
		ptxt_CP_VPassword = new JPasswordField();
		ptxt_CP_VPassword.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		ptxt_CP_VPassword.setBounds(AnchoRelativo(217), AltoRelativo(130), AnchoRelativo(173), AltoRelativo(25));
		frmCambioPass.getContentPane().add(ptxt_CP_VPassword);
		
		frmCambioPass.setVisible(true);
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
