package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.jugador.Jugador;

public class JugadorDAO {

    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createJugador(Jugador jugador) {
        baseDeDatos.insertJugador(jugador);
    }

    public Jugador getJugadorById(String id) {
        return baseDeDatos.getJugadorById(id);
    }

    public void updateJugador(Jugador jugadorActualizado) {
        baseDeDatos.updateJugador(jugadorActualizado);
    }

    public void deleteJugador(String id) {
        baseDeDatos.deleteJugador(id);
    }

    public boolean authJugador(String email, String contrasenia) {
        return baseDeDatos.authJugador(email, contrasenia);
    }
}
