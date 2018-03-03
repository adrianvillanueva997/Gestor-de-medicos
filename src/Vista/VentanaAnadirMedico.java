package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Control.*;


public class VentanaAnadirMedico {

	ControladorAnadirMedico controlador;
	public JFrame frmAMed;
	public JTextField txt_AM_Nombre;
	public JTextField txt_AM_Apellidos;
	public JTextField txt_AM_Dni;
	public JTextField txt_AM_Email;
	public JTextField txt_AM_Clinica;
	public JButton btn_AM_Cancelar;
	public JButton btn_AM_Aceptar;
	
	public void addController(ControladorAnadirMedico mc){
		controlador = mc;
	}
	
	public void crearVentana(){
		frmAMed = new JFrame();
		frmAMed.setTitle("Añadir medico");
		frmAMed.getContentPane().setForeground(Color.WHITE);
		frmAMed.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img" + File.separator + "apus_logo.jpg"));
		frmAMed.setBounds(100, 100, 732, 373);
		frmAMed.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAMed.getContentPane().setLayout(null);
		
		JLabel lbl_AM_Nombre = new JLabel("Nombre:");
		lbl_AM_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_AM_Nombre.setBounds(12, 13, 87, 29);
		frmAMed.getContentPane().add(lbl_AM_Nombre);
		
		txt_AM_Nombre = new JTextField();
		txt_AM_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_AM_Nombre.setBounds(96, 13, 183, 29);
		frmAMed.getContentPane().add(txt_AM_Nombre);
		txt_AM_Nombre.setColumns(10);
		
		JLabel lbl_AM_Apellidos = new JLabel("Apellidos:");
		lbl_AM_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_AM_Apellidos.setBounds(319, 13, 97, 29);
		frmAMed.getContentPane().add(lbl_AM_Apellidos);
		
		txt_AM_Apellidos = new JTextField();
		txt_AM_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_AM_Apellidos.setBounds(407, 13, 286, 29);
		frmAMed.getContentPane().add(txt_AM_Apellidos);
		txt_AM_Apellidos.setColumns(10);
		
		JLabel lbl_AM_Dni = new JLabel("DNI:");
		lbl_AM_Dni.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_AM_Dni.setBounds(12, 111, 56, 26);
		frmAMed.getContentPane().add(lbl_AM_Dni);
		
		txt_AM_Dni = new JTextField();
		txt_AM_Dni.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_AM_Dni.setBounds(96, 108, 183, 29);
		frmAMed.getContentPane().add(txt_AM_Dni);
		txt_AM_Dni.setColumns(10);
		
		JLabel lbl_AM_Email = new JLabel("Email:");
		lbl_AM_Email.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_AM_Email.setBounds(319, 114, 135, 23);
		frmAMed.getContentPane().add(lbl_AM_Email);
		
		txt_AM_Email = new JTextField("pruebasslimue@gmail.com");
		txt_AM_Email.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_AM_Email.setBounds(452, 111, 241, 29);
		frmAMed.getContentPane().add(txt_AM_Email);
		txt_AM_Email.setColumns(10);
		
		JLabel lbl_AM_Clinica = new JLabel("Clinica:");
		lbl_AM_Clinica.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_AM_Clinica.setBounds(12, 201, 87, 26);
		frmAMed.getContentPane().add(lbl_AM_Clinica);
		
		txt_AM_Clinica = new JTextField();
		txt_AM_Clinica.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_AM_Clinica.setBounds(96, 198, 597, 29);
		frmAMed.getContentPane().add(txt_AM_Clinica);
		txt_AM_Clinica.setColumns(10);
		
		btn_AM_Aceptar = new JButton("Aceptar");
		btn_AM_Aceptar.setForeground(Color.WHITE);
		btn_AM_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));
		btn_AM_Aceptar.setOpaque(true);
		btn_AM_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_AM_Aceptar.setBounds(578, 273, 115, 38);
		frmAMed.getContentPane().add(btn_AM_Aceptar);
		btn_AM_Aceptar.addActionListener(controlador);
		
		btn_AM_Cancelar = new JButton("Cancelar");
		btn_AM_Cancelar.setForeground(Color.WHITE);
		btn_AM_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));
		btn_AM_Cancelar.setOpaque(true);
		btn_AM_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_AM_Cancelar.setBounds(432, 273, 115, 38);
		frmAMed.getContentPane().add(btn_AM_Cancelar);
		btn_AM_Cancelar.addActionListener(controlador);
		
		frmAMed.setVisible(true);
	}
}
