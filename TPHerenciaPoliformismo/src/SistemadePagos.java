import java.util.ArrayList;

abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago(double monto);

    @Override
    public String toString() {
        return "Titular: " + titular + ", Número: " + numero;
    }
}

interface Cancelable {
    void cancelarPago();
}

class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago de " + monto + " realizado con Tarjeta de Crédito.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado para Tarjeta de Crédito.");
    }
}

class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago de " + monto + " realizado con PayPal.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado para PayPal.");
    }
}

class Pagos {
    private ArrayList<MetodoPago> metodos;

    public Pagos() {
        metodos = new ArrayList<>();
    }

    public void agregarMetodo(MetodoPago metodo) {
        metodos.add(metodo);
    }

    public void mostrarMetodos() {
        for (MetodoPago metodo : metodos) {
            System.out.println(metodo);
        }
    }

    public void realizarPagos(double monto) {
        for (MetodoPago metodo : metodos) {
            metodo.realizarPago(monto);
        }
    }
}

public class SistemadePagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        MetodoPago tarjeta = new TarjetaCredito("Juan Pérez", "123456789", "12/2025", "123");
        MetodoPago paypal = new PayPal("Ana López", "abcdef123", "ana@correo.com");

        pagos.agregarMetodo(tarjeta);
        pagos.agregarMetodo(paypal);

        System.out.println("Métodos de pago:");
        pagos.mostrarMetodos();

        System.out.println("\nRealizando pagos...");
        pagos.realizarPagos(100.0);
    }
}

