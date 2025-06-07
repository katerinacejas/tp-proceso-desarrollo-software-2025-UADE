package controller;

import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.dao.JugadorDAO;


public class JugadorController {

    private JugadorDAO jugadorDAO;

    public boolean createJugador(JugadorDTO jugadorDTO) {
        //TODO
        return true;
    }

    public JugadorDTO getJugadorById(int id) {
        //TODO
        JugadorDTO jugadorDTO = new JugadorDTO();
        return jugadorDTO;
    }

    public boolean updateJugador(JugadorDTO jugadorDTO) {
        //TODO
        return true;
    }

    public boolean deleteJugador(JugadorDTO jugadorDTO) {
        //TODO
        return true;
    }

    public boolean authJugador(LoginDTO loginDTO){
        //TODO
        return true;
    }
}
