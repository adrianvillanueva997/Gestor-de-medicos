package Modelo;


public class Usuario {
	private String nombreUsuario;
	private String contrasena;
	private int id_usuario;
	private int tipoUsuario;
	private int activo;
	/**
	 * @param nombreUsuario
	 * @param contrasena
	 * @param id_usuario 
	 * @param tipoUsuario
	 */
	public Usuario(String nombreUsuario, String contrasena,int id_usuario, int tipoUsuario, int activo) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.id_usuario=id_usuario;
		this.tipoUsuario = tipoUsuario;
		this.activo = activo;
	}
	
	public Usuario(){
		
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	/**
	 * @return the tipoUsuario
	 */
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}