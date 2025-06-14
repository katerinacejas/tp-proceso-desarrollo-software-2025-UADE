package modelo.entidad.partido;

import modelo.dao.ReseniaDAO;

public class Resenia {
    private String id;
    private int puntuacion;
    private String comentario;

    // metodos de que se llaman en el controller
    public void createResenia(Resenia resenia) {
        ReseniaDAO reseniaDAO = new ReseniaDAO();
        reseniaDAO.createResenia(resenia);
    }

    public Resenia getReseniaById(String id){
        ReseniaDAO deporteDAO = new ReseniaDAO();
        return deporteDAO.getReseniaById(id);
    }

    public void updateResenia(Resenia deporte) {
        ReseniaDAO deporteDAO = new ReseniaDAO();
        deporteDAO.updateResenia(deporte);
    }

    public void deleteResenia(String id) {
        ReseniaDAO deporteDAO = new ReseniaDAO();
        deporteDAO.deleteResenia(id);
    }


    // setters y getters

    public String getComentario() {
        return this.comentario;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public String getId() {
        return this.id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setId(String id) {
        this.id = id;
    }
}
