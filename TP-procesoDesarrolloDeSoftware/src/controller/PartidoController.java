package controller;

import modelo.dto.JugadorDTO;
import modelo.dto.PartidoDTO;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.enumerador.EstrategiaPartido;
import modelo.strategy.emparejamiento.EmparejamientoHistorial;
import modelo.strategy.emparejamiento.EmparejamientoNivel;
import modelo.strategy.emparejamiento.EmparejamientoUbicacion;
import modelo.strategy.emparejamiento.IEmparejador;

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
    }

    public PartidoDTO getPartidoById(String id) {
        PartidoDTO partidoDTO = convertToDTO(partido.getPartidoById(id));
        return partidoDTO;
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
        //partido.setZonaGeografica(partidoDTO.getZonaGeografica()); //ver después cómo se persiste la zona geográfica
        partido.setHorarioEncuentro(partidoDTO.getHorarioEncuentro());

        //desde la vista se setea al organizador como el primer participante del partido por default
        partido.agregarParticipantePorDefault(jugador.getJugadorById(partidoDTO.getParticipantes().iterator().next()));

        partido.setOrganizador(jugador.getJugadorById(partidoDTO.getOrganizador()));
        partido.cambiarEstrategiaEmparejamiento(convertEstrategiaPartidoToEmparejamiento(partidoDTO.getEstrategiaPartido()));
        partido.setNivelJuego(partidoDTO.getNivelJuego());
        return partido;
    }

    private PartidoDTO convertToDTO(Partido partido) {
        PartidoDTO partidoDTO = new PartidoDTO();
        partidoDTO.setId(partido.getId());
        partidoDTO.setDeporte(partido.getDeporte().getId());
        partidoDTO.setDuracionMin(partido.getDuracionMin());
       //partidoDTO.setZonaGeografica(partido.getZonaGeografica());
        partidoDTO.setHorarioEncuentro(partido.getHorarioEncuentro());
        partidoDTO.setParticipantes(partido.getParticipantes()
                                    .stream().map(jugador -> jugador.getId())
                                    .collect(Collectors.toSet()));
        partidoDTO.setOrganizador(partido.getOrganizador().getId());
        partidoDTO.setResenias(partido.getResenias()
                                .stream().map(resenia -> resenia.getId())
                                .toList());
        //partidoDTO.setEstrategiaPartido(); TODO
        partidoDTO.setNivelJuego(partido.getNivelJuego());
        return partidoDTO;
    }

    private IEmparejador convertEstrategiaPartidoToEmparejamiento(EstrategiaPartido estrategiaPartido) {
        switch (estrategiaPartido) {
            case NIVEL:
                return new EmparejamientoNivel();
            case UBICACION:
                return new EmparejamientoUbicacion();
            case HISTORIAL:
                return new EmparejamientoHistorial();
            default:
                throw new IllegalArgumentException("Estrategia desconocida: " + estrategiaPartido);
        }
    }

    public List<PartidoDTO> getPartidosAptosParaJugador(JugadorDTO jugadorDTO) {
        // este metodo es para mostrarle al usuario todos los partidos donde puede unirse
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
        Partido partidoEntity = convertToEntity(partidoDTO);
        partidoEntity.setId(partidoDTO.getId());
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        partidoEntity.emparejar(jugadorEntity);
        // creo que ahí estaría ok. solo falta los observadores y notificaciones en Partido. pero este metodo estaría ok
    }

    public void confirmarPartido(PartidoDTO partidoDTO) {
        Partido partidoEntity = convertToEntity(partidoDTO);
        partidoEntity.setId(partidoDTO.getId());
        partidoEntity.confirmar();
    }

    public PartidoDTO getPartidoQuePuedeConfirmar(JugadorDTO jugadorDTO) {
        Jugador jugadorEntity = jugador.getJugadorById(jugadorDTO.getId());
        jugadorEntity.setId(jugadorDTO.getId());
        Partido partidoEntity = partido.getPartidoQuePuedeConfirmar(jugadorEntity);
        PartidoDTO partidoDTO = convertToDTO(partidoEntity);
        return partidoDTO;
    }
}
