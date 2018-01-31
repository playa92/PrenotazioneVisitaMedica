function send() {
	var text = $("#textarea").val();
	$.ajax({
    	type:'get',
    	url:'../segnalazione',
    	data:{messaggio:text},
	    success:function(data) {
	    	alert("Messaggio inviato con successo");
	    },
	});
}
