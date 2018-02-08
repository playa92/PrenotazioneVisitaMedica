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
	$("#message").text("Attenzione i dati inseriti sono stati resettati");
	$("input[type=text]").val("");
}


function Paziente(codiceFiscale, nome, cognome, matricola, invalidita, hexcode) {

	this.codiceFiscale = codiceFiscale;
	this.nome = nome;
	this.cognome = cognome;
	this.matricola = matricola;
	this.invalidita = invalidita;
	this.hexcode = hexcode;
}

var orario = null;
var hex = null;
function question() {
	
	$.ajax({
		type:'get',
		url:'../registraPrenotazione',
		data: {value:$("input[name=codiceFiscale]").prop("value")},
		success:function(data) {
			
			var values = data.split(";");
						
			if(values[0] == "redirect") {
				$("#notice").modal("show");
				$("#message").text(values[1]);
			
				setTimeout(function() {
					window.location.href = "../home";
				}, 2000);
				
			} else if(values[0] == "false") {
				$("#notice").modal("show");	
				$("#message").text(values[1]);
				
			} else {
				$("#confirm").modal("show");
				$("#confirmMessage").html("Prenotazione: n\u00b0" + values[1] +" <br> " 
                        +" Orario visita: " + values[2] + "<br>" 
                        + "vuole continuare?");
				
				orario = values[2];
			}
		}
	});
}

var array = ["Codice Fiscale: ", "Nome: ", "Cognome: ", 
	"Matricola: ", "Invalidit\340: ", "Importo: ", "Codice: "];

function sendForm() {
	
	var hexcode = generate();
	hex = hexcode;
	
	var paziente = new Paziente(
			$("input[name=codiceFiscale]").prop("value"),
			$("input[name=nome]").prop("value"),
			$("input[name=cognome]").prop("value"),
			$("input[name=matricola]").prop("value"),
			$("#select").val(),
			hexcode);

	$.ajax({
		type:'post',
		url:'../registraPrenotazione',
		datatype:"json",
		data:JSON.stringify(paziente),
		success:function(data) {
			
			var values = data.split(";");
			
			if(values[0] == "true") {
				var strings = values[1].split("|");
				
				for(var i = 0; i < strings.length; i++) {
				
					if(i == 5) {
						$("#" + (i + 1)).text(array[i] + strings[i] + "0\u20ac");
					} else {
						$("#" + (i + 1)).text(array[i] + strings[i]);
					}
				}
				$("#text").val(strings[6]);
				makeCode();
				success();
				
			} else {
				$("#notice").modal("show");	
				$("#message").text(values[1]);
			}
		}
	});
}

//AUTOMATIC PDF-PRINT
function automaticPrint(){
	
	 html2canvas($("#print"),{
	 onrendered:function(canvas){
		 
		 var img=canvas.toDataURL("image/png");
		 var doc = new jsPDF('p', 'pt', 'a4');
		 //TODO
		 doc.text("Recati allo sportello entro le ore "+orario,150,70);
		 doc.addImage(img,'JPEG',80,110);
		 doc.text(hex,233,245);
		 doc.save('qr_code.pdf');
		}

	 });
}

function success() {
	
	$("#notice").modal("show");	
	$("#message").text("Prenotazione avvenuta con successo");
	setTimeout(function() {
		$("#notice").modal('hide');
		$("#riepilogo").modal('show');
		setTimeout(function() {
			automaticPrint();
		},800);
		
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

