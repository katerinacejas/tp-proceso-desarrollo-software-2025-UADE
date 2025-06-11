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
}
