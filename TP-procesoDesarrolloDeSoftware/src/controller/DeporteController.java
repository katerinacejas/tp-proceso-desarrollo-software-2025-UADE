package controller;

import modelo.dto.DeporteDTO;
import modelo.dao.DeporteDAO;
import modelo.entidad.deporte.Deporte;

public class DeporteController {

    private Deporte deporte;

    public boolean createDeporte(DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = convertToEntity(deporteDTO);
        deporte.createDeporte(nuevoDeporte);
        return true;
    }

    public DeporteDTO getDeporteById(int id) {
        DeporteDTO deporteDTO = convertToDTO(deporte.getDeporteById(id));
        return deporteDTO;
    }

    public boolean updateDeporte(int id, DeporteDTO deporteDTO) {
        Deporte nuevoDeporte = convertToEntity(deporteDTO);
        deporte.updateDeporte(id, nuevoDeporte);
        return true;
    }

    public boolean deleteDeporte(int id) {
        deporte.deleteDeporte(id);
        return true;
    }

    private Deporte convertToEntity(DeporteDTO deporteDTO) {
        Deporte deporte = new Deporte();
        deporte.setNombre(deporteDTO.getNombre());
        deporte.setCantJugadores(deporteDTO.getCantJugadores());
        return deporte;
    }

    private DeporteDTO convertToDTO(Deporte deporte) {
        DeporteDTO deporteDTO = new DeporteDTO();
        deporteDTO.setCantJugadores(deporte.getCantJugadores());
        deporteDTO.setNombre(deporteDTO.getNombre());
        return deporteDTO;
    }
}
