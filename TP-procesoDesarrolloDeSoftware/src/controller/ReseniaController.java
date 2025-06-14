package controller;

import modelo.dto.ReseniaDTO;
import modelo.entidad.partido.Resenia;

public class ReseniaController {

    private Resenia resenia;

    public ReseniaController() {
        resenia = new Resenia();
    }

    public void createResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntitySinId(reseniaDTO);
        resenia.createResenia(nuevaResenia);
        reseniaDTO.setId(resenia.getId());
    }

    public ReseniaDTO getReseniaById(String id) {
        ReseniaDTO reseniaDTO = this.convertToDTO(resenia.getReseniaById(id));
        return reseniaDTO;
    }

    public void updateResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntitySinId(reseniaDTO);
        nuevaResenia.setId(reseniaDTO.getId());
        resenia.updateResenia(nuevaResenia);
    }

    public void deleteResenia(String id) {
        resenia.deleteResenia(id);
    }

    private Resenia convertToEntitySinId(ReseniaDTO reseniaDTO) {
        Resenia resenia = new Resenia();
        resenia.setComentario(reseniaDTO.getComentario());
        resenia.setPuntuacion(reseniaDTO.getPuntuacion());
        return resenia;
    }

    private ReseniaDTO convertToDTO(Resenia resenia) {
        ReseniaDTO reseniaDTO = new ReseniaDTO();
        reseniaDTO.setComentario(resenia.getComentario());
        reseniaDTO.setPuntuacion(resenia.getPuntuacion());
        reseniaDTO.setId(resenia.getId());
        return reseniaDTO;
    }
}
