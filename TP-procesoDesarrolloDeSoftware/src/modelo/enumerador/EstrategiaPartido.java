package modelo.enumerador;

import modelo.strategy.emparejamiento.EmparejamientoHistorial;
import modelo.strategy.emparejamiento.EmparejamientoNivel;
import modelo.strategy.emparejamiento.EmparejamientoUbicacion;
import modelo.strategy.emparejamiento.IEmparejador;

public enum EstrategiaPartido {
    /*
        aca implemente un factory method para evitar las funciones de convertir
        el enum por entity (y viceversa) xq sino era un choclo de codigo en el controller
        el enum pasaría a ser su propia fabrica
     */
    NIVEL {
        @Override
        public IEmparejador crearToEntity() {
            return new EmparejamientoNivel();
        }

        @Override
        public boolean coincideConEnum(IEmparejador emparejamiento) {
            return emparejamiento instanceof EmparejamientoNivel;
        }
    },
    UBICACION{
        @Override
        public IEmparejador crearToEntity() {
            return new EmparejamientoUbicacion();
        }

        @Override
        public boolean coincideConEnum(IEmparejador emparejamiento) {
            return emparejamiento instanceof  EmparejamientoUbicacion;
        }
    },
    HISTORIAL{
        @Override
        public IEmparejador crearToEntity() {
            return new EmparejamientoHistorial();
        }

        @Override
        public boolean coincideConEnum(IEmparejador emparejamiento) {
            return emparejamiento instanceof EmparejamientoHistorial;
        }
    };

    public abstract IEmparejador crearToEntity();
    public abstract boolean coincideConEnum(IEmparejador emparejamiento);

}