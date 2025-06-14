package controller;

import modelo.dto.ReseniaDTO;
import modelo.entidad.partido.Resenia;

public class ReseniaController {

    private Resenia resenia;

    public ReseniaController() {
        resenia = new Resenia();
    }

    public ReseniaDTO createResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntityCreate(reseniaDTO);
        nuevaResenia = resenia.createResenia(nuevaResenia);
        return this.convertToDTO(nuevaResenia);
    }

    public ReseniaDTO getReseniaById(String id) {
        ReseniaDTO reseniaDTO = this.convertToDTO(resenia.getReseniaById(id));
        return reseniaDTO;
    }

    public void updateResenia(ReseniaDTO reseniaDTO) {
        Resenia nuevaResenia = this.convertToEntity(reseniaDTO);
        resenia.updateResenia(nuevaResenia);
    }

    public void deleteResenia(String id) {
        resenia.deleteResenia(id);
    }

    private Resenia convertToEntityCreate(ReseniaDTO reseniaDTO) {
        Resenia resenia = new Resenia();
        resenia.setComentario(reseniaDTO.getComentario());
        resenia.setPuntuacion(reseniaDTO.getPuntuacion());
        return resenia;
    }

    private Resenia convertToEntity(ReseniaDTO reseniaDTO) {
        Resenia resenia = new Resenia();
        resenia.setId(reseniaDTO.getId());
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
