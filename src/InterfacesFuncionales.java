import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class InterfacesFuncionales {

    public static void main(String[] args) {
//        usoPredicate();
//        usoConsumer();
//        usoSupplier();
//        usoFunction();
//        usoBifunction();
//        usoUnaryOperator();
//        usoBinaryOperator();
        usoBiPredicate();
    }

    public static void usoPredicate() {
        Predicate<String> noVacia = s -> s.length() > 0;
        System.out.println(noVacia.test(""));

        Predicate<String> caracteres = s -> s.length() > 10;
        System.out.println(noVacia.and(caracteres).test("algo"));
        System.out.println(noVacia.test("algo") && caracteres.test("algo"));
    }

    public static void usoConsumer() {
        Consumer<String> imprimir = s -> System.out.println("Esta es la entrada: " + s);
        imprimir.accept("Entrada");

        Consumer<String> mayusculas = s -> System.out.println(s.toUpperCase());
        imprimir.andThen(mayusculas).accept("otra entrada");

        List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "Cadena 3", "...");
        pasoConsumer(cadenas, imprimir);
        pasoConsumer(cadenas, mayusculas);
        pasoConsumer(cadenas, s -> System.out.println(s.toLowerCase()));
    }

    public static <T> void pasoConsumer(List<T> lista, Consumer<T> consumer) {
        for (T t: lista) {
            consumer.accept(t);
        }
    }

    public static void usoSupplier() {
        Supplier<String> saludo = () -> "Hola";
        System.out.println(saludo.get());
        Supplier<String> saludoIngles = () -> "Hi!";

        Supplier<LocalDate> getFecha = () -> LocalDate.now();
        System.out.println(getFecha.get());

        String idioma = "ingles";
        pasoSupplier(idioma.equals("español") ? saludo : saludoIngles);
    }

    public static void pasoSupplier(Supplier<String> saludo) {
        System.out.println(saludo.get());
    }

    public static void usoFunction() {
        Function<String, Integer> longitud = s -> {
            // Cualquier procesamiento
            return s.length();
        };
        System.out.println(longitud.apply("Cadena"));

        Function<String, Boolean> predicate = s -> s.length() > 0;
        System.out.println(predicate.apply(""));

        Function<Integer, Integer> doble = x -> x * 2;
        Function<Integer, Boolean> mayorA20 = x -> x > 20;

        System.out.println(longitud.andThen(mayorA20).apply("Otra cadena"));
    }

    public static void usoBifunction() {
        BiFunction<Integer, String, String> bifunction = (x, s) -> x + " : " + s;
        System.out.println(bifunction.apply(10, "Cadena"));

        BiFunction<Integer, Integer, Integer> mayor = (x, y) -> {
            if (x > y) {
                return x;
            }
            return y;
        };
        System.out.println(mayor.apply(10, 15));

        System.out.println(mayor.andThen(x -> {
            System.out.println("Dentro de la function anidada");
            return "Después de andThen: " + x;
        }).apply(10, 1000));
    }

    public static void usoUnaryOperator() {
        UnaryOperator<Integer> doble = x -> x * 2;
        System.out.println(doble.apply(20));
    }

    public static void usoBinaryOperator() {
        BinaryOperator<String> binOp = (s, t) -> s + t;
        System.out.println(binOp.apply("Hola ", "Mundo!"));

        BinaryOperator<String> reemplazo = (texto, palabra) -> texto.replace(palabra, "*");
        System.out.println(reemplazo.apply("Tres tristes tigres", "r"));

        String cad1 = "ABC";
        String cad2 = "DEF";

        BinaryOperator<String> mayor = BinaryOperator.maxBy(String::compareToIgnoreCase);
        System.out.println(mayor.apply(cad1, cad2));
    }

    public static void usoBiPredicate() {
        BiPredicate<String, Integer> bipredicate = (s, x) -> s.length() > x;
        System.out.println(bipredicate.test("Cadena", 5));

        BiPredicate<Integer, Integer> multiplicacion = (x, y) -> x * y == 100;
        System.out.println(multiplicacion.test(10, 10));
    }
}
