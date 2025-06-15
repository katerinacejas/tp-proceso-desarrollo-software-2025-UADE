package modelo.dao;

import baseDeDatos.BaseDeDatos;
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

    public void deletePartido(String id) {
        baseDeDatos.deletePartido(id);
    }

    public List<Partido> getAllPartidosNecesitanJugadores() {
        return baseDeDatos.getAllPartidosNecesitanJugadores();
    }
}
