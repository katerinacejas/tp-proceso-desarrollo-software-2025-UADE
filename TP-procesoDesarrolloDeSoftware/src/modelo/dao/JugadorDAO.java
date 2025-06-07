package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.dto.JugadorDTO;
import modelo.entidad.jugador.Jugador;

public class JugadorDAO {

    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createJugador(Jugador jugador) {
        baseDeDatos.insertarJugador(jugador);
    }

    public JugadorDTO getJugadorById(int id) {
        //Consultar al profesor si está bien que sea el DAO que crea el DTO y en caso de que si 
        //setear los demás parámetros   
        Jugador jugador = baseDeDatos.getJugadorById(id);
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombreUsuario(jugador.getNombreUsuario());
        return jugadorDTO;
    }
}
