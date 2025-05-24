package modelo.notificacion;

import modelo.Jugador;

import java.util.Set;

public class NotificacionEmail implements IServicioNotificacion{
    IAdapterEmail adapter;

    @Override
    public void notificar(Notificacion notificacion, Set<Jugador> jugadores) {

    }
}
