package modelo.partido;

public interface IEstadoPartido {
    void cancelar(Partido partido);
    void necesitarJugadores(Partido partido);
    void armar(Partido partido);
    void confirmar(Partido partido);
    void jugar(Partido partido);
    void finalizar(Partido partido);
}
