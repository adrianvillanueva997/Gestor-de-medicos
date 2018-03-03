package Modelo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Paciente extends Usuario {
	private String DNI;
	private String nombre;
	private String apellido;
	private String email;
	private String fechaNacimiento;
	private String fechaInicio;
	private double altura;
	private String sexo;
	private String imagen;
	private String calle;
	private String provincia;
	private String localidad;
	private String CP;
	private String telefono;
	private String claveRecuperacion;
	private int edad;
	private float frecCardMaxima;
	/**
	 * @param nombreUsuario
	 * @param contrasena
	 * @param id_usuario
	 * @param tipoUsuario
	 * @param dNI
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param fechaNacimiento
	 * @param fechaInicio
	 * @param altura
	 * @param sexo 
	 * @param calle
	 * @param provincia
	 * @param localidad
	 * @param cP
	 * @param telefono
	 */
	public Paciente(String nombreUsuario, String contrasena,int id_usuario, int tipoUsuario, int activo, String dNI, String nombre,
			String apellido, String email, String fechaNacimiento, String fechaInicio, double altura, String sexo,
			String calle, String provincia, String localidad, String cP, String telefono) {
		super(nombreUsuario, contrasena,id_usuario, tipoUsuario, activo);
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInicio = fechaInicio;
		this.altura = altura;
		this.sexo = sexo;
		
		this.calle = calle;
		this.provincia = provincia;
		this.localidad = localidad;
		CP = cP;
		this.telefono = telefono;
		edad=edad();
		frecCardMaxima=208-((0.7f)*edad);
	}
	
	public Paciente(String dNI, String nombre, String apellido, String email, String fechaNacimiento, String fechaInicio, double altura, String sexo,
			String calle, String provincia, String localidad, String cP, String telefono){
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInicio = fechaInicio;
		this.altura = altura;
		this.sexo = sexo;
		
		this.calle = calle;
		this.provincia = provincia;
		this.localidad = localidad;
		CP = cP;
		this.telefono = telefono;
		edad=edad();
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}
	/**
	 * @param altura the altura to set
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return the cP
	 */
	public String getCP() {
		return CP;
	}
	/**
	 * @param cP the cP to set
	 */
	public void setCP(String cP) {
		CP = cP;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	protected String generarClave(){
		return this.claveRecuperacion = UUID.randomUUID().toString();
	}
	public String getClaveAcceso(){
		return this.claveRecuperacion;
	}
	
	public int edad(){
	  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	  LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);// Fecha de nacimiento
	  LocalDate ahora = LocalDate.now();// Fecha actual

	  Period periodo = Period.between(fechaNac, ahora);// Diferencia entre fecha naciemiento y fecha actual
	  return periodo.getYears();
		  
	}
	
	 public float frecCardMaxima(){
		 return frecCardMaxima;
	 }
}
