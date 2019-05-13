function imagem1(){
    document.body.style.backgroundImage = 'url("../img/menu/fundo1.png")'
}

function imagem2(){
    document.body.style.backgroundImage = 'url("../img/menu/fundo2.png")'
}

function imagem3(){
    document.body.style.backgroundImage = 'url("../img/menu/fundo3.png")'
}

function imagem4(){
    document.body.style.backgroundImage = 'url("../img/menu/fundo4.png")'
}

function VisibilidadePainelUser(){
    var painel = document.getElementById("selecaoDeCor")
    if(painel.style.display == 'unset'){
        painel.style.display = 'none';
    }else{
        painel.style.display = 'unset';
    }
    
}