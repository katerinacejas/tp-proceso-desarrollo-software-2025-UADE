package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.entidad.jugador.Jugador;
import modelo.entidad.ubicacion.Geolocalizacion;
import modelo.enumerador.NivelJuego;
import modelo.entidad.NivelJuego.NivelJugadorDeporte;
import modelo.entidad.deporte.Deporte;

public class JugadorController {

    private Jugador jugador;
    private Deporte deporte;

    public JugadorController(){
        jugador = new Jugador();
        deporte = new Deporte();
    }

    public void createJugador(JugadorDTO jugadorDTO) {
        Jugador nuevoJugador = this.convertToEntitySinId(jugadorDTO);
        jugador.createJugador(nuevoJugador);
        jugadorDTO.setId(nuevoJugador.getId());
    }

    public JugadorDTO getJugadorById(String id) {
        JugadorDTO jugadorDTO = this.convertToDTO(jugador.getJugadorById(id));
        return jugadorDTO;
    }

    public void updateJugador(JugadorDTO jugadorDTO) {
        Jugador jugadorActualizado = this.convertToEntitySinId(jugadorDTO);
        jugadorActualizado.setId(jugadorDTO.getId());
        jugador.updateJugador(jugadorActualizado);
    }

    public void deleteJugador(String id) {
        jugador.deleteJugador(id);
    }

    public JugadorDTO authJugador(LoginDTO loginDTO){
        Jugador jugadorSesionIniciada = jugador.authJugador(loginDTO.getEmail(), loginDTO.getContrasenia());
        if (jugadorSesionIniciada != null){
            JugadorDTO jugadorSesionIniciadaDTO = this.convertToDTO(jugadorSesionIniciada);
            return jugadorSesionIniciadaDTO;
        }
        return null;
    }

    private Jugador convertToEntitySinId(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombreUsuario(jugadorDTO.getNombreUsuario());
        jugador.setContrasenia(jugadorDTO.getContrasenia());
        jugador.setCelular(jugadorDTO.getCelular());
        jugador.setEmail(jugadorDTO.getEmail());
        jugador.setDeportesFavoritos(getDeportesByIds(jugadorDTO.getDeportesFavoritos())); 
        jugador.setGeolocalizacion(convertLatLongToGeolocalizacion(jugadorDTO.getLatitud(), jugadorDTO.getLongitud()));
        jugador.setNivelPorDeporte(convertMapToNivelPorDeporte(jugadorDTO.getNivelPorDeporte()));
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
        jugadorDTO.setNivelPorDeporte(convertNivelPorDeporteToMap(jugador.getNivelPorDeporte()));
        return jugadorDTO;
    }

    private List<Deporte> getDeportesByIds(List<String> idsDeportesFavoritos){
        List<Deporte> deportesFavoritos = new ArrayList<Deporte>();
        for (String idDeporte : idsDeportesFavoritos){
            deportesFavoritos.add(deporte.getDeporteById(idDeporte));
        }
        return deportesFavoritos;
    }

    private List<String> setIdsDeportesFavoritos(List<Deporte> deportesFavoritos){
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

    private List<NivelJugadorDeporte> convertMapToNivelPorDeporte(Map<String, NivelJuego> nivelPorDeporte) {
        List<NivelJugadorDeporte> nivelesJugadorDeporte = new ArrayList<>();
        for (Map.Entry<String, NivelJuego> entry : nivelPorDeporte.entrySet()) {
            String deporteId = entry.getKey();
            NivelJuego nivel = entry.getValue();
            Deporte deporteKey = deporte.getDeporteById(deporteId);
            NivelJugadorDeporte nivelJugadorDeporte = new NivelJugadorDeporte();
            nivelJugadorDeporte.setDeporte(deporteKey);
            nivelJugadorDeporte.setNivelJuego(nivel);
            nivelesJugadorDeporte.add(nivelJugadorDeporte);
        }
        return nivelesJugadorDeporte;
    }

    private Map<String, NivelJuego> convertNivelPorDeporteToMap(List<NivelJugadorDeporte> nivelPorDeporte) {
        Map<String, NivelJuego> mapNivelPorDeporte = new HashMap<>();
        for (NivelJugadorDeporte nivelJugadorDeporte : nivelPorDeporte) {
            String deporteId = nivelJugadorDeporte.getDeporte().getId(); 
            NivelJuego nivel = nivelJugadorDeporte.getNivelJuego();
            mapNivelPorDeporte.put(deporteId, nivel);
        }
        return mapNivelPorDeporte;
    }

}
