package modelo.strategy.emparejamiento;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.enumerador.NivelJuego;

public class EmparejamientoNivel implements IEmparejador{
    private NivelJuego obtenerNivelJugadorParaDeporte(Jugador jugador, Deporte deporte) {
        return jugador.getNivelPorDeporte().stream()
                .filter(nd -> nd.getDeporte().equals(deporte))
                .map(nd -> nd.getNivelJuego())
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        NivelJuego nivelDelJugador = obtenerNivelJugadorParaDeporte(jugador, partido.getDeporte());
        NivelJuego nivelDelPartido = partido.getNivelJuego();

        if (nivelDelJugador == null) {
            return false;
        }

        return nivelDelJugador == nivelDelPartido;
    }
}