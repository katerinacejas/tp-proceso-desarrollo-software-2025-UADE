package modelo.entidad.jugador;

import modelo.entidad.deporte.Deporte;
import modelo.entidad.ubicacion.Geolocalizacion;

import java.util.List;

public class Jugador {
    private int dni;
    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private List<Deporte> deportesFavoritos;
    private String celular;
    private Geolocalizacion geolocalizacion;

    /*  ESTE METODO AHORA YA NO VA MAS EN JUGADOR PORQUE EXISTE LA CLASE PARTICIPACION_JUGADOR_PARTIDO

    public NivelJuego getNivelJuego(){
        return this.nivelJuego;
    }
    */
}
