package modelo.strategy.notificacion;

import modelo.entidad.notificacion.Notificacion;

import java.util.List;


public interface IServicioNotificacion {
    void notificar(List<Notificacion> notificaciones);
}
