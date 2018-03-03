package Modelo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficas {
	public JFreeChart grafica;
	XYSeriesCollection datos = new XYSeriesCollection();
	String titulo;
	String tx;
	String ty;
	public final static int LINEAL = 1;
	public final static int SERIETIEMPO = 2;
	
	public Graficas (int tipo, String titulo){
		this.titulo = titulo;
		tipoGrafica(tipo);
	}
	
	public void tipoGrafica (int tipo){
		switch (tipo){
		case LINEAL:
			grafica = ChartFactory.createXYLineChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
			break;
		case SERIETIEMPO:
			grafica = ChartFactory.createTimeSeriesChart(titulo, tx, ty, datos, true, true, true);
			break;
		}
	}
	
	public void agregarGrafica(String id, double[] x, double[] y){
		XYSeries s = new XYSeries(id);
		int n = x.length;
		for (int i = 0; i < n; i++){
			s.add(x[i], y[i]);
		}
		datos.addSeries(s);
	}	
}
