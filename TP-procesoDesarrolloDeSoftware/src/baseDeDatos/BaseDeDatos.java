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

    //singleton
    public static BaseDeDatos getInstancia() {
        if(instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    /*
        metodos para JUGADOR
     */
    public void insertJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }

    public Jugador getJugadorById(int id) {
        return this.jugadores.get(id - 1);
    }


    /*
        metodos para DEPORTE
     */
    public void insertDeporte(Deporte deporte) {
        this.deportes.add(deporte);
    }

    public Deporte getDeporteById(int id) {
        if (id >= 0 && id < this.deportes.size()) {
            return this.deportes.get(id - 1);
        }
        else {
            throw new IllegalArgumentException("No existe el deporte con ese id: " + id);
        }
    }

    public void updateDeporte(int id, Deporte deporte) {
        if (id >= 0 && id < this.deportes.size()) {
            this.deportes.set(id, deporte);
        }
        else {
            throw new IllegalArgumentException("No existe el deporte con ese id: " + id);
        }
    }
}
