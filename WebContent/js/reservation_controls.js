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
	
	$("#notice").modal("show");
	$("#message").text("Attenzione i dati inseriti verranno resettati");
	
}

//CONFIRM DIALOG
var MAX_VALUE = 50;
var count = localStorage.getItem("count");
var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"];

function Paziente(codiceFiscale, nome, cognome, matricola, invalidita, hexcode) {

	this.codiceFiscale = codiceFiscale;
	this.nome = nome;
	this.cognome = cognome;
	this.matricola = matricola;
	this.invalidita = invalidita;
	this.hexcode = hexcode;
}

function sendForm() {
	
	var hexcode = generate();
	
	var paziente = new Paziente(
			$("input[name=codiceFiscale]").prop("value"),
			$("input[name=nome]").prop("value"),
			$("input[name=cognome]").prop("value"),
			$("input[name=matricola]").prop("value"),
			$("#select").val(),
			hexcode);

	$.ajax({
		type:'post',
		url:'../formPrenotazione',
		datatype:"json",
		data:JSON.stringify(paziente),
		success:function(data) {
			
			var values = data.split(";");
			
			if(values[0] == "redirect") {
				
				$("#notice").modal("show");
				$("#message").text(values[1]);
				setTimeout(function() {
					window.location.href = "../home";
				}, 2000);
				
			} else 
				if(values[0] == "false") {
				$("#notice").modal("show");	
				$("#message").text(values[1]);
			} else {
				$("#notice2").modal("show");
				$("#message2").text("Prenotazione: " + values[1] +
						" Orario visita: " + values[2] + " vuole continuare?");
			}
			
		}
	});
}

function success() {
	
	$("#notice").modal("show");	
	$("#message").text("Prenotazione avvenuta con successo");
}

//HEXCODE
function generate() {
	return randHex(12);
}

function randHex(len) {
	
  var maxlen = 8,
      min = Math.pow(16,Math.min(len,maxlen)-1) 
      max = Math.pow(16,Math.min(len,maxlen)) - 1,
      n   = Math.floor( Math.random() * (max-min+1) ) + min,
      r   = n.toString(16);
  while(r.length < len) {  
	  r = r + randHex( len - maxlen );
  }
  return r.toUpperCase();
}

