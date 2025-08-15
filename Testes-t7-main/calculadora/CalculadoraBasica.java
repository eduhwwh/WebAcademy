public class CalculadoraBasica {

    public double soma(double a, double b) {
        return a + b;
    }

    public double subtracao(double a, double b) {
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        return a * b;
    }

    public double divisao(double a, double b) {
        return a / b;
    }

    public double potencia(double base, double expoente) {
        double resultado = 1;
        for (int i = 0; i < expoente; i++) {
            resultado *= base;
        }

        return resultado; 
        // return resultado +1; Forçando o erro 
    }

    public int fatorial(int numero){
        return 0;
    }

}
