/*
 * Copyright (C) 2017 APUS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package Modelo;

public class MensajeCorreo {

	public String sesionSubidaPaciente(Paciente paciente){
		String mensaje =
		"<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido()+ ": </p>" +
		"<p>Su sesión ha sido subida con éxtito.</p>" +

		"<p><p>�Gracias por confiar en SlimUE!</p></p>";


		return mensaje;
	}
	public String saberDatosPaciente(Paciente paciente){
		String mensaje =
		"<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido()+ ": </p>" +
		"<p>Su contrase�a es: " + paciente.getContrasena()+ ".</p>" +

		"<p><p>�Gracias por confiar en SlimUEM!</p></p>";


		return mensaje;
	}
	public String pacienteRegistrado(Paciente paciente){
		String mensaje =
		"<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido()+ ": </p>" +
		"<p>Usted ha sido dado de alta en SlimUEM, los datos para acceder a la plataforma son los siguientes:</p>" +
		"<ul>"+
			"<li><strong>Usuario:</strong> "+ paciente.getNombreUsuario() + "</li>"+
			"<li><strong>Contrase�a:</strong> "+ paciente.getContrasena() + "</li>"+

		"</ul>"+

		"<p><p>�Gracias por confiar en SlimUE!</p></p>";


		return mensaje;
	}
	public String cambiarContrasena(Paciente paciente){
		String mensaje =
		"<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido()+ ": </p>" +
		"<p>Usted ha solicitado una clave para cambiar la contrase�a, en caso afirmativo introduzca la siguiente clave para "
		+ "cambiar la clave de acceso, en caso negativo, ignore este correo.</p>" +
		"<ul>"+
			"<li><strong>Clave:</strong> "+ paciente.generarClave() + "</li>"+

		"</ul>"+

		"<p><p>�Gracias por confiar en SlimUE!</p></p>";


		return mensaje;
	}
	
}
