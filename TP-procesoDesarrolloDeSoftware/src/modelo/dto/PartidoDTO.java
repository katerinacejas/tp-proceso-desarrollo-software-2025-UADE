package modelo.dto;

import java.sql.Timestamp;
import java.util.List;

public class PartidoDTO {
    private DeporteDTO deporte;
    private int duracionMin;
    private String geolocalizacion;
    private Timestamp horarioEncuentro;
    private List<JugadorDTO> participantes;
    private JugadorDTO organizador;
    private List<ReseniaDTO> resenias;

    public DeporteDTO getDeporte() {
        return deporte;
    }

    public void setDeporte(DeporteDTO deporte) {
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

    public List<JugadorDTO> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<JugadorDTO> participantes) {
        this.participantes = participantes;
    }

    public JugadorDTO getOrganizador() {
        return organizador;
    }

    public void setOrganizador(JugadorDTO organizador) {
        this.organizador = organizador;
    }

    public List<ReseniaDTO> getResenias() {
        return resenias;
    }

    public void setResenias(List<ReseniaDTO> resenias) {
        this.resenias = resenias;
    }
}
