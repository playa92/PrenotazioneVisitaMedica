var callback = localStorage.getItem("prova");
function send() {
	var text = $("#textarea").val();
	$.ajax({
    	type:'get',
    	url:'../segnalazione',
    	data:{messaggio:text},
	    success: function(data) {
	    	callback = data;
	    	alert("callback is " + callback);
	    	localStorage.setItem("prova", callback);
    }
	});
}

function clicked() {
	
	alert("hai cliccato -> " + localStorage.getItem("prova"));
	$("#mex").text(localStorage.getItem("prova"));
}