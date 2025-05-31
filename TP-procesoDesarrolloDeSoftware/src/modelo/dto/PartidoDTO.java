package modelo.dto;

import java.sql.Timestamp;
import java.util.List;

public class PartidoDTO {
    private int id;
    private String deporte;
    private int duracionMin;
    private String geolocalizacion;
    private Timestamp horarioEncuentro;
    private List<Integer> participantes;
    private int organizador;
    private List<Integer> resenias;
    private int cantidadMaxima;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public Timestamp getHorarioEncuentro() {
        return horarioEncuentro;
    }

    public void setHorarioEncuentro(Timestamp horarioEncuentro) {
        this.horarioEncuentro = horarioEncuentro;
    }

    public List<Integer> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Integer> participantes) {
        this.participantes = participantes;
    }

    public int getOrganizador() {
        return organizador;
    }

    public void setOrganizador(int organizador) {
        this.organizador = organizador;
    }

    public List<Integer> getResenias() {
        return resenias;
    }

    public void setResenias(List<Integer> resenias) {
        this.resenias = resenias;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }
}
