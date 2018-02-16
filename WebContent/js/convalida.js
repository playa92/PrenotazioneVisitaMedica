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
						$("#notice").modal("hide");
						$("#receipt").modal("show");
						var today = new Date();
						$("#messageReceipt").html("Data: " +today.toISOString().substring(0, 10)+"<br>" +
										   "Orario: "+ today.getHours()+":"+today.getMinutes() +"<br>"+
										   "Importo: "+ values[2]+"0\u20ac")
					
					}, 2000);
			
			} else {
				$("#notice").modal("show");
				$("#message").html(data);
			}
		}
	});
}

//when any modal is closing
$('.modal').on('hide.bs.modal', function (e) {
	if(e.target.id == "receipt")
		window.location.href='../home';
})


$("#QR").click(function(){
	if($("#HEXCODE").is(':visible')){
		 $("#HEXCODE").hide();
	}
	if($("#QRCODE").is(':visible')){
	
		 $("#QRCODE").hide();
		 stop();
	}else{
		 $("#QRCODE").show();
		 start();
	}
});

$("#HEX").click(function(){
	if($("#QRCODE").is(':visible')){
		 $("#QRCODE").hide();
		 stop();
	}
	 $("#HEXCODE").toggle();
});


