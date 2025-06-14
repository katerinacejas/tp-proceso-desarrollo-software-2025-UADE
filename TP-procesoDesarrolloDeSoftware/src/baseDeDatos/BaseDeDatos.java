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

    // este metodo se usa para el insert de cada entidad
    public String generadorIdRandom() {
        return UUID.randomUUID().toString();
    }

    /*
        metodos para JUGADOR
    */
    public void insertJugador(Jugador jugador){
        for (Jugador j : jugadores) {
            if (j.getEmail().equals(jugador.getEmail())) {
                throw new IllegalArgumentException("Ya existe un jugador con ese email, no se puede registrar. Por favor iniciar sesion");
            }
        }
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

    public void updateJugador(Jugador jugadorActualizado) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getId().equals(jugadorActualizado.getId())) {
                jugadores.set(i, jugadorActualizado);
            }
        }
    }

    public void deleteJugador(String id) {
         for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getId().equals(id)) {
                jugadores.remove(i);
            }
        }
    }

    public boolean authJugador(String email, String contrasenia) {
        for (Jugador jugador : jugadores) {
            if (jugador.getEmail().equals(email)) {
                return jugador.getContrasenia().equals(contrasenia);
            }
        }
        return false;
    }

    /*
        metodos para DEPORTE
    */
    public Deporte insertDeporte(Deporte deporte) {
        deporte.setId(deporte.getNombre());
        this.deportes.add(deporte);
        return deporte;
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


    /*
        metodos para RESENIA
    */
    public Resenia insertResenia(Resenia resenia) {
        resenia.setId(this.generadorIdRandom());
        this.resenias.add(resenia);
        return resenia;
    }

    public Resenia getReseniaById(String id) {
        for (Resenia resenia : resenias) {
            if (resenia.getId().equals(id)) {
                return resenia;
            }
        }
        throw new IllegalArgumentException("No existe la resenia con ese id: " + id);
    }

    public void updateResenia(Resenia reseniaActualizada) {
        int indice = 0;
        for (Resenia resenia : resenias) {
            if (resenia.getId().equals(reseniaActualizada.getId())) {
                this.resenias.set(indice, reseniaActualizada);
                return;
            }
            indice ++;
        }
    }

    public void deleteResenia(String id) {
        int indice = 0;
        for (Resenia resenia : resenias) {
            if (resenia.getId().equals(id)) {
                this.resenias.remove(indice);
                return;
            }
            indice ++;
        }
        throw new IllegalArgumentException("No existe la resenia con ese id: " + id);
    }

    /*
        metodos para PARTIDO
    */

    public void insertPartido(Partido partido) {
        partido.setId(this.generadorIdRandom());
        this.partidos.add(partido);
    }

    public Partido getPartidoById(String id) {
       for (Partido partido : partidos) {
            if (partido.getId().equals(id)) {
                return partido;
            }
        }
        throw new IllegalArgumentException("No existe el partido con ese id: " + id);
    }

    public void deletePartido(String id) {
         for (int i = 0; i < partidos.size(); i++) {
            if (partidos.get(i).getId().equals(id)) {
                partidos.remove(i);
            }
        }
    }

}
