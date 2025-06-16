package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

import java.time.LocalDateTime;

public class PartidoEnJuego extends AbstractEstadoPartido {

    public PartidoEnJuego() {
    }

    @Override
    public String mensajeEstado() {
        return String.format("El partido de %s está por comenzar. ¡Que lo disfrutes!",
                partido.getDeporte());
    }

    @Override
    public void cancelar() {
        System.out.println("----------------¡No es posible cancelar un partido en juego.!----------------¡");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("---------------- ¡No es posible agregar un jugador a un partido que está en juego!.----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {
        System.out.println("----------------¡No es posible eliminarte de un partido en juego!.----------------¡");
    }

    @Override
    public void confirmar() {
        System.out.println("---------------- ¡No es posible confirmar un partido en juego!.----------------¡");
    }

    @Override
    public void jugar() {
        System.out.println("---------------- El partido ya estaba en juego ----------------¡");
    }

    @Override
    public void finalizar() {
        LocalDateTime finalizacionPartido = partido.getHorarioEncuentro().minusMinutes(((long) partido.getDuracionMin()));
        if(LocalDateTime.now().isAfter(finalizacionPartido)){
            partido.cambiarEstado(new PartidoFinalizado());
            // imprime un mensaje del tipo: "Se finalizó el partido de tenis en Palermo programado para el dia 2025-06-15 15:30"
            System.out.println("Se finalizó el partido de "+ partido.getDeporte() + " en " + partido.getZonaGeografica() + " programado para el dia " + partido.getHorarioEncuentro());
            return;
        }

        System.out.println("---------------- Aún no se puede finalizar el partido, su horario de finalización es a las " + finalizacionPartido + "----------------¡");
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
