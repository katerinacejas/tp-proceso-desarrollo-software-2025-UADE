package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.deporte.Deporte;

public class DeporteDAO {
    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public void createDeporte(Deporte deporte) {
        baseDeDatos.insertDeporte(deporte);
    }

    public Deporte getDeporteById(String id) {
        return baseDeDatos.getDeporteById(id);
    }

    public void updateDeporte(Deporte deporte) {
        baseDeDatos.updateDeporte(deporte);
    }

    public void deleteDeporte(String id) {
        baseDeDatos.deleteDeporte(id);
    }
}
