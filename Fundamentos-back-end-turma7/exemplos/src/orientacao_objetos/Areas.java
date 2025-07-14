package orientacao_objetos;

import java.util.ArrayList;
import java.util.List;

public class Areas {
    public static void main(String[] args) {
        Quadrilatero q1 = new Quadrado(5.5);
        Quadrilatero r1 = new Retangulo(3, 4);
        // System.out.println(q1.calcularArea());
        // System.out.println(r1.calcularArea());

        List<Quadrilatero> figuras = new ArrayList<>();
        figuras.add(q1);
        figuras.add(r1);
        double areas = 0;
        for (Quadrilatero figura : figuras)
            areas += figura.calcularArea();
        System.out.println(areas);

    }

}
