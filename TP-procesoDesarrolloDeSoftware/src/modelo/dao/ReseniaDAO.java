package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.partido.Resenia;

public class ReseniaDAO {
    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createResenia(Resenia resenia) {
        baseDeDatos.insertResenia(resenia);
    }

    public Resenia getReseniaById(String id) {
        return baseDeDatos.getReseniaById(id);
    }

    public void updateResenia(Resenia resenia) {
        baseDeDatos.updateResenia(resenia);
    }

    public void deleteResenia(String id) {
        baseDeDatos.deleteResenia(id);
    }
}
