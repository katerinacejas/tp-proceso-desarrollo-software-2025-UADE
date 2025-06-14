package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoUbicacion implements IEmparejador{
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        return partido.getZonaGeografica().contieneGeolocalizacion(jugador.getGeolocalizacion());
    }
}