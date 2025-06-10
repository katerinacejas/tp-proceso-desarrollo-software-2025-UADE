package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.deporte.Deporte;

public class DeporteDAO {
    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createDeporte(Deporte deporte) {
        baseDeDatos.insertDeporte(deporte);
    }

    public Deporte getDeporteById(int id) {
        return baseDeDatos.getDeporteById(id);
    }
}
