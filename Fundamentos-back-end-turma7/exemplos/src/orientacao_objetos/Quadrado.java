package orientacao_objetos;

public class Quadrado extends Quadrilatero {
    private double lado;

    // Construtor padrão
    public Quadrado() {

    }

    // Construtor personalizado
    public Quadrado(double lado) {
        this.lado = lado;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return Math.pow(this.lado, 2);
    }
}
