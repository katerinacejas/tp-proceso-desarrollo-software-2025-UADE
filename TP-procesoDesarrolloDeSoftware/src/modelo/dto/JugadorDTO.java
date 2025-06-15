package modelo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.enumerador.NivelJuego;

public class JugadorDTO {
    private String id;
    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private List<String> deportesFavoritos;
    private Map<String, NivelJuego> nivelPorDeporte;
    private String celular;
    private double latitud;
    private double longitud;

    public JugadorDTO(){
        this.deportesFavoritos = new ArrayList<>();
        this.nivelPorDeporte = new HashMap<>();
    }

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

    public List<String> getDeportesFavoritos() {
        return deportesFavoritos;
    }

    public void setDeportesFavoritos(List<String> deportesFavoritos) {
        this.deportesFavoritos = deportesFavoritos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

      public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Map<String, NivelJuego> getNivelPorDeporte() {
        return nivelPorDeporte;
    }

    public void setNivelPorDeporte(Map<String, NivelJuego> nivelPorDeporte) {
        this.nivelPorDeporte = nivelPorDeporte;
    }

    public void agregarDeporteFavorito(String deporteFavorito) {
        this.deportesFavoritos.add(deporteFavorito);
    }
}
