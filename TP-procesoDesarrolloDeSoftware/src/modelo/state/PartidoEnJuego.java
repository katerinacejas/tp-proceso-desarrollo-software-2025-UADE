package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public class PartidoEnJuego extends AbstractEstadoPartido {

    public PartidoEnJuego(Partido partido) {
        super(partido);
    }

    @Override
    public void cancelar() {

    }

    @Override
    public void emparejar(Jugador jugador) {

    }

    @Override
    public void eliminar(Jugador jugador) {

    }

    @Override
    public void confirmar() {

    }

    @Override
    public void jugar() {

    }

    @Override
    public void finalizar() {

    }

    @Override
    public void agregarResenia(Resenia resenia) {

    }
}
