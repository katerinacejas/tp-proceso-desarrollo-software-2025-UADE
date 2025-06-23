package modelo.adapter;

import modelo.entidad.notificacion.Notificacion;

public class AdapterEmailJavaMail implements IAdapterEmail {

    private String servidorSMTP;
    private int puerto;
    private String usuario;
    private String password;

    public AdapterEmailJavaMail() {
        this.servidorSMTP = "smtp.gmail.com";
        this.puerto = 587;
        this.usuario = "info@deportesapp.com";
        this.password = "password123";
    }

    public AdapterEmailJavaMail(String servidor, int puerto, String usuario, String password) {
        this.servidorSMTP = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public void enviarEmail(Notificacion notificacion) {
        if (notificacion == null || notificacion.getEmailDestinatario() == null) {
            System.out.println("Error: No se puede enviar email - datos incompletos");
            return;
        }

        try {
            // Simulación del envío de email
            System.out.println("=== ENVIANDO EMAIL ===");
            System.out.println("Servidor SMTP: " + servidorSMTP + ":" + puerto);
            System.out.println("De: " + (notificacion.getEmailRemitente() != null ? notificacion.getEmailRemitente() : usuario));
            System.out.println("Para: " + notificacion.getEmailDestinatario());
            System.out.println("Asunto: Notificación de Partido - Deportes App");
            System.out.println("Mensaje: " + notificacion.getMensaje());
            System.out.println("======================");


            // Simular tiempo de envío
            Thread.sleep(100);

        } catch (Exception e) {
            System.err.println("Error al enviar email: " + e.getMessage());
        }
    }

    public void configurarServidor(String servidor, int puerto) {
        this.servidorSMTP = servidor;
        this.puerto = puerto;
    }

    public void configurarCredenciales(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
}