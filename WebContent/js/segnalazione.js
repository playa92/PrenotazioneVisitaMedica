var motiva = "";
var currentRisolvi = null;
function tmp(idMotivazione, idRisolvi) {
	motiva = $("#" + idMotivazione).text();
	currentRisolvi = idRisolvi;
}

function rispondi() {
	
	$.ajax({
		type:'get',
		url:"restituisciSegnalazioni",
		data:{
			motivazione: motiva,
			risposta: $("#risposta").val()
		},
		success:function(data) {
		      window.location.reload();
		}
	});			
}

function dismiss(){
	$("#risposta").val('');
}
	