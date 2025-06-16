package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public class PartidoNecesitamosJugadores extends AbstractEstadoPartido {

    public PartidoNecesitamosJugadores(Partido partido) {
        super(partido);
    }

    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado(partido));
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        partido.agregarJugador(jugador);
        System.out.println("----------------¡Te uniste al partido! :) ----------------");
        if (partido.tieneTodosLosJugadores()) {
            partido.cambiarEstado(new PartidoArmado(partido));
        }
        //TODO esta parte notificarObservadores();
    }

    @Override
    public void eliminar(Jugador jugador) {

    }

    @Override
    public void confirmar() {
        System.out.println("----------------¡No es posible confirmar un partido que aun necesita jugadores!.----------------¡");
    }

    @Override
    public void jugar() {
        System.out.println("----------------¡No es posible jugar un partido que aun necesita jugadores!.----------------¡");
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido que aun necesita jugadores!.----------------¡");
    }

    @Override
    public void agregarResenia(Resenia resenia) {

    }

    @Override
    public boolean puedeSerCancelado() {
        return true;
    }
}
