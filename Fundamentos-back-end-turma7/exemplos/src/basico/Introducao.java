package basico;

import java.util.ArrayList;
import java.util.List;

public class Introducao {
    public static void main(String[] args) {
        // Tipos de dados primitivos
        int x = 99;
        long w = 20000;
        double y = 9.5;
        float z = 5.5f;
        boolean status = true;
        double soma = x + y;
        char letra = 'a';
        System.out.println(soma);
        // O tipo de dado String é fornecido por uma classe
        String nome = "Limeira";
        String nota = "8.5";
        // Casting entre os tipos de dados
        double resultado = Double.parseDouble(nota) + y;
        System.out.println(resultado);
        int soma_inteiro = (int) soma;
        System.out.println(soma_inteiro);
        System.out.println((int) letra);
        System.out.println((char) x);
        // Operador ternário
        String resultado_final = (status) ? "Aprovado" : "Reprovado";
        System.out.println(resultado_final);
        // Vetores estáticos
        int[] vetor = new int[10]; // Declaração
        // Preenche o vetor estático
        for (int i = 1; i < vetor.length; i++)
            vetor[i] = i + x;
        // Imprime o conteúdo do vetor estático
        for (int item : vetor)
            System.out.println(item);

        // Vetores dinâmicos
        List<Integer> vetor_dinamico = new ArrayList<>(); // Declaração
        // Preenche o vetor dinâmico
        for (int i = 100; i < 103; i++)
            vetor_dinamico.add(i + x);
        // Acessa um item do vetor dinâmico pelo seu índice
        System.out.println(vetor_dinamico.get(0));
        // Remoção
        vetor_dinamico.remove(0); // Remove pelo indice
        vetor_dinamico.remove(Integer.valueOf(200)); // Remove pelo conteúdo
        // Imprime o conteúdo do vetor dinâmico
        for (int item : vetor_dinamico)
            System.out.println(item);

    }
}
