package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public interface IEmparejador {
    boolean puedeEmparejar(Jugador jugador, Partido partido);
}
