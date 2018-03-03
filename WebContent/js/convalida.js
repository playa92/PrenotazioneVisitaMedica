function validate(code) {
	
	code = code.replace(/\s/g, '');
	code = code.toUpperCase();
	
	$.ajax({
		type:"get",
		url:"../convalidaPrenotazione",
		data:{hexcode:code},
		success: function(data) {
			
			var values = data.split(";");
						
			if(values[0] =="true") {

				$("#notice").modal("show");
				$("#message").text(values[1]);
					setTimeout(function() {
						$("#notice").modal("hide");
						$("#receipt").modal("show");
						$("#messageReceipt").html("Data: " + moment(moment()).format("MMM Do YY") + "<br>" +
										   "Orario: " + moment(moment()).format("HH:mm") + "<br>" +
										   "Importo: " + values[2] + "0 &#8364");
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
		 $("html, body").animate({ scrollTop: 200}, 1200)
	}
});

$("#HEX").click(function(){
	if($("#QRCODE").is(':visible')){
		 $("#QRCODE").hide();
		 stop();
		 $("#HEXCODE").toggle();
		 return;
	}
	 $("#HEXCODE").toggle();
	 $("html, body").animate({ scrollTop: 200}, 1200)
});


