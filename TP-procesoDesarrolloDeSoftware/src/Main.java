import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.DeporteController;
import controller.JugadorController;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;
import modelo.enumerador.NivelJuego;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        DeporteController dController = new DeporteController();
        DeporteDTO volleyDTO = new DeporteDTO();
        volleyDTO.setNombre("VOLLEY");
        volleyDTO.setCantJugadores(12);
        dController.createDeporte(volleyDTO);
        JugadorController jController = new JugadorController();
        /*JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombreUsuario("jRodriguez");
        jugadorDTO.setDeportesFavoritos(Arrays.asList(volleyDTO.getId()));
        jController.createJugador(jugadorDTO);*/
        JugadorDTO jugadorDTO2 = new JugadorDTO();
        jugadorDTO2.setNombreUsuario("kCejas");
        jugadorDTO2.setDeportesFavoritos(Arrays.asList(volleyDTO.getId()));
        Map<String,NivelJuego> nivelPorDeporteJugador2 = new HashMap<>();
        nivelPorDeporteJugador2.put(volleyDTO.getId(), NivelJuego.PRINCIPIANTE);
        jugadorDTO2.setNivelPorDeporte(nivelPorDeporteJugador2);
        jController.createJugador(jugadorDTO2);
        jugadorDTO2 = jController.getJugadorById(jugadorDTO2.getId());
        System.out.println(jugadorDTO2.getNivelPorDeporte());
    }
}