var motiva = "";
var currentRisolvi = null;
function tmp(idMotivazione, idRisolvi) {
	motiva = $("#" + idMotivazione).text();
	currentRisolvi = idRisolvi;
}

function rispondi() {
	
	alert($("#risposta").val());
	$.ajax({
		type:'get',
		url:"restituisciSegnalazioni",
		data:{
			motivazione: motiva,
			risposta: $("#risposta").val()
		},
		success:function(data) {
		      window.location.reload(); // This is not jQuery but simple plain ol' JS
		}
	});			
}

function dismiss(){
	$("#risposta").val('');
}
	