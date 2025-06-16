package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Resenia;

public class PartidoArmado extends AbstractEstadoPartido {

    public PartidoArmado() {
    }

    @Override
    public String mensajeEstado() {
        return String.format("El partido de %s está completo y listo para ser confirmado. Horario: %s",
                partido.getDeporte(), partido.getHorarioEncuentro());
    }

    @Override
    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado());
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido que ya está armado!----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {
        partido.removeJugador(jugador);
        partido.cambiarEstado(new PartidoNecesitamosJugadores());
        System.out.println("----------------¡Se te eliminó del partido para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void confirmar() {
        partido.cambiarEstado(new PartidoConfirmado());
        System.out.println("----------------¡Se ha confirmado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void jugar() {
        System.out.println("----------------¡No es posible jugar un partido que aun no fue confirmado!.----------------¡");
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido que aun no fue jugado!.----------------¡");
    }

    @Override
    public void agregarResenia(Resenia resenia) {
        System.out.println("----------------¡No es posible agregar una reseña sobre un partido que no fue finalizado!.----------------¡");
    }

    @Override
    public boolean puedeSerCancelado() {
        return true;
    }
}
