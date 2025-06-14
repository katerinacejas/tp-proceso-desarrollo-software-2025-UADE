package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class EmparejamientoHistorial implements IEmparejador {
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        long vecesJugadasConOrganizador = jugador.getHistorial()
                .stream()
                .filter(p -> p.getOrganizador().equals(partido.getOrganizador()))
                .count();

        return vecesJugadasConOrganizador < 3;
    }
}
