# programacao-avancada-back-end-t7

Repositório da disciplina Programação Avançada Back-end (Turma 7)

<details>

<summary>
    <h2>Atualizando seu repositório local</h2>
</summary>

O código produzido em sala de aula, e compartilhado neste repositório, pode ser atualizado em seu repositório local com o comando:

```console
git pull
```

No entanto, se você fez alterações no seu repositório local, o comando acima pode gerar conflitos. Para evitar lidar com isso, você pode forçar uma atualização com o repositório remoto por meio dos comandos:

```console
git fetch origin
git reset --hard origin/main
```

O primeiro comando recebe as atualizações mais recentes do repositório remoto, e o segundo descarta todas as alterações locais e atualiza com o histórico mais recente do repositório remoto (branch main).

</details>

<details>

<summary>
    <h2>Como inciar a aplicação</h2>
</summary>

A aplicação back-end pode ser iniciada pelo Spring Boot Dashboard ou diretamente com o Maven.

1. No Spring Boot Dashboard, clicar em "Run" na aplicação "sgcmapi".

2. No prompt de comandos, a partir do diretório `./sgcmapi`:

    a. Para iniciar a aplicação com o Maven:

    ```console
    mvn spring-boot:run
    ```

    Ou

    b. Para compilar o pacote e depois executar o JAR gerado:

    ```console
    mvn package
    java -jar target\sgcmapi.jar
    ```

A aplicação vai iniciar no endereço <http://localhost:9000/>, com acesso local a base de dados MySQL, por meio da porta padrão 3306, além de usuário e senha "root".

</details>

<details open>

<summary>
    <h2>Sites de referência</h2>
</summary>

- Spring Boot Reference Documentation: <https://docs.spring.io/spring-boot/docs/3.2.6/reference/html/index.html>
- Spring Getting Started Guides: <https://spring.io/guides#getting-started-guides>
- Swagger Documentation: <https://swagger.io/docs/>
- Baeldung: <https://www.baeldung.com/>
- Engenharia de Software Moderna: <https://engsoftmoderna.info/>

</details>

<details open>

<summary>
    <h2>Ferramentas</h2>
</summary>

- **Docker Desktop**
  - <https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe>
  - Tutorial de instalação: <https://docs.docker.com/desktop/setup/install/windows-install/#install-docker-desktop-on-windows>
  - Para verificar se o Docker está corretamente instalado e configurado, digite no prompt de comandos:

    ```console
    docker info
    ```
  
  - Baixar imagem do Redis:

    ```console
    docker image pull redis:alpine
    ```

</details>

<details>

<summary>
    <h2>Outras ferramentas</h2>
</summary>

- **Visual Studio Code**
  - <https://code.visualstudio.com/Download>
- **Live Server (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer>
- **Extension Pack for Java (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack>
- **Spring Boot Extension Pack (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=pivotal.vscode-boot-dev-pack>
- **XML (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml>
- **Angular Language Service (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=Angular.ng-template>
- **Git**
  - <https://git-scm.com/downloads>
- **Postman**
  - <https://www.postman.com/downloads/>
  - Link para download da coleção compartilhada (contendo requisições utilizadas nas aulas): <https://api.postman.com/collections/19704449-95075334-fa15-4985-ac6e-9d6f1a466648?access_key=PMAT-01K021V113PRMPADCVRQYQE95D>
    - Para importar a coleção no Postman, clique em `Import` e cole o link acima.
- **JDK 17**
  - Para verificar se o JDK está corretamente instalado e configurado, digite no prompt de comandos:

    ```console
    javac -version
    ```

  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.16%2B8/OpenJDK17U-jdk_x64_windows_hotspot_17.0.16_8.msi>
    - Criar a variável de ambiente JAVA_HOME configurada para o diretório de instalação do JDK. Exemplo: “C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot”.
    - Adicionar “%JAVA_HOME%\bin” na variável de ambiente PATH.
    - Tutorial de configuração: <https://mkyong.com/java/how-to-set-java_home-on-windows-10/>
- **Maven**
  - Para verificar se o Maven está corretamente instalado e configurado, digite no prompt de comandos:

    ```console
    mvn -version
    ```

  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip>
    - Adicionar o diretório de instalação do Maven na variável de ambiente PATH. Exemplo: “C:\apache-maven\bin”.
    - Tutorial de instalação: <https://mkyong.com/maven/how-to-install-maven-in-windows/>
- **MySQL**
  - Verificar se o MySQL está funcionando:
    - Para tentar conectar no MySQL, no prompt de comandos digite:

      ```console
      mysql -u root -p
      ```

    - Tentar acessar com senha em branco ou senha igual ao nome de usuário (root).
    - Tutorial para resetar a senha de root, caso necessário: <https://dev.mysql.com/doc/mysql-windows-excerpt/8.0/en/resetting-permissions-windows.html>
  - Remova o banco de dados `sgcm`, se existir:
    - No prompt de comandos digite:
  
      ```console
      mysql -u root -p
      ```
  
    - Ao conectar no MySQL, execute a seguinte instrução SQL:

      ```sql
      DROP DATABASE sgcm;
      ```
  
  - Se necessário, realizar a instalação:
    - Link para download: <https://dev.mysql.com/downloads/file/?id=516927>
    - [Tutorial de instalação](https://github.com/webacademyufac/tutoriais/blob/main/mysql/mysql.md)
- **Node.js (e npm)**
  - Versão 20 (LTS).
  - Para verificar a versão do Node.js, no prompt de comandos digite:

    ```console
    node --version
    ```

  - Link para download: <https://nodejs.org/dist/v20.14.0/node-v20.14.0-x64.msi>
- **Angular CLI**
  - Versão 19.
  - Para verificar a versão do Angular CLI, no prompt de comandos digite:

    ```console
    ng version
    ```

  - Tutorial de instalação: <https://v19.angular.io/guide/setup-local>
  - Para instalar o Angular CLI, no prompt de comandos digite:

    ```console
    npm i -g @angular/cli@19.2.15
    ```

</details>

<details>

<summary>
    <h2>SGCM - Sistema de Gerenciamento de Clínica Médica</h2>
</summary>

A demonstração de uso das ferramentas e tecnologias abordadas na capacitação é baseada em um projeto de exemplo, o SGCM. A documentação básica deste projeto está disponível [em outro repositório](https://github.com/webacademyufac/sgcmdocs) e aborda os seguintes tópicos:

- [Principais funcionalidades](https://github.com/webacademyufac/sgcmdocs#principais-funcionalides)
- [Histórias de usuário](https://github.com/webacademyufac/sgcmdocs#histórias-de-usuário)
- [Diagrama de Classes](https://github.com/webacademyufac/sgcmdocs#diagrama-de-classes)
- [Diagrama Entidade Relacionamento](https://github.com/webacademyufac/sgcmdocs#diagrama-entidade-relacionamento)

</details>

## Atividades práticas

> [!NOTE]
>
> - As atividades serão realizadas com o GitHub Classroom e podem ser acessadas pelos links nas descrições das atividades.
> - No primeiro acesso, _**cada aluno deverá selecionar seu nome na lista para vincular sua conta no GitHub**_ e aceitar o convite para a atividade prática.
> - O repositório da atividade prática será criado automaticamente para cada aluno ou grupo (compartilhado entre os membros).
> - O aluno deverá clonar o repositório para seu computador, fazer as modificações necessárias e subir o repositório para o GitHub (`git push`).
> - Não é necessário nenhuma outra ação para submeter a atividade.
> - Atividades em grupo:
>   - Ao acessar o link da atividade, o aluno deverá criar seu grupo ou ingressar no seu respectivo grupo se existir.
>   - O nome do grupo deve seguir o padrão: `Grupo_X`, onde `X` é o número do grupo.

> [!IMPORTANT]
> _**Todos os membros dos grupos devem participar das atividades**_, registrando esta participação por meio da identificação dos commits com seus respectivos usuários no GitHub.

> [!CAUTION]
> Para atividades avaliadas automaticamente, **não modifique ou exclua** os arquivos responsáveis pela avaliação. Dependendo do escopo da atividade, isso pode incluir arquivos localizados nos diretórios `src/test/java` e `.github/workflows`, bem como arquivos com extensão `*.spec.ts`, ou quaisquer outros destinados exclusivamente à automação da avaliação.

1. [INDIVIDUAL] Criar um DTO para a entidade `Profissional` e alterar o retorno e parâmetros dos métodos da camada de controle para utilizarem o DTO.

    - O DTO deve conter os atributos: id, nome, email, registroConselho, telefone, especialidade_id, especialidade_nome, unidade_id, unidade_nome.
    - A conversão _**DTO <-> Entidade**_ deve ser feita na camada de controle.
    - A utilização de bibliotecas de mapeamento (como MapStruct) é opcional, podendo a conversão ser feita por meio de métodos no próprio DTO.

    - Link da atividade: <https://classroom.github.com/a/_snZ1oHl>
    - Entrega: 04/09/2025 - 18:00

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-back-end-t7/commit/c92e8ba>

2. [INDIVIDUAL] Criar um validador customizado para senha do usuário, que deve ser utilizado tanto na inserção quanto na atualização.

    - As senhas devem possuir no mínimo 1 letra maiúscula, 1 letra minúscula, 1 número e 8 caracteres no total.
    - A implementação do validador deve utilizar a anotação **@Senha** já disponível no projeto da atividade.
    - Senha em branco ou nulas devem ser permitidas ao atulizar um usuário.

    - Link da atividade: <https://classroom.github.com/a/qygL6wZ7>
    - Entrega: 05/09/2025 - 18:00

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-back-end-t7/commit/4be8e52>

3. [INDIVIDUAL] Implementar as funcionalidades de paginação de resultados e ordenação para as demais entidades do SGCM, a exemplo do que foi feito para o agendamento/atendimento.

    - Deve ser definida a ordenação padrão, em ordem crescente, para entidades `Convenio`, `Especialidade`, `Paciente` e `Profissional`, `Unidade` e `Usuario`.
      - A ordenação padrão deve ser feita pelo atributo **nome**, exceto para `Usuario`, que deve ser pelo **nome completo**.
    - O recurso que permite retornar todos os registros sem paginação deve ser implementado para todas as entidades, e deve usar a ordenação padrão definida para a lista paginada.

    - Link da atividade: <https://classroom.github.com/a/zvqmYqox>
    - Entrega: 08/09/2025 - 18:00

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-back-end-t7/commit/e64ef83>

4. [GRUPO] Documentar todos os endpoints da API do SGCM utilizando o Swagger, seguindo o exemplo apresentado em aula, e atendendo aos seguintes requisitos mínimos:

    - Nomear e descrever todas as classes da camada de controle (**@Tag**).
    - Descrever todos os endpoints (**@Operation**).
    - Anotar os parâmetros Pageable com **@ParameterObject**.
    - Documentar tipos de repostas para entradas inválidas (400), novo item criado (201) e item não encontrado (404).
      - Para entradas inválidas, o conteúdo do retorno deve ser baseado no tipo `RespostaErro`.
      - Para itens não encontrados, o retorno não deve ter conteúdo.

    - Link da atividade: <https://classroom.github.com/a/rcznJXB6>
    - Entrega: 15/09/2025 - 18:00

5. [GRUPO] Implementar o armazenamento em cache para os métodos da classes da camada de serviço do SGCM, seguindo o mesmo padrão adotado no exemplo apresentado em aula.

    - O método consultar(termoBusca) e consultar(termoBusca, page) só devem utilizar cache quando termoBusca for nulo ou vazio.
    - O método consultar(id) só deve utilizar cache quando o resultado não for nulo.
    - O método consultar(objeto) e remover(id) devem limpar o cache para todos os registros.
    - O método consultar(objeto) e remover(id) devem limpar o cache para o registro atualizado.

    - Link da atividade: <https://classroom.github.com/a/t4gx9OUl>
    - Entrega: 15/09/2025 - 18:00
