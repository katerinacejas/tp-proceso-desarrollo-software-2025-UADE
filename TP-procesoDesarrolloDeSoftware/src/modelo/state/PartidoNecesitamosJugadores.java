package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoNecesitamosJugadores implements IEstadoPartido {
    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new PartidoCancelado());
    }

    @Override
    public void necesitarJugadores(Partido partido) {
        partido.cambiarEstado(new PartidoNecesitamosJugadores());
    }

    @Override
    public void armar(Partido partido) {
        partido.cambiarEstado(new PartidoArmado());
    }

    @Override
    public void confirmar(Partido partido) {
        throw new IllegalStateException("No se puede confirmar un partido que aún necesita jugadores.");

    }

    @Override
    public void jugar(Partido partido) {
        throw new IllegalStateException("No se puede jugar un partido que aún necesita jugadores.");

    }

    @Override
    public void finalizar(Partido partido) {
        throw new IllegalStateException("No se puede finalizar un partido que aún necesita jugadores.");
    }
}
