function Impiegato(username, newPassword) {
	
	this.username = username;
	this.password = newPassword;
}

function restore() {
	
	var impiegato = new Impiegato(
			$("input[name=username]").prop("value"),
			$("input[name=newPassword]").prop("value")
	);
	
	$.ajax({
		type:"post",
		url:"../ripristinoPassword",
		datatype:"json",
		data:JSON.stringify(impiegato), 
		success:function(data) {
			
			$("#notice").modal('show');
			$("#message").text(data);
		}
	});
}

function verify(password) {
	
	var regex = new RegExp("^[A-z0-9]+$");
	var p = password;
	
	if((p.length > 0 && !regex.test(p)) || p == $("input[name=username]").prop("value")) {
		
		$("#notice").modal("show");
		$("#message").text("Attenzione: la password inserita non rispetta i requisiti");
		$("input[type=password]").val('');
	}
}

function reset() {
	
	$("input[type=text]").val('');
	$("input[type=password]").val('');
}