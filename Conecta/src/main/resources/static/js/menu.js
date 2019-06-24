function imagem1(){
	var imagem = 'url("../img/menu/fundo1.png")'
	window.sessionStorage.clear();
	window.sessionStorage.setItem('fundo1', imagem)
    document.body.style.backgroundImage = imagem
}

function imagem2(){
	var imagem = 'url("../img/menu/fundo2.png")'
	window.sessionStorage.clear();
	window.sessionStorage.setItem('fundo2', imagem)
	document.body.style.backgroundImage = imagem
}

function imagem3(){
	var imagem = 'url("../img/menu/fundo3.png")'
	window.sessionStorage.clear();
	window.sessionStorage.setItem('fundo3', imagem)
	document.body.style.backgroundImage = imagem
}

function imagem4(){
	var imagem = 'url("../img/menu/fundo4.png")'
	window.sessionStorage.clear();
	window.sessionStorage.setItem('fundo4', imagem)
	document.body.style.backgroundImage = imagem
}

function VisibilidadePainelUser(){
    var painel = document.getElementById("menuSelecaoDeCor")
    if(painel.style.display == 'unset'){
        painel.style.display = 'none';
    }else{
        painel.style.display = 'unset';
    }
    
}



