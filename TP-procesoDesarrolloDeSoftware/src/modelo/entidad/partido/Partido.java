package modelo.entidad.partido;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.participacionJugadorPartido.ParticipacionJugadorPartido;
import modelo.entidad.ubicacion.Geolocalizacion;
import modelo.enumerador.NivelJuego;
import modelo.entidad.emparejamiento.Emparejador;
import modelo.entidad.ubicacion.ZonaGeografica;
import modelo.observer.IObservers;
import modelo.state.*;
import modelo.strategy.emparejamiento.IEmparejador;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;



public class Partido {

    private String id;
    private Deporte deporte;
    private int duracionMin;
    private ZonaGeografica zonaGeografica;
    private Timestamp horarioEncuentro;
    private Set<Jugador> participantes;
    private Jugador organizador;
    private Set<Resenia> reseñas;
    private int cantidadMaxima;

    private IEstadoPartido estado;
    private Emparejador emparejador;
    private List<IObservers> observadores;

    private List<ParticipacionJugadorPartido> participacionJugadores;


    /*
        CONSTRUCTOR
     */

    public Partido(Deporte deporte,
                   Jugador organizador,
                   int cantidadMaxima,
                   int duracionMin,
                   ZonaGeografica zonaGeografica,
                   Timestamp horarioEncuentro,
                   Emparejador emparejador) {

        // Validaciones
        if (deporte == null) {
            throw new IllegalArgumentException("El deporte no puede ser null");
        }
        if (organizador == null) {
            throw new IllegalArgumentException("El organizador no puede ser null");
        }
        if (cantidadMaxima <= 0) {
            throw new IllegalArgumentException("La cantidad máxima debe ser positiva");
        }
        if (duracionMin <= 0) {
            throw new IllegalArgumentException("La duración debe ser positiva");
        }
        if (zonaGeografica == null) {
            throw new IllegalArgumentException("La zonaGeografica no puede ser null");
        }
        if (horarioEncuentro == null) {
            throw new IllegalArgumentException("El horario no puede ser null");
        }
        if (horarioEncuentro.before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("El horario no puede ser en el pasado");
        }

        // Inicialización de atributos
        this.deporte = deporte;
        this.organizador = organizador;
        this.cantidadMaxima = cantidadMaxima;
        this.duracionMin = duracionMin;
        this.zonaGeografica = zonaGeografica;
        this.horarioEncuentro = horarioEncuentro;

        // Inicialización de colecciones
        this.participantes = new HashSet<>();
        this.reseñas = new HashSet<>();
        this.observadores = new ArrayList<>();

        // Agregar organizador automáticamente
        this.participantes.add(organizador);

        // Inicialización de componentes
        this.emparejador = new Emparejador(emparejador);
        this.participacionJugadores =

        // Estado inicial
        this.estado = new EstadoPartidoNecesitaJugadores();
    }


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
            // Agregar participación del jugador
            agregarParticipacionJugador(jugador);
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

    /*   METODOS PARA GESTIONAR PARTICIPACIONES DE JUGADORES */
    public boolean agregarParticipacionJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        if (yaFueEmparejado(jugador)) {
            return false; // Ya está en el partido
        }
        ParticipacionJugadorPartido participacion = new ParticipacionJugadorPartido(jugador);
        this.participacionJugadores.add(participacion);
        return true;
    }

    public boolean eliminarParticipacionJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        ParticipacionJugadorPartido participacion = this.participacionJugadores.stream()
                .filter(p -> p.getJugador().equals(jugador))
                .findFirst()
                .orElse(null);
        if (participacion != null) {
            this.participacionJugadores.remove(participacion);
            return true;
        }
        return false;
    }
    private NivelJuego nivelJuego;

    /*
     * METODOS PARA EL ESTADO DEL PARTIDO
     */
    public void cambiarEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void cancelar() {
        estado.cancelar(this);
        // TODO: definir si queremos hacer algo mas luego de cancelar.
    }

    public void necesitarJugadores() {
        estado.necesitarJugadores(this);
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public NivelJuego getNivelJuego() {
        return nivelJuego;
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
     * METODOS PARA EL EMPAREJAMIENTO DEL PARTIDO
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
        // TODO: agregar validador de que si el equipo ahora ya esta completo con este
        // emparejamiento nuevo, se cambie el estado a "PARTIDO ARMADO"
    }

    /*
     * METODOS UTILES
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

    /*
     * ESTE METODO AHORA YA NO VA MAS EN PARTIDO PORQUE EXISTE LA CLASE
     * PARTICIPACION_JUGADOR_PARTIDO
     * 
     * public NivelJuego getNivelJuego() {
     * return this.nivelJuego;
     * }
     */

    public Set<Jugador> obtenerListadoJugadores() {
        return this.participantes;
    }

    /*
     * METODOS PARA NOTIFICAR OBSERVADOR
     */
    public void notificarObservadores() {

    }

    public void addObservador(IObservers observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObservers observador) {
        this.observadores.remove(observador);
    }
}
