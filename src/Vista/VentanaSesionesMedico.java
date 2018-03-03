package Vista;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.*;
import Modelo.*;

public class VentanaSesionesMedico extends JFrame{
	public JFrame frmSesiones;	
	public JPanel panel_botones;
	public JLabel lbl_Sesiones;
	public String usuarioMedico;
	public JLabel lbl_Sesiones_Rectangulo_Superior;
	public JLabel lbl_Sesiones_Nombre;
	public JLabel lbl_Sesiones_Numero;
	public JLabel lbl_Logo_Sesiones_Slim;
	public JButton btn_Sesiones_Atras;
	public JButton btn_Sesiones_Mapa;
	public JLabel lbl_Sesiones_TituloTabla;
	public JPanel panel_graficas;
	public JLabel lbl_Sesiones_Velocidad;
	public JButton btn_Sesiones_Velocidad;
	public JLabel lbl_Sesiones_Pulso;
	public JButton btn_Sesiones_Pulso;
	public JLabel lbl_Sesiones_Oxigeno;
	public JButton btn_Sesiones_Oxigeno;
	public JLabel lbl_Sesiones_Altitud;
	public JButton btn_Sesiones_Altitud;

	public JTable tablaSesiones;
	public JButton btn_Sesiones_Comentario;
	public JButton btn_Sesion_VisualizarNuevaSesion;
	
	public JLabel lbl_Sesiones_Tiempo;
	public JLabel lbl_Sesiones_Tiempo_Num;
	public JLabel lbl_Sesiones_Distancia;
	public JLabel lbl_Sesiones_Distancia_Num;
	public JLabel lbl_Sesiones_AltMax;
	public JLabel lbl_Sesiones_AltMax_Num;
	public JLabel lbl_Sesiones_AltMin;
	public JLabel lbl_Sesiones_AltMin_Num;
	public JLabel lbl_Sesiones_FCMax;
	public JLabel lbl_Sesiones_FCMax_Num;
	public JLabel lbl_Sesiones_FCMin;
	public JLabel lbl_Sesiones_FCMin_Num;
	public JLabel lbl_Sesiones_VelMed;
	public JLabel lbl_Sesiones_VelMed_Num;
	public JLabel lbl_Sesiones_Peso;
	public JLabel lbl_Sesiones_Peso_Num;
	public JLabel lbl_Sesiones_Imc;
	public JLabel lbl_Sesiones_Imc_Num;
	public JLabel lbl_Sesiones_Animo;
	public JLabel lbl_Sesiones_Animo_Num;
	public JLabel lbl_Sesiones_Comentario;
	public JLabel lbl_Sesiones_Comentario_Num;
	public JPanel panel_progbar;
	public Paciente usuarioPaciente;
	ControladorSesionesMedico controlador;
	Vector<Sesion>data_Sesion;

	
	public void addController(ControladorSesionesMedico mc, Paciente usuarioPaciente,Vector<Sesion>sesion){
		controlador = mc;
		this.usuarioPaciente = usuarioPaciente;
		data_Sesion=sesion;
	}
	public Object[][] crearDatostabla(Vector<Sesion> datos){
		Object[][] md = new Object[datos.size()][1];
	      
		for (int i = 0; i < datos.size(); i++){
 	    	  md[i][0] =  datos.get(i).getFecha();
 	    	  
 	    	  
		}
		return md;
	}
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(Medico usuarioMedico, Paciente usuarioPaciente) throws IOException{

		Fichero comprobar = new Fichero();
		Medico med = comprobar.busquedaMedico(usuarioMedico);
		Paciente paciente = comprobar.busquedaPaciente(usuarioPaciente);		
		
		//crea la ventana
		frmSesiones = new JFrame();
		frmSesiones.setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"img"+File.separator+"apus_logo.jpg"));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	//Busca la resoluci�n de la pantalla
		frmSesiones.getContentPane().setBackground(Color.WHITE);
		frmSesiones.setBounds(0, 0,screen.width,screen.height);	//Establece dimensiones de la ventana
		frmSesiones.setExtendedState(JFrame.MAXIMIZED_BOTH);	//Maximiza por defecto la ventana
		frmSesiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Estabalece la operaci�n de cierre por defecto
		frmSesiones.getContentPane().setLayout(null);

  		//PANEL PARA PONER LA GRAFICA
  		panel_graficas = new JPanel();
  		panel_graficas.setBackground(Color.WHITE);
  		panel_graficas.setBorder(new LineBorder(new Color(139, 0, 0), 2));
  		panel_graficas.setBounds(AnchoRelativo(250), AltoRelativo(127), AnchoRelativo(1300), AltoRelativo(520));
  		frmSesiones.getContentPane().add(panel_graficas);
  		panel_graficas.setLayout(new BorderLayout(0, 0));
  		
		//LABEL RECT�NGULO SUPERIOR:
	    lbl_Sesiones_Rectangulo_Superior = new JLabel("");
	    lbl_Sesiones_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
	    lbl_Sesiones_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
	    lbl_Sesiones_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
	    lbl_Sesiones_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(98));
	    frmSesiones.getContentPane().add(lbl_Sesiones_Rectangulo_Superior);
	    
	    //LABEL NOMBRE DEL PACIENTE:
	    lbl_Sesiones_Nombre = new JLabel(paciente.getNombre() + " " + paciente.getApellido());
	    lbl_Sesiones_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
	    lbl_Sesiones_Nombre.setBounds(AnchoRelativo(1417), AltoRelativo(16), AnchoRelativo(371), AltoRelativo(33));
	    lbl_Sesiones_Nombre.setHorizontalAlignment(JLabel.RIGHT);
	    frmSesiones.getContentPane().add(lbl_Sesiones_Nombre);
	      
	    //LABEL HISTORIAL DEL PACIENTE:
	    lbl_Sesiones_Numero = new JLabel("Usuario: " + paciente.getNombreUsuario());
	    lbl_Sesiones_Numero.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
	    lbl_Sesiones_Numero.setBounds(AnchoRelativo(1417), AltoRelativo(52), AnchoRelativo(371), AltoRelativo(33));
	    lbl_Sesiones_Numero.setHorizontalAlignment(JLabel.RIGHT);
	    frmSesiones.getContentPane().add(lbl_Sesiones_Numero);
	    
	    //LABEL LOGO SLIMUE:
	    lbl_Logo_Sesiones_Slim = new JLabel("");
	    lbl_Logo_Sesiones_Slim.setBounds(AnchoRelativo(15), AltoRelativo(0), AnchoRelativo(321), AltoRelativo(98));
	    Image logo_Sesiones_SlimUE = new ImageIcon("."+File.separator+"img"+File.separator+"logo.png").getImage();
	    logo_Sesiones_SlimUE = logo_Sesiones_SlimUE.getScaledInstance(lbl_Logo_Sesiones_Slim.getWidth(), lbl_Logo_Sesiones_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
	    lbl_Logo_Sesiones_Slim.setIcon(new ImageIcon(logo_Sesiones_SlimUE));
	    frmSesiones.getContentPane().add(lbl_Logo_Sesiones_Slim);
	  
	    // BOT�N ATRAS
	    btn_Sesiones_Atras = new JButton("");
	    btn_Sesiones_Atras.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
	    btn_Sesiones_Atras.setBorder(new LineBorder(new Color(153, 0, 0), 3));
	    btn_Sesiones_Atras.setForeground(new Color(153, 0, 0));
	    btn_Sesiones_Atras.setBackground(Color.WHITE);
	    Image icono_atras = new ImageIcon("."+File.separator+"img"+File.separator+"atras.png").getImage();
	    icono_atras = icono_atras.getScaledInstance(btn_Sesiones_Atras.getWidth(), btn_Sesiones_Atras.getHeight(), Image.SCALE_SMOOTH);
	    btn_Sesiones_Atras.setIcon(new ImageIcon(icono_atras));
	    frmSesiones.getContentPane().add(btn_Sesiones_Atras);
	    btn_Sesiones_Atras.addActionListener(controlador);
	    
		//TABLA SESIONES:
		tablaSesiones = new JTable(); 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(AnchoRelativo(1650), AltoRelativo(127), AnchoRelativo(200), AltoRelativo(520));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(tablaSesiones);
		
		String[] nombre_Columnas_Sesion = {"Fecha"};
		TableModel modelo_Tabla_Sesion = new DefaultTableModel(crearDatostabla(data_Sesion), nombre_Columnas_Sesion) {
			public boolean isCellEditable(int row, int column) {return false;}};
		tablaSesiones.setModel(modelo_Tabla_Sesion);
		tablaSesiones.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		tablaSesiones.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(24)));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tablaSesiones.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaSesiones.setRowHeight((scrollPane.getHeight())/10);
		tablaSesiones.setAutoCreateRowSorter(true);
		tablaSesiones.addMouseListener((MouseListener) controlador);
		frmSesiones.getContentPane().add(scrollPane);
		
  		//LABEL TITULO DE LA TABLA
  		lbl_Sesiones_TituloTabla = new JLabel("");
  		lbl_Sesiones_TituloTabla.setBounds(AnchoRelativo(1279), AltoRelativo(189), AnchoRelativo(485), AltoRelativo(61));
  		frmSesiones.getContentPane().add(lbl_Sesiones_TituloTabla);
  				
  		//PANEL BOTONES	
  		panel_botones = new JPanel();
  		panel_botones.setBackground(Color.WHITE);
  		panel_botones.setBounds(AnchoRelativo(0), AltoRelativo(127), AnchoRelativo(250), AltoRelativo(525));
  		frmSesiones.getContentPane().add(panel_botones);
  		
  		//BOTON VELOCIDAD
  		btn_Sesiones_Velocidad = new JButton("");
  		btn_Sesiones_Velocidad.setBounds(AnchoRelativo(399), AltoRelativo(670), AnchoRelativo(100), AltoRelativo(89));
  		btn_Sesiones_Velocidad.setBackground(Color.WHITE);
  		Image icono_velocidad = new ImageIcon("."+File.separator+"img"+File.separator+"velocidad.png").getImage();
  		icono_velocidad = icono_velocidad.getScaledInstance(btn_Sesiones_Velocidad.getWidth(), btn_Sesiones_Velocidad.getHeight(), Image.SCALE_SMOOTH);
  		btn_Sesiones_Velocidad.setIcon(new ImageIcon(icono_velocidad));
  		btn_Sesiones_Velocidad.addActionListener(controlador);
  		frmSesiones.getContentPane().add(btn_Sesiones_Velocidad);
  		panel_botones.add(btn_Sesiones_Velocidad);
  		
  		//BOTON PULSO
  		btn_Sesiones_Pulso = new JButton("");
  		btn_Sesiones_Pulso.setBounds(AnchoRelativo(618), AltoRelativo(675), AnchoRelativo(100), AltoRelativo(89));
  		btn_Sesiones_Pulso.setBackground(Color.WHITE);
  		Image icono_pulso = new ImageIcon(("."+File.separator+"img"+File.separator+"ritmo_cardiaco.png")).getImage();
  		icono_pulso = icono_pulso.getScaledInstance(btn_Sesiones_Pulso.getWidth(), btn_Sesiones_Pulso.getHeight(), Image.SCALE_SMOOTH);
  		btn_Sesiones_Pulso.setIcon(new ImageIcon(icono_pulso));
  		btn_Sesiones_Pulso.addActionListener(controlador);
  		frmSesiones.getContentPane().add(btn_Sesiones_Pulso);
  		panel_botones.add(btn_Sesiones_Pulso);
  		
  		//BOTON OXIGENO
  		btn_Sesiones_Oxigeno = new JButton("");
  		btn_Sesiones_Oxigeno.setBounds(AnchoRelativo(824), AltoRelativo(675), AnchoRelativo(100), AltoRelativo(89));
  		btn_Sesiones_Oxigeno.setBackground(Color.WHITE);
  		Image icono_oxigeno = new ImageIcon(("."+File.separator+"img"+File.separator+"O2.png")).getImage();
  		icono_oxigeno = icono_oxigeno.getScaledInstance(btn_Sesiones_Oxigeno.getWidth(), btn_Sesiones_Oxigeno.getHeight(), Image.SCALE_SMOOTH);
  		btn_Sesiones_Oxigeno.setIcon(new ImageIcon(icono_oxigeno));
  		btn_Sesiones_Oxigeno.addActionListener(controlador);
  		frmSesiones.getContentPane().add(btn_Sesiones_Oxigeno);
  		panel_botones.add(btn_Sesiones_Oxigeno);
  		
  		//BOTON ALTITUD
  		btn_Sesiones_Altitud = new JButton("");
  		btn_Sesiones_Altitud.setBounds(AnchoRelativo(1044), AltoRelativo(675), AnchoRelativo(100), AltoRelativo(89));
  		btn_Sesiones_Altitud.setBackground(Color.WHITE);
  		Image icono_altitud = new ImageIcon(("."+File.separator+"img"+File.separator+"altitud.png")).getImage();
  		icono_altitud = icono_altitud.getScaledInstance(btn_Sesiones_Altitud.getWidth(), btn_Sesiones_Altitud.getHeight(), Image.SCALE_SMOOTH);
  		btn_Sesiones_Altitud.setIcon(new ImageIcon(icono_altitud));
  		btn_Sesiones_Altitud.addActionListener(controlador);
  		frmSesiones.getContentPane().add(btn_Sesiones_Altitud);
  		panel_botones.add(btn_Sesiones_Altitud);
  		
  		//BOTON MAPA
        btn_Sesiones_Mapa = new JButton("");
        btn_Sesiones_Mapa.setBounds(AnchoRelativo(1044), AltoRelativo(675), AnchoRelativo(100), AltoRelativo(89));
        btn_Sesiones_Mapa.setBackground(Color.WHITE);
        Image icono_mapa = new ImageIcon(("."+File.separator+"img"+File.separator+"map.png")).getImage();
        icono_mapa = icono_mapa.getScaledInstance(btn_Sesiones_Mapa.getWidth(), btn_Sesiones_Mapa.getHeight(), Image.SCALE_SMOOTH);
        btn_Sesiones_Mapa.setIcon(new ImageIcon(icono_mapa));
        btn_Sesiones_Mapa.addActionListener(controlador);
        frmSesiones.getContentPane().add(btn_Sesiones_Mapa);
        panel_botones.add(btn_Sesiones_Mapa);
  		
  		/*//BOTON COMENTARIOS SOBRE LA SESION
  		btn_Sesiones_Comentario = new JButton("");
  		btn_Sesiones_Comentario.setBounds(AnchoRelativo(1044), AltoRelativo(675), AnchoRelativo(129), AltoRelativo(109));
  		btn_Sesiones_Comentario.setBackground(Color.WHITE);
  		Image comentario = new ImageIcon(("."+File.separator+"img"+File.separator+"comentario.png")).getImage();
  		comentario = comentario.getScaledInstance(btn_Sesiones_Comentario.getWidth(), btn_Sesiones_Comentario.getHeight(), Image.SCALE_SMOOTH);
  		btn_Sesiones_Comentario.setIcon(new ImageIcon(comentario));
  		frmSesiones.getContentPane().add(btn_Sesiones_Comentario);
  		btn_Sesiones_Comentario.addActionListener(controlador);
  		panel_botones.add(btn_Sesiones_Comentario);*/
  		
  		//BOTON VISUALIZAR NUEVA SESION
  		btn_Sesion_VisualizarNuevaSesion = new JButton("Nueva Sesion");
  		btn_Sesion_VisualizarNuevaSesion.setBackground(new Color(139, 0, 0));
  		btn_Sesion_VisualizarNuevaSesion.setForeground(Color.WHITE);
  		btn_Sesion_VisualizarNuevaSesion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
  		btn_Sesion_VisualizarNuevaSesion.setBounds(AnchoRelativo(1700), AltoRelativo(913), AnchoRelativo(180), AltoRelativo(43));
  		frmSesiones.getContentPane().add(btn_Sesion_VisualizarNuevaSesion);
  		btn_Sesion_VisualizarNuevaSesion.addActionListener(controlador);
  		 
  		
  		//INFORMACION Y ESTADISTICAS
  		lbl_Sesiones_Tiempo = new JLabel("Duraci\u00F3n");
  		lbl_Sesiones_Tiempo.setHorizontalAlignment(SwingConstants.CENTER);
  		lbl_Sesiones_Tiempo.setFont(new Font("Tahoma", Font.BOLD, 20));
  		lbl_Sesiones_Tiempo.setBounds(AnchoRelativo(37), AltoRelativo(670), AnchoRelativo(180), AltoRelativo(43));
  		lbl_Sesiones_Tiempo.setBorder(new LineBorder(new Color(153, 0, 0), 3));
  		frmSesiones.getContentPane().add(lbl_Sesiones_Tiempo);
  		
  		lbl_Sesiones_Tiempo_Num = new JLabel("");
  		lbl_Sesiones_Tiempo_Num.setHorizontalAlignment(SwingConstants.CENTER);
  		lbl_Sesiones_Tiempo_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
  		lbl_Sesiones_Tiempo_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
  		lbl_Sesiones_Tiempo_Num.setBounds(AnchoRelativo(37), AltoRelativo(711), AnchoRelativo(180), AltoRelativo(43));
  		frmSesiones.getContentPane().add(lbl_Sesiones_Tiempo_Num);
  		
  		lbl_Sesiones_AltMax = new JLabel("Altitud M\u00E1x.");
  		lbl_Sesiones_AltMax.setHorizontalAlignment(SwingConstants.CENTER);
  		lbl_Sesiones_AltMax.setToolTipText("");
  		lbl_Sesiones_AltMax.setFont(new Font("Tahoma", Font.BOLD, 20));
  		lbl_Sesiones_AltMax.setBorder(new LineBorder(new Color(153, 0, 0), 3));
  		lbl_Sesiones_AltMax.setBounds(AnchoRelativo(37), AltoRelativo(767), AnchoRelativo(180), AltoRelativo(43));
  		frmSesiones.getContentPane().add(lbl_Sesiones_AltMax);
  		
  		lbl_Sesiones_AltMax_Num = new JLabel("");
  		lbl_Sesiones_AltMax_Num.setHorizontalAlignment(SwingConstants.CENTER);
  		lbl_Sesiones_AltMax_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
  		lbl_Sesiones_AltMax_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
  		lbl_Sesiones_AltMax_Num.setBounds(AnchoRelativo(37), AltoRelativo(808), AnchoRelativo(180), AltoRelativo(43));
  		frmSesiones.getContentPane().add(lbl_Sesiones_AltMax_Num);
        
        lbl_Sesiones_Distancia = new JLabel("Distancia");
        lbl_Sesiones_Distancia.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Distancia.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_Distancia.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Distancia.setBounds(AnchoRelativo(229), AltoRelativo(670), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Distancia);
        
        lbl_Sesiones_Distancia_Num = new JLabel("");
        lbl_Sesiones_Distancia_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Distancia_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_Distancia_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Distancia_Num.setBounds(AnchoRelativo(229), AltoRelativo(711), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Distancia_Num);
        
        lbl_Sesiones_AltMin = new JLabel("Altitud M\u00EDn.");
        lbl_Sesiones_AltMin.setToolTipText("");
        lbl_Sesiones_AltMin.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_AltMin.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_AltMin.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_AltMin.setBounds(AnchoRelativo(37), AltoRelativo(871), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_AltMin);
        
        lbl_Sesiones_AltMin_Num = new JLabel("");
        lbl_Sesiones_AltMin_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_AltMin_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_AltMin_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_AltMin_Num.setBounds(AnchoRelativo(37), AltoRelativo(912), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_AltMin_Num);
        
        lbl_Sesiones_FCMax = new JLabel("FC M\u00E1x.");
        lbl_Sesiones_FCMax.setToolTipText("");
        lbl_Sesiones_FCMax.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_FCMax.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_FCMax.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_FCMax.setBounds(AnchoRelativo(229), AltoRelativo(767), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_FCMax);
        
        lbl_Sesiones_FCMax_Num = new JLabel("");
        lbl_Sesiones_FCMax_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_FCMax_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_FCMax_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_FCMax_Num.setBounds(AnchoRelativo(229), AltoRelativo(808), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_FCMax_Num);
        
        lbl_Sesiones_FCMin = new JLabel("FC M\u00EDn.");
        lbl_Sesiones_FCMin.setToolTipText("");
        lbl_Sesiones_FCMin.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_FCMin.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_FCMin.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_FCMin.setBounds(AnchoRelativo(229), AltoRelativo(872), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_FCMin);
        
        lbl_Sesiones_FCMin_Num = new JLabel("");
        lbl_Sesiones_FCMin_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_FCMin_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_FCMin_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_FCMin_Num.setBounds(AnchoRelativo(229), AltoRelativo(913), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_FCMin_Num);
        
        lbl_Sesiones_VelMed = new JLabel("Velocidad Media");
        lbl_Sesiones_VelMed.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_VelMed.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_VelMed.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_VelMed.setBounds(AnchoRelativo(421), AltoRelativo(670), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_VelMed);
        
        lbl_Sesiones_VelMed_Num = new JLabel("");
        lbl_Sesiones_VelMed_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_VelMed_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_VelMed_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_VelMed_Num.setBounds(AnchoRelativo(421), AltoRelativo(711), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_VelMed_Num);
        
        lbl_Sesiones_Peso = new JLabel("Peso");
        lbl_Sesiones_Peso.setToolTipText("");
        lbl_Sesiones_Peso.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Peso.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_Peso.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Peso.setBounds(AnchoRelativo(421), AltoRelativo(767), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Peso);
        
        lbl_Sesiones_Peso_Num = new JLabel("");
        lbl_Sesiones_Peso_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Peso_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_Peso_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Peso_Num.setBounds(AnchoRelativo(421), AltoRelativo(808), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Peso_Num);
        
        lbl_Sesiones_Imc = new JLabel("IMC");
        lbl_Sesiones_Imc.setToolTipText("");
        lbl_Sesiones_Imc.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Imc.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_Imc.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Imc.setBounds(AnchoRelativo(421), AltoRelativo(872), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Imc);
        
        lbl_Sesiones_Imc_Num = new JLabel("");
        lbl_Sesiones_Imc_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Imc_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_Imc_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Imc_Num.setBounds(AnchoRelativo(421), AltoRelativo(913), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Imc_Num);
  		
        lbl_Sesiones_Animo = new JLabel("\u00C1nimo");
        lbl_Sesiones_Animo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Animo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_Animo.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Animo.setBounds(AnchoRelativo(613), AltoRelativo(670), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Animo);
        
        lbl_Sesiones_Animo_Num = new JLabel("");
        lbl_Sesiones_Animo_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Animo_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_Animo_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Animo_Num.setBounds(AnchoRelativo(613), AltoRelativo(711), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Animo_Num);
        
        lbl_Sesiones_Comentario = new JLabel("Comentario");
        lbl_Sesiones_Comentario.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Comentario.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_Sesiones_Comentario.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Comentario.setBounds(AnchoRelativo(613), AltoRelativo(767), AnchoRelativo(180), AltoRelativo(43));
        frmSesiones.getContentPane().add(lbl_Sesiones_Comentario);
        
        lbl_Sesiones_Comentario_Num = new JLabel("");
        lbl_Sesiones_Comentario_Num.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Sesiones_Comentario_Num.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Sesiones_Comentario_Num.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Sesiones_Comentario_Num.setBounds(AnchoRelativo(613), AltoRelativo(808), AnchoRelativo(180), AltoRelativo(148));
        frmSesiones.getContentPane().add(lbl_Sesiones_Comentario_Num);
        
        //PANEL PARA PONER ProgresBar
  		panel_progbar = new JPanel();
  		panel_progbar.setBackground(Color.WHITE);
  		//panel_progbar.setBorder(new LineBorder(new Color(139, 0, 0), 2));
  		panel_progbar.setBounds(AnchoRelativo(800), AltoRelativo(670), AnchoRelativo(900), AltoRelativo(318));
  		frmSesiones.getContentPane().add(panel_progbar);
  		panel_graficas.setLayout(new BorderLayout(0, 0));
  		
		frmSesiones.setVisible(true);
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





