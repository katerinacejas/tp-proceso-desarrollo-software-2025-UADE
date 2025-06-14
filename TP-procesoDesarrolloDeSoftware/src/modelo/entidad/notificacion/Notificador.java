package modelo.entidad.notificacion;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.observer.IObservers;
import modelo.strategy.notificacion.IServicioNotificacion;

import java.util.ArrayList;
import java.util.List;


public class Notificador implements IObservers {
    private IServicioNotificacion estrategiaNotificacion;
    private Partido partido;

    @Override
    public void notificar() {
        // TODO: hay que de alguna forma mandarle la lista a la estrategia pero no guardarla como atributo en el notificador

        partido.obtenerListadoJugadores().forEach(
                jugador -> crearNotificacion(jugador));
        List<Notificacion> listaNotificaciones = new ArrayList<>();
        estrategiaNotificacion.notificar(listaNotificaciones);
    }

    public void crearNotificacion(Jugador jugador) {
        //TODO
    }

    public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia){
        this.estrategiaNotificacion = estrategia;
    }
}
