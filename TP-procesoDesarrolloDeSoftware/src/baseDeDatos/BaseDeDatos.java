package baseDeDatos;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public String generadorIdRandom() {
        return UUID.randomUUID().toString();
    }

    /*
        metodos para JUGADOR
     */
    public void insertJugador(Jugador jugador){
        jugador.setId(this.generadorIdRandom());
        this.jugadores.add(jugador);
    }

    public Jugador getJugadorById(String id) {
        for (Jugador jugador : jugadores) {
            if (jugador.getId().equals(id)) {
                return jugador;
            }
        }
        throw new IllegalArgumentException("No existe el jugador con ese id: " + id);
    }


    /*
        metodos para DEPORTE
     */
    public void insertDeporte(Deporte deporte) {
        deporte.setId(this.generadorIdRandom());
        this.deportes.add(deporte);
    }

    public Deporte getDeporteById(String id) {
        for (Deporte deporte : deportes) {
            if (deporte.getId().equals(id)) {
                return deporte;
            }
        }
        throw new IllegalArgumentException("No existe el deporte con ese id: " + id);
    }

    public void updateDeporte(Deporte deporteActualizado) {
        int indice = 0;
        for (Deporte deporte : deportes) {
            if (deporte.getId().equals(deporteActualizado.getId())) {
                this.deportes.set(indice, deporteActualizado);
                return;
            }
            indice ++;
        }
    }

    public void deleteDeporte(String id) {
        int indice = 0;
        for (Deporte deporte : deportes) {
            if (deporte.getId().equals(id)) {
                this.deportes.remove(indice);
                return;
            }
            indice ++;
        }
        throw new IllegalArgumentException("No existe el deporte con ese id: " + id);
    }
}
