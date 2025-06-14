package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoFinalizado implements IEstadoPartido {

    //TODO: DEFINIR MEJOR EL TEXTO DEL ERROR EN LOS DEMAS METODOS
    /// DONE
    @Override
    public void cancelar(Partido partido) {
        throw new IllegalStateException("No se puede cancelar un partido finalizado.");
    }

    @Override
    public void necesitarJugadores(Partido partido) {
        throw new IllegalStateException("No se puede necesitar jugadores en un partido finalizado.");
    }

    @Override
    public void armar(Partido partido) {
        throw new IllegalStateException("No se puede armar un partido que ya está finalizado.");
    }


    @Override
    public void confirmar(Partido partido) {
        throw new IllegalStateException("No se puede confirmar un partido que ya está finalizado.");
    }

    @Override
    public void jugar(Partido partido) {
        throw new IllegalStateException("No se puede jugar un partido que ya está finalizado.");
    }

    @Override
    public void finalizar(Partido partido) {
        partido.cambiarEstado(new PartidoFinalizado());
        //TODO: ADEMAS DE PONERLE EL ESTADO FINALIZADO, HACEMOS ALGO MAS??

    }
}
