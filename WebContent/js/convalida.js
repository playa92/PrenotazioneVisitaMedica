function validate() {
	
	$.ajax({
		type:"get",
		url:"../convalidaPrenotazione",
		data:{hexcode:$("#hexcode").val()},
		success: function(data) {
			
			$("#notice").modal("show");
			$("#message").text(data);	
		}
	});
}