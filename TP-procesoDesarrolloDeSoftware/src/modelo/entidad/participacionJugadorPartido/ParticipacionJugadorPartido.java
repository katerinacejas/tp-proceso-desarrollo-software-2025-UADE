package modelo.entidad.participacionJugadorPartido;

import modelo.entidad.jugador.Jugador;
import modelo.enumerador.EstadoParticipacion;

public class ParticipacionJugadorPartido {
    Jugador jugador;
    EstadoParticipacion confirmacion;

    public ParticipacionJugadorPartido(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        this.jugador = jugador;
        this.confirmacion = EstadoParticipacion.PENDIENTE; // Por defecto, la participación está pendiente
    }

    public Jugador getJugador() {
        return jugador;
    }
    public EstadoParticipacion getConfirmacion() {
        return confirmacion;
    }

    public void aceptarParticipacion() {
        this.confirmacion = EstadoParticipacion.ACEPTADO;
    }

    public void rechazarParticipacion() {
        this.confirmacion = EstadoParticipacion.RECHAZADO;
    }

    public boolean isAceptado() {
        return this.confirmacion == EstadoParticipacion.ACEPTADO;
    }

    public boolean isPendiente() {
        return this.confirmacion == EstadoParticipacion.PENDIENTE;
    }

    public boolean isRechazado() {
        return this.confirmacion == EstadoParticipacion.RECHAZADO;
    }




}
