import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Streams {
    static List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "Cadena 123", "Cadena 12133");
    static List<Persona> personas = Arrays.asList();

    public static void main(String[] args) {
//        usoForEach();
//        usoFilter();
//        usoMap();
        usoReduce();
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
        Integer total = cadenas.stream().map(s -> s.length()).reduce((acum, len) -> acum + len);
    }
}

class Persona {
    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + "( " + edad + " )";
    }
}