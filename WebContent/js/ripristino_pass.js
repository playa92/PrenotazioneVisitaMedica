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