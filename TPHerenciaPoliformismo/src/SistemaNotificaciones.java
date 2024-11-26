import java.util.ArrayList;

abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();

    @Override
    public String toString() {
        return "Usuario: " + usuario + ", Mensaje: " + mensaje;
    }
}

interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}

class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo a " + direccionCorreo + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", Dirección de correo: " + direccionCorreo;
    }
}

class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto a " + numeroTelefono + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", Número de teléfono: " + numeroTelefono;
    }
}

class Notificaciones {
    private ArrayList<CanalNotificacion> canales;

    public Notificaciones() {
        canales = new ArrayList<>();
    }

    public void agregarCanal(CanalNotificacion canal) {
        canales.add(canal);
    }

    public void enviarNotificaciones() {
        for (CanalNotificacion canal : canales) {
            canal.enviarNotificacion();
        }
    }

    public void personalizarMensajes(String nuevoMensaje) {
        for (CanalNotificacion canal : canales) {
            if (canal instanceof Personalizable) {
                ((Personalizable) canal).personalizarMensaje(nuevoMensaje);
            }
        }
    }

    public void mostrarCanales() {
        for (CanalNotificacion canal : canales) {
            System.out.println(canal);
        }
    }
}

public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones();

        CanalNotificacion correo = new CorreoElectronico("Juan", "¡Bienvenido a nuestro servicio!", "juan@correo.com");
        CanalNotificacion mensaje = new MensajeTexto("Ana", "Tu pedido está en camino.", "123456789");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensaje);

        System.out.println("Canales de notificación:");
        notificaciones.mostrarCanales();

        System.out.println("\nEnviando notificaciones...");
        notificaciones.enviarNotificaciones();

        System.out.println("\nPersonalizando mensajes...");
        notificaciones.personalizarMensajes("Este es un mensaje personalizado.");

        System.out.println("\nEnviando notificaciones personalizadas...");
        notificaciones.enviarNotificaciones();
    }
}

