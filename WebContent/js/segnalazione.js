function tmp(idMotivazione, idRisolvi) {
	$("#motivazione").val($("#" + idMotivazione).text());
}

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
	
function getMail(mail) {
		$("form").attr("action","https://formspree.io/" + mail );
}