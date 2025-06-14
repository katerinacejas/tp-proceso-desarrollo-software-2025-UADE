package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoNivel implements IEmparejador{
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        // ESTE METODO AHORA YA NO VA MAS PORQUE EXISTE LA CLASE PARTICIPACION_JUGADOR_PARTIDO
        // return jugador.getNivelJuego() == partido.getNivelJuego();
        return true;
    }
}