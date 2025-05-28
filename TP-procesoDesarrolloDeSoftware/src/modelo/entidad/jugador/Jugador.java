package modelo.entidad.jugador;

import modelo.entidad.Deporte;

public class Jugador {
    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private Deporte deporteFavorito;
    private NivelJuego nivelJuego;
    private String celular;
    private Ubicacion ubicacion;

    public NivelJuego getNivelJuego(){
        return this.nivelJuego;
    }
}
