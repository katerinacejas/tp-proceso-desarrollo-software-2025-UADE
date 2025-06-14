package modelo.entidad.ubicacion;

public class ZonaGeografica {
    private String nombre;
    private Geolocalizacion centro;
    private double radioKm;
 
    public ZonaGeografica(String nombre, Geolocalizacion centro, double radioKm) {
        this.nombre = nombre;
        this.centro = centro;
        this.radioKm = radioKm;
    }
 
    public boolean contieneGeolocalizacion(Geolocalizacion punto) {
        return centro.distanciaAproximada(punto) <= radioKm;
    }

}
