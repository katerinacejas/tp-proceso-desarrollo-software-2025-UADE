package modelo.entidad.notificacion;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.observer.IObservers;
import modelo.strategy.notificacion.IServicioNotificacion;
import modelo.strategy.notificacion.NotificacionEmail;
import modelo.strategy.notificacion.NotificacionPush;

import java.util.ArrayList;
import java.util.List;


public class Notificador implements IObservers {
    private IServicioNotificacion estrategiaNotificacion;
    private Partido partido;

    public Notificador(Partido partido){
        this.partido = partido;
    }

    @Override
    public void notificar() {
        List<Notificacion> listaNotificaciones = new ArrayList<>();
        partido.obtenerListadoJugadores().forEach(jugador -> {
            Notificacion notificacion = crearNotificacion(jugador);
            listaNotificaciones.add(notificacion);
        });
        this.cambiarEstrategiaNotificacion(new NotificacionEmail());
        estrategiaNotificacion.notificar(listaNotificaciones);
        this.cambiarEstrategiaNotificacion(new NotificacionPush());
        estrategiaNotificacion.notificar(listaNotificaciones);
    }

    public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia){
        this.estrategiaNotificacion = estrategia;
    }

    private Notificacion crearNotificacion(Jugador jugador) {
        Notificacion notificacion = new Notificacion();
        notificacion.setEmailDestinatario(jugador.getEmail());
        notificacion.setCelularDestinatario(jugador.getCelular());
        //notificacion.setMensaje(this.partido.getEstado().crearMensaje());
         /*
          * Propongo que cada estado que tenga una notificación, construya su cuerpo de mensaje
          */
        return notificacion;
    }

}
