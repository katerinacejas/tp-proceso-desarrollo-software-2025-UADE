import vista.VistaPrincipal;

public class Main {
    public static void main(String[] args) {

        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.iniciar();

        /*
            dejo comentado los test manuales por si no quiero usar la vista

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

         */
    }
}
