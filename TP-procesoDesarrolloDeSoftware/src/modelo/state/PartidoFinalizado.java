package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

public class PartidoFinalizado extends AbstractEstadoPartido {

    public PartidoFinalizado(Partido partido) {
        super(partido);
    }

    @Override
    public void cancelar() {
        System.out.println("----------------¡No es posible cancelar un partido finalizado!.----------------¡");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido finalizado!.----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {
        System.out.println("----------------¡No es posible eliminarte de un partido finalizado!.----------------¡");
    }

    @Override
    public void confirmar() {
        System.out.println("----------------¡No es posible confirmar un partido finalizado!.----------------¡");
    }

    @Override
    public void jugar() {
        System.out.println("----------------¡No es posible jugar un partido finalizado!.----------------¡");
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido que ya fue finalizado!.----------------¡");
    }

    @Override
    public void agregarResenia(Resenia resenia) {

    }

    @Override
    public boolean puedeSerCancelado() {
        return false;
    }
}
