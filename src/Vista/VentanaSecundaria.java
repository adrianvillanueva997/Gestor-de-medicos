package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Control.*;

/**
 *
 * @author APUS
 */
public class VentanaSecundaria extends JFrame {

    public JFrame frmSecundario;
    public JLabel lbl_fondo;
    public JLabel lbl_cargando;

    
    //Funci�n para crear la ventana y sus componentes
  
    public void crearVentana() {
        //crea la ventana
        frmSecundario = new JFrame();
        frmSecundario.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img" + File.separator + "apus_logo.jpg"));
        frmSecundario.getContentPane().setBackground(Color.WHITE);	//Se establece como color de la ventana el blanco
        frmSecundario.setTitle("Cargando..");	//Titulo de la ventana
        frmSecundario.setResizable(false);	//Se impide que se pueda redimensionar la ventana
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	//Busca la resoluci�n de la pantalla
        frmSecundario.setBounds(0, 0, screen.width, screen.height);	//Establece las dimensiones de la ventana
        frmSecundario.setExtendedState(JFrame.MAXIMIZED_BOTH);	//Maximiza por defecto la ventana
        frmSecundario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Estabalece la operaci�n de cierre por defecto
        frmSecundario.getContentPane().setLayout(null);	//Obtiene el del contenido del JFrame y no establece ning�n tipo de Dise�o(Layout)
        frmSecundario.setFocusable(true);
        

        //Crea una etiqueta para colocar el gif de cargando
        lbl_fondo = new JLabel("");
        lbl_fondo.setBounds(0, 0, screen.width,screen.height);	//Establece el tama�o de la etiqueta
        Image logo = new ImageIcon("." + File.separator + "img" + File.separator + "loading.jpg").getImage();	//Crea el objeto imagen y lo vincula a la imagen logo.png guardada en la carpeta img del proyecto
        logo = logo.getScaledInstance(lbl_fondo.getWidth(), lbl_fondo.getHeight(), Image.SCALE_SMOOTH);
        lbl_fondo.setIcon(new ImageIcon(logo));	//Coloca la imagen en la etiqueta
        frmSecundario.getContentPane().add(lbl_fondo);	//Se a�ade el elemento al JFrame
        frmSecundario.setIconImage(Toolkit.getDefaultToolkit().getImage(("." + File.separator + "img" + File.separator + "apus_logo.jpg")));
        frmSecundario.setVisible(true);	//Se hace visible la ventana

}
 
 
 
    /**
     *
     * @param altura
     * @return
     */
    public int AltoRelativo(int altura) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AltoRelat = (screen.height * altura) / 1080;
        return AltoRelat;
    }

    /**
     *
     * @param ancho
     * @return
     */
    public int AnchoRelativo(int ancho) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int AnchoRelat = (screen.width * ancho) / 1920;

        return AnchoRelat;
    }

    /**
     *
     * @param fuenteActual
     * @return
     */
    public int fuenteRelativa(int fuenteActual) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int fuenteBuena = (screen.width * fuenteActual) / 1920;
        return fuenteBuena;
    }
}
