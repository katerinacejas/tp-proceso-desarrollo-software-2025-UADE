package modelo.partido;

import modelo.Deporte;
import modelo.NivelJuego;
import modelo.Jugador;
import modelo.Ubicacion;
import modelo.emparejamiento.Emparejador;
import modelo.emparejamiento.IEmparejador;
import modelo.notificacion.IServicioNotificacion;

import java.sql.Timestamp;
import java.util.Set;

public class Partido {
    private Deporte deporte;
    private int duracionMin;
    private Ubicacion lugarEncuentro;
    private Timestamp horarioEncuentro;
    private NivelJuego nivel;
    private Set<Jugador> participantes;
    private Jugador organizador;
    private Emparejador emparejador;
    private IEstadoPartido estado;
    private IEmparejador estrategiaEmparejamiento;
    private IServicioNotificacion estrategiaNotificacion;
    private Set<Resenia> reseñas;

    private void cambiarEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    private void cancelar() {

    }

    private void necesitarJugadores() {

    }

    private void armar() {

    }

    private void confirmar() {

    }

    private void jugar() {

    }

    private void finalizar() {

    }

    private void emparejar() {

    }
}
