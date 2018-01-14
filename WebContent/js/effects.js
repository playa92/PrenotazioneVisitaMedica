/* NAVBAR LEFT ELEMENTS*/
$(document).ready(function() {
		
	$('ul.nav li.dropdown').click(
		function() {
			$(this).children('.dropdown-menu').slideToggle('fast');
			 $(this).toggleClass("dropdown-active");
	});
	
	$("ul.nav li.dropdown").focusout(
		function() {
			$(this).children('.dropdown-menu').slideUp('fast');		
	});
		
});
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
$(window).on("load", function () {
	// executes when complete page is fully loaded, including all frames, objects and images
	$("#preloader").fadeOut("slow",function(){$(this).remove();});
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

function controllo() {
	
	var entra = false;
	$("#form input").each(function() {
	    if(!$(this).val().trim()) {
	        
	    	if($(this).attr("name") != "matricola") {
	    		$(this).css({"border":"1px solid red"});
	        	entra=true;
	    	}
	    }
	});
	
	if(entra)
		alert("Attenzione! Alcuni campi sono vuoti");
}

$(document).ready(function() {
    $("#form").click(function(e) {
        if(e.target.style.border == "1px solid red") {
        	$("#form input").css({"border":"1px solid gray"});
        }
    });
});