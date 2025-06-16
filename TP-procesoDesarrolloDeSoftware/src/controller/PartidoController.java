package controller;

import modelo.dto.JugadorDTO;
import modelo.dto.PartidoDTO;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.enumerador.EstadoPartido;
import modelo.enumerador.EstrategiaPartido;
import modelo.state.PartidoConfirmado;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartidoController {

    private Partido partido;
    private Deporte deporte;
    private Jugador jugador;

    public PartidoController(){
        partido = new Partido();
        deporte = new Deporte();
        jugador = new Jugador();
    }

    public void createPartido(PartidoDTO partidoDTO) {
        Partido nuevoPartido = convertToEntity(partidoDTO);
        partido.createPartido(nuevoPartido);
        partidoDTO.setId(nuevoPartido.getId());
        //para setearle el estado al DTO
        EstadoPartido estadoEnum = Arrays.stream(EstadoPartido.values())
                .filter(e -> e.coincideConEnum(partido.getEstado()))
                .findFirst()
                .orElseThrow();
        partidoDTO.setEstado(estadoEnum);
    }

    public PartidoDTO getPartidoById(String id) {
        return convertToDTO(partido.getPartidoById(id));
    }

    public void updatePartido(PartidoDTO partidoDTO) {
        Partido partidoActualizado = this.convertToEntity(partidoDTO);
        partidoActualizado.setId(partidoDTO.getId());
        partido.updatePartido(partidoActualizado);
    }

    public void deletePartido(String id) {
        partido.deletePartido(id);
    }

    private Partido convertToEntity(PartidoDTO partidoDTO) {
        Partido partido = new Partido();
        partido.setDeporte(deporte.getDeporteById(partidoDTO.getDeporte()));
        partido.setDuracionMin(partidoDTO.getDuracionMin());
        // TODO partido.setZonaGeografica(partidoDTO.getZonaGeografica()); //ver después cómo se persiste la zona geográfica
        partido.setHorarioEncuentro(partidoDTO.getHorarioEncuentro());

        //el organizador como el primer participante del partido por default
        partido.agregarJugador(jugador.getJugadorById(partidoDTO.getParticipantes().iterator().next()));

        partido.setOrganizador(jugador.getJugadorById(partidoDTO.getOrganizador()));
        partido.cambiarEstrategiaEmparejamiento(partidoDTO.getEstrategiaPartido().crearToEntity());
        partido.setNivelJuego(partidoDTO.getNivelJuego());
        partido.cambiarEstado(partidoDTO.getEstado().crearToEntity(partido));
        return partido;
    }

    private PartidoDTO convertToDTO(Partido partido) {
        PartidoDTO partidoDTO = new PartidoDTO();
        partidoDTO.setId(partido.getId());
        partidoDTO.setDeporte(partido.getDeporte().getId());
        partidoDTO.setDuracionMin(partido.getDuracionMin());
       // TODO partidoDTO.setZonaGeografica(partido.getZonaGeografica());
        partidoDTO.setHorarioEncuentro(partido.getHorarioEncuentro());
        partidoDTO.setParticipantes(partido.getParticipantes()
                                    .stream().map(jugador -> jugador.getId())
                                    .collect(Collectors.toSet()));
        partidoDTO.setOrganizador(partido.getOrganizador().getId());
        partidoDTO.setResenias(partido.getResenias()
                                .stream().map(resenia -> resenia.getId())
                                .toList());
        //reemplacé los metodos de convert entity por enum y viceversa por un factory method en enum. enum es su propia fabrica
        EstrategiaPartido estrategiaEnum = Arrays.stream(EstrategiaPartido.values())
                                                 .filter(e -> e.coincideConEnum(partido.getEmparejador().getEstrategiaEmparejamiento()))
                                                 .findFirst()
                                                 .orElseThrow();
        partidoDTO.setEstrategiaPartido(estrategiaEnum);
        partidoDTO.setNivelJuego(partido.getNivelJuego());
        //para setearle el estado al DTO
        EstadoPartido estadoEnum = Arrays.stream(EstadoPartido.values())
                .filter(e -> e.coincideConEnum(partido.getEstado()))
                .findFirst()
                .orElseThrow();
        partidoDTO.setEstado(estadoEnum);
        return partidoDTO;
    }

    public List<PartidoDTO> getPartidosAptosParaJugador(JugadorDTO jugadorDTO) {
        // este metodo es para mostrarle al usuario todos los partidos donde puede unirse,
        // pero no se une a ninguno, solo los lista para meterlos en un print en la vista y q por consola despues elija a cual unirse
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        List<Partido> partidos = partido.getPartidosAptosParaJugador(jugadorEntity);
        return partidos.stream().map(partido -> convertToDTO(partido)).toList();
    }

    public void unirseAlPartido(PartidoDTO partidoDTO, JugadorDTO jugadorDTO){
        /*
         este metodo no evalua si el jugador PUEDE unirse, porque si la vista le pidio ejecutar este metodo
         al controller, es porque antes le ejecutó el getPartidosAptosParaJugador, y el jugador selecciono uno
         al que ya sabe que puede unirse.
         Ademas de unirlo, debería llamar al partido para evaluar el estado para ver si con esa union ya puede pasar avisarle
         al organizador que ya esta el partido armado para que lo confirme y pase a estado confirmado
         */
        Partido partidoEntity = partido.getPartidoById(partidoDTO.getId());
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        partidoEntity.emparejar(jugadorEntity);
        // solo falta los observadores y notificaciones en Partido. pero este metodo estaría ok
    }

    public void confirmarPartido(PartidoDTO partidoDTO) {
        partido.getPartidoById(partidoDTO.getId()).confirmar();
    }

    public List<PartidoDTO> getPartidosQuePuedeConfirmar(JugadorDTO jugadorDTO) {
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        List<Partido> partidos = partido.getPartidosQuePuedeConfirmar(jugadorEntity);
        if(partidos.isEmpty()){
            return null;
        }
        return partidos.stream().map(partido -> convertToDTO(partido)).toList();
    }

    public List<PartidoDTO> getPartidosQuePuedeCancelar(JugadorDTO jugadorDTO) {
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        List<Partido> partidos = partido.getPartidosQuePuedeCancelar(jugadorEntity);
        if(partidos.isEmpty()){
            return null;
        }
        return partidos.stream().map(partido -> convertToDTO(partido)).toList();
    }

    public void cancelarPartido(PartidoDTO partidoDTO) {
        partido.getPartidoById(partidoDTO.getId()).cancelar();
    }

    public List<PartidoDTO> iniciarPartidos(JugadorDTO jugadorDTO) {
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        List<Partido> partidos = partido.iniciarPartidosDondeJuega(jugadorEntity);
        if(partidos.isEmpty()){
            return null;
        }
        return partidos.stream().map(partido -> convertToDTO(partido)).toList();
    }

    public List<PartidoDTO> finalizarPartidos(JugadorDTO jugadorDTO) {
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        List<Partido> partidos = partido.finalizarPartidosDondeJuega(jugadorEntity);
        if(partidos.isEmpty()){
            return null;
        }
        return partidos.stream().map(partido -> convertToDTO(partido)).toList();
    }

}
