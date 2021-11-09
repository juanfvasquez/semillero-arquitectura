import java.util.Arrays;
import java.util.List;

public class Introduccion {
    public static void main(String[] args) {
        ejemploFuncional();
    }

    public static void ejemploTradicional() {
        List<Integer> numeros = Arrays.asList(1, 3, 5, 7, 10, 12, 56);
        int contador = 0;
        for (int i = 0; i < numeros.size(); i++) {
            if (numeros.get(i) < 10) {
                contador++;
            }
        }
        System.out.println(contador);
    }

    public static void ejemploFuncional() {
        List<Integer> numeros = Arrays.asList(1, 3, 5, 7, 10, 12, 56);
        long contador = numeros.stream()
                .filter(x -> x < 10).count();
        System.out.println(contador);
    }
}
