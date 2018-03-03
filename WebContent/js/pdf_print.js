
// PDF-PRINT
$(document).ready(function() {
		
	// STAMPA RIEPILOGO
	$('#cmd').click(function() {
		  var options = {
		  };
		  var pdf = new jsPDF('l', 'pt', 'a4');		  
		  pdf.addHTML($("#content"), 15, 15, options, function() {
		  pdf.save('riepilogo.pdf'); 
		  });
	});
			
}); 
