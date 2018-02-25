$(document).ready(function(){
	reset();
})
// when any modal is closing
$('.modal').on('hide.bs.modal', function (e) {
	
	if(e.target.id == "contattaci"){
		reset();
	}
});

function Segnalazione(email, nome, cognome, motivazione, commento) {
	
	this.email = email;
	this.nome = nome;
	this.cognome = cognome;
	this.motivazione = motivazione;
	this.commento = commento;
}

function send() {
	
	var segnalazione = new Segnalazione(
		$("input[name=email]").prop("value"),
		$("input[name=nome]").prop("value"),
		$("input[name=cognome]").prop("value"),
		$("#select").val(),
		$("#textarea").val()
	);

	$.ajax({
		type:"post",
		url:"effettuaSegnalazione",
		datatype:"json",
		data:JSON.stringify(segnalazione),
		success:function(data) {
			window.location = "restituisciSegnalazioni";
		}
	});
}

function reset(){
	$("input[name=nome]").val('');
	$("input[name=cognome]").val('');
	$("input[name=email]").val('');
	$("#textarea").val('');
}

//if(typeof window.history.pushState == 'function') {
//    window.history.pushState({}, "Hide", "http://localhost:8080/PrenotazioneVisitaMedica/restituisciSegnalazioni");
//}
