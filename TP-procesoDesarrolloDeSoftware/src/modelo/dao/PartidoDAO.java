package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

import java.util.List;

public class PartidoDAO {

     private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createPartido(Partido partido) {
        baseDeDatos.insertPartido(partido);
    }

    public Partido getPartidoById(String id) {
        return baseDeDatos.getPartidoById(id);
    }

    public void updatePartido(Partido partidoActualizado) {
        baseDeDatos.updatePartido(partidoActualizado);
    }

    public void deletePartido(String id) {
        baseDeDatos.deletePartido(id);
    }

    public List<Partido> getAllPartidos() {
        return baseDeDatos.getAllPartidos();
    }
}
