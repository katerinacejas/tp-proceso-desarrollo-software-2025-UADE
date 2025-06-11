package modelo.entidad.partido;

public class Resenia {
    private String id;
    private int puntuacion;
    private String comentario;

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
