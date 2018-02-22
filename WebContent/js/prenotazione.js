function CFRegex() {
			
//		var regex = new RegExp("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$"); 	
//		var code = $("#cf").val().toUpperCase();
//		
//		if(code.length > 0 && !regex.test(code)) {
//			
//			$("#notice").modal("show");
//			$("#message").text("Attenzione: Codice Fiscale non valido");
//			$("#cf").val("");
//		}
}


function correct(message) {
	
		var id = String (message);
		
		if(id == "n") {
			var numbers = new RegExp("^[0-9]+$");
			var m = $("#n").val();
			
			if(m.length > 0 && !numbers.test(m)) {
				
				$("#notice").modal("show");
				$("#message").text("Attenzione: Matricola non valida");
				$("#n").val("");
			}
		} else {
			var characters = new RegExp("^[A-z]+$");
			var s = $("#"+id).val();
			
			if(s.length > 0 && !characters.test(s)) {
				
				$("#notice").modal("show");
				$("#message").text("Attenzione: Stringa non valida");
				$("#"+id).val("");
			}
		}
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
	
	var inputObj = [document.getElementById("cf"),document.getElementById("s1"),document.getElementById("s2")];
	
	for(var i = 0; i < inputObj.length; i++) {
		
		if(inputObj[i].value == "") {
			$("#notice").modal("show");
			$("#message").text("Attenzione campi obbligatori vuoti");
			return;
		}
	}
	
	$.ajax({
		type:'get',
		url:'../effettuaPrenotazione',
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

var array = ["Codice Fiscale: ", "Nome: ", "Cognome: ", "Matricola: ", "Invalidita': ", "Importo: ", "Codice: "];

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
		url:'../effettuaPrenotazione',
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

// when any modal is closing
$('.modal').on('hide.bs.modal', function (e) {
	if(e.target.id == "riepilogo")
		window.location.href='../home';
})
var flag = false;
 $(document).mousemove(function(event){
	 if(!flag){
		 $("html, body").animate({ scrollTop: 800}, 1200)
		 flag=true;
	 }
})

