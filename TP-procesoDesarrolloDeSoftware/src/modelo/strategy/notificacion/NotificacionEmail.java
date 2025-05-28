package modelo.strategy.notificacion;

import modelo.adapter.IAdapterEmail;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.notificacion.Notificacion;

import java.util.Set;

public class NotificacionEmail implements IServicioNotificacion{
    IAdapterEmail adapter;

    @Override
    public void notificar(Notificacion notificacion, Set<Jugador> jugadores) {

    }
}
