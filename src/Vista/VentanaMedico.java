package Vista;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.*;
import Modelo.*;

public class VentanaMedico extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ControladorMedico controlador;
	public JFrame frmMedico;	
	public JLabel lbl_Medico_Rectangulo_Superior;
	public JTable tabla_Medico;
	public JButton btn_Medico_Anadir_Paciente;
	public JTextField txt_Medico_Buscar;
	public JButton btn_Medico_Cerrar_Sesion;
	public JLabel lbl_Medico_Nombre;
	public JLabel lbl_Medico_Numero;
	public JLabel lbl_Logo_Medico_Slim;
	public JButton btn_Medico_Buscar;
	public JButton btn_Medico_Editar;
	public JButton btn_CambiarPass;
	
	public JCheckBox chckbx_ListadoPacientes;
	public JCheckBox chckbx_PacActivos;
	public JCheckBox chckbx_PacInactivos;
	
	public void addController(ControladorMedico mc){
		controlador = mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(Medico usuarioMedico) throws Exception{

		Fichero comprobar = new Fichero();
		Medico med = comprobar.busquedaMedico(usuarioMedico);
		
		//crea la ventana
		frmMedico = new JFrame();
		frmMedico.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"apus_logo.jpg")));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	//Busca la resoluci�n de la pantalla
		frmMedico.getContentPane().setBackground(Color.WHITE);
		frmMedico.setBounds(0, 0,screen.width,screen.height);	//Establece dimensiones de la ventana
		frmMedico.setExtendedState(JFrame.MAXIMIZED_BOTH);	//Maximiza por defecto la ventana
		frmMedico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Estabalece la operaci�n de cierre por defecto
		frmMedico.getContentPane().setLayout(null);	//obtiene el del contenido del JFrame y no establece ning�n tipo de Dise�o(Layout)
		frmMedico.addKeyListener(controlador);
		
		//LABEL RECT�NGULO SUPERIOR:
		lbl_Medico_Rectangulo_Superior = new JLabel("");
		lbl_Medico_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
		lbl_Medico_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
		lbl_Medico_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		lbl_Medico_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(98));
		frmMedico.getContentPane().add(lbl_Medico_Rectangulo_Superior);
		
		//LABEL NOMBRE DEL M�DICO:
		lbl_Medico_Nombre = new JLabel(med.getNombre() + " " + med.getApellido());
		lbl_Medico_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Medico_Nombre.setBounds(AnchoRelativo(1417), AltoRelativo(16), AnchoRelativo(371), AltoRelativo(33));
		lbl_Medico_Nombre.setHorizontalAlignment(JLabel.RIGHT);
		frmMedico.getContentPane().add(lbl_Medico_Nombre);
			
		//LABEL CLINICA DEL M�DICO:
		lbl_Medico_Numero = new JLabel(med.getClinica());
		lbl_Medico_Numero.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_Medico_Numero.setBounds(AnchoRelativo(1417), AltoRelativo(52), AnchoRelativo(371), AltoRelativo(33));
		lbl_Medico_Numero.setHorizontalAlignment(JLabel.RIGHT);
		frmMedico.getContentPane().add(lbl_Medico_Numero);
		
		//LABEL LOGO SLIMUE:
		lbl_Logo_Medico_Slim = new JLabel("");
		lbl_Logo_Medico_Slim.setBounds(AnchoRelativo(15), AltoRelativo(0), AnchoRelativo(321), AltoRelativo(98));
		Image logo_Medico_SlimUE = new ImageIcon(("."+File.separator+"img"+File.separator+"logo.png")).getImage();
		logo_Medico_SlimUE = logo_Medico_SlimUE.getScaledInstance(lbl_Logo_Medico_Slim.getWidth(), lbl_Logo_Medico_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
		lbl_Logo_Medico_Slim.setIcon(new ImageIcon(logo_Medico_SlimUE));
		frmMedico.getContentPane().add(lbl_Logo_Medico_Slim);

		
		//TABLA PACIENTES:		
		tabla_Medico = new JTable();
		Vector<Paciente> data = controlador.datosInicialesTablaMedico();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(AnchoRelativo(248), AltoRelativo(288), AnchoRelativo(1424), AltoRelativo(550));
		scrollPane.setViewportView(tabla_Medico);
		crearModeloTabla(tabla_Medico, data);
		tabla_Medico.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla_Medico.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		tabla_Medico.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(24)));
		tabla_Medico.setRowHeight((scrollPane.getHeight())/10);
		tabla_Medico.setAutoCreateRowSorter(true);
		
		tabla_Medico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //A�ade la libreria
		tabla_Medico.addMouseListener((MouseListener) controlador);
		
		tabla_Medico.setVisible(true);
		frmMedico.getContentPane().add(scrollPane);
		
		//BOT�N A�ADIR PACIENTE:
		btn_Medico_Anadir_Paciente = new JButton("");
		btn_Medico_Anadir_Paciente.setBackground(Color.WHITE);
		btn_Medico_Anadir_Paciente.setOpaque(true);
		btn_Medico_Anadir_Paciente.setBorder(new LineBorder(new Color(153, 0, 0), 2, true));
		btn_Medico_Anadir_Paciente.setBounds(AnchoRelativo(82), AltoRelativo(245), AnchoRelativo(85), AltoRelativo(85));
		Image icono_anadir_paciente = new ImageIcon("." + File.separator +"img"+ File.separator +"anadir_paciente.png").getImage();
		icono_anadir_paciente = icono_anadir_paciente.getScaledInstance( btn_Medico_Anadir_Paciente.getWidth(), btn_Medico_Anadir_Paciente.getHeight(), Image.SCALE_SMOOTH );
		btn_Medico_Anadir_Paciente.setIcon(new ImageIcon(icono_anadir_paciente));
		frmMedico.getContentPane().add(btn_Medico_Anadir_Paciente);
		btn_Medico_Anadir_Paciente.addActionListener(controlador);
		
		// BOT�N Cerrar_Sesion:
		btn_Medico_Cerrar_Sesion = new JButton("");
		btn_Medico_Cerrar_Sesion.addActionListener(controlador);
		btn_Medico_Cerrar_Sesion.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
		btn_Medico_Cerrar_Sesion.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		btn_Medico_Cerrar_Sesion.setForeground(new Color(153, 0, 0));
		btn_Medico_Cerrar_Sesion.setBackground(new Color(192, 192, 192));
		Image icono_apagar = new ImageIcon("."+File.separator+"img"+File.separator+"apagar.png").getImage();
		icono_apagar = icono_apagar.getScaledInstance(btn_Medico_Cerrar_Sesion.getWidth(), btn_Medico_Cerrar_Sesion.getHeight(), Image.SCALE_SMOOTH);
		btn_Medico_Cerrar_Sesion.setIcon(new ImageIcon(icono_apagar));
		frmMedico.getContentPane().add(btn_Medico_Cerrar_Sesion);
		
		//TEXTFIELD BUSCAR:
		txt_Medico_Buscar = new JTextField();
		txt_Medico_Buscar.setBounds(AnchoRelativo(1257), AltoRelativo(197), AnchoRelativo(415), AltoRelativo(56));
		txt_Medico_Buscar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		txt_Medico_Buscar.setColumns(10);
		txt_Medico_Buscar.addKeyListener(controlador);
		frmMedico.getContentPane().add(txt_Medico_Buscar);
		
		//BOTON BUSCAR:
		btn_Medico_Buscar = new JButton("");
		btn_Medico_Buscar.addActionListener(controlador);
		btn_Medico_Buscar.setBounds(AnchoRelativo(1173), AltoRelativo(197), AnchoRelativo(60), AltoRelativo(56));
		Image icono_buscar = new ImageIcon("."+File.separator+"img"+File.separator+"buscar.png").getImage();
		icono_buscar = icono_buscar.getScaledInstance(btn_Medico_Buscar.getWidth(), btn_Medico_Buscar.getHeight(), Image.SCALE_SMOOTH);
		btn_Medico_Buscar.setIcon(new ImageIcon(icono_buscar));
		btn_Medico_Buscar.setBackground(Color.WHITE);
		btn_Medico_Buscar.setBorderPainted(false);
		frmMedico.getContentPane().add(btn_Medico_Buscar);
		
		//BOTON EDITAR DATOS DEL MEDICO:
		btn_Medico_Editar = new JButton("");
	    btn_Medico_Editar.setBackground(Color.WHITE);
	    btn_Medico_Editar.setBorder(new LineBorder(new Color(139, 0, 0)));
	    btn_Medico_Editar.setBounds(AnchoRelativo(15), AltoRelativo(881), AnchoRelativo(94), AltoRelativo(94));
	    Image icono_editar = new ImageIcon(("."+File.separator+"img"+File.separator+"editar.png")).getImage();
	    icono_editar = icono_editar.getScaledInstance(btn_Medico_Editar.getWidth(), btn_Medico_Editar.getHeight(), Image.SCALE_SMOOTH);
	    btn_Medico_Editar.setIcon(new ImageIcon(icono_editar));
	    frmMedico.getContentPane().add(btn_Medico_Editar);
	    btn_Medico_Editar.addActionListener(controlador);
		
	    //BOTON CAMBIAR PASSWORD
	    btn_CambiarPass = new JButton("");
	    btn_CambiarPass.setBorder(new LineBorder(new Color(139, 0, 0)));
	    btn_CambiarPass.setBackground(Color.WHITE);
	    btn_CambiarPass.setBounds(AnchoRelativo(15), AltoRelativo(774), AnchoRelativo(94), AltoRelativo(94));
	    Image icono_CambioPass = new ImageIcon(("."+File.separator+"img"+File.separator+"pass.png")).getImage();
	    icono_CambioPass = icono_CambioPass.getScaledInstance(btn_CambiarPass.getWidth(), btn_CambiarPass.getHeight(), Image.SCALE_SMOOTH);
	    btn_CambiarPass.setIcon(new ImageIcon(icono_CambioPass));
	    frmMedico.getContentPane().add(btn_CambiarPass);
	    btn_CambiarPass.addActionListener(controlador);
	    
	    //CHECKBOX ACTIVOS
        chckbx_PacActivos = new JCheckBox("Pacientes activos");
        chckbx_PacActivos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_PacActivos.setBounds(AnchoRelativo(248), AltoRelativo(880), AnchoRelativo(174), AltoRelativo(25));
        chckbx_PacActivos.addActionListener(controlador);
        chckbx_PacActivos.setSelected(true);
        chckbx_PacActivos.setEnabled(true);
        frmMedico.getContentPane().add(chckbx_PacActivos);

        //CHECKBOX INACTIVOS
        chckbx_PacInactivos = new JCheckBox("Pacientes inactivos");
        chckbx_PacInactivos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_PacInactivos.setBounds(AnchoRelativo(451), AltoRelativo(883), AnchoRelativo(183), AltoRelativo(25));
        chckbx_PacInactivos.addActionListener(controlador);
        chckbx_PacInactivos.setEnabled(false);
        chckbx_PacInactivos.setSelected(false);
        frmMedico.getContentPane().add(chckbx_PacInactivos);

        //CHECKBOX TODOS
        chckbx_ListadoPacientes = new JCheckBox("Listado Pacientes");
        chckbx_ListadoPacientes.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_ListadoPacientes.setBounds(AnchoRelativo(674), AltoRelativo(883), AnchoRelativo(174), AltoRelativo(25));
        chckbx_ListadoPacientes.addActionListener(controlador);
        chckbx_ListadoPacientes.setSelected(false);
        chckbx_ListadoPacientes.setEnabled(true);
        frmMedico.getContentPane().add(chckbx_ListadoPacientes);

		frmMedico.setVisible(true);		//Se hace visible la ventana
	}
	
	public TableModel crearModeloTabla(JTable tabla_Medico, Vector<Paciente> datos) throws Exception{
		tabla_Medico.removeAll();
		
		String[] nombre_Columnas_Medico= {"DNI","Nombre","Apellido"};
	      
		TableModel modelo_Tabla_Medico = new DefaultTableModel(crearDatostabla(datos), nombre_Columnas_Medico) {
			public boolean isCellEditable(int row, int column) {return false;}};
		tabla_Medico.setModel(modelo_Tabla_Medico);
		tabla_Medico.repaint();
		
		return modelo_Tabla_Medico;
	}
	
	public Object[][] crearDatostabla(Vector<Paciente> datos){
		Object[][] md = new Object[datos.size()][3];
	      
		for (int i = 0; i < datos.size(); i++){
 	    	  md[i][0] =  datos.get(i).getDNI();
 	    	  md[i][1] = datos.get(i).getNombre();
 	    	  md[i][2] = datos.get(i).getApellido();
 	    	  
		}
		return md;
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
