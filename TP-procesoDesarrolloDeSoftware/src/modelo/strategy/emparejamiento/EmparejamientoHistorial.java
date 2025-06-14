package modelo.strategy.emparejamiento;

import modelo.entidad.jugador.Jugador;
import modelo.entidad.partido.Partido;

// public class EmparejamientoHistorial implements IEmparejador{
//     @Override
//     public boolean puedeEmparejar(Jugador jugador, Partido partido) {
//         return true;
//     }
// }

public class EmparejamientoHistorial implements IEmparejador {
    @Override
    public boolean puedeEmparejar(Jugador jugador, Partido partido) {
        // Paso 1: verificar si el deporte del partido está entre los favoritos del
        // jugador
        boolean esDeporteFavorito = jugador.getDeportesFavoritos()
                .stream()
                .anyMatch(deporte -> deporte.equals(partido.getDeporte()));

        if (!esDeporteFavorito) {
            return false;
        }

        return esDeporteFavorito;
    }
}