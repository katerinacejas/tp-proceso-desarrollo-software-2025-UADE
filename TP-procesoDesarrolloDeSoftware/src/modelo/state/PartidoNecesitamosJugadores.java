package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Resenia;

public class PartidoNecesitamosJugadores extends AbstractEstadoPartido {

    public PartidoNecesitamosJugadores() {
    }

    @Override
    public String mensajeEstado() {
        return String.format("¡Hola %s! Se ha unido un nuevo jugador al partido de %s. Faltan %d jugadores más.",
                partido.getDeporte(),
                partido.cantidadJugadoresQueFaltan());
    }

    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado());
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        partido.agregarJugador(jugador);
        System.out.println("----------------¡Te uniste al partido! :) ----------------");
        if (partido.tieneTodosLosJugadores()) {
            partido.cambiarEstado(new PartidoArmado());
        }
    }

    @Override
    public void eliminar(Jugador jugador) {
        partido.removeJugador(jugador);
        System.out.println("----------------¡Se te eliminó del partido para " +partido.getDeporte() + "! :) ----------------");
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
        System.out.println("----------------¡No es posible agregar una reseña sobre un partido que no fue finalizado!.----------------¡");
    }

    @Override
    public boolean puedeSerCancelado() {
        return true;
    }
}
