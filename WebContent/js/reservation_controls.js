function CFRegex() {
	
//		var regex = new RegExp("^[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}" +
//				"[0-7LMNPQRSTUV]{1}[0-9LMNPQRSTUV]{1}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1}$");
//		var code = $("#cf").val();
//		
//		if(code.length > 0 && !regex.test(code)) {
//			alert("Attenzione: CF non valido");
//			$("#cf").val("");
//		}
}

function correct(message) {
	
//		var id = String (message);
//		
//		if(id == "n") {
//			var numbers = new RegExp("^[0-9]+$");
//			var m = $("#n").val();
//			
//			if(m.length > 0 && !numbers.test(m)) {
//				alert("Attenzione: Matricola non valida");
//				$("#n").val("");
//			}
//		} else {
//			var characters = new RegExp("^[A-zÀ-ú]+$");
//			var s = $("#"+id).val();
//			
//			if(s.length > 0 && !characters.test(s)) {
//				alert("Attenzione: Stringa non valida");
//				$("#"+id).val("");
//			}
//		}
}

function avviso() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

//CONFIRM DIALOG
var MAX_VALUE = 5;
var count = localStorage.getItem("count");
var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"];

$(document).on("click", "#conferma", function() {
	
	if(count == null) {
		count = 0;
	}
	
	if(count == MAX_VALUE) {
		
		alert("Attenzione: Limite Prenotazione Raggiunto");
		$("#form").attr("action","home.jsp");
		return;
	}
	
	var d = new Date();
	d.setMinutes(d.getMinutes() + 15 * count);

	var toString = months[d.getMonth()] + " " + d.getDate() 
		+ " " + d.getHours() + ':' + d.getMinutes();
	
	var result = confirm("Prenotazione: " + (++ count) + "/" + MAX_VALUE
			+ "\nOrario visita: " + toString
			+ "\nVuoi continuare?");
	
	if(!result) {
		$("#form").attr("action","home.jsp");
		localStorage.removeItem("count");
	} else {
		localStorage.setItem("count", count);
	}
});

