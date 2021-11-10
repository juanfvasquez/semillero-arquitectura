import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Colectores {
    public static void main(String[] args) {
//        colectoresBasicos();
//        colectoresColeccion();
        colectoresAgrupacion();
    }

    public static void colectoresBasicos() {
        List<Integer> numeros = Arrays.asList(1, 43, 668, 89,23, 35);

        System.out.println(numeros.stream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println(numeros.stream().collect(Collectors.averagingInt(Integer::intValue)));

        IntSummaryStatistics summary = numeros.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("---------- Resultados ------------");
        System.out.println(summary.getSum());
        System.out.println(summary.getAverage());
        System.out.println(summary.getMax());
        System.out.println(summary.getMin());
        System.out.println(summary.getCount());

        summary = numeros.stream()
                .map(x -> new Persona("", x))
                .collect(Collectors.summarizingInt(p -> p.edad));
        System.out.println("---------- Resultados ------------");
        System.out.println(summary.getSum());
        System.out.println(summary.getAverage());
        System.out.println(summary.getMax());
        System.out.println(summary.getMin());
        System.out.println(summary.getCount());

        System.out.println(numeros.stream().map(x -> "asd").collect(Collectors.joining()));
    }

    public static void colectoresColeccion() {
        List<Integer> numeros = Arrays.asList(1, 43, 668, 89,23, 35, 1, 43, 668, 89,23, 35, 1, 43, 668, 89,23, 35);

        System.out.println(numeros.stream().collect(Collectors.toList()));
        System.out.println(numeros.stream().collect(Collectors.toSet()));

        // { 'clave': valor }
        System.out.println(
            numeros.stream().distinct()
            .collect(Collectors.toMap(x -> "Clave: " + x, x -> x * x))
        );

        List<Empleado> empleados = Arrays.asList(
                new Empleado("Juan", "Sistemas", 2000),
                new Empleado("Pedro", "Contabilidad", 3000),
                new Empleado("Ana", "Compras", 4000),
                new Empleado("Lucía", "Ventas", 6000),
                new Empleado("Ricardo", "Ventas", 4000)
        );

        System.out.println(
            empleados.stream().collect(Collectors.toMap(
                    e -> e.nombre, e -> e.salario
            ))
        );
        System.out.println(
            empleados.stream()
            .filter(e -> e.salario >= 3000)
            .collect(Collectors.toMap(
                    e -> e.nombre, e -> e.area
            ))
        );
    }

    public static void colectoresAgrupacion() {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Juan", "Sistemas", 2000),
                new Empleado("Pedro", "Contabilidad", 3000),
                new Empleado("Ana", "Compras", 4000),
                new Empleado("Lucía", "Ventas", 6000),
                new Empleado("Ricardo", "Ventas", 4000)
        );

        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.salario > 3000)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.salario > 3000)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.area)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.salario)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> {
            // Procesamiento
            double total = 0;
            double impuesto = e.salario * 0.23;
            if (e.area.equalsIgnoreCase("sistemas")) {
                total += 2000;
            }
            return e.salario + impuesto + total;
        })));

        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> {
            // Procesamiento
            return e.salario + "," + e.area;
        })));

        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.salario > 3000,
                Collectors.groupingBy(e -> e.salario)))
        );
        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.salario > 3000,
                Collectors.groupingBy(e -> e.area)))
        );

        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.salario > 3000,
                Collectors.groupingBy(e -> e.area, Collectors.counting())))
        );
    }
}

class Empleado {
    String nombre;
    String area;
    double salario;

    public Empleado(String nombre, String area, double salario) {
        this.nombre = nombre;
        this.area = area;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return nombre + "  ( " + area + " - " + salario + " )";
    }
}