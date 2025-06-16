package modelo.entidad.partido;

import modelo.dao.PartidoDAO;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.emparejamiento.Emparejador;
import modelo.entidad.ubicacion.ZonaGeografica;
import modelo.enumerador.NivelJuego;
import modelo.observer.IObservers;
import modelo.state.*;
import modelo.strategy.emparejamiento.IEmparejador;
import modelo.entidad.notificacion.Notificador;
import modelo.strategy.notificacion.NotificacionEmail;
import modelo.strategy.notificacion.IServicioNotificacion;
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
    private List<Resenia> resenias;
    private IEstadoPartido estado;
    private Emparejador emparejador;
    private List<IObservers> observadores;

    /*
        CONSTRUCTOR
    */
    public Partido() {
        this.participantes = new HashSet<>();
        this.resenias = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.emparejador = new Emparejador();
        this.organizador = new Jugador();
        this.estado = new PartidoNecesitamosJugadores();

        IServicioNotificacion estrategiaNotificacion = new NotificacionEmail();
        Notificador notificador = new Notificador(this, estrategiaNotificacion);
        this.addObservador(notificador);
    }

    /*
        METODOS PARA EL ESTADO DEL PARTIDO
    */
    public void cambiarEstado(IEstadoPartido estado) {
        IEstadoPartido estadoAnterior = this.estado;
        this.estado = estado;

        // Solo notifica si cambió el estado
        if (estadoAnterior.getClass() != estado.getClass()) {
            notificarObservadores();
        }
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
            return true;
        }
        return false;
    }

    public void cambiarEstrategiaEmparejamiento(IEmparejador estrategia) {
        this.emparejador.cambiarEstrategiaEmparejamiento(estrategia);
    }

    public void emparejar(Jugador jugador) {
        this.participantes.add(jugador);
        notificarObservadores();
        if (tieneTodosLosJugadores()) {
            this.armar(); // Cambio automático de estado
        }

        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);

        notificarObservadores(); //TODO esta parte

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
        for (IObservers observador : observadores) {
            try {
                observador.notificar();
            } catch (Exception e) {
                System.err.println("Error al notificar observador: " + e.getMessage());
            }
        }
    }

    public void addObservador(IObservers observador){
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObservers observador){
        this.observadores.remove(observador);
    }

    public Emparejador getEmparejador() {
        return emparejador;
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

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
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

    public void agregarParticipantePorDefault(Jugador jugadorOrganizadorPorDefault) {
        this.participantes.add(jugadorOrganizadorPorDefault);
    }

    public IEstadoPartido getEstado() {
        return estado;
    }


    /*
        METODOS Q SON LLAMADOS POR EL CONTROLLER
     */

    public void createPartido(Partido partido) {
        PartidoDAO partidoDAO = new PartidoDAO();
        partidoDAO.createPartido(partido);
        this.necesitarJugadores();
    }

    public Partido getPartidoById(String id) {
        PartidoDAO partidoDAO = new PartidoDAO();
        return partidoDAO.getPartidoById(id);
    }

    public void updatePartido(Partido partidoActualizado){
        PartidoDAO partidoDAO = new PartidoDAO();
        partidoDAO.updatePartido(partidoActualizado);
    }

    public void deletePartido(String id) {
        PartidoDAO partidoDAO = new PartidoDAO();
        partidoDAO.deletePartido(id);
    }

    public int cantJugadoresDelDeporte() {
        return this.deporte.getCantJugadores();
    }

    public boolean leFaltanParticipantes() {
        return this.getCantidadParticipantes() < this.cantJugadoresDelDeporte();
    }

    public List<Partido> getAllPartidosNecesitanJugadores() {
        PartidoDAO partidoDAO = new PartidoDAO();
        List<Partido> partidos = partidoDAO.getAllPartidos();
        List<Partido> partidosNecesitanJugadores = new ArrayList<>();
        for (Partido partido: partidos) {
            if(partido.leFaltanParticipantes()) {
                partidosNecesitanJugadores.add(partido);
            }
        }
        return partidosNecesitanJugadores;
    }

    public List<Partido> getPartidosAptosParaJugador(Jugador jugador){
        List<Partido> partidos = this.getAllPartidosNecesitanJugadores();
        return partidos.stream().filter(partido -> partido.puedeEmparejar(jugador)).toList();
    }

    public Partido getPartidoQuePuedeConfirmar(Jugador jugador) {
        PartidoDAO partidoDAO = new PartidoDAO();
        List<Partido> partidos = partidoDAO.getAllPartidos();
        return partidos.stream()
                .filter(partido -> partido.getOrganizador() == jugador && partido.tieneTodosLosJugadores())
                .findFirst().orElse(null);
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
            notificarObservadores();
            // Si después de remover necesitamos más jugadores, cambiar estado
            if (!tieneTodosLosJugadores()) {
                this.necesitarJugadores();
            }
        }
        return eliminado;
    }
}
