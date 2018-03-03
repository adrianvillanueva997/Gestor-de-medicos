package Vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;


import Control.*;
import Modelo.*;

public class VentanaModMed extends JFrame{
	ControladorModMed controlador;
	public JFrame frmAMed;
	public JTextField txt_MM_Nombre;
	public JTextField txt_MM_Apellidos;
	public JTextField txt_MM_Dni;
	public JTextField txt_MM_Email;
	public JTextField txt_MM_Clinica;
	public JButton btn_MM_Cancelar;
	public JButton btn_MM_Aceptar;
	
	public void addController(ControladorModMed mc){
		controlador = mc;
	}
	
	public void crearVentana(Medico medico){
		
		Fichero f = new Fichero();
		Medico med = f.busquedaMedico(medico);
		
		frmAMed = new JFrame();
		frmAMed.setTitle("Modificar datos medico");
		frmAMed.getContentPane().setForeground(Color.WHITE);
		frmAMed.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img" + File.separator + "apus_logo.jpg"));
		frmAMed.setBounds(100, 100, 732, 373);
		frmAMed.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAMed.getContentPane().setLayout(null);
		
		JLabel lbl_MM_Nombre = new JLabel("Nombre:");
		lbl_MM_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_MM_Nombre.setBounds(12, 13, 87, 29);
		frmAMed.getContentPane().add(lbl_MM_Nombre);
		
		txt_MM_Nombre = new JTextField(med.getNombre());
		txt_MM_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_MM_Nombre.setBounds(96, 13, 183, 29);
		frmAMed.getContentPane().add(txt_MM_Nombre);
		txt_MM_Nombre.setColumns(10);
		
		JLabel lbl_MM_Apellidos = new JLabel("Apellidos:");
		lbl_MM_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_MM_Apellidos.setBounds(319, 13, 97, 29);
		frmAMed.getContentPane().add(lbl_MM_Apellidos);
		
		txt_MM_Apellidos = new JTextField(med.getApellido());
		txt_MM_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_MM_Apellidos.setBounds(407, 13, 286, 29);
		frmAMed.getContentPane().add(txt_MM_Apellidos);
		txt_MM_Apellidos.setColumns(10);
		
		JLabel lbl_MM_Dni = new JLabel("DNI:");
		lbl_MM_Dni.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_MM_Dni.setBounds(12, 111, 56, 26);
		frmAMed.getContentPane().add(lbl_MM_Dni);
		
		txt_MM_Dni = new JTextField(med.getDNI());
		txt_MM_Dni.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_MM_Dni.setBounds(96, 108, 183, 29);
		frmAMed.getContentPane().add(txt_MM_Dni);
		txt_MM_Dni.setColumns(10);
		
		JLabel lbl_MM_Email = new JLabel("Email:");
		lbl_MM_Email.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_MM_Email.setBounds(319, 114, 135, 23);
		frmAMed.getContentPane().add(lbl_MM_Email);

		String email = med.getEmail();
		txt_MM_Email = new JTextField(email);
		txt_MM_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_MM_Email.setBounds(452, 111, 241, 29);
		frmAMed.getContentPane().add(txt_MM_Email);
		txt_MM_Email.setColumns(10);
		
		JLabel lbl_MM_Clinica = new JLabel("Clinica:");
		lbl_MM_Clinica.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_MM_Clinica.setBounds(12, 201, 87, 26);
		frmAMed.getContentPane().add(lbl_MM_Clinica);
		
		txt_MM_Clinica = new JTextField(med.getClinica());
		txt_MM_Clinica.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt_MM_Clinica.setBounds(96, 198, 597, 29);
		frmAMed.getContentPane().add(txt_MM_Clinica);
		txt_MM_Clinica.setColumns(10);
		
		btn_MM_Aceptar = new JButton("Aceptar");
		btn_MM_Aceptar.setForeground(Color.WHITE);
		btn_MM_Aceptar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));
		btn_MM_Aceptar.setOpaque(true);
		btn_MM_Aceptar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_MM_Aceptar.setBounds(578, 273, 115, 38);
		frmAMed.getContentPane().add(btn_MM_Aceptar);
		btn_MM_Aceptar.addActionListener(controlador);
		
		btn_MM_Cancelar = new JButton("Cancelar");
		btn_MM_Cancelar.setForeground(Color.WHITE);
		btn_MM_Cancelar.setBackground(Color.getHSBColor(0.0f,1.0f,0.55f));
		btn_MM_Cancelar.setOpaque(true);
		btn_MM_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_MM_Cancelar.setBounds(432, 273, 115, 38);
		frmAMed.getContentPane().add(btn_MM_Cancelar);
		btn_MM_Cancelar.addActionListener(controlador);
		
		frmAMed.setVisible(true);
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
