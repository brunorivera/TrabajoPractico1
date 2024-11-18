import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadMedida;

    public Ingrediente(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return nombre + " " + cantidad + " " + unidadMedida;
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!esBebida) {
            ingredientes.add(ingrediente);
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("Plato: " + nombreCompleto + "\nPrecio: $" + precio);
        if (!esBebida) {
            info.append("\nIngredientes:");
            for (Ingrediente ingrediente : ingredientes) {
                info.append("\n").append(ingrediente);
            }
        }
        return info.toString();
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.println("Ingrese la cantidad de platos:");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese el nombre del plato:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el precio:");
            double precio = scanner.nextDouble();
            System.out.println("¿Es bebida? (true/false):");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();

            Plato plato = new Plato(nombre, precio, esBebida);
            if (!esBebida) {
                System.out.println("Ingrese la cantidad de ingredientes:");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine();

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String ingredienteNombre = scanner.nextLine();
                    System.out.println("Ingrese la cantidad:");
                    double cantidad = scanner.nextDouble();
                    System.out.println("Ingrese la unidad de medida:");
                    scanner.nextLine();
                    String unidad = scanner.nextLine();

                    plato.agregarIngrediente(new Ingrediente(ingredienteNombre, cantidad, unidad));
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("\nMenú:");
        for (Plato plato : platosMenu) {
            System.out.println(plato);
            System.out.println("-------------------------");
        }

        scanner.close();
    }
}


