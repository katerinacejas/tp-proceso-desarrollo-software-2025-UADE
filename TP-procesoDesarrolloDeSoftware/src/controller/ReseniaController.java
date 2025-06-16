package controller;

import modelo.dto.ReseniaDTO;
import modelo.entidad.partido.Resenia;

public class ReseniaController {

    private Resenia resenia;

    public ReseniaController() {
        resenia = new Resenia();
    }

    public void createResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntity(reseniaDTO);
        resenia.createResenia(nuevaResenia);
        reseniaDTO.setId(nuevaResenia.getId());
    }

    public ReseniaDTO getReseniaById(String id) {
        return this.convertToDTO(resenia.getReseniaById(id));
    }

    public void updateResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntity(reseniaDTO);
        reseniaDTO.setId(nuevaResenia.getId());
        resenia.updateResenia(nuevaResenia);
    }

    public void deleteResenia(String id) {
        resenia.deleteResenia(id);
    }

    private Resenia convertToEntity(ReseniaDTO reseniaDTO) {
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
