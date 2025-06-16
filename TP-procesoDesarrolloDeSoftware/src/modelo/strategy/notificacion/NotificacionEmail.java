package modelo.strategy.notificacion;

import modelo.adapter.AdapterEmailJavaMail;
import modelo.adapter.IAdapterEmail;
import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionEmail implements IServicioNotificacion {
    private IAdapterEmail adapter;

    public NotificacionEmail() {
        this.adapter = new AdapterEmailJavaMail();
    }

    public NotificacionEmail(IAdapterEmail adapter) {
        this.adapter = adapter;
    }

    @Override
    public void notificar(List<Notificacion> notificaciones) {
        if (notificaciones == null || notificaciones.isEmpty()) {
            System.out.println("No hay notificaciones para enviar por email");
            return;
        }

        System.out.println("Iniciando envío de " + notificaciones.size() + " notificaciones por email...");

        for (Notificacion notificacion : notificaciones) {
            try {
                adapter.enviarEmail(notificacion);
                System.out.println("✓ Email enviado a: " + notificacion.getEmailDestinatario());
            } catch (Exception e) {
                System.err.println("✗ Error enviando email a: " + notificacion.getEmailDestinatario() +
                        " - " + e.getMessage());
            }
        }

        System.out.println("Proceso de envío de emails completado");
    }

    public void cambiarAdapter(IAdapterEmail nuevoAdapter) {
        this.adapter = nuevoAdapter;
    }

}