package controller;

import modelo.dto.DeporteDTO;
import modelo.entidad.deporte.Deporte;

public class DeporteController {

    private Deporte deporte;


    public void createDeporte(DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = this.convertToEntitySinId(deporteDTO);
        deporte.createDeporte(nuevoDeporte);
        deporteDTO.setId(nuevoDeporte.getId());
    }
  
    public DeporteController() {
        deporte = new Deporte();
    }
    

    public DeporteDTO getDeporteById(String id) {
        DeporteDTO deporteDTO = this.convertToDTO(deporte.getDeporteById(id));
        return deporteDTO;
    }

    public void updateDeporte(DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = this.convertToEntity(deporteDTO);
        deporte.updateDeporte(nuevoDeporte);
    }

    public void deleteDeporte(String id) {
        deporte.deleteDeporte(id);
    }

    private Deporte convertToEntityCreate(DeporteDTO deporteDTO) {
        Deporte deporte = new Deporte();
        deporte.setNombre(deporteDTO.getNombre());
        deporte.setCantJugadores(deporteDTO.getCantJugadores());
        return deporte;
    }

    private Deporte convertToEntity(DeporteDTO deporteDTO) {
        Deporte deporte = new Deporte();
        deporte.setId(deporteDTO.getId());
        deporte.setNombre(deporteDTO.getNombre());
        deporte.setCantJugadores(deporteDTO.getCantJugadores());
        return deporte;
    }

    private DeporteDTO convertToDTO(Deporte deporte) {
        DeporteDTO deporteDTO = new DeporteDTO();
        deporteDTO.setCantJugadores(deporte.getCantJugadores());
        deporteDTO.setNombre(deporte.getNombre());
        deporteDTO.setId(deporte.getId());
        return deporteDTO;
    }
}
