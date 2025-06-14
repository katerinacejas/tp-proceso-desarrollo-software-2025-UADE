package controller;

import modelo.dto.PartidoDTO;
import modelo.entidad.partido.Partido;

public class PartidoController {

    private Partido partido;

    public PartidoController(){
        partido = new Partido();
    }

    public void createPartido(PartidoDTO partidoDTO) {
        Partido nuevoPartido = convertToEntity(partidoDTO);
        nuevoPartido.necesitarJugadores();
        //partido.createPartido(partidoDTO);
        //partidoDTO.setId(nuevoPartido.getId());
    }

    public PartidoDTO getPartidoById(int id) {
        //TODO
        PartidoDTO partidoDTO = new PartidoDTO();
        return partidoDTO;
    }

    public boolean updatePartido(int id, PartidoDTO partidoDTO) {
        //TODO
        return true;
    }

    public boolean deletePartido(int id) {
        //TODO
        return true;
    }

    private Partido convertToEntity(PartidoDTO partidoDTO) {
        Partido partido = new Partido();
        //partido.setDeporte(partidoDTO.getDeporte());
        partido.setDuracionMin(partidoDTO.getDuracionMin());
        //partido.setZonaGeografica(partidoDTO.getZonaGeografica());
        //partido.setHorarioEncuentro(partidoDTO.getHorarioEncuentro());
        //partido.setOrganizador(partidoDTO.getOrganizador());
        //partido.cambiarEstrategiaEmparejamiento(partidoDTO.getEstrategiaPartido());;
        return partido;

    }
}
