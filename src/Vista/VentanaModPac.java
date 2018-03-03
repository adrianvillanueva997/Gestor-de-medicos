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
import Modelo.*;

public class VentanaModPac {
	ControladorModPac controlador;
	
	public JFrame frmModPac;
	public JLabel lbl_MP_Nombre;
	public JTextField txt_MP_Nombre;
	public JLabel lbl_MP_Apellidos;
	public JTextField txt_MP_Apellidos;
	public JLabel lbl_MP_Dni;
	public JTextField txt_MP_Dni;
	public JLabel lbl_MP_Direccion;
	public JTextField txt_MP_Direccion;
	public JLabel lbl_MP_CP;
	public JTextField txt_MP_CP;
	public JLabel lbl_MP_Localidad;
	public JTextField txt_MP_Localidad;
	public JLabel lbl_MP_Provincia;
	public JTextField txt_MP_Provincia;
	public JLabel lbl_MP_Email;
	public JTextField txt_MP_Email;
	public JLabel lbl_MP_Telefono;
	public JTextField txt_MP_Telefono;	
	public JLabel lbl_MP_Nacimiento;
	public JLabel lbl_MP_Inicio;
	public JLabel lbl_MP_Sexo;
	public JComboBox cb_AP_Sexo;
	public JLabel lbl_MP_Altura;
	public JComboBox cb_AP_Altura;
	public JComboBox cb_AP_Cent;
	public JButton btn_MP_Aceptar;
	public JButton btn_MP_Cancelar;
	public JDateChooser dateChooser_MP_Inicio;
	public JDateChooser dateChooser_MP_Nacimiento;
	
	public void addController(ControladorModPac mc){
		controlador = mc;
	}
	
	public void crearVentana(Paciente paciente){
		
		Fichero f = new Fichero();
		Paciente pac = f.busquedaPaciente(paciente);
		
		frmModPac = new JFrame();
		frmModPac.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmModPac.getContentPane().setBackground(Color.WHITE);
		frmModPac.setTitle("Modificar datos");
		//frmModPac.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(973), AltoRelativo(763));
		frmModPac.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(973), AltoRelativo(500));
		frmModPac.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmModPac.getContentPane().setLayout(null);
		frmModPac.setResizable(false);
		
		lbl_MP_Nombre = new JLabel("Nombre:");
		lbl_MP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Nombre.setBounds(AnchoRelativo(43), AltoRelativo(41), AnchoRelativo(86), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Nombre);
		
		txt_MP_Nombre = new JTextField(pac.getNombre());
		txt_MP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Nombre.setBounds(AnchoRelativo(141), AltoRelativo(38), AnchoRelativo(183), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Nombre);
		txt_MP_Nombre.setColumns(10);
		
		lbl_MP_Apellidos = new JLabel("Apellidos:");
		lbl_MP_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Apellidos.setBounds(AnchoRelativo(336), AltoRelativo(41), AnchoRelativo(86), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Apellidos);
		
		txt_MP_Apellidos = new JTextField(pac.getApellido());
		txt_MP_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Apellidos.setColumns(10);
		txt_MP_Apellidos.setBounds(AnchoRelativo(434), AltoRelativo(38), AnchoRelativo(282), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Apellidos);
		
		lbl_MP_Dni = new JLabel("DNI:");
		lbl_MP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Dni.setBounds(AnchoRelativo(728), AltoRelativo(41), AnchoRelativo(47), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Dni);
		
		txt_MP_Dni = new JTextField(pac.getDNI());
		txt_MP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Dni.setColumns(10);
		txt_MP_Dni.setBounds(AnchoRelativo(787), AltoRelativo(38), AnchoRelativo(138), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Dni);
		
		lbl_MP_Direccion = new JLabel("Dirección:");
		lbl_MP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Direccion.setBounds(AnchoRelativo(43), AltoRelativo(114), AnchoRelativo(86),AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Direccion);
		
		txt_MP_Direccion = new JTextField(pac.getCalle());
		txt_MP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Direccion.setColumns(10);
		txt_MP_Direccion.setBounds(AnchoRelativo(141), AltoRelativo(111), AnchoRelativo(784), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Direccion);
		
		lbl_MP_CP = new JLabel("CP:");
		lbl_MP_CP.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_CP.setBounds(AnchoRelativo(43), AltoRelativo(182), AnchoRelativo(47), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_CP);
		
		txt_MP_CP = new JTextField(pac.getCP());
		txt_MP_CP.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_CP.setColumns(10);
		txt_MP_CP.setBounds(AnchoRelativo(141), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_CP);
		
		lbl_MP_Provincia = new JLabel("Provincia:");
		lbl_MP_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Provincia.setBounds(AnchoRelativo(336), AltoRelativo(182), AnchoRelativo(97), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Provincia);
		
		txt_MP_Provincia = new JTextField(pac.getProvincia());
		txt_MP_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Provincia.setColumns(10);
		txt_MP_Provincia.setBounds(AnchoRelativo(434), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Provincia);
		
		lbl_MP_Localidad = new JLabel("Localidad:");
		lbl_MP_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Localidad.setBounds(AnchoRelativo(689), AltoRelativo(182), AnchoRelativo(86), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Localidad);
		
		txt_MP_Localidad = new JTextField(pac.getLocalidad());
		txt_MP_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Localidad.setColumns(10);
		txt_MP_Localidad.setBounds(AnchoRelativo(787), AltoRelativo(179), AnchoRelativo(138), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Localidad);
		
		lbl_MP_Email = new JLabel("Email:");
		lbl_MP_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Email.setBounds(AnchoRelativo(43), AltoRelativo(249), AnchoRelativo(86), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Email);
		
		txt_MP_Email = new JTextField(pac.getEmail());
		txt_MP_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Email.setColumns(10);
		txt_MP_Email.setBounds(AnchoRelativo(141), AltoRelativo(246), AnchoRelativo(282), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Email);
		
		lbl_MP_Telefono = new JLabel("Teléfono:");
		lbl_MP_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Telefono.setBounds(AnchoRelativo(545), AltoRelativo(249), AnchoRelativo(86), AltoRelativo(22));
		frmModPac.getContentPane().add(lbl_MP_Telefono);
		
		txt_MP_Telefono = new JTextField(pac.getTelefono());
		txt_MP_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_Telefono.setColumns(10);
		txt_MP_Telefono.setBounds(AnchoRelativo(643), AltoRelativo(246), AnchoRelativo(282), AltoRelativo(29));
		frmModPac.getContentPane().add(txt_MP_Telefono);
		
		lbl_MP_Sexo = new JLabel("Sexo:");
		lbl_MP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Sexo.setBounds(AnchoRelativo(738), AltoRelativo(336), AnchoRelativo(56), AltoRelativo(16));
		frmModPac.getContentPane().add(lbl_MP_Sexo);
		
		cb_AP_Sexo = new JComboBox();
		cb_AP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Sexo.setModel(new DefaultComboBoxModel(new String[] {"M", "H"}));
		cb_AP_Sexo.setBounds(AnchoRelativo(808), AltoRelativo(330), AnchoRelativo(56), AltoRelativo(22));
		frmModPac.getContentPane().add(cb_AP_Sexo);
		
		lbl_MP_Altura = new JLabel("Altura:");
		lbl_MP_Altura.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_MP_Altura.setBounds(AnchoRelativo(43), AltoRelativo(330), AnchoRelativo(86), AltoRelativo(16));
		frmModPac.getContentPane().add(lbl_MP_Altura);
		
		cb_AP_Altura = new JComboBox();
		cb_AP_Altura.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Altura.setBounds(AnchoRelativo(141), AltoRelativo(331), AnchoRelativo(65), AltoRelativo(22));
		int i = 0;
		while(i < 3){
			String s = String.valueOf(i);
			cb_AP_Altura.addItem(s);
			i ++;
		}
		frmModPac.getContentPane().add(cb_AP_Altura);
		
		cb_AP_Cent = new JComboBox();
		cb_AP_Cent.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cb_AP_Cent.setBounds(AnchoRelativo(210), AltoRelativo(331), AnchoRelativo(65), AltoRelativo(22));
		int j = 0;
		while(j < 100){
			String s = "." + String.valueOf(j);
			cb_AP_Cent.addItem(s);
			j += 5;
		}
		frmModPac.getContentPane().add(cb_AP_Cent);
		
		/*btn_MP_Aceptar = new JButton("Aceptar");
		btn_MP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_MP_Aceptar.setForeground(new Color(255, 255, 255));
		btn_MP_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_MP_Aceptar.setOpaque(true);
		btn_MP_Aceptar.setBorderPainted(false);
		btn_MP_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_MP_Aceptar.setBounds(AnchoRelativo(798), AltoRelativo(670), AnchoRelativo(127), AltoRelativo(33));
		frmModPac.getContentPane().add(btn_MP_Aceptar);
		btn_MP_Aceptar.addActionListener(controlador);
		
		btn_MP_Cancelar = new JButton("Cancelar");
		btn_MP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_MP_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_MP_Cancelar.setOpaque(true);
		btn_MP_Cancelar.setBorderPainted(false);
		btn_MP_Cancelar.setForeground(new Color(255, 255, 255));
		btn_MP_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_MP_Cancelar.setBounds(AnchoRelativo(643), AltoRelativo(670), AnchoRelativo(132), AltoRelativo(32));
		frmModPac.getContentPane().add(btn_MP_Cancelar);
		btn_MP_Cancelar.addActionListener(controlador);*/
		
		btn_MP_Aceptar = new JButton("Aceptar");
		btn_MP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_MP_Aceptar.setForeground(new Color(255, 255, 255));
		btn_MP_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_MP_Aceptar.setOpaque(true);
		btn_MP_Aceptar.setBorderPainted(false);
		btn_MP_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_MP_Aceptar.setBounds(AnchoRelativo(798), AltoRelativo(400), AnchoRelativo(127), AltoRelativo(33));
		frmModPac.getContentPane().add(btn_MP_Aceptar);
		btn_MP_Aceptar.addActionListener(controlador);
		
		btn_MP_Cancelar = new JButton("Cancelar");
		btn_MP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_MP_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_MP_Cancelar.setOpaque(true);
		btn_MP_Cancelar.setBorderPainted(false);
		btn_MP_Cancelar.setForeground(new Color(255, 255, 255));
		btn_MP_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_MP_Cancelar.setBounds(AnchoRelativo(643), AltoRelativo(400), AnchoRelativo(132), AltoRelativo(32));
		frmModPac.getContentPane().add(btn_MP_Cancelar);
		btn_MP_Cancelar.addActionListener(controlador);
		
		/*txt_MP_BuscarImagen = new JTextField();
		txt_MP_BuscarImagen.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MP_BuscarImagen.setBounds(AnchoRelativo(43), AltoRelativo(629), AnchoRelativo(390), AltoRelativo(31));
		frmModPac.getContentPane().add(txt_MP_BuscarImagen);
		txt_MP_BuscarImagen.setColumns(10);
		
		btn_MP_BuscarImagen = new JButton("Buscar imagen...");
		btn_MP_BuscarImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_MP_BuscarImagen.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));	//Establece el color del bot�n
		btn_MP_BuscarImagen.setOpaque(true);
		btn_MP_BuscarImagen.setBorderPainted(false);
		btn_MP_BuscarImagen.setForeground(new Color(255, 255, 255));
		btn_MP_BuscarImagen.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		btn_MP_BuscarImagen.setBounds(AnchoRelativo(448), AltoRelativo(628), AnchoRelativo(183), AltoRelativo(33));
		frmModPac.getContentPane().add(btn_MP_BuscarImagen);
		btn_MP_BuscarImagen.addActionListener(controlador);*/
		
		frmModPac.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"apus_logo.jpg"))); 
		frmModPac.setVisible(true);
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