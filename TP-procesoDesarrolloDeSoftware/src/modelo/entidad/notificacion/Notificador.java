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

    public Notificador(Partido partido, IServicioNotificacion estrategiaNotificacion) {
        this.partido = partido;
        this.estrategiaNotificacion = estrategiaNotificacion;
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

        String mensaje = generarMensajeSegunEstado(jugador);
        notificacion.setMensaje(mensaje);

        return notificacion;
    }

    private String generarMensajeSegunEstado(Jugador jugador) {
        String estadoClase = partido.getEstado().getClass().getSimpleName();
        String deporte = partido.getDeporte().getNombre();
        String horario = partido.getHorarioEncuentro().toString();

        switch (estadoClase) {
            case "PartidoArmado":
                return String.format("¡Hola %s! El partido de %s está completo y listo para ser confirmado. Horario: %s",
                        jugador.getNombreUsuario(), deporte, horario);

            case "PartidoConfirmado":
                return String.format("¡Hola %s! El partido de %s ha sido confirmado. ¡Nos vemos el %s!",
                        jugador.getNombreUsuario(), deporte, horario);

            case "PartidoCancelado":
                return String.format("Hola %s, lamentamos informarte que el partido de %s del %s ha sido cancelado.",
                        jugador.getNombreUsuario(), deporte, horario);

            case "PartidoEnJuego":
                return String.format("¡Hola %s! El partido de %s está por comenzar. ¡Que lo disfrutes!",
                        jugador.getNombreUsuario(), deporte);

            case "PartidoFinalizado":
                return String.format("¡Hola %s! El partido de %s ha finalizado. ¡Gracias por participar!",
                        jugador.getNombreUsuario(), deporte);

            case "PartidoNecesitamosJugadores":
                return String.format("¡Hola %s! Se ha unido un nuevo jugador al partido de %s. Faltan %d jugadores más.",
                        jugador.getNombreUsuario(), deporte,
                        partido.getDeporte().getCantJugadores() - partido.getCantidadParticipantes());

            default:
                return String.format("¡Hola %s! Hay una actualización en tu partido de %s.",
                        jugador.getNombreUsuario(), deporte);
        }
    }

    public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia){
        this.estrategiaNotificacion = estrategia;
    }
}
