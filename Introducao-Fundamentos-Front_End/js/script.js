function carregarProfissionais() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://my-json-server.typicode.com/juniorlimeiras/json/profissionais');
    xhr.addEventListener('readystatechange', () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let dados = JSON.parse(xhr.responseText);
            // let tabela = document.querySelector('table');
            for (let item of dados) {
                let linha = document.createElement('tr');
                let id = document.createElement('td');
                let nome = document.createElement('td');
                let registro = document.createElement('td');
                let telefone = document.createElement('td');
                let email = document.createElement('td');
                let unidade = document.createElement('td');
                let especialidade = document.createElement('td');
                let opcoes = document.createElement('td');

                id.textContent = item.id;
                nome.textContent = item.nome;
                registro.textContent = item.registro;
                telefone.textContent = item.telefone;
                email.textContent = item.email;
                unidade.textContent = item.unidade;
                especialidade.textContent = item.especialidade;
                opcoes.innerHTML = `<a class="botao_verde" href="">Editar</a>|<a class="botao_vermelho" href="javascript:void(0)">Excluir</a>`;

                linha.appendChild(id);
                linha.appendChild(nome);
                linha.appendChild(registro);
                linha.appendChild(telefone);
                linha.appendChild(email);
                linha.appendChild(unidade);
                linha.appendChild(especialidade);
                linha.appendChild(opcoes);
                tabela.appendChild(linha);
            }
        }

        excluirLinha();

    });//Fecha o escutador de evento
    xhr.send();
}


carregarProfissionais();

let tabela = document.querySelector('table');
let form = document.querySelector('form');
let btn_enviar = document.querySelector('input[type="submit"]');
btn_enviar.addEventListener('click', (event) => {

    event.preventDefault(); //evita que a pagina seja recarregada

    //let objeto = new Object();
    let objeto = {
        // id:
        nome: form.nome.value,
        registro: form.registroConselho.value,
        telefone: form.telefone.value,
        email: form.email.value,
        unidade: form.unidade.options[form.unidade.selectedIndex].label,
        especialidade: form.especialidade.options[form.especialidade.selectedIndex].label
    }
    inserirProfissional(objeto);
});

const inserirProfissional = (item) => {
    // let tabela = document.querySelector('table');
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let registro = document.createElement('td');
    let telefone = document.createElement('td');
    let email = document.createElement('td');
    let unidade = document.createElement('td');
    let especialidade = document.createElement('td');
    let opcoes = document.createElement('td');

    id.textContent = item.id;
    nome.textContent = item.nome;
    registro.textContent = item.registro;
    telefone.textContent = item.telefone;
    email.textContent = item.email;
    unidade.textContent = item.unidade;
    especialidade.textContent = item.especialidade;
    opcoes.innerHTML = `<a class="botao_verde" href="">Editar</a>|<a class="botao_vermelho" href="javascript:void(0)">Excluir</a>`;

    linha.appendChild(id);
    linha.appendChild(nome);
    linha.appendChild(registro);
    linha.appendChild(telefone);
    linha.appendChild(email);
    linha.appendChild(unidade);
    linha.appendChild(especialidade);
    linha.appendChild(opcoes);
    tabela.appendChild(linha);
}



function excluirLinha() {
    let botoesExcluir = document.querySelectorAll('a.botao_vermelho');
    for (let botao of botoesExcluir) {
        botao.addEventListener('click', () => {

            botao.parentElement.parentElement.remove();

        });
    }


}

// Esconde e mostra o botao de adicionar profissionais

const mostrar = document.getElementById('botaoAdd')
const formulario = document.getElementById('formProf');

mostrar.addEventListener('click', () =>{
    mostrar.classList.add('esconder');
    formulario.classList.remove('esconder');
    formulario.reset();
});

function ProfissionalCadastrado(){
    alert("Profissional adicionado!");
}

// function atualizar(){
//     const contador = document.getElementById('quantProfissionais');
//     const total = 
// }