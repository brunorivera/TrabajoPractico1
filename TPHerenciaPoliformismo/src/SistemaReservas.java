import java.util.ArrayList;

abstract class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "Vuelo " + numeroVuelo + " de " + origen + " a " + destino + " el " + fecha;
    }
}

interface Promocionable {
    void aplicarPromocion(double porcentaje);
}

class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioPorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioPorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioPorAsiento = precioPorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioPorAsiento;
    }

    @Override
    public void aplicarPromocion(double porcentaje) {
        precioPorAsiento -= precioPorAsiento * porcentaje / 100;
    }

    @Override
    public String toString() {
        return super.toString() + " | Regular: " + calcularPrecio();
    }
}

class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion(double porcentaje) {
        precioTotal -= precioTotal * porcentaje / 100;
    }

    @Override
    public String toString() {
        return super.toString() + " | Charter: " + calcularPrecio();
    }
}

class Reservas {
    private ArrayList<Vuelo> vuelos;

    public Reservas() {
        vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularPrecioTotal() {
        return vuelos.stream().mapToDouble(Vuelo::calcularPrecio).sum();
    }

    public void aplicarPromociones(double porcentaje) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion(porcentaje);
            }
        }
    }

    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo);
        }
    }
}

public class SistemaReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        Vuelo vuelo1 = new VueloRegular("1234", "Madrid", "Londres", "01/12/2024", 100, 150);
        Vuelo vuelo2 = new VueloCharter("5678", "París", "Nueva York", "15/12/2024", 5000);

        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        System.out.println("Antes de promociones:");
        reservas.mostrarVuelos();

        reservas.aplicarPromociones(10);

        System.out.println("\nDespués de promociones:");
        reservas.mostrarVuelos();

        System.out.println("\nPrecio total de las reservas: " + reservas.calcularPrecioTotal());
    }
}

