package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoCancelado implements IEstadoPartido {
    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new PartidoCancelado());
    }

    //TODO: DEFINIR MEJOR EL ERROR EN LOS METODOS QUE NO SON DE CANCELAR.
    @Override
    public void necesitarJugadores(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }

    @Override
    public void armar(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }

    @Override
    public void confirmar(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }

    @Override
    public void jugar(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }

    @Override
    public void finalizar(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }
}
