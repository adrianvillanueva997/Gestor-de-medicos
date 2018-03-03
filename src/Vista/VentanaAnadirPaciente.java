package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import Control.*;

public class VentanaAnadirPaciente{
	ControladorAnadirPaciente controlador;
	
	public JFrame frmAnadirPaciente;
	public JLabel lbl_AP_Nombre;
	public JTextField txt_AP_Nombre;
	public JLabel lbl_AP_Apellidos;
	public JTextField txt_AP_Apellidos;
	public JLabel lbl_AP_Dni;
	public JTextField txt_AP_Dni;
	public JLabel lbl_AP_Direccion;
	public JTextField txt_AP_Direccion;
	public JLabel lbl_AP_CP;
	public JTextField txt_AP_CP;
	public JLabel lbl_AP_Localidad;
	public JTextField txt_AP_Localidad;
	public JLabel lbl_AP_Provincia;
	public JTextField txt_AP_Provincia;
	public JLabel lbl_AP_Email;
	public JTextField txt_AP_Email;
	public JLabel lbl_AP_Telefono;
	public JTextField txt_AP_Telefono;	
	public JLabel lbl_AP_Nacimiento;
	public JLabel lbl_AP_Inicio;
	public JLabel lbl_AP_Sexo;
	public JComboBox cb_AP_Sexo;
	public JLabel lbl_AP_Altura;
	public JComboBox cb_AP_Altura;
	public JComboBox cb_AP_Cent;
	public JButton btn_AP_Aceptar;
	public JButton btn_AP_Cancelar;
	public JTextField txt_AP_BuscarImagen;
	public JButton btn_AP_BuscarImagen;
	public JDateChooser dateChooser_AP_Nacimiento;
	public JDateChooser dateChooser_AP_Inicio;
	
	
	public void addController(ControladorAnadirPaciente mc){
		controlador = mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(){
		frmAnadirPaciente = new JFrame();
		frmAnadirPaciente.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmAnadirPaciente.getContentPane().setBackground(Color.WHITE);
		frmAnadirPaciente.setTitle("Añadir Paciente");
		frmAnadirPaciente.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(973), AltoRelativo(600));
		frmAnadirPaciente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAnadirPaciente.getContentPane().setLayout(null);
		frmAnadirPaciente.setResizable(false);
		
		lbl_AP_Nombre = new JLabel("Nombre:");
		lbl_AP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Nombre.setBounds(AnchoRelativo(43), AltoRelativo(41), AnchoRelativo(86), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Nombre);
		
		txt_AP_Nombre = new JTextField();
		txt_AP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Nombre.setBounds(AnchoRelativo(141), AltoRelativo(38), AnchoRelativo(183), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Nombre);
		txt_AP_Nombre.setColumns(10);
		
		lbl_AP_Apellidos = new JLabel("Apellidos:");
		lbl_AP_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Apellidos.setBounds(AnchoRelativo(336), AltoRelativo(41), AnchoRelativo(86), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Apellidos);
		
		txt_AP_Apellidos = new JTextField();
		txt_AP_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Apellidos.setColumns(10);
		txt_AP_Apellidos.setBounds(AnchoRelativo(434), AltoRelativo(38), AnchoRelativo(282), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Apellidos);
		
		lbl_AP_Dni = new JLabel("DNI:");
		lbl_AP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Dni.setBounds(AnchoRelativo(728), AltoRelativo(41), AnchoRelativo(47), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Dni);
		
		txt_AP_Dni = new JTextField();
		txt_AP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Dni.setColumns(10);
		txt_AP_Dni.setBounds(AnchoRelativo(787), AltoRelativo(38), AnchoRelativo(138), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Dni);
		
		lbl_AP_Direccion = new JLabel("Dirección:");
		lbl_AP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Direccion.setBounds(AnchoRelativo(43), AltoRelativo(114), AnchoRelativo(86),AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Direccion);
		
		txt_AP_Direccion = new JTextField();
		txt_AP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Direccion.setColumns(10);
		txt_AP_Direccion.setBounds(AnchoRelativo(141), AltoRelativo(111), AnchoRelativo(784), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Direccion);
		
		lbl_AP_CP = new JLabel("CP:");
		lbl_AP_CP.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_CP.setBounds(AnchoRelativo(43), AltoRelativo(182), AnchoRelativo(47), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_CP);
		
		txt_AP_CP = new JTextField();
		txt_AP_CP.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_CP.setColumns(10);
		txt_AP_CP.setBounds(AnchoRelativo(141), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_CP);
		
		lbl_AP_Provincia = new JLabel("Provincia:");
		lbl_AP_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Provincia.setBounds(AnchoRelativo(336), AltoRelativo(182), AnchoRelativo(97), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Provincia);
		
		txt_AP_Provincia = new JTextField();
		txt_AP_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Provincia.setColumns(10);
		txt_AP_Provincia.setBounds(AnchoRelativo(434), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Provincia);
		
		lbl_AP_Localidad = new JLabel("Localidad:");
		lbl_AP_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Localidad.setBounds(AnchoRelativo(689), AltoRelativo(182), AnchoRelativo(86), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Localidad);
		
		txt_AP_Localidad = new JTextField();
		txt_AP_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Localidad.setColumns(10);
		txt_AP_Localidad.setBounds(AnchoRelativo(787), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Localidad);
		
		lbl_AP_Email = new JLabel("Email: ");
		lbl_AP_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Email.setBounds(AnchoRelativo(43), AltoRelativo(249), AnchoRelativo(86), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Email);
		
		txt_AP_Email = new JTextField("pruebasslimue@gmail.com");
		txt_AP_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Email.setColumns(10);
		txt_AP_Email.setBounds(AnchoRelativo(141), AltoRelativo(246), AnchoRelativo(282), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Email);
		
		lbl_AP_Telefono = new JLabel("Teléfono:");
		lbl_AP_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Telefono.setBounds(AnchoRelativo(545), AltoRelativo(249), AnchoRelativo(86), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Telefono);
		
		txt_AP_Telefono = new JTextField();
		txt_AP_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Telefono.setColumns(10);
		txt_AP_Telefono.setBounds(AnchoRelativo(643), AltoRelativo(246), AnchoRelativo(282), AltoRelativo(29));
		frmAnadirPaciente.getContentPane().add(txt_AP_Telefono);
		
		lbl_AP_Nacimiento = new JLabel("Fecha de nacimiento:");
		lbl_AP_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));;
		lbl_AP_Nacimiento.setBounds(AnchoRelativo(43), AltoRelativo(330), AnchoRelativo(183), AltoRelativo(16));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Nacimiento);

		
		dateChooser_AP_Inicio = new JDateChooser();
		dateChooser_AP_Inicio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		dateChooser_AP_Inicio.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_AP_Inicio.setBounds(AnchoRelativo(238), AltoRelativo(416), AnchoRelativo(168), AltoRelativo(22));
		//dateChooser_AP_Inicio.setMaxSelectableDate();
		frmAnadirPaciente.getContentPane().add(dateChooser_AP_Inicio);

		
		dateChooser_AP_Nacimiento = new JDateChooser();
		dateChooser_AP_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		dateChooser_AP_Nacimiento.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_AP_Nacimiento.setBounds(AnchoRelativo(238), AltoRelativo(331), AnchoRelativo(168), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(dateChooser_AP_Nacimiento);
		
		
		lbl_AP_Inicio = new JLabel("Fecha de inicio:");
		lbl_AP_Inicio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Inicio.setBounds(AnchoRelativo(43), AltoRelativo(416), AnchoRelativo(183), AltoRelativo(16));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Inicio);
		
		lbl_AP_Sexo = new JLabel("Sexo:");
		lbl_AP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Sexo.setBounds(AnchoRelativo(545), AltoRelativo(336), AnchoRelativo(56), AltoRelativo(16));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Sexo);
		
		cb_AP_Sexo = new JComboBox();
		cb_AP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Sexo.setModel(new DefaultComboBoxModel(new String[] {"M", "H"}));
		cb_AP_Sexo.setBounds(AnchoRelativo(650), AltoRelativo(330), AnchoRelativo(56), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(cb_AP_Sexo);
		
		lbl_AP_Altura = new JLabel("Altura:");
		lbl_AP_Altura.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Altura.setBounds(AnchoRelativo(545), AltoRelativo(416), AnchoRelativo(86), AltoRelativo(16));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Altura);
		
		cb_AP_Altura = new JComboBox();
		cb_AP_Altura.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Altura.setBounds(AnchoRelativo(650), AltoRelativo(410), AnchoRelativo(65), AltoRelativo(22));
		int i = 0;
		while(i < 3){
			String s = String.valueOf(i);
			cb_AP_Altura.addItem(s);
			i ++;
		}
		frmAnadirPaciente.getContentPane().add(cb_AP_Altura);
		
		cb_AP_Cent = new JComboBox();
		cb_AP_Cent.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Cent.setBounds(AnchoRelativo(720), AltoRelativo(410), AnchoRelativo(65), AltoRelativo(22));
		int j = 0;
		while(j < 100){
			String s = "." + String.valueOf(j);
			cb_AP_Cent.addItem(s);
			j += 5;
		}
		frmAnadirPaciente.getContentPane().add(cb_AP_Cent);
		
			
		btn_AP_Aceptar = new JButton("Aceptar");
		btn_AP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Aceptar.setForeground(new Color(255, 255, 255));
		btn_AP_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_AP_Aceptar.setOpaque(true);
		btn_AP_Aceptar.setBorderPainted(false);
		btn_AP_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_AP_Aceptar.setBounds(AnchoRelativo(798), AltoRelativo(500), AnchoRelativo(127), AltoRelativo(33));
		frmAnadirPaciente.getContentPane().add(btn_AP_Aceptar);
		btn_AP_Aceptar.addActionListener(controlador);
		
		btn_AP_Cancelar = new JButton("Cancelar");
		btn_AP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_AP_Cancelar.setOpaque(true);
		btn_AP_Cancelar.setBorderPainted(false);
		btn_AP_Cancelar.setForeground(new Color(255, 255, 255));
		btn_AP_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_AP_Cancelar.setBounds(AnchoRelativo(643), AltoRelativo(500), AnchoRelativo(132), AltoRelativo(32));
		frmAnadirPaciente.getContentPane().add(btn_AP_Cancelar);
		btn_AP_Cancelar.addActionListener(controlador);

		
		frmAnadirPaciente.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"apus_logo.jpg"))); 
		frmAnadirPaciente.setVisible(true);
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
