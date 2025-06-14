package modelo.entidad.NivelJuego;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.enumerador.NivelJuego;

public class NivelJugadorDeporte {
    private NivelJuego nivelJuego;
    private Jugador jugador;
    private Deporte deporte;

    public NivelJuego getNivelJuego(){
        return nivelJuego;
    }

    public Deporte getDeporte(){
        return deporte;
    }
}
