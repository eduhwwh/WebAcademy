import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraBasica calculadora = new CalculadoraBasica();
        CalculadoraConversao conversor = new CalculadoraConversao();
        int opcao;

        do {
            System.out.println("\n=== Menu Calculadora ===");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Potência");
            System.out.println("6. Decimal para Binário");
            System.out.println("7. Binário para Decimal");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o primeiro número: ");
                    double a = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    double b = scanner.nextDouble();
                    System.out.println("Resultado: " + calculadora.soma(a, b));
                    break;
                case 2:
                    System.out.print("Digite o primeiro número: ");
                    a = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    b = scanner.nextDouble();
                    System.out.println("Resultado: " + calculadora.subtracao(a, b));
                    break;
                case 3:
                    System.out.print("Digite o primeiro número: ");
                    a = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    b = scanner.nextDouble();
                    System.out.println("Resultado: " + calculadora.multiplicacao(a, b));
                    break;
                case 4:
                    System.out.print("Digite o primeiro número: ");
                    a = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    b = scanner.nextDouble();
                    System.out.println("Resultado: " + calculadora.divisao(a, b));
                    break;
                case 5:
                    System.out.print("Digite a base: ");
                    int base = scanner.nextInt();
                    System.out.print("Digite o expoente: ");
                    int exp = scanner.nextInt();
                    System.out.println("Resultado: " + calculadora.potencia(base, exp));
                    break;
                case 6:
                    System.out.print("Digite um número decimal: ");
                    int numDec = scanner.nextInt();
                    System.out.println("Binário: " + conversor.decimalParaBinario(numDec));
                    break;
                case 7:
                    System.out.print("Digite um número binário: ");
                    String numBin = scanner.next();
                    System.out.println("Decimal: " + conversor.binarioParaDecimal(numBin));
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

}
