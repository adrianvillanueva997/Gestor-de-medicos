package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.*;
import Vista.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author APUS
 */
public class ControladorMedico implements ActionListener, MouseListener, KeyListener {

    /**
     *
     */
    public VentanaMedico ventanaControlada;
    JFrame frmDialogo;
    JFrame frmDialogo2;

    boolean listadoPaciente;
    boolean pacActivo;
    boolean pacInactivo;
    /**
     *
     */
    public Medico usuarioMedico;
    Vector<Paciente> pacientes;

    //Funci�n controladora de la ventana de M�dico
    /**
     *
     * @param win
     * @param usuario
     */
    public ControladorMedico(VentanaMedico win, Medico usuario) {
        ventanaControlada = win;
        usuarioMedico = usuario;
        setEstado(false);

        this.pacientes = new Vector<Paciente>();
        try {
            pacientes = Fichero.SelectPacientesActivosMedico(usuarioMedico);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Funci�n que indica las acciones que realizan los distintos objetos de la ventana
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ventanaControlada.btn_Medico_Anadir_Paciente)) {	//analiza la acci�n que se hace en la ventana y la iguala al btn_About
            try {
                abrirVentanaAnadirPaciente();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource().equals(ventanaControlada.btn_Medico_Cerrar_Sesion)) {
            int respuesta = JOptionPane.showConfirmDialog(frmDialogo, "�Desea cerrar sesi�n?", "Cerrar Sesi�n", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    abrirVentanaIndex();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource().equals(ventanaControlada.btn_Medico_Buscar)) {
            try {
                buscarEnTabla();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource().equals(ventanaControlada.btn_Medico_Editar)) {

            try {
                abrirVentanaModMed(usuarioMedico);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource().equals(ventanaControlada.btn_CambiarPass)) {
            abrirCambiarPass();
        } else if (e.getSource().equals(ventanaControlada.chckbx_ListadoPacientes)) {
            this.listadoPaciente = ventanaControlada.chckbx_ListadoPacientes.isSelected();
            if (this.listadoPaciente == true) {
                mostrarTodosPacientes();
                if (this.listadoPaciente == true && this.pacActivo == true) {
                    ventanaControlada.chckbx_PacInactivos.setEnabled(false);
                    mostrarTodosPacientesActivos();
                } else if (this.listadoPaciente == true && pacInactivo == true) {
                    mostrarTodosPacientesInactivos();
                }
            } else if (this.listadoPaciente == false && this.pacActivo == true) {
                mostrarPacientesActivos();
            } else if (this.listadoPaciente == false && this.pacInactivo == true) {
                mostrarPacientesInactivos();
            } else {
                mostrarPacientesActivos();
            }
        } else if (e.getSource().equals(ventanaControlada.chckbx_PacActivos)) {
            this.pacActivo = ventanaControlada.chckbx_PacActivos.isSelected();
            if (this.pacActivo == true) {
                ventanaControlada.chckbx_PacInactivos.setEnabled(false);
                mostrarPacientesActivos();
                if (this.pacActivo == true && listadoPaciente == true) {
                    ventanaControlada.chckbx_PacInactivos.setEnabled(false);
                    mostrarTodosPacientesActivos();
                }
            } else if (this.pacActivo == false && listadoPaciente == true) {
                ventanaControlada.chckbx_PacInactivos.setEnabled(true);
                mostrarTodosPacientes();
            } else {
                ventanaControlada.chckbx_PacInactivos.setEnabled(true);
                mostrarPacientesActivos();
            }
        } else if (e.getSource().equals(ventanaControlada.chckbx_PacInactivos)) {
            this.pacInactivo = ventanaControlada.chckbx_PacInactivos.isSelected();
            if (this.pacInactivo == true) {
                ventanaControlada.chckbx_PacActivos.setEnabled(false);
                mostrarPacientesInactivos();
                if (this.pacInactivo == true && this.listadoPaciente == true) {
                    ventanaControlada.chckbx_PacActivos.setEnabled(false);
                    mostrarTodosPacientesInactivos();
                }
            } else if (this.pacInactivo == false && this.listadoPaciente == true) {
                ventanaControlada.chckbx_PacActivos.setEnabled(true);
                mostrarTodosPacientes();
            } else {
                ventanaControlada.chckbx_PacActivos.setEnabled(true);
                mostrarPacientesActivos();
            }
        }
    }

    public void mostrarTodosPacientesActivos() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.selectPacientesActivos()));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarTodosPacientesInactivos() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.selectPacientesInactivos()));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarTodosPacientes() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.selectPacientes()));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarPacientesMedico() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.pacientesMedico(usuarioMedico)));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarPacientesActivos() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.SelectPacientesActivosMedico(usuarioMedico)));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarPacientesInactivos() {
        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.repaint();
        try {
            ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, Fichero.SelectPacientesInactivosMedico(usuarioMedico)));
        } catch (Exception ex) {
            Logger.getLogger(ControladorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEstado(boolean estado) {
        this.listadoPaciente = estado;
        this.pacActivo = true;
        this.pacInactivo = estado;
    }

    /**
     *
     * @return
     */
    public Vector<Paciente> datosInicialesTablaMedico() {

        return pacientes;
    }

    /**
     *
     * @return
     */
    public Vector<Paciente> datosTablaMedico() {
        //Vector<Paciente> pacientes = new Vector<>();
        try {
            pacientes = Fichero.SelectPacientesActivosMedico(usuarioMedico);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
   
          String dni=(String) elementAt.get(0); 
          int id=Fichero.idUsuarioDni(dni);

            try {

                abrirVentanaMedico2(usuarioMedico, Fichero.busquedaPaciente(Fichero.compUsuario(id)));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     *
     */
    public void abrirCambiarPass() {
        VentanaCambioPass vp = new VentanaCambioPass();
        ControladorCambioPass cp = new ControladorCambioPass(vp, usuarioMedico);
        vp.addController(cp);
        vp.CrearVentana(usuarioMedico);
    }

    /**
     *
     * @throws Exception
     */
    public void abrirVentanaAnadirPaciente() throws Exception {

        VentanaAnadirPaciente vap = new VentanaAnadirPaciente();	//crea nueva ventana
        ControladorAnadirPaciente cap = new ControladorAnadirPaciente(vap, ventanaControlada, usuarioMedico);	//crea nuevo controlador de ventana
        vap.addController(cap);	//asigna el controlador a la ventana creada
        vap.crearVentana();
        ventanaControlada.frmMedico.setEnabled(false);
    }

    /**
     *
     * @throws Exception
     */
    public void abrirVentanaIndex() throws Exception {
        ventanaControlada.frmMedico.setVisible(false);
        VentanaIndex mainframe = new VentanaIndex();
        ControladorIndex mc = new ControladorIndex(mainframe);
        mainframe.addController(mc);
        mainframe.crearVentana();
    }

    /**
     *
     * @param usuarioMedico
     * @param usuarioPaciente
     * @throws IOException
     */
    public void abrirVentanaMedico2(Medico usuarioMedico, Paciente usuarioPaciente) throws IOException {
        ventanaControlada.frmMedico.dispose();//Cierra la ventana de inicio
        VentanaMedico2 vp = new VentanaMedico2();	//crea nueva ventana
        ControladorMedico2 cp = new ControladorMedico2(vp, usuarioMedico, usuarioPaciente, ventanaControlada);	//crea nuevo controlador de ventana
        vp.addController(cp);	//asigna el controlador a la ventana creada
        vp.crearVentana(usuarioMedico, usuarioPaciente);	//crea los elementos de la ventana
    }

    /**
     *
     * @param med
     * @throws IOException
     */
    public void abrirVentanaModMed(Medico med) throws IOException {
        VentanaModMed vm = new VentanaModMed();
        ControladorModMed cm = new ControladorModMed(vm, med, ventanaControlada);
        vm.addController(cm);
        vm.crearVentana(med);
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

    /**
     *
     * @throws Exception
     */
    public void buscarEnTabla() throws Exception {
        String pacienteBuscar = ventanaControlada.txt_Medico_Buscar.getText();
        Vector<Paciente> pacientesTabla = null;
        try {
            if (pacInactivo == true && listadoPaciente == true) {
                pacientesTabla = Fichero.selectPacientesInactivos();
            } else if (pacActivo == true && listadoPaciente == true) {
                pacientesTabla = Fichero.selectPacientesActivos();
            } else if (listadoPaciente == true) {
                pacientesTabla = Fichero.selectPacientes();
            } else if(pacActivo == true) {
                pacientesTabla = Fichero.SelectPacientesActivosMedico(usuarioMedico);
            } else if(pacInactivo == true){
                pacientesTabla = Fichero.SelectPacientesInactivosMedico(usuarioMedico);
            }else{
                pacientesTabla = Fichero.SelectPacientesActivosMedico(usuarioMedico);
            }

        } catch (Exception e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        Vector<Paciente> pacientesNuevos = new Vector<>();
        if (!(pacienteBuscar.equals(""))) {
            try {
                if (pacActivo == true && listadoPaciente == true) {
                    pacientesNuevos = Fichero.buscarTodosPacientesActivos(pacienteBuscar, usuarioMedico);
                } else if (pacInactivo == true && listadoPaciente == true) {
                    pacientesNuevos = Fichero.buscarTodosPacientesInactivos(pacienteBuscar, usuarioMedico);
                } else if (listadoPaciente == true) {
                    pacientesNuevos = Fichero.buscarPaciente(pacienteBuscar);
                } else if(pacActivo == true) {
                    pacientesNuevos = Fichero.buscarPacienteActivoMedico(pacienteBuscar, usuarioMedico);
                } else if(pacInactivo == true){
                    pacientesNuevos = Fichero.buscarPacienteInactivoMedico(pacienteBuscar, usuarioMedico);
                }else{
                    pacientesNuevos = Fichero.buscarPacienteActivoMedico(pacienteBuscar,usuarioMedico);
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            pacientesNuevos = pacientesTabla;
        }

        ventanaControlada.remove(ventanaControlada.tabla_Medico);
        ventanaControlada.tabla_Medico.setModel(ventanaControlada.crearModeloTabla(ventanaControlada.tabla_Medico, pacientesNuevos));
        ventanaControlada.repaint();

    }
    
    
}

