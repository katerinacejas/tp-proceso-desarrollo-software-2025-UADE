package modelo.adapter;

import modelo.entidad.notificacion.Notificacion;

public interface IAdapterEmail {
    void enviarEmail(Notificacion notificacion);
}
