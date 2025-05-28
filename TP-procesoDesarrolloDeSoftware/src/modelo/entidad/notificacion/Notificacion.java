package modelo.entidad.notificacion;

public class Notificacion {
    private String mensaje;
    private String emailRemitente;
    private String emailDestinatario;
    private String celularRemitente;
    private String celularDestinatario;

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getCelularRemitente() {
        return celularRemitente;
    }

    public void setCelularRemitente(String celularRemitente) {
        this.celularRemitente = celularRemitente;
    }

    public String getCelularDestinatario() {
        return celularDestinatario;
    }

    public void setCelularDestinatario(String celularDestinatario) {
        this.celularDestinatario = celularDestinatario;
    }
}
