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

    // TODO: Dejo comentado por si quieren chequear mejor 
    // @Override
    // public void notificar() {
    //     // TODO: hay que de alguna forma mandarle la lista a la estrategia pero no guardarla como atributo en el notificador

    //     partido.obtenerListadoJugadores().forEach(
    //             jugador -> crearNotificacion(jugador));
    //     List<Notificacion> listaNotificaciones = new ArrayList<>();
    //     estrategiaNotificacion.notificar(listaNotificaciones);
    // }

    // public void crearNotificacion(Jugador jugador) {
    //     //TODO
    // }

    // public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia){
    //     this.estrategiaNotificacion = estrategia;
    // }

    public Notificador(Partido partido, IServicioNotificacion estrategia) {
        this.partido = partido;
        this.estrategiaNotificacion = estrategia;
    }

    public void cambiarEstrategiaNotificacion(IServicioNotificacion estrategia) {
        this.estrategiaNotificacion = estrategia;
    }

    public void crearNotificacion(Jugador jugador) {
        Notificacion n = new Notificacion();
        n.setMensaje("¡Nuevo partido disponible!");
        n.setEmailRemitente(partido.getOrganizador().getEmail());
        n.setEmailDestinatario(jugador.getEmail());
        n.setCelularRemitente(partido.getOrganizador().getCelular());
        n.setCelularDestinatario(jugador.getCelular());

        estrategiaNotificacion.notificar(List.of(n));
    }

    public void notificar() {
        List<Notificacion> notificaciones = new ArrayList<>();
        for (Jugador j : partido.getParticipantes()) {
            Notificacion n = new Notificacion();
            n.setMensaje("¡Recordatorio de partido!");
            n.setEmailRemitente(partido.getOrganizador().getEmail());
            n.setEmailDestinatario(j.getEmail());
            n.setCelularRemitente(partido.getOrganizador().getCelular());
            n.setCelularDestinatario(j.getCelular());
            notificaciones.add(n);
        }
        estrategiaNotificacion.notificar(notificaciones);
    }
}
