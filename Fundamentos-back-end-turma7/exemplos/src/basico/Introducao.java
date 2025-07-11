package basico;

import java.util.ArrayList;
import java.util.List;

public class Introducao {
    
    /**
     * @param args
     */
    public static void main(String [] args){
        // Tipo de dados
        int x = 100;
        double y = 9.5;
        float z = 5.5f;
        boolean status = true;

        double soma = x + y;

        System.out.println(soma);
        String nome = "Duda";
        String nota = "8.5";

       double resultado = Double.parseDouble(nota) + y;

       System.out.println(resultado);

        int soma_inteiro = (int)soma;

        System.out.println(soma_inteiro);

        // operador ternario
        String resultado_final  = (status)? "Aprovado": "Reprovado";
        System.out.println(resultado_final);

        // vectors estaticos

        // int [] vetor = new int [10];
        // for(int i=0 ; i < vetor.length; i++){
        //     vetor [i] = i + x;
        // }

        // for(int item : vetor){
        //     System.out.println(item);
        // }

        // vetores dinamicos

        List<Integer> vetor_dinamico = new ArrayList<>();
        for(int i = 100; i < 103; i++){
            vetor_dinamico.add(i + x);
        }
        
        vetor_dinamico.remove(0);
        vetor_dinamico.remove(Integer.valueOf(200));
        

        for(int item : vetor_dinamico){
            System.out.println(item);
        }





    }
}
