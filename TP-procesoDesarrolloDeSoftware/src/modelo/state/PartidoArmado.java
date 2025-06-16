package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public class PartidoArmado extends AbstractEstadoPartido {

    public PartidoArmado(Partido partido) {
        super(partido);
    }

    @Override
    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado(partido));
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido que ya está armado!----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {

    }

    @Override
    public void confirmar() {
        partido.cambiarEstado(new PartidoConfirmado(partido));
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

    }

    @Override
    public boolean puedeSerCancelado() {
        return true;
    }
}
