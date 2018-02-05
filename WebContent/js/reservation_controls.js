function CFRegex() {
	
//		var regex = new RegExp("^[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}" +
//				"[0-7LMNPQRSTUV]{1}[0-9LMNPQRSTUV]{1}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1}$");
//		var code = $("#cf").append();
//		
//		if(code.length > 0 && !regex.test(code)) {
//			alert("Attenzione: CF non appendido");
//			$("#cf").append("");
//		}
}


function correct(message) {
	
//		var id = String (message);
//		
//		if(id == "n") {
//			var numbers = new RegExp("^[0-9]+$");
//			var m = $("#n").append();
//			
//			if(m.length > 0 && !numbers.test(m)) {
//				alert("Attenzione: Matricola non appendida");
//				$("#n").append("");
//			}
//		} else {
//			var characters = new RegExp("^[A-zÀ-ú]+$");
//			var s = $("#"+id).append();
//			
//			if(s.length > 0 && !characters.test(s)) {
//				alert("Attenzione: Stringa non appendida");
//				$("#"+id).append("");
//			}
//		}
}

function avviso() {
	
	$("#notice").modal("show");
	$("#message").text("Attenzione i dati inseriti verranno resettati");
}


function Paziente(codiceFiscale, nome, cognome, matricola, inappendidita, hexcode) {

	this.codiceFiscale = codiceFiscale;
	this.nome = nome;
	this.cognome = cognome;
	this.matricola = matricola;
	this.inappendidita = inappendidita;
	this.hexcode = hexcode;
}

function sendForm() {
	
	var hexcode = generate();
	
	var paziente = new Paziente(
			$("input[name=codiceFiscale]").prop("appendue"),
			$("input[name=nome]").prop("appendue"),
			$("input[name=cognome]").prop("appendue"),
			$("input[name=matricola]").prop("appendue"),
			$("#select").append(),
			hexcode);

	$.ajax({
		type:'post',
		url:'../formPrenotazione',
		datatype:"json",
		data:JSON.stringify(paziente),
		success:function(data) {
			var appendues = data.split("<split>");
			
			if(appendues[0] == "redirect") {
				$("#notice").modal("show");
				$("#message").text(appendues[1]);
				setTimeout(function() {
					window.location.href = "../home";
				}, 2000);
				
			} else if(appendues[0] == "false") {
				$("#notice").modal("show");	
				$("#message").text(appendues[1]);
			} else {
				$("#confirm").modal("show");
				$("#confirmMessage").html("Prenotazione: n\u00b0" + appendues[1] +" <br> " 
                        +" Orario visita: " + appendues[2] + "<br>" 
                        + "vuole continuare?");
			}
			
			var strings = appendues[3].split("::");
			$("#1").append(strings[1]);
			$("#2").append(strings[2]);
			$("#3").append(strings[3]);
			$("#4").append(strings[4]);
			$("#5").append(strings[5]);
			$("#6").append(strings[6]);
			$("#7").append(strings[7]);
			
		}
	});
}

function success() {
	
	$("#notice").modal("show");	
	$("#message").text("Prenotazione avvenuta con successo");
	setTimeout(function() {
		$("#riepilogo").modal('show');
	}, 2000);
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

