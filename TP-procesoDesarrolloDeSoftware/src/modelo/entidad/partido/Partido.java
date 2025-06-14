package modelo.entidad.partido;

import modelo.dao.PartidoDAO;
import modelo.dto.PartidoDTO;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.emparejamiento.Emparejador;
import modelo.entidad.ubicacion.ZonaGeografica;
import modelo.enumerador.NivelJuego;
import modelo.observer.IObservers;
import modelo.state.*;
import modelo.strategy.emparejamiento.IEmparejador;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Partido {

    private String id;
    private Deporte deporte;
    private int duracionMin;
    private ZonaGeografica zonaGeografica;
    private LocalDateTime horarioEncuentro;
    private Set<Jugador> participantes;
    private Jugador organizador;
    private NivelJuego nivelJuego;
    private Set<Resenia> reseñas;
    private IEstadoPartido estado;
    private Emparejador emparejador;
    private List<IObservers> observadores;

    /* METODOS PARA GESTIONAR JUGADORES */

    public boolean agregarJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        if (tieneTodosLosJugadores()) {
            throw new IllegalStateException("El partido ya tiene la capacidad máxima");
        }
        if (yaFueEmparejado(jugador)) {
            return false; // Ya está en el partido
        }

        boolean agregado = this.participantes.add(jugador);
        if (agregado) {
            // Verificar si ahora tenemos suficientes jugadores para armar
            if (tieneTodosLosJugadores()) {
                this.armar(); // Cambio automático de estado
            }
            //notificarObservadores();
        }
        return agregado;
    }

    // Remover jugador
    public boolean eliminarJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }

        // Validar que no sea el organizador
        if (jugador.equals(this.organizador)) {
            throw new IllegalStateException("No se puede eliminar al organizador del partido");
        }

        boolean eliminado = this.participantes.remove(jugador);
        if (eliminado) {
            // Si después de remover necesitamos más jugadores, cambiar estado
            if (!tieneTodosLosJugadores()) {
                this.necesitarJugadores();
            }
            //notificarObservadores();
        }
        return eliminado;
    }

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
        return this.getCantidadParticipantes() == deporte.getCantJugadores();
    }

    public int getCantidadParticipantes() {
        return this.participantes.size();
    }

    public boolean yaFueEmparejado(Jugador jugador) {
        return this.participantes.contains(jugador);
    }

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

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public ZonaGeografica getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(ZonaGeografica zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public LocalDateTime getHorarioEncuentro() {
        return horarioEncuentro;
    }

    public void setHorarioEncuentro(LocalDateTime horarioEncuentro) {
        this.horarioEncuentro = horarioEncuentro;
    }

    public Jugador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Jugador organizador) {
        this.organizador = organizador;
    }

    public Set<Resenia> getReseñas() {
        return reseñas;
    }

    public void setReseñas(Set<Resenia> reseñas) {
        this.reseñas = reseñas;
    }

    public NivelJuego getNivelJuego() {
        return nivelJuego;
    }

    public void setNivelJuego(NivelJuego nivelJuego) {
        this.nivelJuego = nivelJuego;
    }

    public Set<Jugador> getParticipantes() {
        return this.participantes;
   } 


     /*
        CONSTRUCTOR
     */

   public Partido() {

        // Inicialización de colecciones
        this.participantes = new HashSet<>();
        this.reseñas = new HashSet<>();
        this.observadores = new ArrayList<>();
    }

   public void createPartido(Partido partido) {
    PartidoDAO partidoDAO = new PartidoDAO();
    partidoDAO.createPartido(partido);

   }

   public Partido getPartidoById(String id) {
    PartidoDAO partidoDAO = new PartidoDAO();
    return partidoDAO.getPartidoById(id);
   }

   public void deletePartido(String id) {
    PartidoDAO partidoDAO = new PartidoDAO();
    partidoDAO.deletePartido(id);
   }
}
