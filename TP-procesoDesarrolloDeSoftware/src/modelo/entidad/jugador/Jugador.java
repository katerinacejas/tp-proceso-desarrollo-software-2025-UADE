package modelo.entidad.jugador;

import modelo.dao.JugadorDAO;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.ubicacion.Geolocalizacion;

import java.util.List;

public class Jugador {

    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private List<Deporte> deportesFavoritos;
    private String celular;
    private Geolocalizacion geolocalizacion;


    public void createJugador(Jugador jugador) {
        JugadorDAO jugadorDAO = new JugadorDAO();
        jugadorDAO.createJugador(jugador);
    }

    public Jugador getJugadorById(int id) {
        JugadorDAO jugadorDAO = new JugadorDAO();
        return jugadorDAO.getJugadorById(id);
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeportesFavoritos(List<Deporte> deportesFavoritos) {
        this.deportesFavoritos = deportesFavoritos;
    }

    public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

      public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public List<Deporte> getDeportesFavoritos() {
        return deportesFavoritos;
    }

    public String getCelular() {
        return celular;
    }

    public Geolocalizacion getGeolocalizacion() {
        return geolocalizacion;
    }


}
