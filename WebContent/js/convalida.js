function validate() {
	
	$.ajax({
		type:"get",
		url:"../convalidaPrenotazione",
		data:{hexcode:$("#hexcode").val()},
		success: function(data) {
			var values = data.split(";");
			
			if(values[0] =="true"){

				$("#notice").modal("show");
				$("#message").text(values[1]);
					setTimeout(function() {
						
						var today = new Date();
						
						$("#message").html("Importo: "+ values[2]+"0\u20ac"+"<br>" +
										   "Data: " +today.toISOString().substring(0, 10)+"<br>" +
										   "Orario: "+ today.getHours()+":"+today.getMinutes());
					
					}, 2000);
	
			}else{
				$("#notice").modal("show");
				$("#message").text(data);
			}
		
		}
	});
}