package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import Control.*;

public class VentanaFormularioSesion {
	ControladorFormularioSesion controlador;
	public JFrame frmSubirSesion;
	public JButton btn_Cancelar_SubirSesion;
	public JButton btn_Aceptar_SubirSesion;
	public JLabel lbl_Comentario_SubirSesion;
	public JButton btn_Buscar_SubirSesion;
	public JLabel lbl_Animo_SubirSesion;
	public JTextPane txtPn_Comentario_SubirSesion;
	public JButton btn_CaritaFeliz;
	public JButton btn_CaritaTriste;
	public JButton btn_CaritaNeutral;
	public JTextField txt_Buscar_SubirSesion;
	public JComboBox cb_AP_Peso;
	public JComboBox cb_AP_Gramos;
	public JLabel lbl_Formulario_Peso;
	public JLabel lbl_FormularioSesion_Fechas;
	public JDateChooser dateChooser;
	public JComboBox cb_AP_Actividad;
	public JLabel lbl_Formulario_Actividad;
	
	//public JDayChooser dayChooser;
	//public JMonthChooser monthChooser;
	//public JYearChooser yearChooser;
	
	public void addController(ControladorFormularioSesion mc){
		controlador = mc;
	}	
	
	public void crearVentana() {
		frmSubirSesion = new JFrame();
		frmSubirSesion.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(1067), AltoRelativo(786));
		frmSubirSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubirSesion.setResizable(false);
		frmSubirSesion.setTitle("Subir sesión");
		frmSubirSesion.getContentPane().setBackground(Color.WHITE);
		frmSubirSesion.getContentPane().setLayout(null);
		
		btn_Cancelar_SubirSesion = new JButton("Cancelar");
		btn_Cancelar_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		btn_Cancelar_SubirSesion.setBounds(AnchoRelativo(920), AltoRelativo(709), AnchoRelativo(115), AltoRelativo(29));
		btn_Cancelar_SubirSesion.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_Cancelar_SubirSesion.setOpaque(true);
		btn_Cancelar_SubirSesion.setBorderPainted(false);
		btn_Cancelar_SubirSesion.setForeground(new Color(255, 255, 255));
		frmSubirSesion.getContentPane().add(btn_Cancelar_SubirSesion);
		btn_Cancelar_SubirSesion.addActionListener(controlador);
		
		btn_Aceptar_SubirSesion = new JButton("Aceptar");
		btn_Aceptar_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		btn_Aceptar_SubirSesion.setBounds(AnchoRelativo(765), AltoRelativo(709), AnchoRelativo(115), AltoRelativo(29));
		btn_Aceptar_SubirSesion.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_Aceptar_SubirSesion.setOpaque(true);
		btn_Aceptar_SubirSesion.setBorderPainted(false);
		btn_Aceptar_SubirSesion.setForeground(new Color(255, 255, 255));
		frmSubirSesion.getContentPane().add(btn_Aceptar_SubirSesion);
		btn_Aceptar_SubirSesion.addActionListener(controlador);
		
		lbl_Comentario_SubirSesion = new JLabel("Comentario:");
		lbl_Comentario_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Comentario_SubirSesion.setBounds(AnchoRelativo(95), AltoRelativo(427), AnchoRelativo(131), AltoRelativo(20));
		frmSubirSesion.getContentPane().add(lbl_Comentario_SubirSesion);
				
		btn_Buscar_SubirSesion = new JButton("Buscar sesión");
		btn_Buscar_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		btn_Buscar_SubirSesion.setBounds(AnchoRelativo(809), AltoRelativo(47), AnchoRelativo(161), AltoRelativo(39));
		btn_Buscar_SubirSesion.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_Buscar_SubirSesion.setOpaque(true);
		btn_Buscar_SubirSesion.setBorderPainted(false);
		btn_Buscar_SubirSesion.setForeground(new Color(255, 255, 255));
		frmSubirSesion.getContentPane().add(btn_Buscar_SubirSesion);
		btn_Buscar_SubirSesion.addActionListener(controlador);
		
		lbl_Animo_SubirSesion = new JLabel("Estado de ánimo:");
		lbl_Animo_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Animo_SubirSesion.setBounds(AnchoRelativo(95), AltoRelativo(255), AnchoRelativo(171), AltoRelativo(20));
		frmSubirSesion.getContentPane().add(lbl_Animo_SubirSesion);
		
		txtPn_Comentario_SubirSesion = new JTextPane();
		txtPn_Comentario_SubirSesion.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtPn_Comentario_SubirSesion.setBounds(AnchoRelativo(95), AltoRelativo(463), AnchoRelativo(940), AltoRelativo(217));
		txtPn_Comentario_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmSubirSesion.getContentPane().add(txtPn_Comentario_SubirSesion);
		
		btn_CaritaFeliz = new JButton("");
		btn_CaritaFeliz.setBounds(AnchoRelativo(187), AltoRelativo(304), AnchoRelativo(131), AltoRelativo(110));
		Image logo_CaritaFeliz = new ImageIcon("."+File.separator+"img"+File.separator+"feliz.png").getImage();
		logo_CaritaFeliz = logo_CaritaFeliz.getScaledInstance(btn_CaritaFeliz.getWidth(), btn_CaritaFeliz.getHeight(), java.awt.Image.SCALE_SMOOTH);
		btn_CaritaFeliz.setIcon(new ImageIcon(logo_CaritaFeliz));
		btn_CaritaFeliz.setBackground(Color.WHITE);
		frmSubirSesion.getContentPane().add(btn_CaritaFeliz);
		btn_CaritaFeliz.addActionListener(controlador);
		
		btn_CaritaTriste = new JButton("");
		btn_CaritaTriste.setBounds(AnchoRelativo(408), AltoRelativo(304), AnchoRelativo(131), AltoRelativo(110));
		Image logo_CaritaTriste = new ImageIcon(("."+File.separator+"img"+File.separator+"triste.png")).getImage();
		logo_CaritaTriste = logo_CaritaTriste.getScaledInstance(btn_CaritaTriste.getWidth(), btn_CaritaTriste.getHeight(), java.awt.Image.SCALE_SMOOTH);
		btn_CaritaTriste.setIcon(new ImageIcon(logo_CaritaTriste));
		btn_CaritaTriste.setBackground(Color.WHITE);
		frmSubirSesion.getContentPane().add(btn_CaritaTriste);
		btn_CaritaTriste.addActionListener(controlador);
		
		btn_CaritaNeutral = new JButton("");
		btn_CaritaNeutral.setBounds(AnchoRelativo(631), AltoRelativo(304), AnchoRelativo(131), AltoRelativo(110));
		Image logo_CaritaNeutral = new ImageIcon(("."+File.separator+"img"+File.separator+"neutral.png")).getImage();
		logo_CaritaNeutral = logo_CaritaNeutral.getScaledInstance(btn_CaritaNeutral.getWidth(), btn_CaritaNeutral.getHeight(), java.awt.Image.SCALE_SMOOTH);
		btn_CaritaNeutral.setIcon(new ImageIcon(logo_CaritaNeutral));
		btn_CaritaNeutral.setBackground(Color.WHITE);
		frmSubirSesion.getContentPane().add(btn_CaritaNeutral);
		btn_CaritaNeutral.addActionListener(controlador);
		
		txt_Buscar_SubirSesion = new JTextField();
		txt_Buscar_SubirSesion.setBounds(AnchoRelativo(94), AltoRelativo(47), AnchoRelativo(667), AltoRelativo(39));
		txt_Buscar_SubirSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmSubirSesion.getContentPane().add(txt_Buscar_SubirSesion);
		txt_Buscar_SubirSesion.setColumns(10);
		
		lbl_Formulario_Peso = new JLabel("Peso:");
		lbl_Formulario_Peso.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Formulario_Peso.setBounds(AnchoRelativo(94), AltoRelativo(127), AnchoRelativo(62), AnchoRelativo(20));
		frmSubirSesion.getContentPane().add(lbl_Formulario_Peso);
		
		cb_AP_Peso = new JComboBox();
		cb_AP_Peso.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Peso.setBounds(AnchoRelativo(186), AltoRelativo(127), AnchoRelativo(65), AltoRelativo(24));
		int i = 15;
		while(i < 200){
			String s = String.valueOf(i);
			cb_AP_Peso.addItem(s);
			i += 1;
		}
		frmSubirSesion.getContentPane().add(cb_AP_Peso);
		
		cb_AP_Gramos = new JComboBox();
		cb_AP_Gramos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Gramos.setBounds(AnchoRelativo(255), AltoRelativo(127), AnchoRelativo(65), AltoRelativo(24));
		int j = 10;
		while(j < 100){
			String s = "." + String.valueOf(j);
			cb_AP_Gramos.addItem(s);
			j += 10;
		}
		frmSubirSesion.getContentPane().add(cb_AP_Gramos);
		
		lbl_Formulario_Actividad = new JLabel("Actividad:");
		lbl_Formulario_Actividad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Formulario_Actividad.setBounds(AnchoRelativo(94), AltoRelativo(200), AnchoRelativo(100), AnchoRelativo(20));
		frmSubirSesion.getContentPane().add(lbl_Formulario_Actividad);
		
		cb_AP_Actividad = new JComboBox();
		cb_AP_Actividad.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Actividad.setBounds(AnchoRelativo(186), AltoRelativo(200), AnchoRelativo(200), AltoRelativo(24));
		cb_AP_Actividad.addItem("Actividad libre");
		cb_AP_Actividad.addItem("Paseo");
		cb_AP_Actividad.addItem("Ciclismo");
		cb_AP_Actividad.addItem("Maratón");
		cb_AP_Actividad.addItem("Media Maratón");
		cb_AP_Actividad.addItem("Trekking");
		cb_AP_Actividad.addItem("Senderismo");
		cb_AP_Actividad.addItem("Esquí");
		cb_AP_Actividad.addItem("Carrera");
		cb_AP_Actividad.addItem("Hípica");
		cb_AP_Actividad.addItem("Ciclismo en interior");
		cb_AP_Actividad.addItem("Cinta");
		cb_AP_Actividad.addItem("Gimnasio");
		cb_AP_Actividad.addItem("Snowboard");
		cb_AP_Actividad.addItem("Fútbol");
		cb_AP_Actividad.addItem("Baloncesto");
		cb_AP_Actividad.addItem("Rugby");
		cb_AP_Actividad.addItem("Natación");
		frmSubirSesion.getContentPane().add(cb_AP_Actividad);
		
		lbl_FormularioSesion_Fechas = new JLabel("Fecha:");
		lbl_FormularioSesion_Fechas.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_FormularioSesion_Fechas.setBounds(AnchoRelativo(359), AltoRelativo(123), AnchoRelativo(73), AltoRelativo(29));
		frmSubirSesion.getContentPane().add(lbl_FormularioSesion_Fechas);
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser.setBounds(AnchoRelativo(483), AltoRelativo(127), AnchoRelativo(168), AltoRelativo(22));
		frmSubirSesion.getContentPane().add(dateChooser);
		
		frmSubirSesion.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"img"+File.separator+"/apus_logo.jpg")));
		frmSubirSesion.setVisible(true);
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

