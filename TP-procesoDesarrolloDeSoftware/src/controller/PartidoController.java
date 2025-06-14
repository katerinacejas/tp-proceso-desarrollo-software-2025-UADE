package controller;

import modelo.dto.PartidoDTO;
import modelo.entidad.partido.Partido;

public class PartidoController {

    private Partido partido;

    public PartidoController(){
        partido = new Partido();
    }

    public boolean createPartido(PartidoDTO partidoDTO) {
        //TODO
        return true;
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
}
