package modelo.notificacion;

import modelo.Jugador;

import java.util.Set;

public interface IServicioNotificacion {
    void notificar(Notificacion notificacion, Set<Jugador> jugadores);
}
