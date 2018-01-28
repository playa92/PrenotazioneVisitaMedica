$(document).ready(function() {
	$('#cmd').click(function() {
		  var options = {
				  
				  
		  };
		  var pdf = new jsPDF('p', 'pt', 'a4');
		  pdf.setFontSize(50);
          pdf.text(200, 30, "PROVA");
		  pdf.addHTML($("#content"), 15, 15, options, function() {
		    pdf.save('pageContent.pdf');
		  });
		});	
}) 

