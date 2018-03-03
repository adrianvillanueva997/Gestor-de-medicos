package Modelo;

public class Medico extends Usuario {
	private String DNI;
	private String nombre;
	private String apellido;
	private String clinica;
	private String email;
	/**
	 * @param nombreUsuario
	 * @param contrasena
	 * @param tipoUsuario
	 * @param dNI
	 * @param nombre
	 * @param apellido
	 * @param clinica
	 * @param numeroColegiado
	 */
	public Medico(String nombreUsuario, String contrasena,int id_usuario, int tipoUsuario, int activo, String dNI, String nombre, String apellido,
			String clinica, String email) {
		super(nombreUsuario, contrasena,id_usuario, tipoUsuario, activo);
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clinica = clinica;
		this.email = email;
	}
	public Medico(int id_usuario, String dNI, String nombre, String apellido,
			String clinica, String email) {
		this.setId_usuario(id_usuario);
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clinica = clinica;
		this.email = email;
	}
	public Medico(){
		
	}
	
	public Medico(String dNI, String nombre, String apellido, String clinica, String email) {
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clinica = clinica;
		this.email = email;
	}
	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the clinica
	 */
	public String getClinica() {
		return clinica;
	}
	/**
	 * @param clinica the clinica to set
	 */
	public void setClinica(String clinica) {
		this.clinica = clinica;
	}
	/**
	 * @return the numeroColegiado
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param numeroColegiado the numeroColegiado to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	


}
