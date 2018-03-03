package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.*;
import Vista.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorAdmin implements ActionListener, MouseListener, KeyListener {

    public VentanaAdmin ventanaControlada;
    JFrame frmDialogo;
    JFrame frmDialogo2;
    public Administrador usuario;
    public Vector<Medico> medicos;

    private boolean listadoMedico;
    private boolean medActivo;
    private boolean medInactivo;

    //Funci�n controladora de la ventana de M�dico
    public ControladorAdmin(VentanaAdmin win, Administrador usuario) {
        ventanaControlada = win;
        this.usuario = usuario;
        this.medicos = new Vector<Medico>();
        setEstado(false);
        try {
            medicos = Fichero.adminMedicos(usuario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Función que indica las acciones que realizan los distintos objetos de la ventana
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ventanaControlada.btn_Admin_Anadir_Medico)) {	//analiza la acci�n que se hace en la ventana y la iguala al btn_About
            abrirVentanaAnadirMedico();
        } else if (e.getSource().equals(ventanaControlada.btn_Admin_Cerrar_Sesion)) {
            int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "¿Desea cerrar sesion?", "Cerrar Sesion", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                abrirVentanaIndex();
            }
        } else if (e.getSource().equals(ventanaControlada.btn_Admin_Buscar)) {
            try {
                buscarEnTabla();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource().equals(ventanaControlada.btn_CambiarPass)) {
            abrirCambiarPass();
        } else if (e.getSource().equals(ventanaControlada.chckbx_ListadoMedicos)) {
            this.listadoMedico = ventanaControlada.chckbx_ListadoMedicos.isSelected();
            if (this.listadoMedico == true) {
            	mostrarTodosMedicos();
                if (this.listadoMedico == true && this.medActivo == true) {
                	System.out.println("puta7");

                    ventanaControlada.chckbx_MedInactivos.setEnabled(false);
                    mostrarMedicosActivos();
                } else if (this.listadoMedico == true && medInactivo == true) {
                	System.out.println("puta8");

                	ventanaControlada.chckbx_MedActivos.setEnabled(false);
                	mostrarMedicosInactivos();
                }
            } else if (this.listadoMedico == false && this.medActivo == true) {
            	System.out.println("puta1");
            	mostrarMedicosActivos();
            } else if (this.listadoMedico == false && this.medInactivo == true) {
            	System.out.println("puta2");

            	mostrarMedicosInactivos();
            } else if(this.listadoMedico == false && this.medActivo == false && this.medInactivo == false){
            	System.out.println("puta3");

            	mostrarMedicosActivos();
            } else if(this.listadoMedico == true && this.medActivo == true && this.medInactivo == true){
            	System.out.println("puta4");

            	mostrarTodosMedicos();
            } else if (this.medActivo == false && this.listadoMedico == true) {
            	System.out.println("puta5");

                ventanaControlada.chckbx_MedActivos.setEnabled(true);
                mostrarTodosMedicos();
            } else if (this.medInactivo == false && listadoMedico == true) {
            	System.out.println("puta6");

                ventanaControlada.chckbx_MedInactivos.setEnabled(true);
                mostrarTodosMedicos();
            } 
        } else if (e.getSource().equals(ventanaControlada.chckbx_MedInactivos)) {
            this.medInactivo = ventanaControlada.chckbx_MedInactivos.isSelected();
            if (this.medInactivo == true) {
            	System.out.println("puta9");

                ventanaControlada.chckbx_MedActivos.setEnabled(false);
                mostrarMedicosInactivos();
                if (this.medInactivo == true && listadoMedico == true) {
                	System.out.println("puta10");

                    ventanaControlada.chckbx_MedActivos.setEnabled(false);
                    mostrarMedicosInactivos();
                }
            } else if (this.medInactivo == false && listadoMedico == true) {
            	System.out.println("puta11");

                ventanaControlada.chckbx_MedInactivos.setEnabled(true);
                mostrarTodosMedicos();
            } else {
            	System.out.println("puta12");

                ventanaControlada.chckbx_MedInactivos.setEnabled(true);
                ventanaControlada.chckbx_MedActivos.setEnabled(true);
                mostrarTodosMedicos();
            }
        } else if (e.getSource().equals(ventanaControlada.chckbx_MedActivos)) {
            this.medActivo = ventanaControlada.chckbx_MedActivos.isSelected();
            if (this.medActivo == true) {
            	System.out.println("puta13");

                ventanaControlada.chckbx_MedInactivos.setEnabled(false);
                mostrarMedicosActivos();
                if (this.medActivo == true && this.listadoMedico == true) {
                	System.out.println("puta14");

                    ventanaControlada.chckbx_MedInactivos.setEnabled(false);
                    mostrarMedicosActivos();
                }
            } else if (this.medActivo == false && this.listadoMedico == true) {
            	System.out.println("puta15");

            	System.out.println("Entro en medAc false y listado true");
                ventanaControlada.chckbx_MedActivos.setEnabled(true);
                mostrarTodosMedicos();
            } else {
            	System.out.println("puta16");

                ventanaControlada.chckbx_MedActivos.setEnabled(true);
                ventanaControlada.chckbx_MedInactivos.setEnabled(true);
                mostrarTodosMedicos();
            }
        }
    }
        
        
        
        
        
    /**
     * 
     */
	
    public void mostrarTodosMedicos(){
    	System.out.println("Mostrar todos los Médicos");
    	ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.adminMedicos(usuario)));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     */
    public void mostrarMedicosActivos() {
    	System.out.println("Mostrar todos los Médicos ACTIVOS");

        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.SelectMedicosActivos()));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void mostrarMedicosInactivos() {
    	System.out.println("Mostrar todos los Médicos INACTIVOS");

        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.SelectMedicosInactivos()));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEstado(boolean estado) {
        medActivo = true;
        medInactivo = estado;
    }

    public void refrescarDatos() {
        try {
            medicos = Fichero.adminMedicos(usuario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Vector<Medico> datosInicialesTablaAdmin() {
        return medicos;
    }

    public Vector<Medico> datosTablaAdmin() {
        Vector<Medico> medico = new Vector<>();
        try {
            medico = Fichero.adminMedicos(usuario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return medico;
    }

    public void mouseClicked(MouseEvent e) {
        /*if (e.getClickCount() == 2) {
        	int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "�Desea cerrar sesi�n?", "Cerrar Sesi�n", JOptionPane.YES_NO_OPTION);
        	if(respuesta == JOptionPane.YES_OPTION){
	            JTable target = (JTable) e.getSource();
	            int row = target.getSelectedRow();
	            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
	            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
	   
	            String dni=(String) elementAt.get(0); 
	            int id=Fichero.idMedicoDni(dni);
	          
	            Fichero.updateDelMed(id);
        	}
        }*/
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void abrirCambiarPass() {
        VentanaCambioPass vp = new VentanaCambioPass();
        ControladorCambioPass cp = new ControladorCambioPass(vp, usuario);
        vp.addController(cp);
        vp.CrearVentana(usuario);
    }

    public void abrirVentanaAnadirMedico() {
        VentanaAnadirMedico vap = new VentanaAnadirMedico();	//crea nueva ventana
        ControladorAnadirMedico cap = new ControladorAnadirMedico(vap, usuario);	//crea nuevo controlador de ventana
        vap.addController(cap);	//asigna el controlador a la ventana creada
        vap.crearVentana();
        ventanaControlada.frmAdmin.setEnabled(false);
    }

    public void abrirVentanaIndex() {
        ventanaControlada.frmAdmin.setVisible(false);
        VentanaIndex mainframe = new VentanaIndex();
        ControladorIndex mc = new ControladorIndex(mainframe);
        mainframe.addController(mc);
        mainframe.crearVentana();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                buscarEnTabla();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void buscarEnTabla() throws Exception {
        String medicoBuscar = ventanaControlada.txt_Admin_Buscar.getText();
        Vector<Medico> medicosTabla = null;
        try {
            if (medInactivo == true && listadoMedico == true) {
            	medicosTabla = Fichero.SelectMedicosInactivos();
            } else if (medActivo == true && listadoMedico == true) {
            	medicosTabla = Fichero.SelectMedicosActivos();
            } else if (listadoMedico == true && medInactivo == false && medActivo == false) {
            	medicosTabla = Fichero.adminMedicos(usuario);
            } else if(listadoMedico == false && medInactivo == false && medActivo == true) {
            	medicosTabla = Fichero.SelectMedicosActivos();
            } else if(listadoMedico == false && medInactivo == true && medActivo == false){
            	medicosTabla = Fichero.SelectMedicosInactivos();
            }else{
            	medicosTabla = Fichero.adminMedicos(usuario);
            }

        } catch (Exception e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        Vector<Medico> medicosNuevos = new Vector<>();
        if (!(medicoBuscar.equals(""))) {
            try {
                if (medActivo == true && listadoMedico == true) {
                	medicosNuevos = Fichero.buscarMedicosActivos(medicoBuscar, usuario);
                } else if (medInactivo == true && listadoMedico == true) {
                	medicosNuevos = Fichero.buscarMedicosInactivos(medicoBuscar, usuario);
                } else if (listadoMedico == true && medInactivo == false && medActivo == false) {
                	medicosNuevos = Fichero.buscarMedicos(medicoBuscar);
                } else if(listadoMedico == false && medInactivo == false && medActivo == true) {
                	medicosNuevos = Fichero.buscarMedicosActivos(medicoBuscar, usuario);
                } else if(listadoMedico == false && medInactivo == true && medActivo == false){
                	medicosNuevos = Fichero.buscarMedicosInactivos(medicoBuscar, usuario);
                }else{
                	medicosNuevos = Fichero.buscarMedicos(medicoBuscar);
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
        	medicosNuevos = medicosTabla;
        }

        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, medicosNuevos));
        ventanaControlada.repaint();

    }
}
