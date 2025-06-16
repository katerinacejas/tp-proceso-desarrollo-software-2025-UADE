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

    public Notificador(Partido partido) {
        this.partido = partido;
    }

    @Override
    public void notificar() {
        List<Notificacion> listaNotificaciones = new ArrayList<>();

        for (Jugador jugador : partido.obtenerListadoJugadores()) {
            Notificacion notificacion = crearNotificacion(jugador);
            if (notificacion != null) {
                listaNotificaciones.add(notificacion);
            }
        }

        if (!listaNotificaciones.isEmpty()) {
            this.cambiarEstrategiaNotificacion(new NotificacionPush());
            estrategiaNotificacion.notificar(listaNotificaciones);
            this.cambiarEstrategiaNotificacion(new NotificacionEmail());
            estrategiaNotificacion.notificar(listaNotificaciones);
        }
    }
    public Notificacion crearNotificacion(Jugador jugador) {
        if (jugador == null) return null;

        Notificacion notificacion = new Notificacion();

        // Destinatario
        notificacion.setEmailDestinatario(jugador.getEmail());
        notificacion.setCelularDestinatario(jugador.getCelular());

        // Remitente, dejo el organizador del partido como remitente
        if (partido.getOrganizador() != null) {
            notificacion.setEmailRemitente(partido.getOrganizador().getEmail());
            notificacion.setCelularRemitente(partido.getOrganizador().getCelular());
        }

        notificacion.setMensaje(generarMensajeSegunEstado(jugador));

        return notificacion;
    }

    private String generarMensajeSegunEstado(Jugador jugador) {
        String mensajeEstado = partido.mensajeEstado();
        return "Hola " + jugador.getNombreUsuario() + mensajeEstado;
    }

    public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia){
        this.estrategiaNotificacion = estrategia;
    }
}
