package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoCancelado implements IEstadoPartido {
    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new PartidoCancelado());
    }


    //TODO: DEFINIR MEJOR EL ERROR EN LOS METODOS QUE NO SON DE CANCELAR.
    /// En este caso, no se puede pasar a otro estado desde "PartidoCancelado".

    @Override
    public void necesitarJugadores(Partido partido) {
        throw new IllegalStateException("No se puede necesitar jugadores en un partido cancelado.");
    }

    @Override
    public void armar(Partido partido) {
        throw new IllegalStateException("No se puede armar un partido cancelado.");
    }

    @Override
    public void confirmar(Partido partido) {
        throw new IllegalStateException("No se puede confirmar un partido cancelado.");

    }

    @Override
    public void jugar(Partido partido) {
        throw new IllegalStateException("No se puede jugar un partido cancelado.");
    }

    @Override
    public void finalizar(Partido partido) {
        throw new IllegalStateException("No se puede finalizar un partido cancelado.");
    }
}
