function setMotivazione(idMotivazione, idRisolvi) {
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
	
function getMail(mail) {
	$("form").attr("action","https://formspree.io/" + mail );
}

$(document).ready(function(){
	$("textarea").val('');
})

// when any modal is closing
$('.modal').on('hide.bs.modal', function (e) {
	if(e.target.id == "risolvi" || e.target.id == "send"){
		$("textarea").val('');
	}
})
