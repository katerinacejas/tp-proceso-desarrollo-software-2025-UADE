package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoFinalizado implements IEstadoPartido {

    //TODO: DEFINIR MEJOR EL TEXTO DEL ERROR EN LOS DEMAS METODOS
    @Override
    public void cancelar(Partido partido) {
        throw new UnsupportedOperationException("No se puede pasar a ese estado");
    }

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
        partido.cambiarEstado(new PartidoFinalizado());
        //TODO: ADEMAS DE PONERLE EL ESTADO FINALIZADO, HACEMOS ALGO MAS??
    }
}
