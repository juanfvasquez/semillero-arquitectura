import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Streams {
    static List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "Cadena 123", "Cadena 12133", " ");
    static List<Persona> personas = Arrays.asList();

    public static void main(String[] args) {
//        usoForEach();
//        usoFilter();
//        usoMap();
//        usoReduce();
        usoMatch();
    }

    public static void usoForEach() {
        cadenas.stream().forEach(s -> {
            // Procesamiento
            System.out.println(s);
        });
    }

    public static void usoFilter() {
        cadenas.stream()
                .filter(s -> s.contains("1"))
                .forEach(System.out::println);
        System.out.println(cadenas);
        List<String> nueva = cadenas.stream().filter(s -> s.contains("2")).collect(Collectors.toList());
        System.out.println(nueva);
    }

    public static void usoMap() {
        cadenas.stream().map(s -> s.length()).forEach(System.out::println);
        List<Persona> personas = cadenas.stream()
                .map(s -> new Persona(s, s.length()))
                .collect(Collectors.toList());
        personas.stream().forEach(System.out::println);

        cadenas.stream()
                .filter(p -> p.length() >= 10)
                .map(s -> new Persona(s, s.length()))
                .forEach(System.out::println);

        cadenas.stream()
                .map(s -> new Persona(s, s.length()))
                .filter(p -> p.edad >= 10)
                .forEach(System.out::println);
    }

    public static void usoReduce() {
//        int acum = 0;
//        for (String s: cadenas) {
//            acum += s.length();
//        }
        Integer total = cadenas.stream().map(s -> s.length()).reduce((acum, len) -> acum + len).get();
        System.out.println(total);
        cadenas.stream().map(s -> s.length()).reduce((acum, len) -> acum + len).ifPresent(System.out::println);

        cadenas.stream().reduce((s1, s2) -> {
            //
            return s1 + ", " + s2;
        }).ifPresent(System.out::println);

        Optional<String> op = Optional.empty();
        op.ifPresent(System.out::println);
        System.out.println(op.orElse("Valor por defecto"));
        op.orElseGet(() -> "Otro valor por defecto");
    }

    public static void usoMatch() {
        boolean hayCadenaVacia = cadenas.stream().anyMatch(s -> s.length() == 0);
        System.out.println(hayCadenaVacia);

        boolean permitido = cadenas.stream().noneMatch(s -> s.length() == 0);
        System.out.println(permitido ? "Permitido" : "No permitido");

        permitido = cadenas.stream().allMatch(s -> s.length() > 0);
        System.out.println(permitido ? "Permitido" : "No permitido");

//        cadenas.stream().map(s -> new Persona(s, s.length()))
        boolean hayMenores = cadenas.stream()
                .map(Persona::new)
                .filter(p -> p.nombre.contains("123"))
                .noneMatch(p -> p.edad < 10);
        System.out.println(hayMenores ? "Hay menores" : "No hay menores");
    }
}

class Persona {
    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona(String s) {
        this.nombre = s;
        this.edad = s.length();
    }

    @Override
    public String toString() {
        return nombre + "( " + edad + " )";
    }
}