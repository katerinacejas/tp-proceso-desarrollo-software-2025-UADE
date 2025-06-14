package modelo.strategy.notificacion;

import modelo.adapter.IAdapterEmail;
import modelo.entidad.notificacion.Notificacion;

import java.util.List;

public class NotificacionEmail implements IServicioNotificacion{
    IAdapterEmail adapter;

    @Override
    public void notificar(List<Notificacion> notificaciones) {

    }
}
