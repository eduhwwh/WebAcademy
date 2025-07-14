package orientacao_objetos;

public class Teste {
    public static void main(String[] args) {
        // Criação de um objeto do tipo Pessoa
        Pessoa vinicius = new Pessoa();
        // Não podemos acessar diretamente os atributos privados
        // vinicius.nome = "Vinicius Barros de Melo";
        // vinicius.email = "vinicius@ufac.br";
        // vinicius.telefone = "12345678901";
        // System.out.println(vinicius.nome);
        vinicius.setNome("Vinicius Barros de Melo");
        System.out.println(vinicius.getNome());
        Aluno alonso = new Aluno();
        alonso.setMatricula("20230300023");
        // vinicius.setMatricula("9999999999"); //O tipo Pessoa não tem matrícula

    }

}
