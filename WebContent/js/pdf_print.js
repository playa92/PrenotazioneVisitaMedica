// PDF-PRINT
$(document).ready(function(){
	
	var doc = new jsPDF();
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};
	
	$('#cmd').click(function () {
		
		alert("PRINT");
	    doc.fromHTML($('#content').html(), 15, 15, {
	        'width': 170,
	            'elementHandlers': specialElementHandlers
	    });
	    doc.save('riepilogo-prenotazione visita medica.pdf');
	});
}) 