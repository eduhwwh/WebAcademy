let btn = document.querySelector('button');

btn.onclick = () =>{
    alert('Forma externa na tg HTML.');
}

//Escutador de eventos

btn.addEventListener('click', exibirMensagem);
btn.addEventListener('click',alterarTitulo);

function alterarTitulo(){
    let titulo = document.querySelector('h1');
    titulo.innerHTML = 'WebAcademy';
    titulo.style.color = 'green'
}

function exibirMensagem(){
    alert('Forma de eventos do JavaScript');
}