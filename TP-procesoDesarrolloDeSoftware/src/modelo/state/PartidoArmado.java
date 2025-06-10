package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoArmado implements IEstadoPartido {
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
        partido.cambiarEstado(new PartidoConfirmado());
    }

    @Override
    public void jugar(Partido partido) {
        throw new IllegalStateException("No se puede jugar un partido armado sin confirmar.");
    }

    @Override
    public void finalizar(Partido partido) {
        throw new IllegalStateException("No se puede finalizar un partido armado sin haber jugado.");
    }
}
