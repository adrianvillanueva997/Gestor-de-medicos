/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author adrian
 */
public class Conexion {

    private static Properties properties;
    private static String USERNAME = "UsuarioGenerico";
    private static String PASSWORD = "user1";
    private static String MAXPOOL = "250";
    private final static String URL = "jdbc:mysql://185.117.72.136:3306/proyectoinfo2";
    private static Connection connection;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAXPOOL);
        }
        return properties;
    }

    protected Connection conectarse() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("No se ha encontrado el driver", e);
            }
            try {
                connection = DriverManager.getConnection(URL, getProperties());
            } catch (SQLException e) {
                throw new IllegalStateException("No se ha podido conectar a la base de datos", e);
            }
        }
        return connection;
    }

    public void desconectar() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
