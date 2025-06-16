/*package modelo.enumerador;

import modelo.entidad.partido.Partido;
import modelo.state.*;

public enum EstadoPartido {
    PARTIDO_CANCELADO {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoCancelado();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoCancelado;
        }
    },
    PARTIDO_NECESITAMOS_JUGADORES {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoNecesitamosJugadores();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoNecesitamosJugadores;
        }
    },
    PARTIDO_ARMADO {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoArmado();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoArmado;
        }
    },
    PARTIDO_CONFIRMADO {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoConfirmado();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoConfirmado;
        }
    },
    PARTIDO_EN_JUEGO {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoEnJuego();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoEnJuego;
        }
    },
    PARTIDO_FINALIZADO {
        @Override
        public AbstractEstadoPartido crearToEntity(Partido partido) {
            return new PartidoFinalizado();
        }

        @Override
        public boolean coincideConEnum(AbstractEstadoPartido estado) {
            return estado instanceof PartidoFinalizado;
        }
    };

    public abstract AbstractEstadoPartido crearToEntity(Partido partido);
    public abstract boolean coincideConEnum(AbstractEstadoPartido estado);

}
*/
