package Modelo;

import java.util.Vector;

public class Administrador extends Usuario {
private int id_administrador;
private Vector<Medico> vectMedicos;
/**
 * @param id_administrador
 * @param id_usuario
 * @param vectMedicos
 */
public Administrador(String nombreUsuario, String contrasena,int id_usuario, int tipoUsuario, int activo,int id_administrador){
	super(nombreUsuario,contrasena,id_usuario,tipoUsuario, activo);
	this.id_administrador=id_administrador;
	
	
}
public int getId_administrador() {
	return id_administrador;
}
public void setId_administrador(int id_administrador) {
	this.id_administrador = id_administrador;
}

public Vector<Medico> getVectMedicos() {
	return vectMedicos;
}
public void setVectMedicos(Vector<Medico> vectMedicos) {
	this.vectMedicos = vectMedicos;
}
}