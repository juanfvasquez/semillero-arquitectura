public class Lambda {
    public static void main(String[] args) {
        Operacion op = (int x, int y) -> {
            System.out.println("Lambda");
            return x * y;
        };
        op = (x, y) -> x * y;
        System.out.println(op.operar(10, 20));

        Impresion imp = x -> System.out.println(x);
        imp.imprimir(op.operar(3, 5));

        ejecutar(op);
    }

    public static void ejecutar(Operacion op) {
        Impresion imp = Lambda::impresion;
        imp.imprimir(op.operar(13, 25));
    }

    public static void impresion(Object obj) {
        System.out.println(obj);
    }
}

interface Operacion {
    public double operar(int x, int y);
}

interface Impresion {
    public void imprimir(double x);
}
