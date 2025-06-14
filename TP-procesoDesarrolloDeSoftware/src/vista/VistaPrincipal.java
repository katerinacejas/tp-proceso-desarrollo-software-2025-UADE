package vista;

import controller.DeporteController;
import controller.JugadorController;
import controller.PartidoController;
import controller.ReseniaController;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.entidad.jugador.Jugador;
import modelo.enumerador.NivelJuego;

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
        this.crearDeportesPreCargados();
    }

    private void crearDeportesPreCargados() {
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
        int opcionMenu;
        System.out.println("¡Bienvenido/a a nuestra app para partidos!, Elija una opcion ingresando el numero:" +
                "\n 1: Registrarse" +
                "\n 2: Iniciar sesion" +
                "\n 3: Salir");
        opcionMenu = input.nextInt();
        input.nextLine(); // limpia el salto de línea
        JugadorDTO jugador = null;
        switch (opcionMenu) {
            case 1:
                jugador = this.registrarse();
                break;
            case 2:
                jugador = this.iniciarSesion();
                break;
            case 3:
                return;
        }
        if(jugador != null) {
            this.menuSesionIniciada(jugador);
        }
        else {
            this.iniciar();
        }

    }

    private JugadorDTO registrarse() {
        JugadorDTO jugadorDTO = new JugadorDTO();
        System.out.println("Formulario de registro de jugador: ");

        System.out.println("Ingrese un nombre de usuario: ");
        String nombreUsuario = input.nextLine();
        jugadorDTO.setNombreUsuario(nombreUsuario);

        System.out.println("Ingrese su email: ");
        String email = input.nextLine();
        jugadorDTO.setEmail(email);

        System.out.println("Ingrese su contraseña: ");
        String contrasenia = input.nextLine();
        jugadorDTO.setContrasenia(contrasenia);

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

        System.out.println("Desea agregar un deporte favorito? " +
                "\n 0: NO" +
                "\n 1: SI");
        int agregarDeporteFav;
        agregarDeporteFav = input.nextInt();
        input.nextLine(); // limpia el salto de línea

        Map<String, NivelJuego> nivelDeporte = new HashMap<>();
        String nivelJuego;
        String deporteFavorito;

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

        jugadorController.createJugador(jugadorDTO);
        System.out.println("--------¡Jugador Creado! Sesion iniciada :) --------");
        return jugadorDTO;
    }

    private JugadorDTO iniciarSesion() {
        LoginDTO loginDTO = new LoginDTO();
        System.out.println("Formulario de inicio de sesion de jugador: ");

        System.out.println("Ingrese su email: ");
        String email = input.nextLine();
        loginDTO.setEmail(email);

        System.out.println("Ingrese su contraseña: ");
        String contrasenia = input.nextLine();
        loginDTO.setContrasenia(contrasenia);

        JugadorDTO jugador = jugadorController.authJugador(loginDTO);

        if(jugador != null){
            System.out.println("¡Sesion iniciada correctamente!");
            return jugador;
        }
        System.out.println("Email y contraseña incorrecta. Se redigirá al menu principal");
        return null;
    }

    private void menuSesionIniciada(JugadorDTO jugadorDTO) {
        int opcionMenu;
        System.out.println("¡Bienvenido/a a nuestra app para partidos!, Elija una opcion ingresando el numero:" +
                "\n 1: Crear partido" +
                "\n 2: " +
                "\n 3: Cerrar Sesion");
        opcionMenu = input.nextInt();
        input.nextLine(); // limpia el salto de línea

        while(opcionMenu != 3) {
            switch (opcionMenu) {
                case 1:
                    this.crearPartido();
                    break;
                case 2:

                    break;
            }
        }

        this.iniciar();
    }

    private void crearPartido() {

    }
}


















