function validate() {
	
	alert($("#hexcode").val());
	$.ajax({
		type:"get",
		url:"../convalidaPrenotazione",
		data:{hexcode:$("#hexcode").val()},
		success: function(data) {
			
			$("#notice").modal("show");
			$("#message").text(data);	
		}
	});
	
	alert("dopo");
}