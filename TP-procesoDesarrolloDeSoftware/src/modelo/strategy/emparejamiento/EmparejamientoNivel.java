package modelo.strategy.emparejamiento;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.enumerador.NivelJuego;

public class EmparejamientoNivel implements IEmparejador {

    private NivelJuego obtenerNivelJugador(Jugador jugador, Deporte deporte) {
        return jugador.getNivel(deporte);
    }

    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        NivelJuego nivelDelJugador = obtenerNivelJugador(jugador, partido.getDeporte());
        NivelJuego nivelDelPartido = partido.getNivelJuego(); // usa el nivel del partido

        return nivelDelJugador == nivelDelPartido;
    }

}