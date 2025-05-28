package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoNivel implements IEmparejador{
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        return jugador.getNivelJuego() == partido.getNivelJuego();
    }
}