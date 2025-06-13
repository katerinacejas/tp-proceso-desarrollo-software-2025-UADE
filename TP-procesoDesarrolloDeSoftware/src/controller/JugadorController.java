package controller;

import java.util.ArrayList;
import java.util.List;
import baseDeDatos.BaseDeDatos;
import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.ubicacion.Geolocalizacion;
import modelo.entidad.deporte.Deporte;

public class JugadorController {

    private Jugador jugador;

    public void createJugador(JugadorDTO jugadorDTO) {
        Jugador nuevoJugador = this.convertToEntitySinId(jugadorDTO);
        jugador.createJugador(nuevoJugador);
    }

    public JugadorDTO getJugadorById(String id) {
        JugadorDTO jugadorDTO = this.convertToDTO(jugador.getJugadorById(id));
        return jugadorDTO;
    }

    public void updateJugador(JugadorDTO jugadorDTO) {
        //TODO: fijate como lo hice en Deporte Controller (esta terminado todo el circuito)
    }

    public void deleteJugador(String id) {
        //TODO: fijate como lo hice en Deporte Controller (esta terminado todo el circuito)
    }

    public boolean authJugador(LoginDTO loginDTO){
        //TODO
        return true;
    }

    private Jugador convertToEntitySinId(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombreUsuario(jugadorDTO.getNombreUsuario());
        jugador.setContrasenia(jugadorDTO.getContrasenia());
        jugador.setCelular(jugadorDTO.getCelular());
        jugador.setEmail(jugadorDTO.getEmail());
        jugador.setDeportesFavoritos(getDeportesByIds(jugadorDTO.getDeportesFavoritos())); 
        jugador.setGeolocalizacion(convertLatLongToGeolocalizacion(jugadorDTO.getLatitud(), jugadorDTO.getLongitud()));
        return jugador;
    }

    private JugadorDTO convertToDTO(Jugador jugador){
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombreUsuario(jugador.getNombreUsuario());
        jugadorDTO.setContrasenia(jugador.getContrasenia());
        jugadorDTO.setCelular(jugador.getCelular());
        jugadorDTO.setEmail(jugador.getEmail());
        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setDeportesFavoritos(setIdsDeportesFavoritos(jugador.getDeportesFavoritos()));
        jugadorDTO.setLatitud(jugador.getGeolocalizacion().getLatitud());
        jugadorDTO.setLongitud(jugador.getGeolocalizacion().getLongitud());
        return jugadorDTO;
    }
    
    private List<Deporte> getDeportesByIds(List<String> idsDeportesFavoritos){
        /* En validación con el profesor */
        List<Deporte> deportesFavoritos = new ArrayList<Deporte>();
        for (String idDeporte : idsDeportesFavoritos){
            deportesFavoritos.add(BaseDeDatos.getInstancia().getDeporteById(idDeporte));
        }
        return deportesFavoritos;
    }

    private List<String> setIdsDeportesFavoritos(List<Deporte> deportesFavoritos){
        /* En validación con el profesor */
        List<String> idsDeportesFavoritos = new ArrayList<String>();
        for (Deporte deporte : deportesFavoritos){
            idsDeportesFavoritos.add(deporte.getId());
        }
        return idsDeportesFavoritos;
    }

    private Geolocalizacion convertLatLongToGeolocalizacion(double latitud, double longitud){
        Geolocalizacion geolocalizacionJugador = new Geolocalizacion();
        geolocalizacionJugador.setLatitud(latitud);
        geolocalizacionJugador.setLongitud(longitud);
        return geolocalizacionJugador;
    }

}
