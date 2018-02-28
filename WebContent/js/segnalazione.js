var id2;
function set(idMotivazione,id) {
	$("#inpt").val($("#" + idMotivazione).text());
	id2=id;
}

function Risposta(id, risposta) {
	
	this.id=id;
	this.risposta = risposta;
	
}

function risolvi() {
	
	var risposta = new Risposta(
		id2,
		$("#risposta").val()	
	);
	
	$.ajax({
		type:'post',
		url:"risolviSegnalazione",
		datatype:"json",
		data:JSON.stringify(risposta),
		success:function(data) {
			window.location.reload();
		}
	});			
}

var address = "";
function setAddress(to) {
	address = to;
}

function Email(to, from, message) {
	
	this.to = to;
	this.from = from;
	this.message = message;
}

function sendMail() {
	
	var email = new Email(
		address,
		"assistenza.cup18@gmail.com",
		$("#textarea").val()	
	);
	
	$.ajax({
		type:"post",
		url:"inviaEmail",
		datatype:"json",
		data:JSON.stringify(email),
		success:function(data, status) {
			
			$("#notice").modal("show");
			$("#message").text(data);
		}
	});
}

$(document).ready(function() {
	$("textarea").val('');
})

// when any modal is closing
$('.modal').on('hide.bs.modal', function(e) {
	if(e.target.id == "risolvi" || e.target.id == "send") {
		$("textarea").val('');
	}
});

if(typeof window.history.pushState == 'function') {
    window.history.pushState({}, "Hide", "/PrenotazioneVisitaMedica/risolviSegnalazione");
}
