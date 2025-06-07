package baseDeDatos;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

import java.util.HashSet;
import java.util.Set;

public class BaseDeDatos {
    private Set<Jugador> jugadores;
    private Set<Partido> partidos;
    private Set<Deporte> deportes;
    private Set<Resenia> resenias;

    private static BaseDeDatos instancia; //para singleton

    private BaseDeDatos() {
        jugadores = new HashSet<>();
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
}
