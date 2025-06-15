package modelo.entidad.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.strategy.emparejamiento.IEmparejador;

public class Emparejador {
    private IEmparejador estrategiaEmparejamiento;

    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        return estrategiaEmparejamiento.puedeEmparejar(jugador, partido);
    }

    public void cambiarEstrategiaEmparejamiento(IEmparejador estrategia) {
        this.estrategiaEmparejamiento = estrategia;
    }

    public IEmparejador getEstrategiaEmparejamiento() {
        return this.estrategiaEmparejamiento;
    }
}
