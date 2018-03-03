package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Control.*;
import Modelo.*;

public class VentanaMedico2 extends JFrame{
	
	ControladorMedico2 controlador;
	
	public JFrame frmMedico2;
	
	public JLabel lbl_Medico_Info;
	public JLabel lbl_Paciente_Informacion;
	public JLabel lbl_Medico_Rectangulo_Superior;
	public JLabel lblNombre;
	public JLabel lbl_Medico_Nombre;
	public JLabel lbl_Medico_Apellidos;
	public JLabel lbl_Medico_Nacimiento ;
	public JLabel lbl_Medico_Direccion;
	public JLabel lbl_Medico_Telefono;
	public JLabel lbl_Medico_Email;
	public JLabel lbl_Medico_Sexo;
	public JLabel lbl_Medico_Peso;
 	public JLabel lbl_Medico_Altura;
	public JLabel lbl_Medico_Imc;
	public JLabel lbl_Medico_NombreyApellidos;
	public JLabel lbl_Medico_Historial;
	public JLabel lbl_Logo_Medico_Slim;
	public JLabel lbl_Medico_Inicio;
	public JLabel lbl_Medico_Cp;
	public JLabel lbl_Medico_Localidad;
	public JLabel lbl_Medico_Provincia;
	public JLabel lbl_Medico_Dni;
	public JButton btn_Medico_HistorialSesiones;
	public JButton btn_Medico_EnviarEmail;
	public JButton btn_Medico_Atras;
	public JButton btn_Medico_DarAlta;
	public JButton btn_Medico_AddPaciente;
	public JLabel lbl_SesionesRealizadas;
	public JLabel lbl_UltimoPeso;
	
	
	//Funci�n para controlar la ventana
	public void addController(ControladorMedico2 mc){
		controlador=mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(Medico usuarioMedico, Paciente usuarioPaciente) throws IOException{
		
		/*
		 * ultimo peso registrado
		 * nº sesiones realizadas
		 * 
		 * 
		 * */

		Medico medico = Fichero.busquedaMedico(usuarioMedico);
		
		//FRAME DE LA VENTANA Medico
		frmMedico2 = new JFrame();
		frmMedico2.getContentPane().setBackground(new Color(255, 255, 255));
		frmMedico2.setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"img"+File.separator+"apus_logo.jpg"));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	//Busca la resoluci�n de la pantalla
		frmMedico2.setBounds(0, 0,screen.width,screen.height);	//Establece dimensiones de la ventana
		frmMedico2.setExtendedState(JFrame.MAXIMIZED_BOTH);	//Maximiza por defecto la ventana
		frmMedico2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Estabalece la operaci�n de cierre por defecto
		frmMedico2.getContentPane().setLayout(null);
		
		//LABEL LOGO SLIMUE:
		lbl_Logo_Medico_Slim = new JLabel("");
		lbl_Logo_Medico_Slim.setBounds(AnchoRelativo(15), AltoRelativo(3), AnchoRelativo(321), AltoRelativo(94));
		Image logo_Medico_SlimUE = new ImageIcon("."+File.separator+"img"+File.separator+"logo.png").getImage();
		logo_Medico_SlimUE = logo_Medico_SlimUE.getScaledInstance(lbl_Logo_Medico_Slim.getWidth(), lbl_Logo_Medico_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
		lbl_Logo_Medico_Slim.setIcon(new ImageIcon(logo_Medico_SlimUE));
		frmMedico2.getContentPane().add(lbl_Logo_Medico_Slim);
		
		//LABEL DE INFORMACI�N
		lbl_Paciente_Informacion = new JLabel("INFORMACIÓN");
		lbl_Paciente_Informacion.setForeground(new Color(139, 0, 0));
		lbl_Paciente_Informacion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(30)));
		lbl_Paciente_Informacion.setBounds(AnchoRelativo(404), AltoRelativo(113), AnchoRelativo(229), AltoRelativo(33));
		frmMedico2.getContentPane().add(lbl_Paciente_Informacion);
		
		//LABEL RECTÁNGULO SUPERIOR:
		lbl_Medico_Rectangulo_Superior = new JLabel("");
		lbl_Medico_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
		lbl_Medico_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
		lbl_Medico_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		lbl_Medico_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(100));
		frmMedico2.getContentPane().add(lbl_Medico_Rectangulo_Superior);
		
		//LABEL NOMBRE DEL MEDICO - RECTANGULO SUPERIOR
		lbl_Medico_NombreyApellidos = new JLabel(medico.getNombre() + " " + medico.getApellido());
		lbl_Medico_NombreyApellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_NombreyApellidos.setBounds(AnchoRelativo(1417), AltoRelativo(16), AnchoRelativo(371), AltoRelativo(33));
		lbl_Medico_NombreyApellidos.setHorizontalAlignment(JLabel.RIGHT);
		frmMedico2.getContentPane().add(lbl_Medico_NombreyApellidos);
					
		//LABEL HISTORIAL DEL MEDICO - RECTANGULO SUPERIOR
		lbl_Medico_Historial = new JLabel(medico.getClinica());
		lbl_Medico_Historial.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Historial.setBounds(AnchoRelativo(1417), AltoRelativo(52), AnchoRelativo(371), AltoRelativo(33));
		lbl_Medico_Historial.setHorizontalAlignment(JLabel.RIGHT);
		frmMedico2.getContentPane().add(lbl_Medico_Historial);
		
		//RECTANGULO QUE CONTIENE INFORMACION DE usuarioPaciente
		lbl_Medico_Info = new JLabel("");
		lbl_Medico_Info.setBorder(new LineBorder(new Color(139, 0, 0), 4, true));
		lbl_Medico_Info.setBounds(AnchoRelativo(253), AltoRelativo(147), AnchoRelativo(591), AltoRelativo(828));
		frmMedico2.getContentPane().add(lbl_Medico_Info);
		
		//LABEL NOMBRE
		lbl_Medico_Nombre = new JLabel("Nombre: " + usuarioPaciente.getNombre());
		lbl_Medico_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Nombre.setBounds(AnchoRelativo(338), AltoRelativo(202), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Nombre);
		
		//LABEL APELLIDOS
		lbl_Medico_Apellidos = new JLabel("Apellidos: " + usuarioPaciente.getApellido());
		lbl_Medico_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Apellidos.setBounds(AnchoRelativo(344), AltoRelativo(261), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Apellidos);
		
		//LABEL FECHA DE NACIMIENTO
		lbl_Medico_Nacimiento = new JLabel("Fecha de nacimiento: " + usuarioPaciente.getFechaNacimiento());
		lbl_Medico_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Nacimiento.setBounds(AnchoRelativo(344), AltoRelativo(363), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Nacimiento);
		
		//LABEL FECHA DE INICIO
		lbl_Medico_Inicio = new JLabel("Fecha de inicio: " + usuarioPaciente.getFechaInicio());
		lbl_Medico_Inicio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Inicio.setBounds(AnchoRelativo(344), AltoRelativo(425), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Inicio);
		
		//LABEL DNI
		lbl_Medico_Dni = new JLabel("DNI: " + usuarioPaciente.getDNI());
		lbl_Medico_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Dni.setBounds(AnchoRelativo(344), AltoRelativo(309), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Dni);
		
		//LABEL DIRECCION
		lbl_Medico_Direccion = new JLabel("Dirección: " + usuarioPaciente.getCalle());
		lbl_Medico_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Direccion.setBounds(AnchoRelativo(344), AltoRelativo(480), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Direccion);
			
		//LABEL CODIGO POSTAL
		lbl_Medico_Cp = new JLabel("CP: " + usuarioPaciente.getCP());
		lbl_Medico_Cp.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Cp.setBounds(AnchoRelativo(344), AltoRelativo(535), AnchoRelativo(170), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Cp);
		
		//LABEL LOCALIDAD
		lbl_Medico_Localidad = new JLabel("Localidad: " + usuarioPaciente.getLocalidad());
		lbl_Medico_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Localidad.setBounds(AnchoRelativo(344), AltoRelativo(657), AnchoRelativo(400), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Localidad);
		
		//LABEL PROVINCIA
		lbl_Medico_Provincia = new JLabel("Provincia: " + usuarioPaciente.getProvincia());
		lbl_Medico_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Provincia.setBounds(AnchoRelativo(344), AltoRelativo(593), AnchoRelativo(247), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Provincia);
		
		//LABEL TELEFONO
		lbl_Medico_Telefono = new JLabel("Teléfono: " + usuarioPaciente.getTelefono());
		lbl_Medico_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Telefono.setBounds(AnchoRelativo(344), AltoRelativo(717), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Telefono);
		
		//LABEL EMAIL
		lbl_Medico_Email = new JLabel("Email: " + usuarioPaciente.getEmail());
		lbl_Medico_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Email.setBounds(AnchoRelativo(344), AltoRelativo(766), AnchoRelativo(420), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Email);
		
		//LABEL SEXO
		lbl_Medico_Sexo = new JLabel("Sexo: " + usuarioPaciente.getSexo());
		lbl_Medico_Sexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Sexo.setBounds(AnchoRelativo(344), AltoRelativo(813), AnchoRelativo(247), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Sexo);
		
		//LABEL ALTURA
		lbl_Medico_Altura = new JLabel("Altura: " + usuarioPaciente.getAltura());
		lbl_Medico_Altura.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
		lbl_Medico_Altura.setBounds(AnchoRelativo(344), AltoRelativo(872), AnchoRelativo(161), AltoRelativo(23));
		frmMedico2.getContentPane().add(lbl_Medico_Altura);
		
		// BOTÓN CERRAR SESION
		btn_Medico_Atras = new JButton("");
		btn_Medico_Atras.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
		btn_Medico_Atras.setBorder(new LineBorder(new Color(153, 0, 0), 3));
		btn_Medico_Atras.setForeground(new Color(153, 0, 0));
		btn_Medico_Atras.setBackground(Color.WHITE);
		Image icono_atras = new ImageIcon("." + File.separator + "img" + File.separator + "atras.png").getImage();
		icono_atras = icono_atras.getScaledInstance(btn_Medico_Atras.getWidth(), btn_Medico_Atras.getHeight(), Image.SCALE_SMOOTH);
		btn_Medico_Atras.setIcon(new ImageIcon(icono_atras));
		frmMedico2.getContentPane().add(btn_Medico_Atras);
		btn_Medico_Atras.addActionListener(controlador);
		
		
		btn_Medico_HistorialSesiones = new JButton("");
		btn_Medico_DarAlta = new JButton("");
		btn_Medico_AddPaciente = new JButton("");
		
		if(usuarioPaciente.getActivo() == 1){
			//BOTÓN HISTORIAL DE SESIONES
			//btn_Medico_HistorialSesiones.setBounds(AnchoRelativo(1471), AltoRelativo(274), AnchoRelativo(223), AltoRelativo(223));
			btn_Medico_HistorialSesiones.setBounds(AnchoRelativo(1214), AltoRelativo(273), AnchoRelativo(317), AltoRelativo(200));
			btn_Medico_HistorialSesiones.setBackground(Color.WHITE);
			Image icono_Historial = new ImageIcon("."+File.separator+"img"+File.separator+"historial.png").getImage();
			icono_Historial = icono_Historial.getScaledInstance(btn_Medico_HistorialSesiones.getWidth(), btn_Medico_HistorialSesiones.getHeight(), Image.SCALE_SMOOTH);
			btn_Medico_HistorialSesiones.setIcon(new ImageIcon(icono_Historial));
			frmMedico2.getContentPane().add(btn_Medico_HistorialSesiones);
			btn_Medico_HistorialSesiones.addActionListener(controlador);
			
			//BOTÓN DAR DE ALTA A UN PACIENTE
			//btn_Medico_DarAlta.setBounds(AnchoRelativo(1471), AltoRelativo(554), AnchoRelativo(223), AltoRelativo(223));
			btn_Medico_DarAlta.setBounds(AnchoRelativo(1214), AltoRelativo(553), AnchoRelativo(250), AltoRelativo(250));
			btn_Medico_DarAlta.setBackground(Color.WHITE);
			Image icono_DarAlta = new ImageIcon("."+File.separator+"img"+File.separator+"alta.jpg").getImage();
			icono_DarAlta = icono_DarAlta.getScaledInstance(btn_Medico_DarAlta.getWidth(), btn_Medico_DarAlta.getHeight(), Image.SCALE_SMOOTH);
			btn_Medico_DarAlta.setIcon(new ImageIcon(icono_DarAlta));
			frmMedico2.getContentPane().add(btn_Medico_DarAlta);
			btn_Medico_DarAlta.addActionListener(controlador);
			
		} else if(usuarioPaciente.getActivo() == 0){
			//BOTON AÑADIR PACIENTE SI ESTA INACTIVO 
			//btn_Medico_AddPaciente.setBounds(AnchoRelativo(1471), AltoRelativo(554), AnchoRelativo(223), AltoRelativo(223));
			btn_Medico_AddPaciente.setBounds(AnchoRelativo(1214), AltoRelativo(553), AnchoRelativo(250), AltoRelativo(250));
			btn_Medico_AddPaciente.setBackground(Color.WHITE);
			Image icono_add = new ImageIcon("."+File.separator+"img"+File.separator+"anadir_paciente.png").getImage();
			icono_add = icono_add.getScaledInstance(btn_Medico_AddPaciente.getWidth(), btn_Medico_AddPaciente.getHeight(), Image.SCALE_SMOOTH);
			btn_Medico_AddPaciente.setIcon(new ImageIcon(icono_add));
			frmMedico2.getContentPane().add(btn_Medico_AddPaciente);
			btn_Medico_AddPaciente.addActionListener(controlador);
		}
		
		/*//ESTADISTICAS PACIENTE
		 Vector<Sesion> ses = Fichero.sesionesPaciente(usuarioPaciente);
		 int numSes = ses.size();
		 
		 //Sesion sesion = Fichero.sesionRegistro(ses);
		 //sesion.getDistancia().get(sesion.getDistancia().size()-1).toString()
		 
		   	
		lbl_UltimoPeso = new JLabel("Ultimo peso: ");
		lbl_UltimoPeso.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    lbl_UltimoPeso.setBounds(967, 370, 359, 33);
	    frmMedico2.getContentPane().add(lbl_UltimoPeso);
	    

	    lbl_SesionesRealizadas = new JLabel("Sesiones realizadas: " + numSes);
	    lbl_SesionesRealizadas.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    lbl_SesionesRealizadas.setBounds(967, 438, 359, 23);
	    frmMedico2.getContentPane().add(lbl_SesionesRealizadas);*/
		
		frmMedico2.setVisible(true);	//Se hace visible la ventana

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
