package baseDeDatos;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;
import modelo.entidad.partido.Resenia;
import modelo.entidad.ubicacion.Geolocalizacion;
import modelo.entidad.ubicacion.ZonaGeografica;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseDeDatos {
    private List <Jugador> jugadores;
    private List<Partido> partidos;
    private List<Deporte> deportes;
    private List<Resenia> resenias;
    private List<ZonaGeografica> zonasGeograficas;

    private static BaseDeDatos instancia; //para singleton

    private BaseDeDatos() {
        jugadores = new ArrayList<>();
        partidos = new ArrayList<>();
        deportes = new ArrayList<>();
        resenias = new ArrayList<>();
        zonasGeograficas = new ArrayList<>();
        cargarZonasGeograficas();
        cargarDeportes();
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
                System.out.println("Ya existe un jugador con ese email, no se puede registrar. Por favor iniciar sesion");
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
        System.out.println("No existe el jugador con ese id: " + id);
        return null;
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

    public Jugador authJugador(String email, String contrasenia) {
        for (Jugador jugador : jugadores) {
            if (jugador.getEmail().equals(email) && jugador.getContrasenia().equals(contrasenia)) {
                return this.getJugadorById(jugador.getId());
            }
        }
        return null;
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
        System.out.println("No existe el deporte con ese id: " + id);
        return null;
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
        System.out.println("No existe el deporte con ese id: " + id);
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
        System.out.println("No existe la resenia con ese id: " + id);
        return null;
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
        System.out.println("No existe la resenia con ese id: " + id);
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
        System.out.println("No existe el partido con ese id: " + id);
       return null;
    }

    public void updatePartido(Partido partidoActualizado) {
        for (int i = 0; i < partidos.size(); i++) {
            if (partidos.get(i).getId().equals(partidoActualizado.getId())) {
                partidos.set(i, partidoActualizado);
            }
        }
    }

    public void deletePartido(String id) {
         for (int i = 0; i < partidos.size(); i++) {
            if (partidos.get(i).getId().equals(id)) {
                partidos.remove(i);
            }
        }
    }

    public List<Partido> getAllPartidos(){
        return partidos;
    }

    public ZonaGeografica getZonaGeograficaByName(String zonaGeografica) {
        for (ZonaGeografica zona : zonasGeograficas){
            if (zona.getNombre().equals(zonaGeografica)){
                return zona;
            }
        }
        System.out.println("No existe una zona con ese nombre: " + zonaGeografica);
        return null;
    }

    private void cargarZonasGeograficas() {
        zonasGeograficas.add(new ZonaGeografica("Palermo", new Geolocalizacion(-34.578, -58.426), 5));
        zonasGeograficas.add(new ZonaGeografica("Recoleta", new Geolocalizacion(-34.588, -58.393), 3));
        zonasGeograficas.add(new ZonaGeografica("Belgrano", new Geolocalizacion(-34.563, -58.460), 4));
        zonasGeograficas.add(new ZonaGeografica("Caballito", new Geolocalizacion(-34.618, -58.441), 4));
        zonasGeograficas.add(new ZonaGeografica("Villa Urquiza", new Geolocalizacion(-34.580, -58.491), 4));
        zonasGeograficas.add(new ZonaGeografica("Almagro", new Geolocalizacion(-34.609, -58.419), 3));
        zonasGeograficas.add(new ZonaGeografica("San Telmo", new Geolocalizacion(-34.621, -58.373), 2));
        zonasGeograficas.add(new ZonaGeografica("Flores", new Geolocalizacion(-34.634, -58.468), 5));
        zonasGeograficas.add(new ZonaGeografica("Barracas", new Geolocalizacion(-34.642, -58.381), 4));
        zonasGeograficas.add(new ZonaGeografica("Puerto Madero", new Geolocalizacion(-34.608, -58.362), 3));
    }

    private void cargarDeportes() {
        deportes.add(new Deporte("futbol", 22));
        deportes.add(new Deporte("handball", 14));
        deportes.add(new Deporte("tenis", 2));
        deportes.add(new Deporte("hockey", 22));
        deportes.add(new Deporte("voley", 12));
        deportes.add(new Deporte("rugby", 30));
    }

}
