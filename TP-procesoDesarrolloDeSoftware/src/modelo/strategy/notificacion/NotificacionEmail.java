package modelo.strategy.notificacion;

import modelo.adapter.IAdapterEmail;
import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionEmail implements IServicioNotificacion {
    private IAdapterEmail adapter;

    public NotificacionEmail(IAdapterEmail adapter) {
        this.adapter = adapter;
    }

    @Override
    public void notificar(List<Notificacion> notificaciones) {
        for (Notificacion n : notificaciones) {
            adapter.enviarEmail(n);
        }
    }
}
