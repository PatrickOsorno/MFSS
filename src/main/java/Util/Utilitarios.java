/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Model.Entidades.Cliente;
import Model.Entidades.Direccion;
import Model.Entidades.Horario;
import Model.Entidades.MedioDespacho;
import Model.Entidades.Pedido;
import Model.Entidades.PedidoDetalle;
import Model.Entidades.Usuario;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Melisa
 */
public class Utilitarios {
//Se valida que en el ingreso no hayan espacios en blanco
//      inicioBean=>index(btnIngresar)

    public static boolean validacionInicio(String usuario, String contrasena, int rol) {
        return (usuario.equals("") || contrasena.equals("") || rol == 0);
    }

//Se valida que en el autorregistro del cliente no hayan espacios en blanco
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroUsuarios)
    public static boolean validacionRegistroCliente(String identificacion, String nombre, String apellidos, String correo, String telefono) {
        return (identificacion.equals("") || nombre.equals("") || apellidos.equals("") || correo.equals("") || telefono.equals(""));
    }

//Se valida que en el registro de direcciones no hayan espacios en blanco
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroDirecciones)
    public static boolean validacionRegistroDireccion(int tipoDireccion, int provincia, int canton, int distrito, int barrio, String otrasSenas) {
        return (tipoDireccion == 0 || provincia == 0 || canton == 0 || distrito == 0 || barrio == 0 || otrasSenas.equals(""));
    }

//Se valida que en el registro de Horarios no hayan espacios en blanco
//      RegistroBean=>RegistroClientes(btnGuardar => RegistroHorarios)
    public static boolean validacionRegistroHorario(Date fechaHoraInic, Date fechaHoraFin) {
        return (fechaHoraFin == null || fechaHoraInic == null);
    }

    public static boolean validacionRegistroClienteBd(Cliente cliente) {
        return (cliente == null || cliente.getHorarios().isEmpty() || cliente.getHorarios().isEmpty());
    }
//Se valida que en el mantenimiento de Productos no hayan espacios en blanco
//      GestionProductosBean=>GestionProductos(btnNuevo y btnEditar => MantenimientoProductos)
    public static boolean validarProductoNuevo(int codigo, String descripcion, String foto, float precio, int stock, int cantMinima) {
        return (codigo == 0 || descripcion.equals("") || foto.equals("") || precio == 0f || stock == 0 || cantMinima == 0);
    }

//Se valida que el gestion de Pedidos, en la confirmacion de orden no halla espacios en nulo
//      PedidosBean=>Pedidos(btnConfirmarOrden => ConfirmarOrden)
    public static boolean validarDatosPedido(List<PedidoDetalle> detalles, Date fechaEntrega,
            Direccion direccionEntrega, Horario horarioEntrega, MedioDespacho medioDespacho) {
        return (detalles.isEmpty() || fechaEntrega == null || direccionEntrega == null || horarioEntrega == null || medioDespacho == null);
    }
//Se valida en el reporte de Pedidos no hayan espacios en blanco
//      ReportesBean=>Reportes(btnMostrarReporte => ReportePedidos)

    public static boolean validarMostrarReportePedidos(List<Date> fecha, int estado) {
        return (fecha == null || estado == 0);
    }

//Se valida que  en el reporte de Ventas no hayan espacios en blanco
//      ReportesBean=>Reportes(btnMostrarReporte => ReporteVentas)
    public static boolean validarMostrarReporteVentas(List<Date> fecha, int tipoPago) {
        return (fecha == null || tipoPago == 0);
    }

    public static boolean validarFacturacion(List<Pedido> pedidos, int tipoPago) {
        return (pedidos.isEmpty() || tipoPago == 0);
    }

    public static boolean validarDespacho(String observacion) {
        return (observacion.equals(""));
    }

//Mediante este metodo se le enviará al solicitante si fue aprovada su inscripcion
    public static void enviarCorreo(String correo, String mensaje, String asunto) {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.office365.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.ssl.trust", "smtp.office365.com");
        propiedades.setProperty("mail.smtp.port", "589");
        propiedades.setProperty("mail.smtp.port", "mpfss@outlook.es");
        propiedades.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedades);
        try {
            MimeMessage mail = new MimeMessage(sesion);
            mail.setFrom(new InternetAddress("mpfss@outlook.es"));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            try ( Transport transporte = sesion.getTransport("smtp")) {
                transporte.connect("mpfss@outlook.es", "Hol@1234");
                transporte.sendMessage(mail, mail.getAllRecipients());
            }
        } catch (MessagingException ex) {
            ex.getMessage();
        }
    }

//    Se genera una contraseña aleatoria para el ingreso del usuario al sistema
    public static String genearContrasenaAleatoria() {

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        int cantCaracteres = (int) (Math.random() * (16 - 8 + 1) + 8);
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantCaracteres; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    public static boolean administrador(Usuario usuario) {
        return usuario.getRol().getId() == 1;
    }

    public static boolean cliente(Usuario usuario) {
        return usuario.getRol().getId() == 2;
    }

    public static boolean bodeguero(Usuario usuario) {
        return usuario.getRol().getId() == 3;
    }

    public static boolean produccion(Usuario usuario) {
        return usuario.getRol().getId() == 4;
    }
}
