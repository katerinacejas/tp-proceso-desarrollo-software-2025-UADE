package modelo.entidad.jugador;

import modelo.dao.JugadorDAO;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;
import modelo.entidad.NivelJuego.NivelJugadorDeporte;
import modelo.entidad.deporte.Deporte;
import modelo.entidad.ubicacion.Geolocalizacion;
import modelo.enumerador.NivelJuego;

import java.util.List;

public class Jugador {

    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private List<Deporte> deportesFavoritos;
    private String celular;
    private Geolocalizacion geolocalizacion;
    private List<NivelJugadorDeporte> niveles;


    public void createJugador(JugadorDTO jugadorDTO) {
        this.nombreUsuario = jugadorDTO.getNombreUsuario();
        this.email = jugadorDTO.getEmail();
        this.contrasenia = jugadorDTO.getContrasenia();
        //this.deportesFavoritos = jugadorDTO.getDeportesFavoritos(); [CONSULTAR porque ahora JugadorDTO conoce a los DeporteDTO]
        this.celular = jugadorDTO.getCelular();
        //this.geolocalizacion = jugadorDTO.getGeolocalizacion();
        JugadorDAO jugadorDAO = new JugadorDAO();
        jugadorDAO.createJugador(this);
    }

    public JugadorDTO getJugadorById(int id) {
        JugadorDAO jugadorDAO = new JugadorDAO();
        return jugadorDAO.getJugadorById(id);
    }

    public NivelJuego getNivel(Deporte deporte) {
        return niveles.stream()
            .filter(n -> n.getDeporte().equals(deporte))
            .findFirst()
            .map(NivelJugadorDeporte::getNivelJuego)
            .orElse(null);
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

    public void setDeportesFavoritos(List<DeporteDTO> deportesFavoritos) {
       // CREO QUE CUANDO SETEAMOS DEPORTES HAY QUE PASARLE DEPORTES O BIEN HACER ALGO CON LOS DEPORTEDTO
    }

    public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
        //ACA SE LE VA A PASAR LAS COORDENADAS Y LUEGO SE VA A SETEAR LA GEOLOCALIZACION
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
