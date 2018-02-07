	
	var motiva = "";

	function tmp(id){
		motiva = $("#"+id).text();
	}
	
	function risp(){
		
		
		//TODO andare a sostituire Risolvi con risolto  a tutti
		// mettere button pulisci che pulisce tutto
		// notifica da gestire una volta risolto rimuovere
		
		$.ajax({
			type:'get',
			url:"gestisciSegnalazioni",
			data:{
				motivazione: motiva,
				risp: $("#risp").val()
			},
			success:function(data) {
			}
		});
	}