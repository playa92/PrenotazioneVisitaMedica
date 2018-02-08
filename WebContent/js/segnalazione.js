	
	var motiva = "";
	var currentRisolvi = null;

	function tmp(idMotivazione, idRisolvi){
		motiva = $("#"+idMotivazione).text();
		currentRisolvi = idRisolvi;
	}
	
	function risp(){
		$.ajax({
			type:'get',
			url:"gestisciSegnalazioni",
			data:{
				motivazione: motiva,
				risp: $("#risp").val()
			},
			success:function(data) {
			      window.location.reload(); // This is not jQuery but simple plain ol' JS
			}
		});			
	}
	
	function dismiss(){
		$("#risp").val('');
	}
	