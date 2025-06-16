package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Resenia;

import java.time.LocalDateTime;

public class PartidoConfirmado extends AbstractEstadoPartido {

    public PartidoConfirmado() {
    }

    @Override
    public String mensajeEstado() {
        return String.format(" El partido de %s ha sido confirmado. ¡Nos vemos el %s!",
                partido.getDeporte().getNombre(), partido.getHorarioEncuentro());
    }

    @Override
    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado());
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte().getNombre() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido confirmado.----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {
        System.out.println("----------------¡No es posible eliminarte de un partido confirmado!.----------------¡");
    }

    @Override
    public void confirmar() {
        System.out.println("----------------¡No es posible confirmar un partido ya confirmado.----------------¡");
    }

    @Override
    public void jugar() {
        if(LocalDateTime.now().isBefore(partido.getHorarioEncuentro())){
            System.out.println("---------------- Aún no se puede iniciar el partido, su horario es a las " + partido.getHorarioEncuentro() + "----------------¡");
            return;
        }
        partido.cambiarEstado(new PartidoEnJuego());
        // imprime un mensaje del tipo: "Se inicio el partido de tenis en Palermo programado para el dia 2025-06-15 15:30"
        System.out.println("Se inicio el partido de "+ partido.getDeporte().getNombre() + " en " + partido.getZonaGeografica().getNombre() + " programado para el dia " + partido.getHorarioEncuentro());
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido confirmado sin haberlo jugado previamente!.----------------¡");
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
