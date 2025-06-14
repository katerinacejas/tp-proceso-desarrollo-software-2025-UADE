package vista;

import controller.DeporteController;
import controller.JugadorController;
import controller.PartidoController;
import controller.ReseniaController;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;
import modelo.enumerador.NivelJuego;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaPrincipal {
    JugadorController jugadorController;
    DeporteController deporteController;
    ReseniaController reseniaController;
    PartidoController partidoController;
    Scanner input;

    public VistaPrincipal() {
        jugadorController = new JugadorController();
        deporteController = new DeporteController();
        reseniaController = new ReseniaController();
        partidoController = new PartidoController();
        input = new Scanner(System.in);
        input.nextLine(); // limpia el buffer
    }

    public void crearDeportesPreCargados() {
        DeporteDTO futbolDTO = new DeporteDTO();
        futbolDTO.setNombre("futbol");
        futbolDTO.setCantJugadores(22);
        DeporteDTO handballDTO = new DeporteDTO();
        handballDTO.setNombre("handball");
        handballDTO.setCantJugadores(14);
        DeporteDTO tenisDTO = new DeporteDTO();
        tenisDTO.setNombre("tenis");
        tenisDTO.setCantJugadores(4);
        DeporteDTO hockeyDTO = new DeporteDTO();
        hockeyDTO.setNombre("hockey");
        hockeyDTO.setCantJugadores(22);
        DeporteDTO voleyDTO = new DeporteDTO();
        voleyDTO.setNombre("voley");
        voleyDTO.setCantJugadores(12);
        DeporteDTO rugbyDTO = new DeporteDTO();
        rugbyDTO.setNombre("rugby");
        rugbyDTO.setCantJugadores(30);
        deporteController.createDeporte(futbolDTO);
        deporteController.createDeporte(handballDTO);
        deporteController.createDeporte(tenisDTO);
        deporteController.createDeporte(hockeyDTO);
        deporteController.createDeporte(voleyDTO);
        deporteController.createDeporte(rugbyDTO);
    }

    public void iniciar() {
        int opcionMenu = 0;
        int salir = 9;

        while(opcionMenu != salir) {
            System.out.println("Bienvenido/a, elija una opcion ingresando el numero:" +
                    "\n 1: Registrarse" +
                    "\n 2: Iniciar sesion" +
                    "\n 3: Crear partido" +
                    "\n 9: Salir");

            opcionMenu = input.nextInt();
            input.nextLine(); // limpia el salto de línea
            switch (opcionMenu) {
                case 1:
                    this.registrarse();
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }

    public void registrarse() {
        JugadorDTO jugadorDTO = new JugadorDTO();
        System.out.println("Formulario de registro de jugador: ");

        System.out.println("Ingrese su nombre completo: ");
        String nombreUsuario = input.nextLine();
        jugadorDTO.setNombreUsuario(nombreUsuario);

        System.out.println("Ingrese su email: ");
        String email = input.nextLine();
        jugadorDTO.setEmail(email);

        System.out.println("Ingrese su contraseña: ");
        String contrasenia = input.nextLine();
        jugadorDTO.setContrasenia(contrasenia);

        System.out.println("Ingrese un deporte favorito :" +
                "\nOpciones validas: futbol, handball, tenis, hockey, voley, rugby: ");
        String deporteFavorito = input.nextLine();
        jugadorDTO.agregarDeporteFavorito(deporteFavorito);

        System.out.println("Ingrese el nivel de juego para el deporte favorito " + deporteFavorito +
                "\nOpciones validas: PRINCIPIANTE, INTERMEDIO, AVANZADO: ");
        String nivelJuego = input.nextLine().trim().toUpperCase();
        Map<String, NivelJuego> nivelDeporte = new HashMap<>();
        nivelDeporte.put(deporteFavorito, NivelJuego.valueOf(nivelJuego));
        jugadorDTO.setNivelPorDeporte(nivelDeporte);

        System.out.println("Desea agregar otro deporte favorito? " +
                "\n 0: NO" +
                "\n 1: SI");
        int agregarDeporteFav;
        agregarDeporteFav = input.nextInt();
        input.nextLine(); // limpia el salto de línea

        while (agregarDeporteFav == 1) {
            System.out.println("Ingrese un deporte favorito :" +
                    "\nOpciones validas: futbol, handball, tenis, hockey, voley, rugby: ");
            deporteFavorito = input.nextLine();
            jugadorDTO.agregarDeporteFavorito(deporteFavorito);

            System.out.println("Ingrese el nivel de juego para el deporte favorito " + deporteFavorito +
                    "\nOpciones validas: PRINCIPIANTE, INTERMEDIO, AVANZADO: ");
            nivelJuego = input.nextLine().trim().toUpperCase();
            nivelDeporte.put(deporteFavorito, NivelJuego.valueOf(nivelJuego));
            jugadorDTO.setNivelPorDeporte(nivelDeporte);

            System.out.println("Desea agregar otro deporte favorito? " +
                    "\n 0: NO" +
                    "\n 1: SI: ");
            agregarDeporteFav = input.nextInt();
            input.nextLine(); // limpia el salto de línea
        }

        System.out.println("Ingrese su celular: ");
        String celular = input.nextLine();
        jugadorDTO.setCelular(celular);

        System.out.println("Ingrese la longitud de su ubicacion: ");
        String longitudStr = input.nextLine().replace(",", ".");
        double longitud = Double.parseDouble(longitudStr);
        jugadorDTO.setLongitud(longitud);

        System.out.println("Ingrese la latitud de su ubicacion: ");
        String latitudStr = input.nextLine().replace(",", ".");
        double latitud = Double.parseDouble(latitudStr);
        jugadorDTO.setLatitud(latitud);

        jugadorController.createJugador(jugadorDTO);
        System.out.println(jugadorDTO.getId());
    }
}