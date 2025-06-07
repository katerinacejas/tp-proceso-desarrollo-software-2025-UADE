package modelo.dto;

import java.util.List;

public class JugadorDTO {
    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private List<DeporteDTO> deportesFavoritos;
    private String celular;
    private String geolocalizacion;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<DeporteDTO> getDeportesFavoritos() {
        return deportesFavoritos;
    }

    public void setDeportesFavoritos(List<DeporteDTO> deportesFavoritos) {
        this.deportesFavoritos = deportesFavoritos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }
}
