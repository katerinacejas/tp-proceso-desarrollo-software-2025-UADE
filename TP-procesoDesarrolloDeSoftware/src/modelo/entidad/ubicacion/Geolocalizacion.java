package modelo.entidad.ubicacion;

public class Geolocalizacion {
    private double latitud;
    private double longitud;

    public Geolocalizacion(double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }
 
    public double getLongitud() {
        return longitud;
    }
 
    // Distancia euclideana aproximada ==> intentar entender este tipo de lógica
    public double distanciaAproximada(Geolocalizacion geolocalizacion) {
        double deltaLatitud = geolocalizacion.latitud - this.latitud;
        double deltaLongitud = geolocalizacion.longitud - this.longitud;
        double latitudProm = Math.toRadians((this.latitud + geolocalizacion.latitud) / 2.0);
        // 1 grado ≈ 111 km
        return Math.sqrt(Math.pow(deltaLatitud, 2) + Math.pow(deltaLongitud * Math.cos(latitudProm), 2)) * 111;
    }
}
