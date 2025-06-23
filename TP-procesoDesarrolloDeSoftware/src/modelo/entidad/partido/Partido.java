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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

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
    private AbstractEstadoPartido estado;
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
    }

    public Partido(AbstractEstadoPartido estadoInicial) {
        this.participantes = new HashSet<>();
        this.resenias = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.emparejador = new Emparejador();
        this.organizador = new Jugador();
        setearEstadoInicial(estadoInicial);
    }

    /*
        METODOS PARA EL ESTADO DEL PARTIDO
    */

    public void setearEstadoInicial (AbstractEstadoPartido estadoInicial){
        this.estado = estadoInicial;
        this.estado.setContexto(this);
    }

    public void cambiarEstado(AbstractEstadoPartido estado) {
        estado.setContexto(this);
        this.estado = estado;
        notificarObservadores();
    }

    public void cancelar() {
        this.estado.cancelar();
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void emparejar(Jugador jugador) {
        this.estado.emparejar(jugador);
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void eliminar(Jugador jugador) {
        this.estado.eliminar(jugador);
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void confirmar() {
        this.estado.confirmar();
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void jugar() {
        this.estado.jugar();
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void finalizar() {
        this.estado.finalizar();
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    public void agregarResenia(Resenia resenia) {
        this.estado.agregarResenia(resenia);
        // esto es para actualizar en la base el nuevo jugador en partido
        this.updatePartido(this);
    }

    /*
        METODOS PARA EL EMPAREJAMIENTO DEL PARTIDO
    */
    public boolean puedeEmparejar(Jugador jugador) {
        return !tieneTodosLosJugadores() && !yaFueEmparejado(jugador) && emparejador.puedeEmparejar(jugador, this);
    }

    public void cambiarEstrategiaEmparejamiento(IEmparejador estrategia) {
        this.emparejador.cambiarEstrategiaEmparejamiento(estrategia);
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
            observador.notificar();
        }
    }

    public void addObservador(IObservers observador){
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObservers observador){
        this.observadores.remove(observador);
    }

    /*
        METODOS Q SON LLAMADOS POR EL CONTROLLER
     */

    public void createPartido(Partido partido) {
        PartidoDAO partidoDAO = new PartidoDAO();
        partidoDAO.createPartido(partido);
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

    public List<Partido> getPartidosQuePuedeConfirmar(Jugador jugador) {
        PartidoDAO partidoDAO = new PartidoDAO();
        List<Partido> partidos = partidoDAO.getAllPartidos();
        return partidos.stream()
                .filter(partido -> partido.getOrganizador() == jugador && partido.tieneTodosLosJugadores())
                .toList();
    }

    public List<Partido> getPartidosQuePuedeCancelar(Jugador jugador) {
        PartidoDAO partidoDAO = new PartidoDAO();
        List<Partido> partidos = partidoDAO.getAllPartidos();
        return partidos.stream()
                .filter(partido -> partido.puedoSerCanceladoPor(jugador))
                .toList();
    }

    public boolean puedoSerCanceladoPor(Jugador jugador) {
        return this.getOrganizador() == jugador && this.estado.puedeSerCancelado();
    }

    public List<Partido> iniciarPartidosDondeJuega(Jugador jugador) {
        List<Partido> partidosAIniciar = this.getPartidosDondeParticipa(jugador, PartidoConfirmado.class);
        if(!partidosAIniciar.isEmpty()) {
            for(Partido p : partidosAIniciar) {
                p.jugar();
            }
        }
        return partidosAIniciar;
    }

    public List<Partido> getPartidosDondeParticipa(Jugador jugador, Class<? extends AbstractEstadoPartido> estadoClase) {
        PartidoDAO partidoDAO = new PartidoDAO();
        List<Partido> partidos = partidoDAO.getAllPartidos();

        List<Partido> partidosParticipa = new ArrayList<>();
        for(Partido p : partidos) {
            if(p.getParticipantes().contains(jugador) && estadoClase.isInstance(p.getEstado())){
                partidosParticipa.add(p);
            }
        }
        return partidosParticipa;
    }

    public List<Partido> finalizarPartidosDondeJuega(Jugador jugador) {
        List<Partido> partidosAFinalizar = this.getPartidosDondeParticipa(jugador, PartidoEnJuego.class);
        if(!partidosAFinalizar.isEmpty()) {
            for(Partido p : partidosAFinalizar) {
                p.finalizar();
            }
        }
        return partidosAFinalizar;
    }

    public List<Partido> getPartidosDondeDarmeDeBaja(Jugador jugador) {
        // solo se podría dar de baja de partidos q aun necesitan jugadores o que estan armados pero no confirmados
        List<Partido> lista1 = getPartidosDondeParticipa(jugador, PartidoNecesitamosJugadores.class);
        List<Partido> lista2 = getPartidosDondeParticipa(jugador, PartidoArmado.class);
        List<Partido> partidos = new ArrayList<>(lista1);
        for (Partido partido : lista1){
            partidos.add(partido);
        }
        for (Partido partido : lista2){
            partidos.add(partido);
        }
        // saco los partidos q el jugador creó porque no se puede dar de baja de esos
        return partidos.stream().filter(partido -> !partido.getOrganizador().equals(jugador)).collect(Collectors.toList());
    }

    public List<Partido> getPartidosDondeDejarResenia(Jugador jugador) {
        return getPartidosDondeParticipa(jugador, PartidoFinalizado.class);
    }

    public void removeJugador(Jugador jugador) {
        this.participantes.remove(jugador);
    }

    public String mensajeEstado(){
        return this.estado.mensajeEstado();
    }

    public int cantidadJugadoresQueFaltan() {
        return deporte.getCantJugadores() - getCantidadParticipantes();
    }

    /*
        GETTERS Y SETTERS
     */

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

    public void agregarJugador(Jugador jugador) {
        this.participantes.add(jugador);
    }

    public AbstractEstadoPartido getEstado() {
        return estado;
    }

    public void addResenia(Resenia resenia) {
        this.resenias.add(resenia);
    }

}
