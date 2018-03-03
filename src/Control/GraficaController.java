package Control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Modelo.Sesion;

public class GraficaController<T> {
	boolean [] flags;
	Sesion sesion;
	JFreeChart grafica;
	 XYDataset datos;
	public GraficaController(boolean [] flag, Sesion ses){
		
		 flags=flag;
		 sesion=ses;
		 datos=crearDatos(0,ses.getVectorTiempo().size());
		 grafica=createChart(datos);
		
	}
	
 	private Vector<T> vectorMostrar(int i){// Funcion ayuda vector deseado
 		Vector<T> aux = null;
 		switch(i){
 		case 0:aux= (Vector<T>) sesion.getVectorPulsacion();
 			break;
 		case 1: aux=(Vector<T>) sesion.getVectorSaturacion();
 		break;
 		case 2: aux= (Vector<T>) sesion.getVectorAltitud();
 		break;
 		case 3: aux= ((Vector<T>) sesion.getVectorVelocidad());
 		break;
 		}
 		return aux;
 	}
 	
 	private String fltoString(int i){// Funcion ayuda titulo 
 		String aux=null;
 		switch(i){
 		case 0:aux= "Frecuencia Cardiaca";
 			break;
 		case 1: aux= "Oxigeno en Sangre";
 		break;
 		case 2: aux= "Altitud";
 		break;
 		case 3: aux= "Velocidad";
 		break;
 		}
 		return aux;
 	}
 	private XYDataset crearDatos(int ini, int fin){// Creamos datos de las graficas
 		XYSeriesCollection dataset = new XYSeriesCollection();
 		for(int i=0;i<flags.length;i++){// Recorremos el array de flgs comprobando cual quermos activar
 			if(flags[i]==true){
 				XYSeries series1 = new XYSeries(fltoString(i));// titulo de la serie
 				for(int a=ini;a<fin;a++){// Creamos la serie con la informacion del vector deseado
 		 			double aux=new Double(vectorMostrar(i).get(a).toString());
 		 			String[] hora=sesion.getVectorTiempo().get(a).split(":");
 		 			double aux1=new Double(hora[0]+"."+hora[1]+hora[2]);
 		        series1.add(aux1, aux);
 			}
 				dataset.addSeries(series1);	// Añadimos la serie a nuestra datos
 		}
 			
 		}
 		return dataset;
 	}
 	
	public JFreeChart createChart( XYDataset dataset) {
        
       
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Data",      // chart title
            "Time",                      // x axis label
            " ",                      // y axis label
            dataset                 // data
                                
        );
       customizeChart(chart);
        return chart;
 	}
	 private void customizeChart(JFreeChart grafica) {
	  		XYPlot plot = grafica.getXYPlot();
	  		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	  		LegendItemCollection leyends=plot.getLegendItems();
	  		
	  		
	  		for(int i=0;i<leyends.getItemCount();i++){// Coloreamos la lineas
	  			renderer.setSeriesStroke(i, new BasicStroke(3.0f));
	  			if(leyends.get(i).getSeriesKey().equals("Frecuencia Cardiaca")){
	  				renderer.setSeriesPaint(i, Color.red);
	  			}else if(leyends.get(i).getSeriesKey().equals("Oxigeno en Sangre")){
	  				renderer.setSeriesPaint(i, Color.GREEN);
	  			}else if(leyends.get(i).getSeriesKey().equals("Altitud")){
	  				renderer.setSeriesPaint(i, Color.BLUE);
	  			}else if(leyends.get(i).getSeriesKey().equals("Velocidad")){
	  				renderer.setSeriesPaint(i, Color.ORANGE);
	  			}
	  		}
	
	  		
	  		
	  		// Borde de la grafica
	  		plot.setOutlinePaint(Color.getHSBColor(0.0f, 1.0f, 0.55f));
	  		plot.setOutlineStroke(new BasicStroke(3.0f));
	  		
	  		// Seleccionamos el render de las líneas
	  		plot.setRenderer(renderer);
	  		
	  		// fondo de la grafica
	  		plot.setBackgroundPaint(Color.WHITE);
	  		
	  		BufferedImage image = null;
	        try {
	            File url = new File("." + File.separator +"img"+File.separator+"fondografica.jpg");
	            image = ImageIO.read(url);
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        plot.setBackgroundImage(image);
	       
	  		
	  		// Color líneas discontinuas del fondo
	  		//HORIZONTALES
	  		plot.setRangeGridlinesVisible(true);
	  		plot.setRangeGridlinePaint(Color.BLACK);
	  		//VERTICALES
	  		plot.setDomainGridlinesVisible(false);
	  		plot.setDomainGridlinePaint(Color.DARK_GRAY);
	  		
	  		 plot.setDomainCrosshairPaint(Color.RED.darker());
	  	     plot.setRangeCrosshairPaint(Color.RED.darker());
	  	     
	  	     plot.setDomainCrosshairVisible(true);
	  	     plot.setRangeCrosshairVisible(true);
	  	}

 	public JFreeChart getGrafica(){
 		return grafica;
 	}
}
