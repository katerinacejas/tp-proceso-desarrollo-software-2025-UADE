package modelo.emparejamiento;

public class Emparejador {
    private IEmparejador estrategiaEmparejamiento;

    public void emparejar() {

    }

    public void cambiarEstrategiaEmparejamiento(IEmparejador estrategia) {
        this.estrategiaEmparejamiento = estrategia;
    }
}
