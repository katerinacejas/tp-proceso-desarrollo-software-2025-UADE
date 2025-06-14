package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoUbicacion implements IEmparejador {
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        double distancia = jugador.getGeolocalizacion().distanciaA(partido.getGeolocalizacion());
        return distancia < 6.0; // TODO: definir este tema
    }
}