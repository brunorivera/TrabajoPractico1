import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoItem = cantidad > 5 ? precioUnitario * 0.1 : 0;
        this.subtotal = (precioUnitario * cantidad) - (descuentoItem * cantidad);
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return codigoArticulo + "\t" + nombreArticulo + "\t" + cantidad + "\t" +
                precioUnitario + "\t" + descuentoItem + "\t" + subtotal;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura;
    private double totalCalculadoFactura;

    public Factura() {
        detallesFactura = new ArrayList<>();
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = detallesFactura.stream()
                .mapToDouble(DetalleFactura::getSubtotal)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder facturaInfo = new StringBuilder();
        facturaInfo.append("Fecha: ").append(fechaFactura).append("\n")
                .append("Número: ").append(numeroFactura).append("\n")
                .append("Cliente: ").append(cliente).append("\n")
                .append("Código\tNombre\tCantidad\tPrecio Unitario\tDescuento\tSubtotal\n");
        for (DetalleFactura detalle : detallesFactura) {
            facturaInfo.append(detalle).append("\n");
        }
        facturaInfo.append("Total: ").append(totalCalculadoFactura);
        return facturaInfo.toString();
    }
}

public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Ingresar datos de la factura
        System.out.println("Ingrese la fecha de la factura (dd/mm/aaaa):");
        factura.setFechaFactura(scanner.nextLine());

        long numeroFactura;
        do {
            System.out.println("Ingrese el número de factura (mayor a 0):");
            numeroFactura = scanner.nextLong();
        } while (numeroFactura <= 0);
        factura.setNumeroFactura(numeroFactura);

        scanner.nextLine(); // Consumir el salto de línea
        String cliente;
        do {
            System.out.println("Ingrese el nombre del cliente:");
            cliente = scanner.nextLine();
        } while (cliente.trim().isEmpty());
        factura.setCliente(cliente);

        // Cargar artículos
        boolean continuar = true;
        while (continuar) {
            String codigo;
            boolean codigoValido;
            do {
                System.out.println("Ingrese el código del artículo:");
                codigo = scanner.nextLine();
                codigoValido = false;
                for (String[] articulo : articulos) {
                    if (articulo[0].equals(codigo)) {
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    System.out.println("El código ingresado no existe, intente nuevamente.");
                }
            } while (!codigoValido);

            System.out.println("Ingrese la cantidad a facturar:");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigo)) {
                    String nombre = articulo[1];
                    double precio = Double.parseDouble(articulo[2]);
                    DetalleFactura detalle = new DetalleFactura(codigo, nombre, cantidad, precio);
                    factura.agregarDetalle(detalle);
                    break;
                }
            }

            System.out.println("¿Desea continuar cargando artículos? (true/false):");
            continuar = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea
        }

        // Calcular y mostrar la factura
        factura.calcularMontoTotal();
        System.out.println("\nFactura generada:");
        System.out.println(factura);

        scanner.close();
    }
}

