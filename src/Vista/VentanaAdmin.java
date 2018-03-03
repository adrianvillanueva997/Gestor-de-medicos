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

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import Control.*;
import Modelo.*;

public class VentanaAdmin extends JFrame{

    ControladorAdmin controlador;
    public JFrame frmAdmin;
    public JLabel lbl_Admin_Rectangulo_Superior;
    public JTable tabla_Medico;
    public JButton btn_Admin_Anadir_Medico;
    public JTextField txt_Admin_Buscar;
    public JButton btn_Admin_Cerrar_Sesion;
    public JLabel lbl_Admin_Nombre;
    public JLabel lbl_Admin_Numero;
    public JLabel lbl_Logo_Admin_Slim;
    public JButton btn_Admin_Buscar;
    public JButton btn_CambiarPass;
    public JCheckBox chckbx_MedInactivos;
    public JCheckBox chckbx_MedActivos;
    public JCheckBox chckbx_ListadoMedicos;

    public void addController(ControladorAdmin mc) {
        controlador = mc;
    }

    //Funciï¿½n para crear la ventana y sus componentes
    public void crearVentana(Administrador usuario) throws Exception {

        //crea la ventana
        frmAdmin = new JFrame();
        frmAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage(("." + File.separator + "img" + File.separator + "apus_logo.jpg")));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  //Busca la resoluciï¿½n de la pantalla
        frmAdmin.getContentPane().setBackground(Color.WHITE);
        frmAdmin.setBounds(0, 0, screen.width, screen.height);  //Establece dimensiones de la ventana
        frmAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Maximiza por defecto la ventana
        frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Estabalece la operaciï¿½n de cierre por defecto
        frmAdmin.getContentPane().setLayout(null);  //obtiene el del contenido del JFrame y no establece ningï¿½n tipo de Diseï¿½o(Layout)
        frmAdmin.addKeyListener(controlador);

        //LABEL RECTï¿½NGULO SUPERIOR:
        lbl_Admin_Rectangulo_Superior = new JLabel("");
        lbl_Admin_Rectangulo_Superior.setForeground(new Color(0, 0, 0));
        lbl_Admin_Rectangulo_Superior.setBackground(new Color(153, 0, 0));
        lbl_Admin_Rectangulo_Superior.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        lbl_Admin_Rectangulo_Superior.setBounds(AnchoRelativo(0), AltoRelativo(0), AnchoRelativo(1943), AltoRelativo(98));
        frmAdmin.getContentPane().add(lbl_Admin_Rectangulo_Superior);

        Fichero comprobar = new Fichero();
        Administrador Admin = comprobar.busquedaAdministrador(usuario);

        lbl_Admin_Nombre = new JLabel(Admin.getNombreUsuario());
        lbl_Admin_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        lbl_Admin_Nombre.setBounds(AnchoRelativo(1417), AltoRelativo(16), AnchoRelativo(371), AltoRelativo(33));
        lbl_Admin_Nombre.setHorizontalAlignment(JLabel.RIGHT);
        frmAdmin.getContentPane().add(lbl_Admin_Nombre);

        //LABEL LOGO SLIMUE:
        lbl_Logo_Admin_Slim = new JLabel("");
        lbl_Logo_Admin_Slim.setBounds(AnchoRelativo(15), AltoRelativo(0), AnchoRelativo(321), AltoRelativo(98));
        Image logo_Admin_SlimUE = new ImageIcon(("." + File.separator + "img" + File.separator + "logo.png")).getImage();
        logo_Admin_SlimUE = logo_Admin_SlimUE.getScaledInstance(lbl_Logo_Admin_Slim.getWidth(), lbl_Logo_Admin_Slim.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lbl_Logo_Admin_Slim.setIcon(new ImageIcon(logo_Admin_SlimUE));
        frmAdmin.getContentPane().add(lbl_Logo_Admin_Slim);

        //TABLA MEDICOS:    
        tabla_Medico = new JTable();
        Vector<Medico> data = controlador.datosInicialesTablaAdmin();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(AnchoRelativo(248), AltoRelativo(288), AnchoRelativo(1424), AltoRelativo(550));
        scrollPane.setViewportView(tabla_Medico);
        crearModeloTabla(tabla_Medico, data);

        tabla_Medico.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabla_Medico.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        tabla_Medico.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(24)));
        tabla_Medico.setRowHeight((scrollPane.getHeight()) / 10);
        tabla_Medico.setAutoCreateRowSorter(true);

        tabla_Medico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Aï¿½ade la libreria
        tabla_Medico.addMouseListener((MouseListener) controlador);

        tabla_Medico.setVisible(true);
        frmAdmin.getContentPane().add(scrollPane);

        //BOTON AÑADIR MEDICO:
        btn_Admin_Anadir_Medico = new JButton("");
        btn_Admin_Anadir_Medico.setOpaque(true);
        btn_Admin_Anadir_Medico.setBorder(new LineBorder(new Color(153, 0, 0), 2, true));
        btn_Admin_Anadir_Medico.setBackground(Color.WHITE);
        btn_Admin_Anadir_Medico.setBounds(AnchoRelativo(82), AltoRelativo(245), AnchoRelativo(85), AltoRelativo(85));
        Image icono_anadir_paciente = new ImageIcon("." + File.separator + "img" + File.separator + "anadir_paciente.png").getImage();
        icono_anadir_paciente = icono_anadir_paciente.getScaledInstance(btn_Admin_Anadir_Medico.getWidth(), btn_Admin_Anadir_Medico.getHeight(), Image.SCALE_SMOOTH);
        btn_Admin_Anadir_Medico.setIcon(new ImageIcon(icono_anadir_paciente));
        frmAdmin.getContentPane().add(btn_Admin_Anadir_Medico);
        btn_Admin_Anadir_Medico.addActionListener(controlador);

        // BOTï¿½N Cerrar_Sesion:
        btn_Admin_Cerrar_Sesion = new JButton("");
        btn_Admin_Cerrar_Sesion.addActionListener(controlador);
        btn_Admin_Cerrar_Sesion.setBounds(AnchoRelativo(1803), AltoRelativo(0), AnchoRelativo(126), AltoRelativo(98));
        btn_Admin_Cerrar_Sesion.setBorder(new LineBorder(new Color(153, 0, 0), 3));
        btn_Admin_Cerrar_Sesion.setForeground(new Color(153, 0, 0));
        btn_Admin_Cerrar_Sesion.setBackground(new Color(192, 192, 192));
        Image icono_apagar = new ImageIcon("." + File.separator + "img" + File.separator + "apagar.png").getImage();
        icono_apagar = icono_apagar.getScaledInstance(btn_Admin_Cerrar_Sesion.getWidth(), btn_Admin_Cerrar_Sesion.getHeight(), Image.SCALE_SMOOTH);
        btn_Admin_Cerrar_Sesion.setIcon(new ImageIcon(icono_apagar));
        frmAdmin.getContentPane().add(btn_Admin_Cerrar_Sesion);

        //TEXTFIELD BUSCAR:
        txt_Admin_Buscar = new JTextField();
        txt_Admin_Buscar.setBounds(AnchoRelativo(1257), AltoRelativo(197), AnchoRelativo(415), AltoRelativo(56));
        txt_Admin_Buscar.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
        txt_Admin_Buscar.setColumns(10);
        txt_Admin_Buscar.addKeyListener(controlador);
        frmAdmin.getContentPane().add(txt_Admin_Buscar);

        //BOTON BUSCAR:
        btn_Admin_Buscar = new JButton("");
        btn_Admin_Buscar.addActionListener(controlador);
        btn_Admin_Buscar.setBounds(AnchoRelativo(1173), AltoRelativo(197), AnchoRelativo(60), AltoRelativo(56));
        Image icono_buscar = new ImageIcon("." + File.separator + "img" + File.separator + "buscar.png").getImage();
        icono_buscar = icono_buscar.getScaledInstance(btn_Admin_Buscar.getWidth(), btn_Admin_Buscar.getHeight(), Image.SCALE_SMOOTH);
        btn_Admin_Buscar.setIcon(new ImageIcon(icono_buscar));
        btn_Admin_Buscar.setBackground(Color.WHITE);
        btn_Admin_Buscar.setBorderPainted(false);
        frmAdmin.getContentPane().add(btn_Admin_Buscar);

        //BOTON CAMBIAR PASSWORD
        btn_CambiarPass = new JButton("");
        btn_CambiarPass.setBorder(new LineBorder(new Color(139, 0, 0)));
        btn_CambiarPass.setBackground(Color.WHITE);
        btn_CambiarPass.setBounds(AnchoRelativo(15), AltoRelativo(774), AnchoRelativo(94), AltoRelativo(94));
        Image icono_CambioPass = new ImageIcon(("." + File.separator + "img" + File.separator + "pass.png")).getImage();
        icono_CambioPass = icono_CambioPass.getScaledInstance(btn_CambiarPass.getWidth(), btn_CambiarPass.getHeight(), Image.SCALE_SMOOTH);
        btn_CambiarPass.setIcon(new ImageIcon(icono_CambioPass));
        frmAdmin.getContentPane().add(btn_CambiarPass);
        btn_CambiarPass.addActionListener(controlador);

        //CHECKBOX ACTIVOS
        chckbx_MedActivos = new JCheckBox("Medicos activos");
        chckbx_MedActivos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_MedActivos.setBounds(AnchoRelativo(248), AltoRelativo(880), AnchoRelativo(174), AltoRelativo(25));
        chckbx_MedActivos.addActionListener(controlador);
        chckbx_MedActivos.setSelected(false);
        chckbx_MedActivos.setEnabled(true);
        frmAdmin.getContentPane().add(chckbx_MedActivos);

        //CHECKBOX INACTIVOS
        chckbx_MedInactivos = new JCheckBox("Medicos inactivos");
        chckbx_MedInactivos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_MedInactivos.setBounds(AnchoRelativo(451), AltoRelativo(883), AnchoRelativo(183), AltoRelativo(25));
        chckbx_MedInactivos.addActionListener(controlador);
        chckbx_MedInactivos.setEnabled(true);
        chckbx_MedInactivos.setSelected(false);
        frmAdmin.getContentPane().add(chckbx_MedInactivos);
        
        //CHECKBOX TODOS
        chckbx_ListadoMedicos = new JCheckBox("Listado Medicos");
        chckbx_ListadoMedicos.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
        chckbx_ListadoMedicos.setBounds(AnchoRelativo(674), AltoRelativo(883), AnchoRelativo(174), AltoRelativo(25));
        chckbx_ListadoMedicos.addActionListener(controlador);
        chckbx_ListadoMedicos.setSelected(true);
        chckbx_ListadoMedicos.setEnabled(true);
        frmAdmin.getContentPane().add(chckbx_ListadoMedicos);

        frmAdmin.setVisible(true);    //Se hace visible la ventana
    }

    public TableModel crearModeloTabla(JTable tabla_Medico, Vector<Medico> datos) throws Exception {
        tabla_Medico.removeAll();

        String[] nombre_Columnas_Medico = {"DNI", "Nombre", "Apellido"};

        TableModel modelo_Tabla_Medico = new DefaultTableModel(crearDatostabla(datos), nombre_Columnas_Medico) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla_Medico.setModel(modelo_Tabla_Medico);
        tabla_Medico.repaint();

        return modelo_Tabla_Medico;
    }

    public Object[][] crearDatostabla(Vector<Medico> datos) {
        Object[][] md = new Object[datos.size()][3];

        for (int i = 0; i < datos.size(); i++) {
            md[i][0] = datos.get(i).getDNI();
            md[i][1] = datos.get(i).getNombre();
            md[i][2] = datos.get(i).getApellido();

        }
        return md;
    }

    public int AltoRelativo(int altura) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AltoRelat = (screen.height * altura) / 1080;
        return AltoRelat;
    }

    public int AnchoRelativo(int ancho) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AnchoRelat = (screen.width * ancho) / 1920;

        return AnchoRelat;
    }

    public int fuenteRelativa(int fuenteActual) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int fuenteBuena = (screen.width * fuenteActual) / 1920;
        return fuenteBuena;
    }

}
