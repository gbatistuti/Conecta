var url_atual = window.location.href
function aberturaDoPopUp() {
	var url_atual = window.location.href
	if(url_atual == "http://localhost:8080/homeConsultor?falha"){
		$('avisoModal').modal(true)
	}
}

