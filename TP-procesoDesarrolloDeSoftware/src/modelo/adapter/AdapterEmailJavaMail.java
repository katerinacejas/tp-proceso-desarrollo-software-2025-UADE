package modelo.adapter;

import modelo.entidad.notificacion.Notificacion;

public class AdapterEmailJavaMail implements IAdapterEmail {
    @Override
    public void enviarEmail(Notificacion notificacion) {
        System.out
                .println("Enviando EMAIL a " + notificacion.getEmailDestinatario() + ": " + notificacion.getMensaje());
    }

}
