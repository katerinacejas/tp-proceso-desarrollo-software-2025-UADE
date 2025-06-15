package modelo.entidad.NivelJuego;

import modelo.entidad.deporte.Deporte;
import modelo.enumerador.NivelJuego;

public class NivelJugadorDeporte {
    
    private NivelJuego nivelJuego;
    private Deporte deporte;

    public NivelJuego getNivelJuego() {
        return nivelJuego;
    }
    public void setNivelJuego(NivelJuego nivelJuego) {
        this.nivelJuego = nivelJuego;
    }
    public Deporte getDeporte() {
        return deporte;
    }
    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
}
