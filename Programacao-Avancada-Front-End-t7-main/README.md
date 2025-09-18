# programacao-avancada-front-end-t7

Repositório da disciplina Programação Avançada Front-end (Turma 7)

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

- Angular Docs: <https://v19.angular.dev/docs>
- Bootstrap: <https://getbootstrap.com/docs/5.3/getting-started/introduction/>
- MDN Web Docs: <https://developer.mozilla.org/pt-BR/docs/Learn>
- Sass Documentation: <https://sass-lang.com/documentation/>
- TypeScript Documentation: <https://www.typescriptlang.org/docs/>
- WCAG 2.1: <https://www.w3c.br/traducoes/wcag/wcag21-pt-BR>

</details>

<details>

<summary>
    <h2>Ferramentas</h2>
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

> [!IMPORTANT]
> _**Todos os membros dos grupos devem participar das atividades**_, registrando esta participação por meio da identificação dos commits com seus respectivos usuários no GitHub.

> [!CAUTION]
> Para atividades avaliadas automaticamente, **não modifique ou exclua** os arquivos responsáveis pela avaliação. Dependendo do escopo da atividade, isso pode incluir arquivos localizados nos diretórios `src/test/java` e `.github/workflows`, bem como arquivos com extensão `*.spec.ts`, ou quaisquer outros destinados exclusivamente à automação da avaliação.

1. [GRUPO] Implementar melhorias nos demais componentes do SGCM usando **Bootstrap** e **ng-bootstrap**, baseando-se no `AgendaListComponent`.

    - Adicionar ícones em todos os botões.
    - Substituir todas as chamadas `alert()` pelo `NotificacaoService`.
    - Substituir todas as chamadas `confirm()` pelo `ConfirmacaoService`.
    - Implementar paginação com ng-bootstrap em todas os componentes que listam registros.
      - Deve usar também a diretiva de ordenação nos mesmo componentes.
    - Adicionar uma funcionalidade ao recurso de paginação que permita ao usuário selecionar a quantidade de itens por página.

    - Link da atividade: <https://classroom.github.com/a/LbUK67lG>
    - Entrega: 22/09/2025 - 18:00
