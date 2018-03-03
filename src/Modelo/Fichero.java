package Modelo;

import java.awt.Component;

import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.*;

import Control.*;
import Vista.*;

public class Fichero {

    private static Conexion conexion = new Conexion();

    //Función para comprobar si el usuario pertenece al fichero abierto
    public static Usuario comprobarUsuario(String usuario, String Password) throws IOException {	// BUsqueda de usuarios log-in
        System.out.println("ComprobarUsuario");
        Boolean busqueda = false;
        Connection c = conexion.conectarse();
        Statement stmt = null;
        String pass = null;
        int tipous = 0;
        String name = " ";
        int id = 0;
        int activo = 0;
        Usuario us = new Usuario();

        try {

            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO"
            		+ " JOIN PASS ON USUARIO.ID_USUARIO = PASS.ID_USUARIO"
            		+ " WHERE USUARIO.NICK LIKE '" + usuario + "';");

            while (rs.next() && (!busqueda)) {	//inicia b�squeda del usuario
                id = rs.getInt("ID_USUARIO");
                name = rs.getString("NICK");

                if (usuario.equals(name)) {
                    pass = rs.getString("CONTRA");
                    if (pass.equals(Password)) {
                        tipous = rs.getInt("TIPOUSER");
                        activo = rs.getInt("ACTIVIDAD");
                        us.setId_usuario(id);
                        us.setTipoUsuario(tipous);
                        us.setContrasena(Password);
                        us.setNombreUsuario(name);
                        us.setActivo(activo);
                        busqueda = true;
                    }
                }
            }
            ////rs.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        if (busqueda == true) {
            return us;
        } else {
            return null;
        }

    }

    public static Usuario compUsuario(int id_usuario) throws IOException {	// Devuelve un usuario a partir de un id_usuario
        System.out.println("compUsuario");
        Boolean busqueda = false;

        Connection c = null;
        Statement stmt = null;
        String pass;
        int tipous = 0;
        String name = " ";
        int id = 0;
        int activo = 0;
        Usuario us = new Usuario();
        try {

            c = conexion.conectarse();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO"
            		+ " JOIN PASS ON PASS.ID_USUARIO = USUARIO.ID_USUARIO"
            		+ " where USUARIO.ID_USUARIO like " + id_usuario + " ;");

            while (rs.next() && (!busqueda)) {	//inicia b�squeda del usuario
                id = rs.getInt("ID_USUARIO");
                name = rs.getString("NICK");
                pass = rs.getString("CONTRA");
                tipous = rs.getInt("TIPOUSER");
                activo = rs.getInt("ACTIVIDAD");

                us.setId_usuario(id);
                us.setTipoUsuario(tipous);
                us.setContrasena(pass);
                us.setNombreUsuario(name);
                us.setActivo(activo);
                busqueda = true;
            }

            rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        if (busqueda == true) {

            return us;
        } else {
            return null;
        }
    }

    public void guardarUsuario(String nick, int tipoUsuario) throws SQLException {

        System.out.println("GuardarUsuario");

        Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;

        String insertUsuario = "INSERT INTO USUARIO"
                + "(NICK,TIPOUSER,ACTIVIDAD) VALUES"
                + "(?,?,?)";

        try {
            preparedStatement = c.prepareStatement(insertUsuario);

            preparedStatement.setString(1, nick);
            preparedStatement.setInt(2, tipoUsuario);
            preparedStatement.setInt(3, 1);

            preparedStatement.execute();
            System.out.println("¡Datos de Usuario correctamente guardados!\n");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (c != null) {
                conexion.desconectar();
            }
        }

    }
    
    public void guardarPass(String nick, String password) throws SQLException {

        System.out.println("GuardarPass");

        Connection c = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;

        try {
        	c = conexion.conectarse();
            stmt = c.createStatement();
            System.out.println("EMPIEZO EL SELECT");
            ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM USUARIO WHERE NICK like '" + nick + "'");
            int idUsuario = 0;
            while(rs.next()){
            	idUsuario = rs.getInt("ID_USUARIO");
            	System.out.println(idUsuario);
            }
            
            String insertUsuario = "INSERT INTO PASS"
                    + "(ID_USUARIO, CONTRA) VALUES"
                    + "(?,?)";
            preparedStatement = c.prepareStatement(insertUsuario);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setString(2, password);

            preparedStatement.execute();
            System.out.println("¡Datos de Password correctamente guardados!\n");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (c != null) {
                conexion.desconectar();
            }
        }

    }

    public void guardarMedico(String dni, String nombre, String apellidos, String email, String clinica) throws SQLException {
        System.out.println("GuardarMedico");

        Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM USUARIO WHERE NICK = '" + dni + "'");
            int idUsuario = 0;
            while(rs.next()){
            	idUsuario = Integer.parseInt(rs.getString("ID_USUARIO"));
            }

            String insertMedico = "INSERT INTO MEDICO"
                    + "(DNIM,ID_USUARIO,NOMBRE,APELLIDOS,CLINICA,EMAIL) VALUES"
                    + "(?,?,?,?,?,?)";

            preparedStatement = c.prepareStatement(insertMedico);
            preparedStatement.setString(1, dni);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidos);
            preparedStatement.setString(5, clinica);
            preparedStatement.setString(6, email);
            preparedStatement.execute();
            System.out.println("¡Datos de Medicos correctamente guardados!\n");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (c != null) {
                conexion.desconectar();
            }
        }
    }

    public void guardarPaciente(String dni, String nombre, String apellidos, String calle, int cp, String localidad, String provincia,
            int telefono, String email, String sexo, double altura, String fechaInicio, String fechaNacimiento) {

        System.out.println("guardarPaciente");
        Connection c = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;

        try {
        	c = DriverManager.getConnection("jdbc:mysql://185.117.72.136/proyectoinfo2?" +
					"user=UsuarioGenerico&password=user1");
            stmt = c.createStatement();
            System.out.println("EMPIEZO EL SELECT");
            ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM USUARIO WHERE NICK like '" + dni + "'");
            int idUsuario = 0;
            while(rs.next()){
            	idUsuario = rs.getInt("ID_USUARIO");
            	System.out.println(idUsuario);
            }
            ////rs.close();
            
            System.out.println("YA HE TERMINADO EL SELECT");
            
            String insertPacientes = "INSERT INTO PACIENTE"
                    + "(DNIP,ID_USUARIO,NOMBRE,APELLIDOS,EMAIL,NACIMIENTO,INICIO,ALTURA,SEXO,CALLE,PROVINCIA,LOCALIDAD,CP,TELEFONO) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = c.prepareStatement(insertPacientes);
            preparedStatement.setString(1, dni);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidos);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, fechaNacimiento);
            preparedStatement.setString(7, fechaInicio);
            preparedStatement.setDouble(8, altura);
            preparedStatement.setString(9, sexo);
            preparedStatement.setString(10, calle);
            preparedStatement.setString(11, provincia);
            preparedStatement.setString(12, localidad);
            preparedStatement.setInt(13, cp);
            preparedStatement.setInt(14, telefono);

            // execute the preparedstatement
            preparedStatement.execute();
            System.out.println("¡Datos de Pacientes correctamente guardados!\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (preparedStatement != null && stmt != null) {
                try {
                    stmt.close();
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void guardarMedicoPaciente(String dniPaciente, int idMedico) {

        System.out.println("guardarMedicoPaciente");

        Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
        Statement stmt = null;


        try {

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM USUARIO WHERE NICK = '" + dniPaciente + "'");
            int idPaciente = 0;
            while(rs.next()){
            	idPaciente = rs.getInt("ID_USUARIO");
            }
            
            String insertMedicoPaciente = "INSERT INTO MEDICO_PACIENTE"
                    + "(ID_UsuarioMed,ID_UsuarioPac) VALUES"
                    + "(?,?)";
            
            preparedStatement = c.prepareStatement(insertMedicoPaciente);
            preparedStatement.setInt(1, idMedico);
            preparedStatement.setInt(2, idPaciente);

            // execute the preparedstatement
            preparedStatement.execute();
            System.out.println("¡Datos MEDICO-PACIENTE correctamente guardados!\n");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null && stmt != null) {
                try {
                    stmt.close();
                    preparedStatement.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (c != null) {
                try {
                    conexion.desconectar();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public void guardarSesion(int idUser, String fecha, double peso, int estado, String comentario, double imc, int tag) {

        System.out.println("guardarSesion");

        Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;

        String insertMedicoPaciente = "INSERT INTO SESIONES"
                + "(ID_USUARIO,PESO, FECHA, ESTADO, COMENTARIO, IMC, TAG) VALUES"
                + "(?,?,?,?,?,?,?)";

        try {
            preparedStatement = c.prepareStatement(insertMedicoPaciente);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setDouble(2, peso);
            preparedStatement.setString(3, fecha);
            preparedStatement.setInt(4, estado);
            preparedStatement.setString(5, comentario);
            preparedStatement.setDouble(6, imc);
            preparedStatement.setInt(7, tag);

            // execute the preparedstatement
            preparedStatement.execute();
            System.out.println("¡Datos SESION correctamente guardados!\n");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (c != null) {
                try {
                    conexion.desconectar();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public void parches(int idUser, String comentario, String fecha, List<String[]> datosCsv) {
        System.out.println("parches");
        Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
        Statement stmt = null;
        String tiempo = null;
        float latitud = 0, longitud = 0, altitud = 0, distancia = 0, velocidad = 0;
        int saturacion = 0, pulsacion = 0;
        try {
            stmt = c.createStatement();
            ResultSet select = stmt.executeQuery("SELECT ID_SESIONES FROM SESIONES WHERE ID_USUARIO like " + idUser + " and FECHA like '" + fecha
                    + "' and COMENTARIO like '" + comentario + "'");
            int idSesion = 0;
            while(select.next()){
            	idSesion = select.getInt("ID_SESIONES");
            }

            String insertMedicoPaciente = "INSERT INTO REGISTRO"
                    + "(ID_SESIONES,TIME,LATITUDE,LONGITUDE,ALTITUDE,SPEED,DISTANCE,PULSACIONES,SpO2) VALUES"
                    + "(?,?,?,?,?,?,?,?,?)";

            boolean encontrado = false;
            for (int i = 0; i < datosCsv.size(); i++) {	//recorrer todo la lista de Strings
                int a = 0;
                preparedStatement = c.prepareStatement(insertMedicoPaciente);
                while (encontrado == false && a < 8) {	//recorre cada array y lo asigna
                    if (datosCsv.get(i)[a].split(";") != null) {
                        switch (a % 8) {	//asignacion
                            case 0:
                                tiempo = datosCsv.get(i)[a];
                                break;
                            case 1:
                                String temp = datosCsv.get(i)[a];
                                latitud = Float.parseFloat(temp);
                                break;
                            case 2:
                                longitud = Float.parseFloat(datosCsv.get(i)[a]);
                                break;
                            case 3:
                                altitud = Float.parseFloat(datosCsv.get(i)[a]);
                                break;
                            case 4:
                                velocidad = Float.parseFloat(datosCsv.get(i)[a]);
                                break;
                            case 5:
                                distancia = Float.parseFloat(datosCsv.get(i)[a]);
                                break;
                            case 6:
                                pulsacion = Integer.parseInt(datosCsv.get(i)[a]);
                                break;
                            case 7:
                                saturacion = Integer.parseInt(datosCsv.get(i)[a]);
                                break;
                            case 8:
                                encontrado = true;
                                break;
                        }	//switch
                    }	//if
                    a++;
                }	//while
                preparedStatement.setInt(1, idSesion);
                preparedStatement.setString(2, tiempo);
                preparedStatement.setFloat(3, latitud);
                preparedStatement.setFloat(4, longitud);
                preparedStatement.setFloat(5, altitud);
                preparedStatement.setFloat(6, velocidad);
                preparedStatement.setFloat(7, distancia);
                preparedStatement.setInt(8, pulsacion);
                preparedStatement.setInt(9, saturacion);
                preparedStatement.execute();
            } //for
            System.out.println("insert realizado con exito");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        } finally {

            if (preparedStatement != null) {
                try {
                    stmt.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (c != null) {
                try {
                    conexion.desconectar();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public static void updatePassword(int idUser, String passNuevo) {
        System.out.println("updatePassword");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        try {
        	char [] pass = passNuevo.toCharArray();
        	if(pass.length >= 64){
        		JFrame frame = null;
				JOptionPane.showMessageDialog(frame, "Numero de caracteres excedido.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error
        	} else {
	            c.setAutoCommit(false);
	            stmt = c.createStatement();
	            String sql = "UPDATE PASS SET CONTRA = '" + passNuevo + "' WHERE ID_USUARIO LIKE " + idUser + ";";
	            stmt.executeUpdate(sql);
	            c.commit();
	            stmt.close();
	            conexion.desconectar();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Actualizacion de PASSWORD realizada");
    }

    public static void updatePac(int idUser, String dni, String nombre, String apellidos, String calle, String cp, String localidad, String provincia,
            String telefono, String email, String sexo, String altura) {
        System.out.println("updatePac");
        Connection c = conexion.conectarse();
        PreparedStatement pstmt = null;
        Statement stmt = null;
        try {
        	double alt = Double.parseDouble(altura);
        	int CP =Integer.parseInt(cp);
        	int tel = Integer.parseInt(telefono);
            c.setAutoCommit(false);

            //stmt = c.createStatement();
            String sql = "UPDATE IGNORE PACIENTE SET "
            		+ "NOMBRE = ?, APELLIDOS = ?, EMAIL  = ?, ALTURA = ?, CALLE = ?,"
            		+ "LOCALIDAD = ?, PROVINCIA = ?, CP = ?, TELEFONO = ? WHERE ID_USUARIO LIKE ?";
            pstmt = c.prepareStatement(sql); //esto pendiente de comprobacion 
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, email);
            pstmt.setDouble(4, alt);
            pstmt.setString(5, calle);
            pstmt.setString(6, localidad);
            pstmt.setString(7, provincia);
            pstmt.setInt(8, CP);
            pstmt.setInt(9, tel);
            pstmt.setInt(10, idUser);
            pstmt.executeUpdate();
            
            c.commit();
            pstmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Actualizacion de PACIENTE realizada");
    }

    public void updateMed(int idUser, String nombre, String apellidos, String clinica, String email) {
        System.out.println("updateMed");
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
        	c = conexion.conectarse();
            c.setAutoCommit(false);
            String sql = "UPDATE IGNORE MEDICO SET NOMBRE = ?, APELLIDOS = ?,CLINICA  = ?, EMAIL = ? where ID_USUARIO = ?;";
            
            pstmt = c.prepareStatement(sql); //esto pendiente de comprobacion 
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, clinica);
            pstmt.setString(4, email);
            pstmt.setInt(5, idUser);
            pstmt.executeUpdate();
            
            
            c.commit();
            pstmt.close();
            conexion.desconectar();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Actualizacion de MEDICO realizada");
    }

    public void updateAddPac(int idUser) {
        System.out.println("updateAddPac");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "UPDATE USUARIO SET ACTIVIDAD = 1 WHERE ID_USUARIO = " + idUser;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Paciente activado.");
    }

    public void updateDelPac(int idUser) {
        System.out.println("updateDelPac");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "UPDATE USUARIO SET ACTIVIDAD = 0 WHERE ID_USUARIO = " + idUser;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Paciente borrado.");
    }

    public static void updateDelMed(int idUser) {
        System.out.println("updatedelMed");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "UPDATE MEDICO SET ACTIVIDAD = 0 WHERE ID_USUARIO = " + idUser;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Medico borrado.");
    }

    public static Vector<Sesion> sesionesPaciente(Paciente pac) throws IOException {//Sesiones a partir de de un paciente y una fecha( sintaxis 00/00/0000)
        System.out.println("sesionesPaciente");
        Connection c = null;
        Statement stmt = null;
        Sesion ses = null;
        Vector<Sesion> vectSesiones = new Vector<Sesion>();
        try {
        	c = conexion.conectarse();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT* FROM SESIONES where ID_USUARIO like " + pac.getId_usuario() + " ;");

            while (rs.next()) {	//inicia bï¿½squeda del usuario
                int id_sesion = rs.getInt("ID_SESIONES");
                int peso = rs.getInt("PESO");
                String fech = rs.getString("FECHA");
                int estado = rs.getInt("ESTADO");
                String comentario = rs.getString("COMENTARIO");
                int imc = rs.getInt("IMC");

                ses = new Sesion(id_sesion, pac.getId_usuario(), peso, fech, estado, comentario, imc);
                vectSesiones.add(ses);
            }
            ////rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vectSesiones;
    }

    public static Medico busquedaMedico(Usuario us) {// busqueda de medico a partir de un usuario
        System.out.println("busqueda Medico");
        //Connection c = conexion.conectarse();
        Connection c = null;
        Statement stmt = null;

        Medico med = null;
        try {
        	c = conexion.conectarse();
        	
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEDICO "
            		+ "JOIN PASS ON MEDICO.ID_USUARIO = PASS.ID_USUARIO "
            		+ "WHERE PASS.ID_USUARIO LIKE " + us.getId_usuario() + " ;");

            while (rs.next()) {	//inicia busqueda del medico
            	String pass = rs.getString("CONTRA");
                String DNI = rs.getString("DNIM");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDOS");
                String clinica = rs.getString("CLINICA");
                String email = rs.getString("EMAIL");

                med = new Medico(us.getNombreUsuario(), pass, us.getId_usuario(), us.getTipoUsuario(), us.getActivo(), DNI, nombre, apellido, clinica, email);

            }
            
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return med;

    }

    public static Paciente busquedaPaciente(Usuario us) { // busqueda de paciente a partir de un usuario
        System.out.println("busquedaPaciente");
        //Connection c = conexion.conectarse();
        Connection c = null;
        Statement stmt = null;
        Paciente pac = null;
        try {
        	c = DriverManager.getConnection("jdbc:mysql://185.117.72.136/proyectoinfo2?" +
					"user=UsuarioGenerico&password=user1");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            int id = us.getId_usuario();
            System.out.println(id);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PACIENTE where ID_USUARIO like " + id + ";");
            while (rs.next()) {	//inicia busqueda de pacientes
                String dnip = rs.getString("DNIP");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDOS");
                String email = rs.getString("EMAIL");
                String fechaNacimiento = rs.getString("NACIMIENTO");
                String fechaInicio = rs.getString("INICIO");
                int altura = rs.getInt("ALTURA");
                String sexo = rs.getString("SEXO");
                String calle = rs.getString("CALLE");
                String provincia = rs.getString("PROVINCIA");
                String localidad = rs.getString("LOCALIDAD");
                String CP = rs.getString("CP");
                String telefono = rs.getString("TELEFONO");
                pac = new Paciente(us.getNombreUsuario(), us.getContrasena(), us.getId_usuario(), us.getTipoUsuario(), us.getActivo(), dnip, nombre, apellido,
                        email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return pac;

    }

    public static Vector<Paciente> pacientesMedico(Medico med) {// Paciente asociados a un medico
        System.out.println("pacientesMedico");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vectPaciente = new Vector<Paciente>();
        System.out.println(med.getId_usuario());
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEDICO_PACIENTE  where ID_UsuarioMed like " + med.getId_usuario() + ";");

            while (rs.next()) {	//inicia b�squeda del usuario
                int ID_UsuarioPac = rs.getInt("ID_UsuarioPac");
                Usuario us = compUsuario(ID_UsuarioPac);
                Paciente pacient = busquedaPaciente(us);
                vectPaciente.add(pacient);
            }
            ////rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vectPaciente;

    }

    public static Vector<Sesion> sesionesFecha(Paciente pac, String fecha) throws IOException {//Sesiones a partir de de un paciente y una fecha( sintaxis 00/00/0000)
        System.out.println("sesionesFecha");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Sesion ses = null;
        Vector<Sesion> vectSesiones = new Vector<Sesion>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT* FROM SESIONES where ID_USUARIO like " + pac.getId_usuario() + " and FECHA like " + "'" + fecha + "'" + " ;");

            while (rs.next()) {	//inicia b�squeda del usuario
                int id_sesion = rs.getInt("ID_SESIONES");
                int peso = rs.getInt("PESO");
                String fech = rs.getString("FECHA");
                int estado = rs.getInt("ESTADO");
                String comentario = rs.getString("COMENTARIO");
                int imc = rs.getInt("IMC");

                ses = new Sesion(id_sesion, pac.getId_usuario(), peso, fech, estado, comentario, imc);
                vectSesiones.add(ses);
            }
            ////rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vectSesiones;
    }

    public static Sesion sesionRegistro(Sesion ses) throws IOException { // Cargado de registro en la sesion correspondiente
        System.out.println("sesionRegistro");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Vector<String> vectorTiempo = new Vector<String>();
        Vector<Float> vectorAltitud = new Vector<Float>();
        Vector<Float> vectorSaturacion = new Vector<Float>();
        Vector<Float> vectorPulsacion = new Vector<Float>();
        Vector<Float> vectorVelocidad = new Vector<Float>();
        Vector<Float> vectorLatitud = new Vector<Float>();
        Vector<Float> vectorLongitud = new Vector<Float>();
        Vector<Float> vectorDistancia = new Vector<Float>();

        try {

            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REGISTRO where ID_SESIONES like " + ses.getId_sesion() + ";");

            while (rs.next()) {	//inicia b�squeda del usuario
                String tiempo = rs.getString("TIME");
                float latitud = rs.getFloat("LATITUDE");
                float longitud = rs.getFloat("LONGITUDE");
                float altitud = rs.getFloat("ALTITUDE");
                float velocidad = rs.getFloat("SPEED");
                float distancia = rs.getFloat("DISTANCE");
                float pulsaciones = rs.getFloat("PULSACIONES");
                float spo2 = rs.getFloat("SpO2");
                vectorTiempo.add(tiempo);
                vectorLatitud.add(latitud);
                vectorLongitud.add(longitud);
                vectorAltitud.add(altitud);
                vectorVelocidad.add(velocidad);
                vectorDistancia.add(distancia);
                vectorPulsacion.add(pulsaciones);
                vectorSaturacion.add(spo2);
            }
            ////rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        ses.setVectorAltitud(vectorAltitud);
        ses.setDistancia(vectorDistancia);
        ses.setVectorLongitud(vectorLongitud);
        ses.setVectorPulsacion(vectorPulsacion);
        ses.setVectorSaturacion(vectorSaturacion);
        ses.setVectorTiempo(vectorTiempo);
        ses.setVectorVelocidad(vectorVelocidad);
        ses.setVectorLatitud(vectorLatitud);

        return ses;
    }

    public static String[] busquedaSesion(Paciente us, Sesion ses) {
        System.out.println("busquedasesion");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        String[] sesion = new String[7];
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SESIONES where ID_USUARIO = " + us.getId_usuario() + " AND ID_SESIONES = " + ses.getId_sesion() + ";");

            while (rs.next()) {	//inicia busqueda de pacientes
                int idSesion = rs.getInt("ID_SESIONES");
                String iS = String.valueOf(idSesion);
                sesion[0] = iS;
                int idUsuario = rs.getInt("ID_USUARIO");
                String iU = String.valueOf(idUsuario);
                sesion[1] = iU;
                String peso = rs.getString("PESO");
                sesion[2] = peso;
                String fecha = rs.getString("FECHA");
                sesion[3] = fecha;
                int estado = rs.getInt("ESTADO");
                String est = String.valueOf(estado);
                sesion[4] = est;
                String comentario = rs.getString("COMENTARIO");
                sesion[5] = comentario;
                String imc = rs.getString("IMC");
                sesion[6] = imc;
            }
            //rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return sesion;
    }

    public static Administrador busquedaAdministrador(Usuario us) {
        System.out.println("busquedaAdministrador");
        Connection c = conexion.conectarse();
        Statement stmt = null;

        Administrador admin = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINISTRADOR where ID_USUARIO like " + us.getId_usuario() + " ;");

            while (rs.next()) {	//inicia busqueda del medico
                int id_administrador = rs.getInt("ID_ADMINISTRADOR");
                admin = new Administrador(us.getNombreUsuario(), us.getContrasena(), us.getId_usuario(), us.getTipoUsuario(), us.getActivo(), id_administrador);

            }

            //rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return admin;

    }

    public static Vector<Medico> adminMedicos(Administrador admin) {
        System.out.println("adminMedicos");
        Connection c = conexion.conectarse();
        Statement stmt = null;

        Vector<Medico> vectMed = new Vector<Medico>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEDICO ;");

            while (rs.next()) {	//inicia b�squeda del usuario
                String DNI = rs.getString("DNIM");
                int id_medico = rs.getInt("ID_USUARIO");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDOS");
                String clinica = rs.getString("CLINICA");
                String email = rs.getString("EMAIL");
                Medico medi = new Medico(id_medico, DNI, nombre, apellido, clinica, email);
                vectMed.add(medi);
            }
            //rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        admin.setVectMedicos(vectMed);
        return vectMed;
    }

    public List<String[]> leerCsv(String sesionCSV) throws IOException {
        System.out.println("leerCSV");
        List<String[]> datosCSV = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader(sesionCSV));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] lineatxt = linea.split(";");
            datosCSV.add(lineatxt);
        }
        br.close();
        return datosCSV;
    }

    public static Vector<Paciente> buscarPaciente(String busqueda) {
        System.out.println("buscarPaciente");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vpacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PACIENTE where NOMBRE like " + "'%" + busqueda + "%' OR "
                    + "APELLIDOS like " + "'%" + busqueda + "%'" + " OR DNIP like " + "'%" + busqueda + "%'" + " ;");

            while (rs.next()) { //inicia busqueda de pacientes
                String dnip = rs.getString("DNIP");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDOS");
                String email = rs.getString("EMAIL");
                String fechaNacimiento = rs.getString("NACIMIENTO");
                String fechaInicio = rs.getString("INICIO");
                int altura = rs.getInt("ALTURA");
                String sexo = rs.getString("SEXO");
                String calle = rs.getString("CALLE");
                String provincia = rs.getString("PROVINCIA");
                String localidad = rs.getString("LOCALIDAD");
                String CP = rs.getString("CP");
                String telefono = rs.getString("TELEFONO");
                pac = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                vpacientes.add(pac);
            }
            //rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vpacientes;

    }

    public static Vector<Medico> buscarMedicos(String busqueda) {// busqueda de medico a partir de un usuario
        System.out.println("buscarMedicos");
        System.out.println("buscar medico");
        Connection c = conexion.conectarse();
        Statement stmt = null;

        Vector<Medico> med = new Vector<>();
        Medico medico = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEDICO where NOMBRE like " + "'%" + busqueda + "%' OR "
                    + "APELLIDOS like " + "'%" + busqueda + "%'" + " OR DNIM like " + "'%" + busqueda + "%'" + " ;");

            while (rs.next()) {	//inicia busqueda del medico
                String dni = rs.getString("DNIM");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDOS");
                String clinica = rs.getString("CLINICA");
                String email = rs.getString("EMAIL");

                medico = new Medico(dni, nombre, apellido, clinica, email);
                med.add(medico);
            }

            //rs.close();
            stmt.close();
            conexion.desconectar();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return med;

    }

    public static Vector<Paciente> selectPacientes() {
        System.out.println("SelectPacientes()");
        Statement stmt = null;
        Paciente paciente;
        Connection c = conexion.conectarse();
        Vector<Paciente> listaPacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM PACIENTE;")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    paciente = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    listaPacientes.add(paciente);
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaPacientes;
    }

    public static Vector<Paciente> selectPacientesActivos() {
        System.out.println("SelectPacientesActivos");
        Statement stmt = null;
        Vector<Paciente> listaPacientesActivos = new Vector<Paciente>();
        Paciente paciente;
        Connection c = conexion.conectarse();
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO "
            		+ "JOIN PACIENTE ON PACIENTE.ID_USUARIO = USUARIO.ID_USUARIO "
            		+ "WHERE USUARIO.ACTIVIDAD LIKE 1")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    paciente = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    listaPacientesActivos.add(paciente);
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaPacientesActivos;
    }

    public static Vector<Paciente> selectPacientesInactivos() {
        System.out.println("SelectPacientesInactivos");
        Statement stmt = null;
        Connection c = conexion.conectarse();
        Vector<Paciente> listaPacientesInactivos = new Vector<Paciente>();
        Paciente paciente;
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "join PACIENTE on USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 0\n"
                    + "GROUP BY PACIENTE.ID_USUARIO")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    paciente = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    listaPacientesInactivos.add(paciente);
                }
            }
            stmt.close();
            //conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return listaPacientesInactivos;
    }

    public static Vector<Paciente> SelectPacientesInactivosMedico(Medico medico) {
        System.out.println("SelectPacientesInactivosMedico");
        Statement stmt = null;
        Vector<Paciente> listaPacientesInactivos = new Vector<Paciente>();
        Paciente paciente;
        Connection c = conexion.conectarse();
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "JOIN PACIENTE ON USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "JOIN MEDICO_PACIENTE ON PACIENTE.ID_USUARIO = MEDICO_PACIENTE.ID_UsuarioPac\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 0 AND MEDICO_PACIENTE.ID_UsuarioMed LIKE " + medico.getId_usuario() + "\n"
                    + "ORDER BY PACIENTE.NOMBRE")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    paciente = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    listaPacientesInactivos.add(paciente);
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaPacientesInactivos;
    }

    public static Vector<Paciente> SelectPacientesActivosMedico(Medico medico) throws IOException {
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Vector<Paciente> listaPacientesActivos = new Vector<Paciente>();
        Paciente paciente;
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM MEDICO_PACIENTE"
            		+ " JOIN PASS ON MEDICO_PACIENTE.ID_UsuarioPac = PASS.ID_USUARIO"
            		+ " JOIN USUARIO ON MEDICO_PACIENTE.ID_UsuarioPac = USUARIO.ID_USUARIO"
            		+ " JOIN PACIENTE ON MEDICO_PACIENTE.ID_UsuarioPac = PACIENTE.ID_USUARIO"
            		+ " WHERE ID_UsuarioMed LIKE " + medico.getId_usuario() +" AND USUARIO.ACTIVIDAD LIKE 1"
            		+ " ORDER BY NOMBRE;");
            
            while (rs.next()) {	//inicia b�squeda del usuario
                 int ID_UsuarioPac=rs.getInt("ID_UsuarioPac");
        		 int id = rs.getInt("ID_USUARIO");
                 String name = rs.getString("NICK");
                 String pass = rs.getString("CONTRA");
                 int tipous = rs.getInt("TIPOUSER");
                 int activo = rs.getInt("ACTIVIDAD");
                 Usuario us = new Usuario(name, pass, id, tipous, activo);
            	 if(activo == 1){
                	 String dnip = rs.getString("DNIP");
                     String nombre = rs.getString("NOMBRE");
                     String apellido = rs.getString("APELLIDOS");
                     String email = rs.getString("EMAIL");
                     String fechaNacimiento = rs.getString("NACIMIENTO");
                     String fechaInicio = rs.getString("INICIO");
                     double altura = rs.getDouble("ALTURA");
                     String sexo = rs.getString("SEXO");
                     String calle = rs.getString("CALLE");
                     String provincia = rs.getString("PROVINCIA");
                     String localidad = rs.getString("LOCALIDAD");
                     String CP = rs.getString("CP");
                     String telefono = rs.getString("TELEFONO");
                     paciente = new Paciente(us.getNombreUsuario(), us.getContrasena(), us.getId_usuario(), us.getTipoUsuario(), us.getActivo(), 
                    		 					dnip, nombre, apellido,  email,  fechaNacimiento,  fechaInicio, altura,  sexo,
                    		 					calle,  provincia,  localidad,  CP,  telefono);
                     listaPacientesActivos.add(paciente);
            	 }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
        	
        }
        return listaPacientesActivos;
    }

    public static Vector<Paciente> buscarPacienteInactivoMedico(String busqueda, Medico medico) {
        System.out.println("buscarpacienteinactivomedico");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vpacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "JOIN PACIENTE ON USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "JOIN MEDICO_PACIENTE ON PACIENTE.ID_USUARIO = MEDICO_PACIENTE.ID_UsuarioPac\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 0 AND(PACIENTE.NOMBRE like '%" + busqueda + "%' OR PACIENTE.DNIP like '%" + busqueda + "%' OR PACIENTE.APELLIDOS LIKE '%" + busqueda + "%')\n"
                    + "AND MEDICO_PACIENTE.ID_UsuarioMed LIKE " + medico.getId_usuario() + "\n"
                    + "order by PACIENTE.NOMBRE")) {
                while (rs.next()) { //inicia busqueda de pacientes
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    pac = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    vpacientes.add(pac);
                }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vpacientes;

    }

    public static Vector<Paciente> buscarPacienteActivoMedico(String busqueda, Medico medico) {
        System.out.println("buscarpacienteactivomedico");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vpacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "JOIN PACIENTE ON USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "JOIN MEDICO_PACIENTE ON PACIENTE.ID_USUARIO = MEDICO_PACIENTE.ID_UsuarioPac\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 1 AND(PACIENTE.NOMBRE like '%" + busqueda + "%' OR PACIENTE.DNIP like '%" + busqueda + "%' OR PACIENTE.APELLIDOS LIKE '%" + busqueda + "%')\n"
                    + "AND MEDICO_PACIENTE.ID_UsuarioMed LIKE " + medico.getId_usuario() + "\n"
                    + "order by PACIENTE.NOMBRE")) {
                while (rs.next()) { //inicia busqueda de pacientes
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    pac = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    vpacientes.add(pac);
                }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vpacientes;

    }

    public static Vector<Paciente> buscarTodosPacientesActivos(String busqueda, Medico medico) {
        System.out.println("buscartodospacientesactivos");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vpacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "JOIN PACIENTE ON USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 1 AND(PACIENTE.NOMBRE like '%" + busqueda + "%' OR PACIENTE.DNIP like '%" + busqueda + "%' OR PACIENTE.APELLIDOS LIKE '%" + busqueda + "%')\n"
                    + "order by PACIENTE.NOMBRE")) {
                System.out.println(rs);
                while (rs.next()) { //inicia busqueda de pacientes
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    pac = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    vpacientes.add(pac);
                }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vpacientes;

    }

    public static Vector<Paciente> buscarTodosPacientesInactivos(String busqueda, Medico medico) {
        System.out.println("buscartodospacientesinactivos");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Paciente pac = null;
        Vector<Paciente> vpacientes = new Vector<Paciente>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO\n"
                    + "JOIN PACIENTE ON USUARIO.ID_USUARIO = PACIENTE.ID_USUARIO\n"
                    + "WHERE USUARIO.ACTIVIDAD LIKE 0 AND(PACIENTE.NOMBRE like '%" + busqueda + "%' OR PACIENTE.DNIP like '%" + busqueda + "%' OR PACIENTE.APELLIDOS LIKE '%" + busqueda + "%')\n" 
                    + "order by PACIENTE.NOMBRE")) {
                while (rs.next()) { //inicia busqueda de pacientes
                    String dnip = rs.getString("DNIP");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String email = rs.getString("EMAIL");
                    String fechaNacimiento = rs.getString("NACIMIENTO");
                    String fechaInicio = rs.getString("INICIO");
                    int altura = rs.getInt("ALTURA");
                    String sexo = rs.getString("SEXO");
                    String calle = rs.getString("CALLE");
                    String provincia = rs.getString("PROVINCIA");
                    String localidad = rs.getString("LOCALIDAD");
                    String CP = rs.getString("CP");
                    String telefono = rs.getString("TELEFONO");
                    pac = new Paciente(dnip, nombre, apellido, email, fechaNacimiento, fechaInicio, altura, sexo, calle, provincia, localidad, CP, telefono);
                    vpacientes.add(pac);
                }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vpacientes;

    }
    
    public static Vector<Medico> buscarMedicosInactivos(String busqueda, Administrador admin) {
        System.out.println("buscarMedicosInactivos");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Medico med = null;
        Vector<Medico> vMedico = new Vector<>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO "
            		+ "JOIN MEDICO ON USUARIO.ID_USUARIO = MEDICO.ID_USUARIO "
            		+ "WHERE USUARIO.ACTIVIDAD LIKE 0 AND(MEDICO.NOMBRE like '%" + busqueda + "%' OR MEDICO.DNIM like '%" + busqueda + "%' OR MEDICO.APELLIDOS LIKE '%" + busqueda + "%') "
            		+ "ORDER BY MEDICO.NOMBRE ")) {
                while (rs.next()) { //inicia busqueda de pacientes
                    String dnip = rs.getString("DNIM");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String clinica = rs.getString("CLINICA");
                    String email = rs.getString("EMAIL");
                    med = new Medico(dnip, nombre, apellido, clinica, email);
                    vMedico.add(med);
                }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vMedico;

    }
    
    public static Vector<Medico> buscarMedicosActivos(String busqueda, Administrador admin) {
        System.out.println("buscarMedicosActivos");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        Medico med = null;
        Vector<Medico> vMedico = new Vector<>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO "
            		+ "JOIN MEDICO ON USUARIO.ID_USUARIO = MEDICO.ID_USUARIO "
            		+ "WHERE USUARIO.ACTIVIDAD LIKE 1 AND(MEDICO.NOMBRE like '%" + busqueda + "%' OR MEDICO.DNIM like '%" + busqueda + "%' OR MEDICO.APELLIDOS LIKE '%" + busqueda + "%') "
            		+ "ORDER BY MEDICO.NOMBRE ")) {
            	 while (rs.next()) { //inicia busqueda de pacientes
                     String dnip = rs.getString("DNIM");
                     String nombre = rs.getString("NOMBRE");
                     String apellido = rs.getString("APELLIDOS");
                     String clinica = rs.getString("CLINICA");
                     String email = rs.getString("EMAIL");
                     med = new Medico(dnip, nombre, apellido, clinica, email);
                     vMedico.add(med);
                 }
            }
            stmt.close();
            conexion.desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return vMedico;

    }
    
    public static Vector<Medico> SelectMedicosActivos(){
    	System.out.println("SelectMedicosActivos");
        Statement stmt = null;
        Vector<Medico> listaMedicosActivos = new Vector<Medico>();
        Medico med;
        Connection c = conexion.conectarse();
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO "
            		+ "JOIN PASS ON USUARIO.ID_USUARIO = PASS.ID_USUARIO "
            		+ "JOIN MEDICO ON MEDICO.ID_USUARIO = USUARIO.ID_USUARIO "
            		+ "WHERE USUARIO.ACTIVIDAD LIKE 1")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                	int id = rs.getInt("ID_USUARIO");
                    String name = rs.getString("NICK");
                    String pass = rs.getString("CONTRA");
                    int tipous = rs.getInt("TIPOUSER");
                    int activo = rs.getInt("ACTIVIDAD");
                	String DNI = rs.getString("DNIM");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String clinica = rs.getString("CLINICA");
                    String email = rs.getString("EMAIL");

                    med = new Medico(name, pass, id, tipous,activo, DNI, nombre, apellido, clinica, email);
                    listaMedicosActivos.add(med);
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaMedicosActivos;
    }
    
    public static Vector<Medico> SelectMedicosInactivos(){
    	System.out.println("SelectMedicosActivos");
        Statement stmt = null;
        Vector<Medico> listaMedicosInactivos = new Vector<Medico>();
        Medico med;
        Connection c = conexion.conectarse();
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO "
            		+ "JOIN PASS ON USUARIO.ID_USUARIO = PASS.ID_USUARIO "
            		+ "JOIN MEDICO ON MEDICO.ID_USUARIO = USUARIO.ID_USUARIO "
            		+ "WHERE USUARIO.ACTIVIDAD LIKE 0")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                	int id = rs.getInt("ID_USUARIO");
                    String name = rs.getString("NICK");
                    String pass = rs.getString("CONTRA");
                    int tipous = rs.getInt("TIPOUSER");
                    int activo = rs.getInt("ACTIVIDAD");
                	String DNI = rs.getString("DNIM");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDOS");
                    String clinica = rs.getString("CLINICA");
                    String email = rs.getString("EMAIL");

                    med = new Medico(name, pass, id, tipous,activo, DNI, nombre, apellido, clinica, email);
                    listaMedicosInactivos.add(med);
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaMedicosInactivos;
    }
  
    public static int idUsuarioDni(String dni){
        
        int id=0;
        Statement stmt = null;
        Connection c = conexion.conectarse();
           try {
               c.setAutoCommit(false);
               
               stmt = c.createStatement();
               try (ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM PACIENTE "
            		   +"where DNIP like '"+dni+"';")) {
                   while (rs.next()) { //inicia b�squeda del usuario
                    id = rs.getInt("ID_USUARIO");
                      }
                  }
                  stmt.close();
                  conexion.desconectar();
              } catch (SQLException e) {
                  System.err.println(e.getClass().getName() + ": " + e.getMessage());
                  System.exit(0);
              }
           return id;
    }
    
    public static int idMedicoDni(String dni){
        
        int id=0;
        Statement stmt = null;
        Connection c = conexion.conectarse();
           try {
               c.setAutoCommit(false);
               
               stmt = c.createStatement();
               try (ResultSet rs = stmt.executeQuery("SELECT ID_USUARIO FROM MEDICO "
            		   +"where DNIM like '"+dni+"';")) {
                   while (rs.next()) { //inicia b�squeda del usuario
                    id = rs.getInt("ID_USUARIO");
                      }
                  }
                  stmt.close();
                  conexion.desconectar();
              } catch (SQLException e) {
                  System.err.println(e.getClass().getName() + ": " + e.getMessage());
                  System.exit(0);
              }
           return id;
    }

    public static void updateContraTemporal(Paciente pac) {
        System.out.println("updatePassword");
        Connection c = conexion.conectarse();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE PASS SET CONTRA = '" + pac.getClaveAcceso() + "' WHERE ID_USUARIO LIKE " + pac.getId_usuario() + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Actualizacion de PASSWORD realizada");
    }

    public static double velMedia (String fecha, int idUsuario, String comentario, int idSesion){
    	System.out.println("velMedia");
    	double vel = 0;
        Statement stmt = null;
        Medico med;
        Connection c = conexion.conectarse();
        try {
            c.setAutoCommit(false);

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT AVG(REGISTRO.SPEED) "
            		+ "FROM REGISTRO "
            		+ "JOIN SESIONES ON SESIONES.ID_SESIONES = REGISTRO.ID_SESIONES "
            		+ "JOIN PACIENTE ON PACIENTE.ID_USUARIO = SESIONES.ID_USUARIO "
            		+ "WHERE PACIENTE.ID_USUARIO LIKE "+idUsuario+" AND (SESIONES.ID_SESION LIKE "+ idSesion +" SESIONES.FECHA LIKE '"+fecha+"' AND SESIONES.COMENTARIO LIKE '"+comentario+"')")) {
                while (rs.next()) {	//inicia b�squeda del usuario
                	vel = rs.getDouble("AVG(REGISTRO.SPEED)");
                }
            }
            stmt.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
   	
    	return vel;
    }
    

}
