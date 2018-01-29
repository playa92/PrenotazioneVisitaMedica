
$(document).ready(function() {
	$("#form").submit(function(e) {
		
		$ajax({
			type:e.method,
			url:e.action,
			data: $("#input").val(),
			success: function(data) {
				
//				var a = new Array();
//				a = data.split(';');
//				alert(data);
//				if(data == "true") {
					$("#result").text(data);
//				} else {
//					window.location.href = "http://localhost:8080/PrenotazioneVisitaMedica/cerca_prenotazione.jsp"
			// 		$("#result").text("")
//					}
				}
			});
		e.preventDefault();
	});
});