package modelo.state;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

import java.time.LocalDateTime;

public class PartidoConfirmado extends AbstractEstadoPartido {

    public PartidoConfirmado(Partido partido) {
        super(partido);
    }

    @Override
    public void cancelar() {
        partido.cambiarEstado(new PartidoCancelado(partido));
        System.out.println("----------------¡Se ha cancelado el partido que creaste para " +partido.getDeporte() + "! :) ----------------");
    }

    @Override
    public void emparejar(Jugador jugador) {
        System.out.println("----------------¡No es posible agregar un jugador a un partido confirmado.----------------¡");
    }

    @Override
    public void eliminar(Jugador jugador) {

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
        partido.cambiarEstado(new PartidoEnJuego(partido));
        // imprime un mensaje del tipo: "Se inicio el partido de tenis en Palermo programado para el dia 2025-06-15 15:30"
        System.out.println("Se inicio el partido de "+ partido.getDeporte() + " en " + partido.getZonaGeografica() + " programado para el dia " + partido.getHorarioEncuentro());
    }

    @Override
    public void finalizar() {
        System.out.println("----------------¡No es posible finalizar un partido confirmado sin haberlo jugado previamente!.----------------¡");
    }

    @Override
    public void agregarResenia(Resenia resenia) {

    }

    @Override
    public boolean puedeSerCancelado() {
        return true;
    }
}
