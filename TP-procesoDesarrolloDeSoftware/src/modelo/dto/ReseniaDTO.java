package modelo.dto;

public class ReseniaDTO {
    private String id;
    private int puntuacion;
    private String comentario;

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
}