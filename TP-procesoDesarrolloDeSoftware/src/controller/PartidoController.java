package controller;

import modelo.dto.PartidoDTO;
import modelo.dao.PartidoDAO;

public class PartidoController {
    private PartidoDAO partidoDAO;

    public boolean createPartido(PartidoDTO partidoDTO) {
        //TODO
        return true;
    }

    public PartidoDTO getPartidoById(int id) {
        //TODO
        PartidoDTO partidoDTO = new PartidoDTO();
        return partidoDTO;
    }

    public boolean updatePartido(PartidoDTO partidoDTO) {
        //TODO
        return true;
    }

    public boolean deletePartido(PartidoDTO partidoDTO) {
        //TODO
        return true;
    }
}
