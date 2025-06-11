package modelo.dto;

public class DeporteDTO {
    private String id;
    private String nombre;
    private int cantJugadores;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantJugadores() {
        return cantJugadores;
    }
    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }





}
