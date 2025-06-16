package modelo.dao;

import baseDeDatos.BaseDeDatos;
import modelo.entidad.ubicacion.ZonaGeografica;

public class ZonaGeograficaDAO {

    private BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public ZonaGeografica getZonaGeograficaByName(String zonaGeografica){
        return baseDeDatos.getZonaGeograficaByName(zonaGeografica);
    }


}