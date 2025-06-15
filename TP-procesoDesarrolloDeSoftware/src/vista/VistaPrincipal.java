package vista;

import controller.DeporteController;
import controller.JugadorController;
import controller.PartidoController;
import controller.ReseniaController;
import modelo.dto.DeporteDTO;
import modelo.dto.JugadorDTO;
import modelo.dto.LoginDTO;
import modelo.dto.PartidoDTO;
import modelo.enumerador.EstrategiaPartido;
import modelo.enumerador.NivelJuego;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VistaPrincipal {
    JugadorController jugadorController;
    DeporteController deporteController;
    ReseniaController reseniaController;
    PartidoController partidoController;
    Scanner input;
    JugadorDTO jugadorDTO;
    DateTimeFormatter formatoDateTime;

    public VistaPrincipal() {
        jugadorController = new JugadorController();
        deporteController = new DeporteController();
        reseniaController = new ReseniaController();
        partidoController = new PartidoController();
        input = new Scanner(System.in);
        formatoDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
        tenisDTO.setCantJugadores(2);
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

        // seteo null el jugadorDTO por si entra a este metodo por haber cerrado la sesión o porque el registro o inicio de sesion falló
        jugadorDTO = null;

        switch (opcionMenu) {
            case 1:
                jugadorDTO = this.registrarse();
                break;
            case 2:
                jugadorDTO = this.iniciarSesion();
                break;
            case 3:
                return;
        }
        if(jugadorDTO != null) {
            this.menuSesionIniciada(jugadorDTO);
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

        JugadorDTO jugadorDTO = jugadorController.authJugador(loginDTO);

        if(jugadorDTO != null){
            System.out.println("¡Sesion iniciada correctamente!");
            return jugadorDTO;
        }
        System.out.println("Email y contraseña incorrecta. Se redigirá al menu principal");
        return null;
    }

    private void menuSesionIniciada(JugadorDTO jugadorDTO) {
        int opcionMenu;
        System.out.println("¡Bienvenido/a " + jugadorDTO.getNombreUsuario() +"!, Elija una opcion ingresando el numero:" +
                "\n 1: Crear partido" +
                "\n 2: Buscar un partido para unirme" +
                "\n 3: Confirmar partido armado (en caso de haber creado uno)" +
                "\n 4: Cancelar partido que cree" +
                "\n 5: Dejar resenia del partido" +
                "\n 6: Cerrar sesion");
        opcionMenu = input.nextInt();
        input.nextLine(); // limpia el salto de línea

        while(opcionMenu != 5) {
            switch (opcionMenu) {
                case 1:
                    this.crearPartido(jugadorDTO);
                    break;
                case 2:
                    this.buscarPartidoParaUnirme(jugadorDTO);
                    break;
                case 3:
                    this.confirmarPartidoCreado(jugadorDTO);
                    break;
                case 4:
                    this.cancelarPartidoCreado(jugadorDTO);
                    break;
                case 5:
                    // TODO this.dejarReseniaPartido(jugadorDTO);
                    break;
            }
        }

        // se cerró la sesion asi que vamos al metodo inicial del menú
        this.iniciar();
    }

    private void crearPartido(JugadorDTO jugadorDTO) {
        PartidoDTO partidoDTO = new PartidoDTO();
        System.out.println("Formulario para crear un partido");

        System.out.println("Ingrese el deporte para el partido:" +
                "\nOpciones validas: futbol, handball, tenis, hockey, voley, rugby: ");
        String deporte = input.nextLine();
        partidoDTO.setDeporte(deporte);

        System.out.println("Ingrese la cantidad de minutos del partido:");
        int duracionMin = input.nextInt();
        partidoDTO.setDuracionMin(duracionMin);

        //TODO: ESTO HAY QUE PONER LA ZONA GEOGRAFICA PERO NO ESTA HECHA EN EL CONTROLLER
        //System.out.println("Ingrese la zona geografica :");

        System.out.println("Ingrese el dia y hora del partido (yyyy-MM-dd hh:mm) :");
        String horarioEncuentroStr = input.nextLine();
        LocalDateTime horarioEncuentro = LocalDateTime.parse(horarioEncuentroStr, formatoDateTime);
        partidoDTO.setHorarioEncuentro(horarioEncuentro);

        partidoDTO.agregarParticipantePorDefault(jugadorDTO.getId());
        partidoDTO.setOrganizador(jugadorDTO.getId());

        System.out.println("Ingrese la estrategia elegida para emparejar a los participantes: " +
                "\nOpciones validas: NIVEL, UBICACION, HISTORIAL: ");
        String estrategiaPartido = input.nextLine().trim().toUpperCase();
        partidoDTO.setEstrategiaPartido(EstrategiaPartido.valueOf(estrategiaPartido));

        System.out.println("Ingrese el nivel de juego minimo como requisito para el partido " +
                "\nOpciones validas: PRINCIPIANTE, INTERMEDIO, AVANZADO: ");
        String nivelJuego = input.nextLine().trim().toUpperCase();
        partidoDTO.setNivelJuego(NivelJuego.valueOf(nivelJuego));

        partidoController.createPartido(partidoDTO);
        System.out.println("--------¡Partido Creado! Esperando jugadores para que se unan :) --------");
    }

    private void buscarPartidoParaUnirme(JugadorDTO jugadorDTO) {
        System.out.println("Elije el partido al que queres unirte indicando el numero de la opcion: ");
        List<PartidoDTO> partidosDTO = new ArrayList<>();
        partidosDTO = partidoController.getPartidosAptosParaJugador(jugadorDTO);
        if (partidosDTO == null)
        for(int i = 1; i<= partidosDTO.size(); i++) {
            // imprime un mensaje del tipo: "1: tenis en Palermo el dia 2025-06-15 15:30"
            System.out.println(i+": "+ partidosDTO.get(i-1).getDeporte() + " en " + partidosDTO.get(i-1).getZonaGeografica() + " el día " + partidosDTO.get(i-1).getHorarioEncuentro());
        }
        int iPartidoElegido = input.nextInt();
        PartidoDTO partidoElegidoDTO = partidosDTO.get(iPartidoElegido-1);
        partidoController.unirseAlPartido(partidoElegidoDTO, jugadorDTO);
        System.out.println("--------¡Te uniste al partido! :) --------");
    }

    private void confirmarPartidoCreado(JugadorDTO jugadorDTO) {
        /*
            ¿EL JUGADOR PUEDE CREAR MAS DE UN PARTIDO? EN ESE CASO, HABRÍA QUE MODIFICAR ESTO
            PARA QUE LE DEVUELVA UNA LISTA DE PARTIDOS CREADOS QUE AUN NO SE CONFIRMARON
            Y QUE ELIJA CUAL CONFIRMAR.
            POR AHORA SOLO CREA UNO Y CONFIRMA UNO
         */
        PartidoDTO partidoDTO = partidoController.getPartidoQuePuedeConfirmar(jugadorDTO);
        if(partidoDTO == null){
            System.out.println("No tenes creado ningun partido para poder confirmar.");
            return;
        }
        partidoController.confirmarPartido(partidoDTO);
        System.out.println("--------¡Se ha confirmado el partido que creaste para " +partidoDTO.getDeporte() + "! :) --------");
    }

    public void cancelarPartidoCreado(JugadorDTO jugadorDTO) {
        // TODO
    }
}


















