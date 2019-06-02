window.onload = initPage();

 function initPage(){
 	aberturaDoPopUp()
 }
 var elementRef = document.getElementById("body")

 function aberturaDoPopUp() {
 	var url_atual = window.location.href
 	
 	var sucessoNaUrl = url_atual.indexOf("sucesso")
 	var erroNaUrl = url_atual.indexOf("falha")
 	
 	var modal = document.getElementById('avisoModal')
 	
 	
 	if(sucessoNaUrl != -1 || erroNaUrl != -1 ){
 		$("#avisoModal").modal('show');
 	}
 }
