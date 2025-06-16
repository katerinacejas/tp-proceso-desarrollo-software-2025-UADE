package modelo.entidad.ubicacion;

import modelo.dao.ZonaGeograficaDAO;

public class ZonaGeografica {
    private String id;
    private String nombre;
    private Geolocalizacion centro;
    private double radioKm;

    public ZonaGeografica(){}

    public ZonaGeografica(String nombre, Geolocalizacion geolocalizacion, double radioKm){
        this.id = nombre;
        this.nombre = nombre;
        this.centro = geolocalizacion;
        this.radioKm = radioKm;
    }

    public boolean contieneGeolocalizacion(Geolocalizacion punto) {
        return centro.distanciaAproximada(punto) <= radioKm;
    }

    public ZonaGeografica getZonaGeograficaByName(String zonaGeografica) {
        ZonaGeograficaDAO zonaGeograficaDAO = new ZonaGeograficaDAO();
        return zonaGeograficaDAO.getZonaGeograficaByName(zonaGeografica);

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCentro(Geolocalizacion centro) {
        this.centro = centro;
    }

    public void setRadioKm(double radioKm) {
        this.radioKm = radioKm;
    }

    public String getNombre() {
        return nombre;
    }

    public Geolocalizacion getCentro() {
        return centro;
    }

    public double getRadioKm() {
        return radioKm;
    }



}