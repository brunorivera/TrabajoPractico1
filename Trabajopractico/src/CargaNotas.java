import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        return notas.stream().mapToDouble(Nota::getNotaExamen).average().orElse(0);
    }

    @Override
    public String toString() {
        return "Alumno: " + nombreCompleto + " (Legajo: " + legajo + ")\nNotas: " + notas + "\nPromedio: " + calcularPromedio();
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.println("Ingrese la cantidad de alumnos:");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre completo del alumno:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el legajo del alumno:");
            long legajo = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombre, legajo);
            System.out.println("Ingrese la cantidad de notas para este alumno:");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la cátedra:");
                String catedra = scanner.nextLine();
                System.out.println("Ingrese la nota:");
                double nota = scanner.nextDouble();
                scanner.nextLine();

                alumno.agregarNota(new Nota(catedra, nota));
            }

            alumnos.add(alumno);
        }

        System.out.println("\nInformación de los alumnos:");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
            System.out.println("-------------------------");
        }

        scanner.close();
    }
}
