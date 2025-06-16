package modelo.strategy.notificacion;

import modelo.adapter.AdapterEmailJavaMail;
import modelo.adapter.IAdapterEmail;
import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionEmail implements IServicioNotificacion{
    IAdapterEmail adapter;

    public NotificacionEmail() {
        this.adapter = new AdapterEmailJavaMail();
    }

    @Override
    public void notificar(List<Notificacion> notificaciones) {
            adapter.enviarEmails(notificaciones);

    }
}
