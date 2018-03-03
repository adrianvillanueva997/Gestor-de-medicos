package Vista;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Control.ControladorPaciente;
import Modelo.*;

public class VentanaPaciente extends JFrame{
  
  ControladorPaciente controlador;
  
  public JFrame frmPaciente;
  public JLabel lbl_Paciente_Info;
  public JLabel lbl_Paciente_Informacion;
  public JLabel lbl_Paciente_Rectangulo_Superior;
  public JLabel lblNombre;
  public JLabel lbl_Paciente_Nombre;
  public JLabel lbl_Paciente_Apellidos;
  public JLabel lbl_Paciente_Nacimiento ;
  public JLabel lbl_Paciente_Direccion;
  public JLabel lbl_Paciente_Telefono;
  public JLabel lbl_Paciente_Email;
  public JLabel lbl_Paciente_Sexo;
  public JLabel lbl_Paciente_Altura;
  public JLabel lbl_Paciente_NombreyApellidos;
  public JLabel lbl_Paciente_Historial;
  public JLabel lbl_Logo_Medico_Slim;
  public JLabel lbl_Paciente_Inicio;
  public JLabel lbl_Paciente_Cp;
  public JLabel lbl_Paciente_Localidad;
  public JLabel lbl_Paciente_Provincia;
  public JLabel lbl_Paciente_Dni;
  public JButton btn_Paciente_HistorialSesiones;
  public JButton btn_Paciente_SubirSesion;
  public JButton btn_Paciente_Atras;
  public JButton btn_Paciente_Editar;
  public JButton btn_CambiarPass;
  
  //Funci�n para controlar la ventana
  public void addController(ControladorPaciente mc){
    controlador=mc;
  }
  
  //Funci�n para crear la ventana y sus componentes
  public void crearVentana(Paciente usuarioPaciente) throws IOException{
    
    Fichero comprobar = new Fichero();
    //String [] paciente = comprobar.leeDatosPaciente(usuarioPaciente);
    Paciente pac = comprobar.busquedaPaciente(usuarioPaciente);
    
    //FRAME DE LA VENTANA PACIENTE
    frmPaciente = new JFrame();
    frmPaciente.getContentPane().setBackground(new Color(255, 255, 255));
    frmPaciente.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img"  +File.separator + "apus_logo.jpg"));
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  //Busca la resoluci�n de la pantalla
    frmPaciente.setBounds(0, 0,screen.width,screen.height);  //Establece dimensiones de la ventana
    frmPaciente.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Maximiza por defecto la ventana
    frmPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Estabalece la operaci�n de cierre por defecto
    frmPaciente.getContentPane().setLayout(null);
    
    //LABEL LOGO SLIMUE:
    lbl_Logo_Medico_Slim = new JLabel("");
    lbl_Logo_Medico_Slim.setBounds(AnchoRelativo(15), AltoRelativo(3), AnchoRelativo(321), AltoRelativo(94));
    Image logo_Medico_SlimUE = new ImageIcon("."+File.separator+"img"+File.separator+"logo.png").getImage();
    logo_Medico_SlimUE = logo_Medico_SlimUE.getScaledInstance(lbl_Logo_Medico_Slim.getWidth(), lbl_Logo_Medico_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
    lbl_Logo_Medico_Slim.setIcon(new ImageIcon(logo_Medico_SlimUE));
    frmPaciente.getContentPane().add(lbl_Logo_Medico_Slim);
    
    //LABEL DE INFORMACIÓN
    lbl_Paciente_Informacion = new JLabel("INFORMACIÓN");
    lbl_Paciente_Informacion.setForeground(new Color(139, 0, 0));
    lbl_Paciente_Informacion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(30)));
    lbl_Paciente_Informacion.setBounds(AnchoRelativo(504), AltoRelativo(113), AnchoRelativo(229), AltoRelativo(33));
    frmPaciente.getContentPane().add(lbl_Paciente_Informacion);
    
    //LABEL RECTÁNGULO SUPERIOR:
    lbl_Paciente_Rectangulo_Superior = new JLabel("");
    lbl_Paciente_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
    lbl_Paciente_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
    lbl_Paciente_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
    lbl_Paciente_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(100));
    frmPaciente.getContentPane().add(lbl_Paciente_Rectangulo_Superior);
 
    
    //LABEL NOMBRE DEL PACIENTE - RECTANGULO SUPERIOR
    lbl_Paciente_NombreyApellidos = new JLabel(pac.getNombre() + " " + pac.getApellido());
    lbl_Paciente_NombreyApellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_NombreyApellidos.setBounds(AnchoRelativo(1417), AltoRelativo(16), AnchoRelativo(371), AltoRelativo(33));
    lbl_Paciente_NombreyApellidos.setHorizontalAlignment(JLabel.RIGHT);
    frmPaciente.getContentPane().add(lbl_Paciente_NombreyApellidos);
          
    //LABEL HISTORIAL DEL PACIENTE - RECTANGULO SUPERIOR
    lbl_Paciente_Historial = new JLabel("Usuario: " + pac.getNombreUsuario());
    lbl_Paciente_Historial.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Historial.setBounds(AnchoRelativo(1417), AltoRelativo(52), AnchoRelativo(371), AltoRelativo(33));
    lbl_Paciente_Historial.setHorizontalAlignment(JLabel.RIGHT);
    frmPaciente.getContentPane().add(lbl_Paciente_Historial);
    
    //RECTANGULO QUE CONTIENE INFORMACION DE PACIENTE
    lbl_Paciente_Info = new JLabel("");
    lbl_Paciente_Info.setBorder(new LineBorder(new Color(139, 0, 0), 4, true));
    lbl_Paciente_Info.setBounds(AnchoRelativo(353), AltoRelativo(147), AnchoRelativo(591), AltoRelativo(828));
    frmPaciente.getContentPane().add(lbl_Paciente_Info);
    
    //LABEL NOMBRE
    lbl_Paciente_Nombre = new JLabel("Nombre: " + pac.getNombre());
    lbl_Paciente_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Nombre.setBounds(AnchoRelativo(438), AltoRelativo(202), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Nombre);
    
    //LABEL APELLIDOS
    lbl_Paciente_Apellidos = new JLabel("Apellidos: " + pac.getApellido());
    lbl_Paciente_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Apellidos.setBounds(AnchoRelativo(444), AltoRelativo(261), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Apellidos);
    
    //LABEL FECHA DE NACIMIENTO
    lbl_Paciente_Nacimiento = new JLabel("Fecha de nacimiento: " + pac.getFechaNacimiento());
    lbl_Paciente_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Nacimiento.setBounds(AnchoRelativo(444), AltoRelativo(363), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Nacimiento );
    
    //LABEL FECHA DE INICIO
    lbl_Paciente_Inicio = new JLabel("Fecha de inicio: " + pac.getFechaInicio());
    lbl_Paciente_Inicio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Inicio.setBounds(AnchoRelativo(444), AltoRelativo(425), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Inicio);
    
    //LABEL DNI
    lbl_Paciente_Dni = new JLabel("DNI: " + pac.getDNI());
    lbl_Paciente_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Dni.setBounds(AnchoRelativo(444), AltoRelativo(309), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Dni);
    
    //LABEL DIRECCION
    lbl_Paciente_Direccion = new JLabel("Dirección: " + pac.getCalle());
    lbl_Paciente_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Direccion.setBounds(AnchoRelativo(444), AltoRelativo(480), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Direccion);
      
    //LABEL CODIGO POSTAL
    lbl_Paciente_Cp = new JLabel("CP: " + pac.getCP());
    lbl_Paciente_Cp.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Cp.setBounds(AnchoRelativo(444), AltoRelativo(535), AnchoRelativo(170), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Cp);
    
    //LABEL LOCALIDAD
    lbl_Paciente_Localidad = new JLabel("Localidad: " + pac.getLocalidad());
    lbl_Paciente_Localidad.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Localidad.setBounds(AnchoRelativo(444), AltoRelativo(657), AnchoRelativo(400), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Localidad);
    
    //LABEL PROVINCIA
    lbl_Paciente_Provincia = new JLabel("Provincia: " + pac.getProvincia());
    lbl_Paciente_Provincia.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Provincia.setBounds(AnchoRelativo(444), AltoRelativo(593), AnchoRelativo(247), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Provincia);
    
    //LABEL TELEFONO
    lbl_Paciente_Telefono = new JLabel("Teléfono: " + pac.getTelefono());
    lbl_Paciente_Telefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Telefono.setBounds(AnchoRelativo(444), AltoRelativo(717), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Telefono);
    
    //LABEL EMAIL
    lbl_Paciente_Email = new JLabel("Email: " + pac.getEmail());
 
    lbl_Paciente_Email.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Email.setBounds(AnchoRelativo(444), AltoRelativo(766), AnchoRelativo(420), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Email);
    
    //LABEL SEXO
    lbl_Paciente_Sexo = new JLabel("Sexo: " + pac.getSexo());
    lbl_Paciente_Sexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Sexo.setBounds(AnchoRelativo(444), AltoRelativo(813), AnchoRelativo(247), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Sexo);
    
    //LABEL ALTURA
    lbl_Paciente_Altura = new JLabel("Altura: " + pac.getAltura());
    lbl_Paciente_Altura.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(22)));
    lbl_Paciente_Altura.setBounds(AnchoRelativo(444), AltoRelativo(872), AnchoRelativo(161), AltoRelativo(23));
    frmPaciente.getContentPane().add(lbl_Paciente_Altura);
    
    // BOT�N CERRAR SESION
    btn_Paciente_Atras = new JButton("");
    btn_Paciente_Atras.addActionListener(controlador);
    btn_Paciente_Atras.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
    btn_Paciente_Atras.setBorder(new LineBorder(new Color(153, 0, 0), 3));
    btn_Paciente_Atras.setForeground(new Color(153, 0, 0));
    btn_Paciente_Atras.setBackground(Color.WHITE);
    Image icono_atras = new ImageIcon("."+File.separator+"img"+File.separator+"apagar.png").getImage();
    icono_atras = icono_atras.getScaledInstance(btn_Paciente_Atras.getWidth(), btn_Paciente_Atras.getHeight(), Image.SCALE_SMOOTH);
    btn_Paciente_Atras.setIcon(new ImageIcon(icono_atras));
    frmPaciente.getContentPane().add(btn_Paciente_Atras);
        
    //BOT�N SUBIR SESI�N
    btn_Paciente_SubirSesion = new JButton("");
    btn_Paciente_SubirSesion.setBounds(AnchoRelativo(1214), AltoRelativo(273), AnchoRelativo(317), AltoRelativo(200));
    Image icono_subir = new ImageIcon(("."+File.separator+"img"+File.separator+"subir.png")).getImage();
    icono_subir = icono_subir.getScaledInstance(btn_Paciente_SubirSesion.getWidth(), btn_Paciente_SubirSesion.getHeight(), Image.SCALE_SMOOTH);
    btn_Paciente_SubirSesion.setIcon(new ImageIcon(icono_subir));
    btn_Paciente_SubirSesion.setBackground(Color.WHITE);
    btn_Paciente_SubirSesion.setBorder(new LineBorder(new Color(153, 0, 0), 3));
    frmPaciente.getContentPane().add(btn_Paciente_SubirSesion);
    btn_Paciente_SubirSesion.addActionListener(controlador);
    
    //BOT�N HISTORIAL DE SESIONES
    btn_Paciente_HistorialSesiones = new JButton("");
    btn_Paciente_HistorialSesiones.setBounds(AnchoRelativo(1214), AltoRelativo(553), AnchoRelativo(317), AltoRelativo(200));
    Image icono_Historial = new ImageIcon(("."+File.separator+"img"+File.separator+"historial.png")).getImage();
    icono_Historial = icono_Historial.getScaledInstance(btn_Paciente_HistorialSesiones.getWidth(), btn_Paciente_HistorialSesiones.getHeight(), Image.SCALE_SMOOTH);
    btn_Paciente_HistorialSesiones.setIcon(new ImageIcon(icono_Historial));
    btn_Paciente_HistorialSesiones.setBorder(new LineBorder(new Color(153, 0, 0), 3));
    btn_Paciente_HistorialSesiones.setBackground(Color.WHITE);
    frmPaciente.getContentPane().add(btn_Paciente_HistorialSesiones);
    btn_Paciente_HistorialSesiones.addActionListener(controlador);
    
    //BOTON EDITAR
    btn_Paciente_Editar = new JButton("");
    btn_Paciente_Editar.setBounds(AnchoRelativo(956), AltoRelativo(881), AnchoRelativo(94), AltoRelativo(94));
    btn_Paciente_Editar.setBackground(Color.WHITE);
    Image icono_editar = new ImageIcon(("."+File.separator+"img"+File.separator+"editar.png")).getImage();
    icono_editar = icono_editar.getScaledInstance(btn_Paciente_Editar.getWidth(), btn_Paciente_Editar.getHeight(), Image.SCALE_SMOOTH);
    btn_Paciente_Editar.setIcon(new ImageIcon(icono_editar));
    btn_Paciente_Editar.setBorder(new LineBorder(new Color(153, 0, 0), 3));
    frmPaciente.getContentPane().add(btn_Paciente_Editar);
    btn_Paciente_Editar.addActionListener(controlador);
    
    btn_CambiarPass = new JButton("");
    btn_CambiarPass.setBorder(new LineBorder(new Color(139, 0, 0)));
    btn_CambiarPass.setBackground(Color.WHITE);
    btn_CambiarPass.setBounds(AnchoRelativo(956), AltoRelativo(774), AnchoRelativo(94), AltoRelativo(94));
    Image icono_CambioPass = new ImageIcon(("."+File.separator+"img"+File.separator+"pass.png")).getImage();
    icono_CambioPass = icono_CambioPass.getScaledInstance(btn_CambiarPass.getWidth(), btn_CambiarPass.getHeight(), Image.SCALE_SMOOTH);
    btn_CambiarPass.setIcon(new ImageIcon(icono_CambioPass));
    frmPaciente.getContentPane().add(btn_CambiarPass);
    btn_CambiarPass.addActionListener(controlador);
    
    
    frmPaciente.setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"img"+File.separator+"apus_logo.jpg"));
    frmPaciente.setVisible(true);  //Se hace visible la ventana

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

