package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoHistorial implements IEmparejador{
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        return jugador.getDeportesFavoritos()
                .stream()
                .anyMatch(deporte -> deporte.equals(partido.getDeporte()));
    }
}
