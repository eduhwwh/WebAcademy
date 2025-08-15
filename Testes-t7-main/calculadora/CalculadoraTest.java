public class CalculadoraTest {
    
    public static void main(String[] args){
        testUnitarioSoma();
        testUnitarioIntergracaoParaDecimal();
        System.out.println("Todos os testes passaram!");
    }

    public static void testUnitarioSoma(){
        var calculadora = new CalculadoraBasica();
        var soma = calculadora.soma(1,1);

        // if(soma != 2){
        //     throw new AssertionError("1 + 1 deve ser igual a 2");
        // }

        assert soma == 2 : "1 + 1 deve ser igual a 2";
    }

    public static void testUnitarioIntergracaoParaDecimal(){
        var calculadora = new CalculadoraConversao();
        var resultado = calculadora.binarioParaDecimal("1010");

        assert resultado == 10 : "1010 em decimal deve ser igual a 10, mas o resultado foi "+ resultado;
    }
}
