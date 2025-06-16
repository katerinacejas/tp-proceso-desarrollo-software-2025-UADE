package modelo.adapter;

import java.util.List;

import modelo.entidad.notificacion.Notificacion;

public interface IAdapterEmail {
    void enviarEmails(List<Notificacion> notificaciones);
}
