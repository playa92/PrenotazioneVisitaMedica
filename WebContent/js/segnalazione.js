function tmp(idMotivazione, idRisolvi) {
	$("#motivazione").val($("#" + idMotivazione).text());
	alert($("#motivazione").val());
}
//
//function rispondi() {	
//	$.ajax({
//		type:'get',
//		url:"restituisciSegnalazioni",
//		data:{
//			motivazione: motiva,
//			risposta: $("#risposta").val()
//		},
//		success:function(data) {
//		      window.location.reload();
//		}
//	});			
//}

function dismiss(){
	$("#risposta").val('');
}
	