public class CalculadoraConversao {

    private CalculadoraBasica calculadora;

    public CalculadoraConversao() {
        this.calculadora = new CalculadoraBasica();
    }

    public String decimalParaBinario(int numero) {
        String binario = "";
        while (numero > 0) {
            binario = (numero % 2) + binario;
            numero /= 2;
        }
        return binario;
    }

    public int binarioParaDecimal(String numero) {
        int decimal = 0;
        for (int i = 0; i < numero.length(); i++) {
            decimal += (numero.charAt(i) - '0') * calculadora.potencia(2, numero.length() - i - 1);
        }
        return decimal;
    }
    
}
