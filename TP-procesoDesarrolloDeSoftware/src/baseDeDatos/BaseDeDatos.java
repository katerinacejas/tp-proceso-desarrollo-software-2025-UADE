package baseDeDatos;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseDeDatos {
    private List <Jugador> jugadores;
    private Set<Partido> partidos;
    private Set<Deporte> deportes;
    private Set<Resenia> resenias;

    private static BaseDeDatos instancia; //para singleton

    private BaseDeDatos() {
        jugadores = new ArrayList<>();
        partidos = new HashSet<>();
        deportes = new HashSet<>();
        resenias = new HashSet<>();
    }

    public static BaseDeDatos getInstancia() {
        if(instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    public void insertJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }

    public Jugador getJugadorById(int id) {
        return jugadores.get(id);
    }
}
