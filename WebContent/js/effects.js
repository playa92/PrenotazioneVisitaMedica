
/* NAVBAR LOGIN */
$(document).ready(function() {
	
	$("#login-form").click(function(e){
		$(".login").fadeToggle();
		e.stopPropagation();
	});
	
	$(".login").click(function(e){
		e.stopPropagation();
	});
});
$(document).click(function(){
	$(".login").fadeOut();
});

/* PRELOADER */
$(window).on('load', function () {
	 // executes when complete page is fully loaded, including all frames, objects and images
		$('#preloader').fadeOut('slow',function(){$(this).remove();});
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

var entra = false;
function controllo() {
	
	$('#form input').each(function(){
	    if(!$(this).val().trim()){
	        $(this).css({
	        	"border":"1px solid red"
	        })
	        entra=true;
	    }else{
	        $(this).css({
	        	"border":"1px solid white"
	    	})
	    }
	})
	if(entra)
	alert("Attenzione! Alcuni campi sono vuoti");
}

