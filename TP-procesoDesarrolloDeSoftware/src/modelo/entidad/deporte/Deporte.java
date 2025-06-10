package modelo.entidad.deporte;

import modelo.dao.DeporteDAO;

public class Deporte {
    private String nombre;
    private int cantJugadores;

    // metodos de que se llaman en el controller
    public void createDeporte(Deporte deporte) {
        DeporteDAO deporteDAO = new DeporteDAO();
        deporteDAO.createDeporte(deporte);
    }

    public Deporte getDeporteById(int id){
        DeporteDAO deporteDAO = new DeporteDAO();
        return deporteDAO.getDeporteById(id);
    }

    public void updateDeporte(int id, Deporte deporte) {
        DeporteDAO deporteDAO = new DeporteDAO();
        deporteDAO.updateDeporte(id, deporte);
    }

    // setters y getters

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
