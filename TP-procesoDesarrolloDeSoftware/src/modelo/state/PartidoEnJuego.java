package modelo.state;

import modelo.entidad.partido.Partido;

public class PartidoEnJuego implements IEstadoPartido {
    @Override
    public void cancelar(Partido partido) {
        throw new IllegalStateException("No se puede cancelar un partido en juego.");
    }

    @Override
    public void necesitarJugadores(Partido partido) {
        throw new IllegalStateException("No se puede necesitar jugadores en un partido en juego.");
    }

    @Override
    public void armar(Partido partido) {
        throw new IllegalStateException("No se puede armar un partido que ya está en juego.");
    }

    @Override
    public void confirmar(Partido partido) {
        throw new IllegalStateException("No se puede confirmar un partido que ya está en juego.");
    }

    @Override
    public void jugar(Partido partido) {
        throw new IllegalStateException("El partido ya está en juego.");
    }

    @Override
    public void finalizar(Partido partido) {
        partido.cambiarEstado(new PartidoFinalizado());
    }
}
