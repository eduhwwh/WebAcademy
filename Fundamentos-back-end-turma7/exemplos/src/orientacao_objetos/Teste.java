package orientacao_objetos;

public class Teste {
    public static void main(String [] args){
        // Criação de um obejto do tipo de pessoa
        // Não podemos acessar os atributos privados
        
        Pessoa Maria = new Pessoa();
        // Maria.nome = "Maria Eduarda";
        // Maria.email = "maria@gmail";
        // Maria.telefone = "123456789";

        // System.out.println(Maria.nome);


        Maria.setNome("Maria Eduarda");
        System.out.println(Maria.getNome());

    }
}
