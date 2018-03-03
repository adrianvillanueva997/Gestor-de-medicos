package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modelo.Fichero;
import Modelo.Paciente;
import Vista.*;

public class ControladorFormularioSesion implements ActionListener{

	public VentanaFormularioSesion ventanaControlada;
	public VentanaPaciente ventanaPaciente;
	public Paciente usuarioPaciente;
	File selectedFile = null;
	public boolean csvComprobado = false;
	public String csv;
	public String comentario;
	public int estadoAnimo = 0;
	
	public ControladorFormularioSesion(VentanaFormularioSesion vp, VentanaPaciente win2, Paciente usuarioPaciente) {
		ventanaControlada = vp;
		this.usuarioPaciente = usuarioPaciente;
		ventanaPaciente = win2;
	}

	public void actionPerformed(ActionEvent e) {
		  if (e.getSource().equals(ventanaControlada.btn_Cancelar_SubirSesion)){ //analiaza la acci�n en la ventana
		      ventanaControlada.frmSubirSesion.dispose();//deja de hacerse visible la ventana cuando se pulsa el bot�n
		      ventanaPaciente.frmPaciente.setEnabled(true);
		  } else if (e.getSource().equals(ventanaControlada.btn_Aceptar_SubirSesion)){
			  JFrame frmDialogo = null;
			  int dialogo = JOptionPane.showConfirmDialog(frmDialogo, "¿Guardar Cambios?", "Guardar nuevo paciente", JOptionPane.YES_NO_OPTION);
			  if(dialogo == JOptionPane.YES_OPTION){
				  funcionVentana();
			  }
		   
		  } else if (e.getSource().equals(ventanaControlada.btn_Buscar_SubirSesion)){
			   final JFileChooser fc = new JFileChooser();
			   int result = fc.showOpenDialog(ventanaControlada.btn_Buscar_SubirSesion);
			   if (result == JFileChooser.APPROVE_OPTION) {
				    selectedFile = fc.getSelectedFile();
				    if(Comprobar_Csv(selectedFile) == true){
				           csvComprobado = true;
				           ventanaControlada.txt_Buscar_SubirSesion.setText(selectedFile.getAbsolutePath());
				    }
			   }
		  } else if (e.getSource().equals(ventanaControlada.btn_CaritaFeliz)) {
			  estadoAnimo = 1;
		  } else if (e.getSource().equals(ventanaControlada.btn_CaritaTriste)) {
			  estadoAnimo = 2;
		  } else if (e.getSource().equals(ventanaControlada.btn_CaritaNeutral)) {
			  estadoAnimo = 3;
		 }
	}
	
	
	private String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
		         return "";
		}
	}
		 
	public boolean Comprobar_Csv(File selectedFile) {
		boolean comprobado = false;
		if (selectedFile.isDirectory()) {
		   comprobado = true;
		}
		String extension = getFileExtension(selectedFile);
		if(extension.equals("csv")){
			comprobado = true;
		 }else{
			 JFrame frame = new JFrame();
			 JOptionPane.showMessageDialog((Component) frame, "Por favor, selecciona otro archivo.", "Error", JOptionPane.ERROR_MESSAGE);
		 }
			 return comprobado;
	}
	
	public void abrirVentanaPaciente(Paciente usuarioPaciente) throws IOException{
		VentanaPaciente vp = new VentanaPaciente();	//crea nueva ventana
		ControladorPaciente cp = new ControladorPaciente(vp, usuarioPaciente);	//crea nuevo controlador de ventana
		vp.addController(cp);	//asigna el controlador a la ventana creada
		vp.crearVentana(usuarioPaciente);	//crea los elementos de la ventana
	}
	
	public void funcionVentana(){
	
		Date date = ventanaControlada.dateChooser.getDate();
		String fecha = DateFormat.getDateInstance().format(date);
		
		String peso1 = ventanaControlada.cb_AP_Gramos.getSelectedItem().toString();
		String peso2 = ventanaControlada.cb_AP_Peso.getSelectedItem().toString();
		String pesoTot = peso2 + peso1;
		if (ventanaControlada.txt_Buscar_SubirSesion.getText().isEmpty() || estadoAnimo == 0 || ventanaControlada.txtPn_Comentario_SubirSesion.getText().isEmpty()) {
		  Object frame = null;
		  JOptionPane.showMessageDialog((Component) frame, "Faltan campos por rellenar.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String path = ventanaControlada.txt_Buscar_SubirSesion.getText();
		  	String comentario = ventanaControlada.txtPn_Comentario_SubirSesion.getText();
		  	int actividad = ventanaControlada.cb_AP_Actividad.getSelectedIndex();
		    Fichero fich = new Fichero();
		    double peso = 0.0;
		    try{
		    	peso = Double.parseDouble(pesoTot);
		    } catch(Exception e){
		    	System.out.println("Error de parseo.");
		    	e.printStackTrace();
		    }
		    double imc = 1;	//A modificar para el siguiente careo
		    fich.guardarSesion(usuarioPaciente.getId_usuario(), fecha, peso, estadoAnimo, comentario, imc, actividad);
	          try {
	           List<String[]> datosXlsx = fich.leerCsv(path);
	           fich.parches(usuarioPaciente.getId_usuario(), comentario, fecha, datosXlsx);

	    	} catch (IOException e1) {
	     	// TODO Auto-generated catch block
	     	e1.printStackTrace();
	    	}
		    ventanaControlada.frmSubirSesion.dispose();
		    ventanaPaciente.frmPaciente.setEnabled(true);
		}
	}
	
	
	
	public double imc(double peso){
		double altura = usuarioPaciente.getAltura() * usuarioPaciente.getAltura();
		double imcT = peso/altura;
		DecimalFormat df = new DecimalFormat("##.00");
		double imc = Double.parseDouble(df.format(imcT));
		return imc;
	}
}