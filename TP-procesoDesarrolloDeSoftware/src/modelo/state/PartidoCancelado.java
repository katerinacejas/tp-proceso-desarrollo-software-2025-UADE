package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public class PartidoCancelado extends AbstractEstadoPartido {

    public PartidoCancelado(Partido partido) {
        super(partido);
    }

    @Override
    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado(partido));
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido cancelado!.----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {
        System.out.println("----------------¡No es posible eliminarte de un partido cancelado!.----------------¡");
    }

    @Override
    public void confirmar() {
        System.out.println("----------------¡No es posible confirmar un partido cancelado!.----------------¡");
    }

    @Override
    public void jugar() {
        System.out.println("----------------¡No es posible jugar un partido cancelado!.----------------¡");
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido que ya fue cancelado!.----------------¡");
    }

    @Override
    public void agregarResenia(Resenia resenia) {

    }

    @Override
    public boolean puedeSerCancelado() {
        return false;
    }
}
