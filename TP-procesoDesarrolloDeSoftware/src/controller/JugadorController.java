package controller;

import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.entidad.jugador.Jugador;

public class JugadorController {

    private Jugador jugador;

    public boolean createJugador(JugadorDTO jugadorDTO) {
        Jugador nuevoJugador = convertToEntity(jugadorDTO);
        jugador.createJugador(nuevoJugador);
        return true;
    }

    public JugadorDTO getJugadorById(int id) {
        JugadorDTO jugadorDTO = convertToDTO(jugador.getJugadorById(id));
        return jugadorDTO;
    }

    public boolean updateJugador(int id, JugadorDTO jugadorDTO) {
        //TODO
        return true;
    }

    public boolean deleteJugador(int id) {
        return true;
    }

    public boolean authJugador(LoginDTO loginDTO){
        //TODO
        return true;
    }

     private Jugador convertToEntity(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombreUsuario(jugadorDTO.getNombreUsuario());
        jugador.setContrasenia(jugadorDTO.getContrasenia());
        jugador.setCelular(jugadorDTO.getCelular());
        jugador.setEmail(jugadorDTO.getEmail());
        //jugador.setDeportesFavoritos(jugadorDTO.getDeportesFavoritos()); Pasar de DTO a Deporte ¿necesita invocacion del Controller de Deporte?
        //jugador.setGeolocalizacion(jugadorDTO.getGeolocalizacion()); Pasar de String a Geolocalizacion
        return jugador;
    }

    private JugadorDTO convertToDTO(Jugador jugador){
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombreUsuario(jugador.getNombreUsuario());
        jugadorDTO.setContrasenia(jugador.getContrasenia());
        jugadorDTO.setCelular(jugador.getCelular());
        jugadorDTO.setEmail(jugador.getEmail());
        //jugadorDTO.setDeportesFavoritos(jugador.getDeportesFavoritos()); Pasar de Deporte a DeporteDTO ¿necesita invocacion del Controller de Deporte?
        //jugadorDTO.setGeolocalizacion(jugador.getGeolocalizacion()); Pasar de Geolocalizacion a String
        return jugadorDTO;
        
    }
}
