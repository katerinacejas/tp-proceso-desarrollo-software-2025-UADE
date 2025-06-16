package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public abstract class AbstractEstadoPartido {
    Partido partido;

    public AbstractEstadoPartido(){
    }

    public void setContexto(Partido partido) {
        this.partido = partido;
    }

    public abstract String mensajeEstado();
    public abstract void cancelar();
    public abstract void emparejar(Jugador jugador);
    public abstract void eliminar(Jugador jugador);
    public abstract void confirmar();
    public abstract void jugar();
    public abstract void finalizar();
    public abstract void agregarResenia(Resenia resenia);
    public abstract boolean puedeSerCancelado();
}
