package orientacao_objetos;

public class Retangulo extends Quadrilatero {
    private double base;
    private double altura;

    // Construtor personalizado
    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double calcularArea() {
        return this.base * this.altura;
    }

}
