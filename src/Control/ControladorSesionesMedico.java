package Control;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Vista.*;
import Modelo.*;


public class ControladorSesionesMedico implements ActionListener, MouseListener{
  
  JFrame frmDialogo;
  
  public VentanaSesionesMedico ventanaControlada;
  public Paciente usuarioPaciente;
  public Medico usuarioMedico;
  public Sesion sesion;
  public Fichero sesionfichero;
  Vector<Sesion>sesiones;
  private volatile boolean[] flags;
  /* flags flags[0]=FrecuenciaCardiaca,flags[1]=OxigSangre,flags[2]=altitud,flags[3]=velocidad
   * 
   */
  public ControladorSesionesMedico (VentanaSesionesMedico win, Medico usuarioMedico, Paciente usuarioPaciente, Vector<Sesion> ses){
    ventanaControlada = win;
    this.usuarioMedico = usuarioMedico;
    this.usuarioPaciente = usuarioPaciente;
    flags=new boolean[4];
    for(int i=0;i<4;i++){
    	flags[i]=false;
    }
    sesiones = ses;
    
  }
  public void repintarGraficas(JFreeChart jFreeChart){
	  ChartPanel chartPanel = new ChartPanel(jFreeChart);
		ventanaControlada.panel_graficas.removeAll();
		ventanaControlada.panel_graficas.add(chartPanel);
		ventanaControlada.panel_graficas.validate();
  }
  //Funci�n que indica las acciones que realizan los distintos objetos de la ventana
  public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(ventanaControlada.btn_Sesiones_Atras)){
    	try {
			abrirVentanaCalen(usuarioMedico, usuarioPaciente);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    } else if (e.getSource().equals(ventanaControlada.btn_Sesiones_Altitud)){
    	if(flags[2]==true){
    		flags[2]=false;
    	}else flags[2]=true;
    	
    	GraficaController grafica=new GraficaController(flags,sesion);
    	repintarGraficas(grafica.getGrafica());
	
		
    } else if (e.getSource().equals(ventanaControlada.btn_Sesiones_Oxigeno)){
    	if(flags[1]==true){
    		flags[1]=false;
    	}else flags[1]=true;
    	
    	GraficaController grafica=new GraficaController(flags,sesion);
    	repintarGraficas(grafica.getGrafica());
	
		
    } else if (e.getSource().equals(ventanaControlada.btn_Sesiones_Pulso)){
    	if(flags[0]==true){
    		flags[0]=false;
    	}else flags[0]=true;
    	
    	GraficaController grafica=new GraficaController(flags,sesion);
    	repintarGraficas(grafica.getGrafica());
	
		
    } else if (e.getSource().equals(ventanaControlada.btn_Sesiones_Velocidad)){
    	if(flags[3]==true){
    		flags[3]=false;
    	}else flags[3]=true;
    	
    	GraficaController grafica=new GraficaController(flags,sesion);
    	repintarGraficas(grafica.getGrafica());
	
    } else if(e.getSource().equals(ventanaControlada.btn_Sesion_VisualizarNuevaSesion)){
    	
    	try {
			abrirVentanaSesiones(usuarioMedico, usuarioPaciente);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    } else if(e.getSource().equals(ventanaControlada.btn_Sesiones_Mapa)){
        mapsController mpc=new mapsController(sesion);
        
        ventanaControlada.panel_graficas.removeAll();
        ventanaControlada.panel_graficas.add(mpc.browserView);
        ventanaControlada.panel_graficas.validate();
     }
  }
  
  
  public void abrirVentanaCalen(Medico usuarioMedico, Paciente usuarioPaciente) throws Exception{
	  ventanaControlada.frmSesiones.dispose();
	  VentanaCalendarioMedico cal = new VentanaCalendarioMedico();
	  Fichero fich=new Fichero();
	  CalendarController cs = new CalendarController(cal,fich.sesionesPaciente(usuarioPaciente), usuarioMedico, usuarioPaciente);
	  cal.addController(cs);
	  cal.crearVentana(fich.sesionesPaciente(usuarioPaciente));
  }
    
  public void abrirVentanaMedico2(Medico usuarioMedico, Paciente usuarioPaciente) throws IOException{
    ventanaControlada.frmSesiones.dispose();//Cierra la ventana de inicio
    VentanaMedico2 vp = new VentanaMedico2();  //crea nueva ventana
    ControladorMedico2 cp = new ControladorMedico2(vp,usuarioMedico, usuarioPaciente);  //crea nuevo controlador de ventana
    vp.addController(cp);  //asigna el controlador a la ventana creada
    vp.crearVentana(usuarioMedico, usuarioPaciente);  //crea los elementos de la ventana
  }
  
 
  public void abrirVentanaSesiones(Medico usuarioMedico,Paciente usuarioPaciente) throws IOException{
		Vector<Sesion>se=Fichero.sesionesPaciente(usuarioPaciente);
		ventanaControlada.frmSesiones.dispose();
		VentanaSesionesMedico vs = new VentanaSesionesMedico();
		ControladorSesionesMedico cs = new ControladorSesionesMedico(vs, usuarioMedico, usuarioPaciente,se);
		vs.addController(cs, usuarioPaciente,se);
		vs.crearVentana(usuarioMedico, usuarioPaciente);
	}
 
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			   Object target = (JTable) e.getSource();
			   if(target instanceof JTable){
			    
			    int row = ((JTable) target).getSelectedRow();
			    try {
			     sesion=Fichero.sesionRegistro(sesiones.get(row));
			    } catch (IOException e1) {

			     e1.printStackTrace();
			    }
			    
			    //Tiempo total
			    String[] ini=sesion.getVectorTiempo().get(0).split(":");
			    String[] fin=sesion.getVectorTiempo().get(sesion.getVectorTiempo().size()-1).split(":");
			    int segfin=Integer.parseInt(fin[0])*3600+Integer.parseInt(fin[1])*60+Integer.parseInt(fin[2]);
			    int segini=Integer.parseInt(ini[0])*3600+Integer.parseInt(ini[1])*60+Integer.parseInt(ini[2]);
			    double total= ((double)segfin-segini);
			    int horas = (segfin-segini)/3600;
			    int minutos = (segfin-segini)/60;
			    double segundos = total%60;
			    ventanaControlada.lbl_Sesiones_Tiempo_Num.setText(String.valueOf(horas+"h"+minutos+"'" + segundos+"''"));
			    ventanaControlada.lbl_Sesiones_Tiempo_Num.repaint();
			    //Distancia total
			    ventanaControlada.lbl_Sesiones_Distancia_Num.setText(sesion.getDistancia().get(sesion.getDistancia().size()-1).toString());
			    ventanaControlada.lbl_Sesiones_Distancia_Num.repaint();
			    //Peso
			    String peso = String.valueOf(sesion.getPeso());
			    ventanaControlada.lbl_Sesiones_Peso_Num.setText(peso);
			    ventanaControlada.lbl_Sesiones_Peso_Num.repaint();
			    //Comentario 
			    ventanaControlada.lbl_Sesiones_Comentario_Num.setText(sesion.getComentario());
			    ventanaControlada.lbl_Sesiones_Comentario_Num.repaint();
			    //pulsaciones Max
			    ventanaControlada.lbl_Sesiones_FCMax_Num.setText(String.valueOf(usuarioPaciente.frecCardMaxima()));
			    ventanaControlada.lbl_Sesiones_FCMax_Num.repaint();
			    //pulsaciones Min
			    ventanaControlada.lbl_Sesiones_FCMin_Num.setText(String.valueOf(sesion.getVectorPulsacion().get(0)));
			    ventanaControlada.lbl_Sesiones_FCMin_Num.repaint();
			    //altitud Max
			    
			    //altitud Min
			    
			    //Imc 
			    ventanaControlada.lbl_Sesiones_Imc_Num.setText(String.valueOf(sesion.getIMC()));
			    ventanaControlada.lbl_Sesiones_Imc_Num.repaint();
			    //Velocidad media
			    //double vel = Fichero.velMedia(sesion.getFecha(), usuarioPaciente.getId_usuario(), sesion.getComentario(), sesion.getId_sesion());
			    //String velocidad = String.valueOf(vel);
			    /*char[] v = velocidad.toCharArray();
			    for(int i = 0; i < velocidad.length(); i++){
			    	System.out.println("velocidad[" + i + "] = " + v[i]);
			    }
			    int velo = v[0]+v[1]+v[2]+v[3]+v[4]+v[5];
			    System.out.println(velo);*/
			    
			    //ventanaControlada.lbl_Sesiones_VelMed_Num.setText(String.valueOf(velocidad));
			    //ventanaControlada.lbl_Sesiones_VelMed_Num.repaint();
			    //Estado
			    int estado = sesion.getEstado();
			    String est = null;
			    switch(estado){
			    case 1:
			    	est = "feliz";
			    	break;
			    case 2:
			    	est = "triste";
			    	break;
			    case 3:
			    	est = "neutral";
			    	break;
			    }
			   ventanaControlada.lbl_Sesiones_Animo_Num.setText(est);
			   ventanaControlada.lbl_Sesiones_Animo_Num.repaint();
			    
			    
			    ProgresBarController pbc=new ProgresBarController(sesion.getVectorPulsacion(),usuarioPaciente);
			    ChartPanel chartPanel = new ChartPanel(pbc.progresBar());
			    chartPanel.setPreferredSize(new Dimension( AnchoRelativo(900), AltoRelativo(318)));
			    chartPanel.setFillZoomRectangle(true);
			             chartPanel.setMouseWheelEnabled(true);
			    ventanaControlada.panel_progbar.add(chartPanel);
			    ventanaControlada.panel_progbar.validate();
			   }
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

