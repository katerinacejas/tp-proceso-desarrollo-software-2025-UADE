package modelo.entidad.participacionJugadorPartido;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

public class ParticipacionJugadorPartido {
    Jugador jugador;
    Partido partido;
    EstadoParticipacion confirmacion;

    public void actualizarParticipacion(EstadoParticipacion estado){
        this.confirmacion = estado;
    }
}
