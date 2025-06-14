package modelo.dto;

import java.sql.Timestamp;
import java.util.List;

public class PartidoDTO {
    private String id;
    private DeporteDTO deporte;
    private int duracionMin;
    private String zonaGeografica;
    private Timestamp horarioEncuentro;
    private List<JugadorDTO> participantes;
    private JugadorDTO organizador;
    private List<ReseniaDTO> resenias;

    public DeporteDTO getDeporte() {
        return this.deporte;
    }

    public void setDeporte(DeporteDTO deporte) {
        this.deporte = deporte;
    }

    public int getDuracionMin() {
        return this.duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getZonaGeografica() {
        return this.zonaGeografica;
    }

    public void setZonaGeografica(String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public Timestamp getHorarioEncuentro() {
        return this.horarioEncuentro;
    }

    public void setHorarioEncuentro(Timestamp horarioEncuentro) {
        this.horarioEncuentro = horarioEncuentro;
    }

    public List<JugadorDTO> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(List<JugadorDTO> participantes) {
        this.participantes = participantes;
    }

    public JugadorDTO getOrganizador() {
        return this.organizador;
    }

    public void setOrganizador(JugadorDTO organizador) {
        this.organizador = organizador;
    }

    public List<ReseniaDTO> getResenias() {
        return this.resenias;
    }

    public void setResenias(List<ReseniaDTO> resenias) {
        this.resenias = resenias;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
}
