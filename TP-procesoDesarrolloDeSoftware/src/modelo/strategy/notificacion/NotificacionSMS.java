package modelo.strategy.notificacion;

import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionSMS implements IServicioNotificacion {

    @Override
    public void notificar(List<Notificacion> notificaciones) {
        if (notificaciones == null || notificaciones.isEmpty()) {
            System.out.println("No hay notificaciones para enviar por SMS");
            return;
        }

        System.out.println("Iniciando envío de " + notificaciones.size() + " notificaciones SMS...");

        for (Notificacion notificacion : notificaciones) {
            try {
                enviarSMS(notificacion);
                System.out.println("✓ SMS enviado a: " + notificacion.getCelularDestinatario());
            } catch (Exception e) {
                System.err.println("✗ Error enviando SMS a: " + notificacion.getCelularDestinatario() +
                        " - " + e.getMessage());
            }
        }

        System.out.println("Proceso de envío de SMS completado");
    }

    private void enviarSMS(Notificacion notificacion) {
        System.out.println("=== ENVIANDO SMS ===");
        System.out.println("Desde: " + (notificacion.getCelularRemitente() != null ?
                notificacion.getCelularRemitente() : "+5491134567890"));
        System.out.println("Para: " + notificacion.getCelularDestinatario());
        System.out.println("Mensaje: " + notificacion.getMensaje());
        System.out.println("====================");


        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}