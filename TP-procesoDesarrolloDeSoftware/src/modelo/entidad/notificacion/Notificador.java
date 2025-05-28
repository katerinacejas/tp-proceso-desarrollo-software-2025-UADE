package modelo.entidad.notificacion;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.strategy.notificacion.IServicioNotificacion;

import java.util.HashSet;
import java.util.Set;

public class Notificador {
    private IServicioNotificacion estrategiaNotificacion;
    private Set<Notificacion> notificaciones = new HashSet<>();

    public void crearNotificacion(Partido partido) {

    }

    public void notificar(Notificacion notificacion, Set<Jugador> jugadores) {
        estrategiaNotificacion.notificar(notificacion, jugadores);
    }

}
