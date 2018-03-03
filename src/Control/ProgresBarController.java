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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import Modelo.Paciente;

public class ProgresBarController {
	
	Vector<Float>vectorPulsacion;
	
	 JFreeChart chart;
	Paciente pac;
	int contadorS = 0;
	int contadorV = 0;
	int contadorC = 0;
	int contadorF = 0;
	int contadorR = 0;	
	public double porcentajeS;
	public double porcentajeV;
	public double porcentajeC;
	public double porcentajeF;
	public double porcentajeR;
	
	public ProgresBarController(Vector<Float>VectorPulsacion,Paciente pac){
	this.pac=pac;
	this.vectorPulsacion=VectorPulsacion;
	rellenarContadores();
	rellenarPorcenajes();
	datosChart();
	
	}
	
	public void rellenarContadores(){
		float maximo=pac.frecCardMaxima();
		float s = (90 * maximo)/100;
		float v = (80 * maximo)/100;
		float c = (70 * maximo)/100;
		float f = (60 * maximo)/100;
		float r = (40 * maximo)/100;

		for(int i = 0; i < vectorPulsacion.size();i++){
			
			if (vectorPulsacion.get(i) >= s){
				contadorS++;
			}
			else if (vectorPulsacion.get(i) >= v && vectorPulsacion.get(i) < s){
				contadorV++;
			}
			else if (vectorPulsacion.get(i) >= c && vectorPulsacion.get(i) < v){
				contadorC++;
			}
			else if (vectorPulsacion.get(i) >= f && vectorPulsacion.get(i) < c){
				contadorF++;
			}
			else if (vectorPulsacion.get(i) >= r && vectorPulsacion.get(i) < f) {
				contadorR++;
			}
			else{
				contadorR++;
			}
		}
	}
	public void rellenarPorcenajes(){
		int total = vectorPulsacion.size();
		
		porcentajeS = (contadorS * 100) / total;
		porcentajeV = (contadorV * 100) / total;
		porcentajeC = (contadorC * 100) / total;
		porcentajeF = (contadorF * 100) / total;
		porcentajeR = (contadorR * 100) / total;
	}
	public void datosChart(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.setValue(porcentajeS, "Sprint", "Sprint");
        dataset.setValue(porcentajeV, "Velocidad", "Velocidad");
        dataset.setValue(porcentajeC, "Cardio", "Cardio");
        dataset.setValue(porcentajeF, "Fitness", "Fitness");
        dataset.setValue(porcentajeR, "Relajado", "Relajado");
         chart = ChartFactory.createBarChart(
                "Zonas de frecuencia cardiaca",
                "Zonas de frecuencia", 
                "Porcentaje(%)", 
                dataset, 
                PlotOrientation.HORIZONTAL,
                true, 
                false, 
                false
        );
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
	        chart.setBackgroundImage(image);
	        CategoryPlot plot = chart.getCategoryPlot();
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setRange(0, 100);
	       
	        plot.setBackgroundPaint(Color.white);
	        plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);
			
	        chart.removeLegend();
        //customizeChart(chart);

	}
	 private void customizeChart(JFreeChart grafica) {
	  		XYPlot plot = grafica.getXYPlot();
	  		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	  		
	  		// Seleccionamos el render de las líneas
	  		plot.setRenderer(renderer);
	  		
	  		// fondo de la grafica
	  		plot.setBackgroundPaint(Color.WHITE);
	  		
	  		
	       
	  		
	  		// Color líneas discontinuas del fondo
	  		//HORIZONTALES
	  		plot.setRangeGridlinesVisible(true);
	  		plot.setRangeGridlinePaint(Color.BLACK);
	  		//VERTICALES
	  		plot.setDomainGridlinesVisible(false);
	  		plot.setDomainGridlinePaint(Color.DARK_GRAY);
	  	}
	public JFreeChart progresBar(){
		return chart;
	}
	
}