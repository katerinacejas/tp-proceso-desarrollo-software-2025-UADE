import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import controller.DeporteController;
import controller.JugadorController;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        DeporteController dController = new DeporteController();
        DeporteDTO volleyDTO = new DeporteDTO();
        volleyDTO.setNombre("VOLLEY");
        volleyDTO.setCantJugadores(12);
        dController.createDeporte(volleyDTO);
        JugadorController jController = new JugadorController();
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombreUsuario("jRodriguez");
        jugadorDTO.setDeportesFavoritos(Arrays.asList(volleyDTO.getId()));
        jController.createJugador(jugadorDTO);
        JugadorDTO jugadorDTO2 = new JugadorDTO();
        jugadorDTO2.setNombreUsuario("kCejas");
        jugadorDTO2.setDeportesFavoritos(Arrays.asList(volleyDTO.getId()));
        jController.createJugador(jugadorDTO2);
    }
}