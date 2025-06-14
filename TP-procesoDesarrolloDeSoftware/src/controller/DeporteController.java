package controller;

import modelo.dto.DeporteDTO;
import modelo.entidad.deporte.Deporte;

public class DeporteController {

    private Deporte deporte = new Deporte();;

    public void createDeporte(DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = this.convertToEntitySinId(deporteDTO);
        deporte.createDeporte(nuevoDeporte);
        deporteDTO.setId(nuevoDeporte.getId());
    }

    public DeporteDTO getDeporteById(String id) {
        DeporteDTO deporteDTO = this.convertToDTO(deporte.getDeporteById(id));
        return deporteDTO;
    }

    public void updateDeporte(DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = this.convertToEntitySinId(deporteDTO);
        nuevoDeporte.setId(deporteDTO.getId());
        deporte.updateDeporte(nuevoDeporte);
    }

    public void deleteDeporte(String id) {
        deporte.deleteDeporte(id);
    }

    private Deporte convertToEntitySinId(DeporteDTO deporteDTO) {
        Deporte deporte = new Deporte();
        deporte.setNombre(deporteDTO.getNombre());
        deporte.setCantJugadores(deporteDTO.getCantJugadores());
        return deporte;
    }

    private DeporteDTO convertToDTO(Deporte deporte) {
        DeporteDTO deporteDTO = new DeporteDTO();
        deporteDTO.setCantJugadores(deporte.getCantJugadores());
        deporteDTO.setNombre(deporteDTO.getNombre());
        deporteDTO.setId(deporte.getId());
        return deporteDTO;
    }
}
