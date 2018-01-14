/* NAVBAR */
$(document).on('click', function(event) {
    if (event.target.id == "login-form") {
      $(".login").fadeToggle();
    }else{
      $(".login").fadeOut();
    }  
    
    if (event.target.id == "navbar-text") {
      $(this).fadeIn();
    }else {
      $(this).fadeOut();
    }  
});

$(document).on('click', '#navbar-text', function(event) {
    
    //mettere if solo se è aperto il form
    $(".login").fadeOut(); // si deve chiudere il login se clicco su un navbar-text
  
    if (event.target.id == "navbar-text") {  
      $(this).siblings('.dropdown-menu').fadeToggle('fast');
    }
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