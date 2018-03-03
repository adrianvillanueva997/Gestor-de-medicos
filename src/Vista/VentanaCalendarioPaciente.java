package Vista;


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.ActionListener;
	import java.io.File;
	import java.util.Vector;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.border.LineBorder;

	import com.toedter.calendar.JCalendar;

	import Control.*;
	import Modelo.*;

	public class VentanaCalendarioPaciente {
		CalendarControllerPaciente controlador;
		Paciente usuarioPaciente;
		Vector<Sesion> sesiones;
		JPanel panel_graficas;
		JLabel lbl_Sesiones_Rectangulo_Superior;
		JLabel lbl_Logo_Sesiones_Slim;
		public JCalendar calen;
		public JFrame frmCalen;
		public JButton btn_Calendario_Atras;
		public JButton btn_Aceptar;
		public JButton btn_Sesionestotales;
		
		public void addController(CalendarControllerPaciente cs){
			controlador = cs;
		}
		
		public void crearVentana(Vector<Sesion> sesiones){
			this.sesiones = sesiones;
		
			ColorearDias coldia=new ColorearDias(sesiones);
			calen=new JCalendar();
			calen.getDayChooser().addDateEvaluator(coldia);
			calen.getDayChooser().getDayPanel().setBackground(Color.WHITE);
			calen.setCalendar(calen.getCalendar());
			calen.setFont(new Font("Tahoma", Font.PLAIN,fuenteRelativa(20)));
			calen.setSelectableDateRange(coldia.getStartDate(), coldia.getEndDate());
			
			
			
			//VENTANA 
			 frmCalen=new JFrame();
			
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			frmCalen.setBounds(0, 0,screen.width,screen.height);	//Se establece el tama�o
			frmCalen.getContentPane().setForeground(Color.WHITE);
			frmCalen.setBackground(Color.WHITE);
			frmCalen.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frmCalen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Se establece su funcion por defecto al cerrar con la "X"
			frmCalen.setResizable(false);
			frmCalen.setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"img"+File.separator+"apus_logo.jpg"));
			frmCalen.getContentPane().setLayout(null);
			frmCalen.getContentPane().setBackground(Color.WHITE);
			
			//PANEL DEL CALENDARIO
			JPanel panel_calendario = new JPanel();
	  		panel_calendario.setBackground(Color.WHITE);
	  		panel_calendario.setBorder(new LineBorder(new Color(139, 0, 0), 2));
	  		panel_calendario.setBounds(AnchoRelativo(20),AltoRelativo(130),AnchoRelativo(1800), AltoRelativo(800));
	  		calen.setBounds(AnchoRelativo(0),AltoRelativo(0), AnchoRelativo(1800), AltoRelativo(800));
	  		calen.getMonthChooser().setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(200), AltoRelativo(200));
	  		panel_calendario.add(calen);
	  		
	  		// BOT�N aceptar
		    btn_Aceptar = new JButton("Aceptar dia seleccionado");
		    btn_Aceptar.setBounds(AnchoRelativo(1169), AltoRelativo(936), AnchoRelativo(300), AltoRelativo(96));
		    btn_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		    btn_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
			btn_Aceptar.setOpaque(true);
			btn_Aceptar.setBorderPainted(false);
			btn_Aceptar.setForeground(Color.WHITE);	
		    btn_Aceptar.addActionListener(controlador);
		    frmCalen.add(btn_Aceptar); 
		    
		    //BOTON SESIONES TOTALES
		    btn_Sesionestotales = new JButton("Sesiones Totales");
		    btn_Sesionestotales.setBounds(AnchoRelativo(1520), AltoRelativo(936), AnchoRelativo(300), AltoRelativo(96));
		    btn_Sesionestotales.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		    btn_Sesionestotales.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		    btn_Sesionestotales.setOpaque(true);
		    btn_Sesionestotales.setBorderPainted(false);
		    btn_Sesionestotales.setForeground(Color.WHITE);	
		    btn_Sesionestotales.addActionListener(controlador);
		    frmCalen.add(btn_Sesionestotales);
		    
	  		//LABEL LOGO SLIMUE:
		    lbl_Logo_Sesiones_Slim = new JLabel("");
		    lbl_Logo_Sesiones_Slim.setBounds(AnchoRelativo(15), AltoRelativo(0), AnchoRelativo(320), AltoRelativo(98));
		    Image logo_Sesiones_SlimUE = new ImageIcon("."+File.separator+"img"+File.separator+"logo.png").getImage();
		    logo_Sesiones_SlimUE = logo_Sesiones_SlimUE.getScaledInstance(lbl_Logo_Sesiones_Slim.getWidth(), lbl_Logo_Sesiones_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
		    lbl_Logo_Sesiones_Slim.setIcon(new ImageIcon(logo_Sesiones_SlimUE));
		    lbl_Logo_Sesiones_Slim.setBackground(Color.WHITE);
		    lbl_Logo_Sesiones_Slim.setForeground(new Color(153, 0, 0));
		    frmCalen.getContentPane().add(lbl_Logo_Sesiones_Slim);
		  
		    // BOTON ATRAS
		    btn_Calendario_Atras = new JButton("");
		    btn_Calendario_Atras.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
		    btn_Calendario_Atras.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		    btn_Calendario_Atras.setForeground(new Color(153, 0, 0));
		    btn_Calendario_Atras.setBackground(Color.WHITE);
		    Image icono_atras = new ImageIcon("."+File.separator+"img"+File.separator+"atras.png").getImage();
		    icono_atras = icono_atras.getScaledInstance(btn_Calendario_Atras.getWidth(), btn_Calendario_Atras.getHeight(), Image.SCALE_SMOOTH);
		    btn_Calendario_Atras.setIcon(new ImageIcon(icono_atras));
		    frmCalen.getContentPane().add(btn_Calendario_Atras);
		    btn_Calendario_Atras.addActionListener(controlador);
	  		
	  		
	  		panel_calendario.add(calen);
	  		frmCalen.getContentPane().add(panel_calendario);
	  		panel_calendario.setLayout(new BorderLayout(0, 0));
	  		
	  		//LABEL RECT�NGULO SUPERIOR:
		    lbl_Sesiones_Rectangulo_Superior = new JLabel("");
		    lbl_Sesiones_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
		    lbl_Sesiones_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
		    lbl_Sesiones_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		    lbl_Sesiones_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(98));
		    frmCalen.getContentPane().add(lbl_Sesiones_Rectangulo_Superior);
	  		
	  		frmCalen.setVisible(true);
			
			
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
