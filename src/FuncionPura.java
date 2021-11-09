import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FuncionPura {

    static double impuesto = 20.6;

    public static void main(String[] args) {
        imprimirImpuesto(100000);
        impuesto = 23.0;
        imprimirImpuesto(100000);
        imprimirImpuestoPura(100000, 23.5);

        List<Integer> facturas = Arrays.asList(
                50000, 40000, 30000, 2000,
                50000, 40000, 30000, 2000,
                50000, 40000, 30000, 2000,
                50000, 40000, 30000, 2000
        );
        HashMap<Integer, Double> impuestos = new HashMap<>();
        for (Integer i : facturas) {
            Double calculo = impuestos.get(i);
            if (calculo == null) {
                calculo = calculoImpuesto(i);
                impuestos.put(i, calculo);
            }
            System.out.println(calculo);
        }
    }

    public static void imprimirImpuesto(double valor) {
        System.out.println(valor * impuesto);
    }

    public static void imprimirImpuestoPura(double valor, double impuesto) {
        System.out.println(valor * impuesto);
    }

    // Memoización
    public static double calculoImpuesto(int valor) {
        double impuesto = valor * 20.0;
        return impuesto;
    }
}
