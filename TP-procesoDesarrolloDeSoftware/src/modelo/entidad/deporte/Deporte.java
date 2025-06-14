package modelo.entidad.deporte;

import modelo.dao.DeporteDAO;

public class Deporte {
    private String id;
    private String nombre;
    private int cantJugadores;

    // metodos de que se llaman en el controller
    public Deporte createDeporte(Deporte deporte) {
        DeporteDAO deporteDAO = new DeporteDAO();
        return deporteDAO.createDeporte(deporte);
    }

    public Deporte getDeporteById(String id){
        DeporteDAO deporteDAO = new DeporteDAO();
        return deporteDAO.getDeporteById(id);
    }

    public void updateDeporte(Deporte deporte) {
        DeporteDAO deporteDAO = new DeporteDAO();
        deporteDAO.updateDeporte(deporte);
    }

    public void deleteDeporte(String id) {
        DeporteDAO deporteDAO = new DeporteDAO();
        deporteDAO.deleteDeporte(id);
    }


    // setters y getters

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantJugadores() {
        return this.cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }
}
