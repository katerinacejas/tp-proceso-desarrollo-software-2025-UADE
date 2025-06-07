package controller;

import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.entidad.jugador.Jugador;


public class JugadorController {

    private Jugador jugador;

    public boolean createJugador(JugadorDTO jugadorDTO) {
        jugador = new Jugador();
        jugador.createJugador(jugadorDTO);
        return true;
        //Jugador jugador = convertirAJugador(jugadorDTO); //de esta forma convierto a un JugadorDTO en jugador.
        //JugadorDAO.createJugador(jugador); // me conectaría directamente con el DAO sin necesidad de pasar por jugador. Sería como ir al servicio.
    }

    public JugadorDTO getJugadorById(int id) {
        //Creo que acá se deberia crear el DTO.
        return jugador.getJugadorById(id);
    }

    public boolean updateJugador(JugadorDTO jugadorDTO) {
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

     /*private Jugador convertirAJugador(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombreUsuario(jugadorDTO.getNombreUsuario());
        jugador.setContrasenia(jugadorDTO.getContrasenia());
        jugador.setCelular(jugadorDTO.getCelular());
        jugador.setEmail(jugadorDTO.getEmail());
        jugador.setDeportesFavoritos(jugadorDTO.getDeportesFavoritos());
        jugador.setGeolocalizacion(jugadorDTO.getGeolocalizacion());
        return jugador;
    }*/
}
