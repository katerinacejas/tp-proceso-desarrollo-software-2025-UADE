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


    // setters y getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }

}
