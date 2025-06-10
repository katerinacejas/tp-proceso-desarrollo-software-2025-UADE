package modelo.entidad.partido;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.emparejamiento.Emparejador;
import modelo.entidad.ubicacion.ZonaGeografica;
import modelo.observer.IObservers;
import modelo.state.IEstadoPartido;
import modelo.strategy.emparejamiento.IEmparejador;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Partido {
    private Deporte deporte;
    private int duracionMin;
    private ZonaGeografica zonaGeografica;
    private Timestamp horarioEncuentro;
    private Set<Jugador> participantes;
    private Jugador organizador;
    private Set<Resenia> reseñas;

    private IEstadoPartido estado;
    private Emparejador emparejador;
    private List<IObservers> observadores;


    /*
        METODOS PARA EL ESTADO DEL PARTIDO
    */
    public void cambiarEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void cancelar() {
        estado.cancelar(this);
        //TODO: definir si queremos hacer algo mas luego de cancelar.
    }

    public void necesitarJugadores() {
        estado.necesitarJugadores(this);
    }

    public void armar() {
        estado.armar(this);
    }

    public void confirmar() {
        estado.confirmar(this);
    }

    public void jugar() {
        estado.jugar(this);
    }

    public void finalizar() {
        estado.finalizar(this);
    }

    /*
        METODOS PARA EL EMPAREJAMIENTO DEL PARTIDO
    */
    public boolean puedeEmparejar(Jugador jugador) {
        if (!tieneTodosLosJugadores() && !yaFueEmparejado(jugador) && emparejador.puedeEmparejar(jugador, this)) {
            this.emparejar(jugador);
            return true;
        }
        return false;
    }

    public void cambiarEstrategiaEmparejamiento(IEmparejador estrategia) {
        this.emparejador.cambiarEstrategiaEmparejamiento(estrategia);
    }

    public void emparejar(Jugador jugador) {
        this.participantes.add(jugador);
        // TODO: agregar validador de que si el equipo ahora ya esta completo con este emparejamiento nuevo, se cambie el estado a "PARTIDO ARMADO"
    }

    /*
        METODOS UTILES
    */
    public boolean tieneTodosLosJugadores() {
        return this.getCantidadParticipantes() == this.cantidadMaxima;
    }

    public int getCantidadParticipantes() {
        return this.participantes.size();
    }

    public boolean yaFueEmparejado(Jugador jugador) {
        return this.participantes.contains(jugador);
    }

    /*  ESTE METODO AHORA YA NO VA MAS EN PARTIDO PORQUE EXISTE LA CLASE PARTICIPACION_JUGADOR_PARTIDO

    public NivelJuego getNivelJuego() {
        return this.nivelJuego;
    }
    */

    public Set<Jugador> obtenerListadoJugadores() {
        return this.participantes;
    }

    /*
        METODOS PARA NOTIFICAR OBSERVADOR
     */
    public void notificarObservadores() {

    }

    public void addObservador(IObservers observador){
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObservers observador){
        this.observadores.remove(observador);
    }
}
