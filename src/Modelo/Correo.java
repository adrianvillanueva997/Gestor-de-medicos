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

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author APUS
 */
public class Correo {

    /**
     *
     */
    private final String direccionContacto = "contactoapus@gmail.com";

    /**
     *
     */
    private final String contraContacto = "APUS1234";

    /**
     *
     */
    private Properties properties;

    /**
     *
     */
    private void setPropierties() {
        properties = new Properties();
        // Nombre del host de correo, es smtp.gmail.com
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si estó disponible
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        properties.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        properties.setProperty("mail.smtp.user", "ejemplo@gmail.com");

        // Si requiere o no usuario y password para conectarse.
        properties.setProperty("mail.smtp.auth", "true");
    }

    /**
     *
     * @param asunto
     * @param idMensaje
     * @param paciente
     */
    public void enviarMensajePaciente(Paciente paciente, String asunto, int idMensaje) {
        setPropierties();
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(direccionContacto, contraContacto);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(direccionContacto));
        } catch (AddressException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(paciente.getEmail()));
        } catch (AddressException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setSubject(asunto);
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setText(establecerMensajePaciente(idMensaje, paciente) + firma(), "utf-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            Transport t = session.getTransport("smtp");
            t.connect(direccionContacto, contraContacto);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String establecerMensajePaciente(int idMensaje, Paciente paciente) {
        String mensaje = null;
        switch (idMensaje) {
            case 1:
                mensaje
                        = "<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido() + ": </p>"
                        + "<p>Su sesión ha sido subida con óxtito.</p>"
                        + "<p><p>óGracias por confiar en SlimUE!</p></p>";
                break;
            case 2:
                mensaje
                        = "<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido() + ": </p>"
                        + "<p>Su contraseóa es: " + paciente.getContrasena() + ".</p>"
                        + "<p><p>óGracias por confiar en SlimUEM!</p></p>";
                break;
            case 3:
                mensaje
                        = "<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido() + ": </p>"
                        + "<p>Usted ha sido dado de alta en SlimUEM, los datos para acceder a la plataforma son los siguientes:</p>"
                        + "<ul>"
                        + "<li><strong>Usuario:</strong> " + paciente.getNombreUsuario() + "</li>"
                        + "<li><strong>Contraseóa:</strong> " + paciente.getContrasena() + "</li>"
                        + "</ul>"
                        + "<p><p>óGracias por confiar en SlimUE!</p></p>";
                break;
            case 4:
                mensaje
                        = "<p> Estimado Sr/Sra " + paciente.getNombre() + " " + paciente.getApellido() + ": </p>"
                        + "<p>Usted ha solicitado cambiar la contraseña, introduzca el código de autentificación a la hora de iniciar se sesión en el sistema para cambiar la contraseña:</p>"
                        + "<ul>"
                        + "<li><strong>Código:</strong> " + paciente.generarClave() + "</li>"
                        + "</ul>"
                        + "<p><p>¡Gracias por confiar en SlimUE!</p></p>";
                Fichero.updateContraTemporal(paciente);
                break;
        }
        return mensaje;
    }

    /**
     *
     * @return
     */
    private String firma() {
        String firma
                = "<meta charset=\"UTF-8\">"
                + "<title>CORREO APUS</title>"
                + "<p style=\"font-family: Helvetica, Arial, sans-serif; font-size: 12px; line-height: 14px; margin-bottom: 10px;\">"
                + "<a style=\"text-decoration:none\" href=\"http://apus.com/\" target=\"_blank\" class=\"clink logo-container\">"
                + "<img src=\"http://i.imgur.com/cO3mHWj.jpg\" alt=\"banner logo apus\" title=\"Acceso web APUS\" border=\"0\" class=\"sig-logo\" height=\"\" width=\"\">"
                + "</a>"
                + "</p>"
                + "<p style=\"font-family: Helvetica, Arial, sans-serif; font-size: 14px; line-height: 14px; color: rgb(33, 33, 33); margin-bottom: 10px;\"><span style=\"font-weight: bold; color: rgb(33, 33, 33); display: inline;\" class=\"txt signature_companyname-target sig-hide\">APUS INC.</span>"
                + "</p>"
                + "</p>"
                + "<span class=\"title-sep sep\" style=\"display: inline;\"></span> <span style=\"color: rgb(33, 33, 33); display: inline;\" class=\"txt signature_jobtitle-target sig-hide\">¡SOFTWARE PARA TODOS!</span>"
                + "<span class=\"email-sep break\" style=\"display: inline;\"><br></span>"
                + "<a class=\"link email signature_email-target sig-hide\" href=\"mailto:contactoapus@gmail.com\" target=\"_blank\" style=\"color: rgb(71, 124, 204); text-decoration: none; display: inline;\">contactoapus@gmail.com</a><span style=\"color: #212121;\" class=\"txt"
                + "signature_mobilephone-target sig-hide\"></span></p>"
                + "<p></p>"
                + "<p></p>"
                + "<p style=\"font-family: Helvetica, Arial, sans-serif; font-size: 12px; line-height: 14px; margin-bottom: 10px;\"> "
                + "<span style=\"font-weight: bold; color: rgb(33, 33, 33); display: inline;\" class=\"txt signature_name-target sig-hide\">APUS INC.</span>"
                + "<span class=\"company-sep break\" style=\"display: inline;\"><br></span>"
                + "<span style=\"color: rgb(33, 33, 33); display: inline;\" class=\"txt signature_officephone-target sig-hide\">+34 XXX XXX XXX</span>"
                + "<span class=\"address-sep break\" style=\"display: inline;\"><br></span> <span style=\"color: rgb(33, 33, 33); display: inline;\" class=\"txt signature_address-target sig-hide\">28670 Villaviciosa de Odón, ESPAÑA</span>"
                + "<span class=\"address2-sep break\"></span>"
                + "<span class=\"website-sep break\" style=\"display: inline;\"><br></span>"
                + "<a class=\"link signature_website-target sig-hide\" href=\"http://www.apus.com/\" target=\"_blank\" style=\"color: rgb(71, 124, 204); text-decoration: none; display: inline;\">www.apus.com/</a>"
                + "</p>"
                + "<p class=\"social-list\" style=\"font-size: 0px; line-height: 0; font-family: Helvetica, Arial, sans-serif;\">"
                + "<a style=\"text-decoration: none; display: inline;\" class=\"social signature_twitter-target sig-hide\" href=\"https://twitter.com/apus/\" target=\"_blank\"><img width=\"32\" style=\"margin-bottom:2px; border:none; display:inline;\" height=\"32\" data-filename=\"twitter_bird_n.png\" src=\"http://joanmorci.com/wp-content/uploads/2015/12/twitter_bird_n.png\" alt=\"Twitter\" title=\"Ver perfil de APUS en Twitter\"></a><span style=\"white-space:nowrap;\" class=\"signature_twitter_bird_n-sep\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/spacer.gif\" width=\"2\"></span>"
                + "<a style=\"text-decoration: none; display: inline;\" class=\"social signature_googleplus-target sig-hide\" href=\"https://plus.google.com/apus\" target=\"_blank\"><img width=\"32\" style=\"margin-bottom:2px; border:none; display:inline;\" height=\"32\" data-filename=\"google_plus_n.png\" src=\"http://joanmorci.com/wp-content/uploads/2015/12/google_plus_n.png\" alt=\"Google +\" title=\"Ver perfil de + APUS en Google+\"></a><span style=\"white-space:nowrap;\" class=\"signature_google_plus_n-sep\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/spacer.gif\" width=\"2\"></span> "
                + "<a style=\"text-decoration: none; display: inline;\" class=\"social signature_linkedin-target sig-hide\" href=\"https://www.linkedin.com/in/apus\" target=\"_blank\"><img width=\"32\" style=\"margin-bottom:2px; border:none; display:inline;\" height=\"32\" data-filename=\"linkedin_n.png\" src=\"http://joanmorci.com/wp-content/uploads/2015/12/linkedin_n.png\" alt=\"LinkedIn\" title=\"Ver perfil de APUS en LinkedIn\"></a><span style=\"white-space:nowrap;\" class=\"signature_linkedin_n-sep\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/spacer.gif\" width=\"2\"></span> "
                + "<a style=\"text-decoration: none; display: inline;\" class=\"social signature_youtube-target sig-hide\" href=\"https://www.youtube.com/user/Apus/\" target=\"_blank\"><img width=\"32\" style=\"margin-bottom:2px; border:none; display:inline;\" height=\"32\" data-filename=\"youtube_n.png\" src=\"http://joanmorci.com/wp-content/uploads/2015/12/youtube_n.png\" alt=\"Youtube\" title=\"Ver perfil de APUS en Youtube\"></a><span style=\"white-space:nowrap;\" class=\"signature_youtube_n-sep\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/spacer.gif\" width=\"2\"></span>"
                + "<a style=\"text-decoration: none; display: inline;\" class=\"social signature_email-target sig-hide\" href=\"mailto:contactoapus@gmail.com\" target=\"_blank\"><img width=\"32\" style=\"margin-bottom:2px; border:none; display:inline;\" height=\"32\" data-filename=\"email_n.png\" src=\"http://joanmorci.com/wp-content/uploads/2015/12/email_n.png\" alt=\"Email\" title=\"Enviar e-mail a: contactoapus@gmail.com\"></a><span style=\"white-space:nowrap;\" class=\"signature_email_n-sep\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/spacer.gif\" width=\"2\"></span>"
                + "</p>"
                + "</p>"
                + "<p style=\"font-family: Helvetica, Arial, sans-serif; color: #828282; font-size: 9px; line-height: 12px;\" class=\"txt signature_disclaimer-target\">Protección de Datos: En cumplimiento de la L.O. 15/ 1999 de 13 de Diciembre de Protección de Datos de Carácter Personal, APUS le informa que su dirección de correo electrónico, asó como el resto de los datos de carácter personal que nos facilite , se encuentran incorporados en un fichero del que es responsable esta empresa y cuya finalidad es gestionar nuestra agenda de contactos y el envóo de comunicaciones electrónicas profesionales y/o personales, comerciales e informativas. Así mismo se le informa que podrá ejercitar el derecho de acceso, rectificación, cancelación y oposición en contactoapus@gmail.com. Confidencialidad. El contenido de esta comunicación, así como el de toda la documentación anexa, es confidencial, puede estar protegido por disposiciones legales y va dirigido únicamente al destinatario del mismo. En el supuesto de que usted no fuera el destinatario, le solicitamos que nos lo indique y no comunique su contenido a terceros, procediendo a su destrucción.<p style=\"font-family: Helvetica, Arial, sans-serif; color: #828282; font-size: 9px; line-height: 12px;\" class=\"txt signature_disclaimer-target\">Copyright (C) 2017 APUS"
                + "This program is free software: you can redistribute it and/or modify"
                + "it under the terms of the GNU Lesser General Public"
                + "License as published by the Free Software Foundation; either"
                + "version 2.1 of the License, or (at your option) any later version."
                + "This program is distributed in the hope that it will be useful,"
                + "but WITHOUT ANY WARRANTY; without even the implied warranty of"
                + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the"
                + "Lesser General Public License for more details."
                + "You should have received a copy of the GNU Lesser General Public"
                + "along with this program.  If not, see <http://www.gnu.org/licenses/>.</p>"
                + "<p style=\"font-family: Helvetica, Arial, sans-serif; color: #689804; font-size: 9px; line-height: 12px;\"><img src=\"http://joanmorci.com/wp-content/uploads/2015/12/ico-eco.gif\" alt=\"eco\" width=\"14\" height=\"14\" align=\"absmiddle\" /> No me imprimas si no es necesario. Protege el medio ambiente.</font></p>";

        return firma;
    }

}