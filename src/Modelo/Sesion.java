package Modelo;

import java.util.Vector;

public class Sesion {
	private Vector<String> vectorTiempo;
	private Vector<Float> vectorAltitud;
	private Vector<Float> vectorSaturacion;
	private Vector<Float> vectorPulsacion;
	private Vector<Float> vectorVelocidad;
	private Vector <Float> vectorLatitud;
	private Vector <Float> vectorLongitud;
	private Vector<Float> vectorDistancia;
	private int id_sesion;
	private int id_usuario;
	private int peso;
	private String fecha;
	private int estado;
	private String comentario;
	private float IMC;

	/**
	 * @param vectorTiempo
	 * @param vectorAltitud
	 * @param vectorSaturacion
	 * @param vectorPulsacion
	 * @param vectorVelocidad
	 * @param vectorLatitud
	 * @param vectorLongitud
	 * @param distancia
	 * @param id_sesion
	 * @param id_usuario
	 * @param fecha
	 * @param estado
	 * @param comentario
	 * @param IMC
	 */
	public Sesion(int id_sesion,int id_usuario,Vector<String> vectorTiempo,Vector<Float> vectorLatitud,Vector<Float> vectorLongitud, Vector<Float> vectorAltitud, 
			 Vector<Float> vectorVelocidad,Vector<Float>vectorDistancia,Vector<Float> vectorPulsacion, Vector<Float> vectorSaturacion,
			 int peso,String fecha,int estado,String comentario, float iMC) {
		
		this.vectorTiempo = vectorTiempo;
		this.vectorAltitud = vectorAltitud;
		this.vectorSaturacion = vectorSaturacion;
		this.vectorPulsacion = vectorPulsacion;
		this.vectorVelocidad = vectorVelocidad;
		this.vectorLatitud = vectorLatitud;
		this.vectorLongitud = vectorLongitud;
		this.vectorDistancia=vectorDistancia;
		this.setId_sesion(id_sesion);
		this.setId_usuario(id_usuario);
		this.setPeso(peso);
		this.setFecha(fecha);
		this.setEstado(estado);
		this.setComentario(comentario);
		IMC = iMC;
	}
	public Sesion(int id_sesion,int id_usuario,int peso,String fecha,int estado,String comentario,float imc){
		this.setId_sesion(id_sesion);
		this.setId_usuario(id_usuario);
		this.setPeso(peso);
		this.setFecha(fecha);
		this.setEstado(estado);
		this.setComentario(comentario);
		IMC = imc;
	}
	/**
	 * @return the vectorTiempo
	 */
	public Vector<String> getVectorTiempo() {
		return vectorTiempo;
	}

	/**
	 * @param vectorTiempo the vectorTiempo to set
	 */
	public void setVectorTiempo(Vector<String> vectorTiempo) {
		this.vectorTiempo = vectorTiempo;
	}

	/**
	 * @return the vectorAltitud
	 */
	public Vector<Float> getVectorAltitud() {
		return vectorAltitud;
	}

	/**
	 * @param vectorAltitud the vectorAltitud to set
	 */
	public void setVectorAltitud(Vector<Float> vectorAltitud) {
		this.vectorAltitud = vectorAltitud;
	}

	/**
	 * @return the vectorSaturacion
	 */
	public Vector<Float> getVectorSaturacion() {
		return vectorSaturacion;
	}

	/**
	 * @param vectorSaturacion the vectorSaturacion to set
	 */
	public void setVectorSaturacion(Vector<Float> vectorSaturacion) {
		this.vectorSaturacion = vectorSaturacion;
	}

	/**
	 * @return the vectorPulsacion
	 */
	public Vector<Float> getVectorPulsacion() {
		return vectorPulsacion;
	}

	/**
	 * @param vectorPulsacion the vectorPulsacion to set
	 */
	public void setVectorPulsacion(Vector<Float> vectorPulsacion) {
		this.vectorPulsacion = vectorPulsacion;
	}

	/**
	 * @return the vectorVelocidad
	 */
	public Vector<Float> getVectorVelocidad() {
		return vectorVelocidad;
	}

	/**
	 * @param vectorVelocidad the vectorVelocidad to set
	 */
	public void setVectorVelocidad(Vector<Float> vectorVelocidad) {
		this.vectorVelocidad = vectorVelocidad;
	}

	/**
	 * @return the vectorLatitud
	 */
	public Vector<Float> getVectorLatitud() {
		return vectorLatitud;
	}

	/**
	 * @param vectorLatitud the vectorLatitud to set
	 */
	public void setVectorLatitud(Vector<Float> vectorLatitud) {
		this.vectorLatitud = vectorLatitud;
	}

	/**
	 * @return the vectorLongitud
	 */
	public Vector<Float> getVectorLongitud() {
		return vectorLongitud;
	}

	/**
	 * @param vectorLongitud the vectorLongitud to set
	 */
	public void setVectorLongitud(Vector<Float> vectorLongitud) {
		this.vectorLongitud = vectorLongitud;
	}

	/**
	 * @return the distancia
	 */
	public Vector<Float> getDistancia() {
		return vectorDistancia;
	}

	/**
	 * @param distancia the distancia to set
	 */
	public void setDistancia(Vector<Float> vectorDistancia) {
		this.vectorDistancia = vectorDistancia;
	}

	/**
	 * @return the iMC
	 */
	public float getIMC() {
		return IMC;
	}

	/**
	 * @param iMC the iMC to set
	 */
	public void setIMC(float iMC) {
		IMC = iMC;
	}
	public int getId_sesion() {
		return id_sesion;
	}
	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	

}