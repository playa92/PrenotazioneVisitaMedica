//TROVARE UNA SOSTITUZIONE AL LOCAL STORAGE
var callback = localStorage.getItem("prova");

function send() {
	var text = $("#textarea").val();
	$.ajax({
    	type:'get',
    	url:'../segnalazione',
    	data:{messaggio:text},
	    success: function(data) {
	    	callback = data;
	    	localStorage.setItem("prova", callback);
    }
	});
}

function clicked() {
	$("#mex").text(localStorage.getItem("prova"));
}