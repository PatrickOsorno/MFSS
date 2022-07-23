/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static org.primefaces.component.keyboard.KeyboardBase.PropertyKeys.password;

/**
 *
 * @author Melisa
 */
public class Utilitarios {
//Se valida que el ingreso no haya espacios en blanco
//      inicioBean=>index(btnIngresar)

    public static boolean validacionInicio(String usuario, String contrasena, String rol) {
        return (usuario.equals("") || contrasena.equals("") || rol.equals(""));
    }

//Se valida que el autorregistro del cliente no halla espacios en nulo
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroUsuarios)
    public static boolean validacionRegistroCliente(String identificacion, String nombre, String apellidos, String correo, String telefono) {
        if (identificacion.equals("") || nombre.equals("") || apellidos.equals("") || correo.equals("") || telefono.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que el registro de direcciones no halla espacios en nulo
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroDirecciones)
    public static boolean validacionRegistroDireccion(String tipoDireccion, String provincia, String canton, String distrito, String barrio, String otrasSenas) {
        if (tipoDireccion.equals("") || provincia.equals("") || canton.equals("") || distrito.equals("") || barrio.equals("") || otrasSenas.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que el registro de Horarios no halla espacios en nulo
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroHorarios)
//Arreglar
    public static boolean validacionRegistroHorario(Date fechaHoraInic, Date fechaHoraFin) {
        if (fechaHoraFin.equals("") || fechaHoraInic.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que el gestion de usuarios, en las solicitudes no halla espacios en nulo
//      GestionUsuariosBean=>GestionUsuarios(btnAceptar => Solicitudes)
//Arreglar
    public static boolean validarGestionSolicitudes(String seleccionado) {
        if (seleccionado.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que el gestion de usuarios, en los usuarios no halla espacios en nulo
//      GestionUsuariosBean=>GestionUsuarios(btnCrearUsuario => NuevoUsuario)
    public static boolean validarGestionNuevUsuario(String correo, String contrasena, String rolUsuario) {
        if (correo.equals("") || contrasena.equals("") || rolUsuario.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que el gestion de Productos, en el mantenimiento de Productos no halla espacios en nulo
//      GestionProductosBean=>GestionProductos(btnNuevo y btnEditar => MantenimientoProductos)
    public static boolean validarProductos_Nuevo(int codigo, String descripcion, String foto, float precio, int stock, int cantMinima) {
        if (codigo == 0 || descripcion.equals("") || foto.equals("") || precio == 0f || stock == 0 || cantMinima == 0) {
            return false;
        }
        return true;
    }

//Se valida que el gestion de Pedidos, en la confirmacion de orden no halla espacios en nulo
//      PedidosBean=>Pedidos(btnConfirmarOrden => ConfirmarOrden)
//Arreglar
//    public static boolean validarDatosEntrega(Date fechaEntrega, Object direccionEntrega, Object horarioEntrega) {
//        if (fechaEntrega) {
//
//        }
//
//        return true;
//    }
//Se valida que los reportes, en el reporte de Pedidos no halla espacios en nulo
//      ReportesBean=>Reportes(btnMostrarReporte => ReportePedidos)
    public static boolean validarMostrarReporte_Pedidos(Date rangoFecha, String estado) {
        if (rangoFecha.equals("") || estado.equals("")) {
            return false;
        }
        return true;
    }

//Se valida que los reportes, en el reporte de Ventas no halla espacios en nulo
//      ReportesBean=>Reportes(btnMostrarReporte => ReporteVentas)
    public static boolean validarMostrarReporte_Ventas(Date rangoFecha, String tipoPago) {
        if (rangoFecha.equals("") || tipoPago.equals("")) {
            return false;
        }
        return true;
    }

//Mediante este metodo se le enviará al solicitante si fue aprovada su inscripcion
    public static void enviarCorreo(String correo, String mensaje) {
        Properties propiedad = new Properties();
        propiedad.put("mail.smtp.host", "smtp.gmail.com");
        propiedad.put("mail.smtp.ssl.trust", "*");
        propiedad.put("mail.smtp.port", "587");
        propiedad.put("mail.smtp.auth", "true");

        Session sesion = Session.getInstance(propiedad, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("pruebas.patrick.utn@gmail.com", "prueb@sMFSS");
	            }
	        });
        Message mail = new MimeMessage(sesion);
        try {

            mail.setFrom(new InternetAddress("pruebas.patrick.utn@gmail.com"));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject("Bienvenido al Sistema");
            mail.setText(mensaje);
            Transport.send(mail);
        } catch (AddressException ex) {

        } catch (MessagingException ex) {

        }
    }
}
