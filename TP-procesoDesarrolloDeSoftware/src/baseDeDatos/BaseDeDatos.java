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
    private List<Partido> partidos;
    private List<Deporte> deportes;
    private List<Resenia> resenias;

    private static BaseDeDatos instancia; //para singleton

    private BaseDeDatos() {
        jugadores = new ArrayList<>();
        partidos = new ArrayList<>();
        deportes = new ArrayList<>();
        resenias = new ArrayList<>();
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
        return this.jugadores.get(id);
    }


    public void insertDeporte(Deporte deporte) {
        this.deportes.add(deporte);
    }

    public Deporte getDeporteById(int id) {
        return this.deportes.get(id);
    }
}
