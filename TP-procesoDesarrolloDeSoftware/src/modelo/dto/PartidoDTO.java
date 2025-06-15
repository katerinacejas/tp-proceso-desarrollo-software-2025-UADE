package modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.enumerador.NivelJuego;
import modelo.enumerador.EstrategiaPartido;

public class PartidoDTO {
    private String id;
    private String deporte;
    private int duracionMin;
    private String zonaGeografica;
    private LocalDateTime horarioEncuentro;
    private Set<String> participantes;
    private String organizador;
    private List<String> resenias;
    private EstrategiaPartido estrategiaPartido;
    private NivelJuego nivelJuego;

    public PartidoDTO() {
        this.participantes = new HashSet<>();
        this.resenias = new ArrayList<>();
    }

    public NivelJuego getNivelJuego() {
        return nivelJuego;
    }

    public void setNivelJuego(NivelJuego nivelJuego) {
        this.nivelJuego = nivelJuego;
    }

    public EstrategiaPartido getEstrategiaPartido() {
        return estrategiaPartido;
    }

    public void setEstrategiaPartido(EstrategiaPartido estrategiaPartido) {
        this.estrategiaPartido = estrategiaPartido;
    }

    public String getDeporte() {
        return this.deporte;
    }

    public void setDeporte(String deporte) {
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

    public LocalDateTime getHorarioEncuentro() {
        return this.horarioEncuentro;
    }

    public void setHorarioEncuentro(LocalDateTime horarioEncuentro) {
        this.horarioEncuentro = horarioEncuentro;
    }

    public Set<String> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(Set<String> participantes) {
        this.participantes = participantes;
    }

    public String getOrganizador() {
        return this.organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public List<String> getResenias() {
        return this.resenias;
    }

    public void setResenias(List<String> resenias) {
        this.resenias = resenias;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public void agregarParticipantePorDefault(String idOrganizadorJugadorDefault) {
        this.participantes.add(idOrganizadorJugadorDefault);
    }
}
