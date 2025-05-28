package modelo.strategy.notificacion;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.notificacion.Notificacion;

import java.util.Set;

public interface IServicioNotificacion {
    void notificar(Notificacion notificacion, Set<Jugador> jugadores);
}
