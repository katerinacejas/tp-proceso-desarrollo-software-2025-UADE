package modelo.adapter;

import java.util.List;

import modelo.entidad.notificacion.Notificacion;

public class AdapterEmailJavaMail implements IAdapterEmail{
    @Override
    public void enviarEmails(List<Notificacion> notificaciones){
        for (Notificacion notificacionEmail : notificaciones){
            System.out.println(notificacionEmail.getMensaje());
        }
    }
}
