package modelo.strategy.notificacion;

import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionPush implements IServicioNotificacion{

    @Override
    public void notificar(List<Notificacion> notificaciones) {
        for (Notificacion n : notificaciones) {
            System.out.println("Enviando PUSH a " + n.getCelularDestinatario() + ": " + n.getMensaje());
        }
    }
}
