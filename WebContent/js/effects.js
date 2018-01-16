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

var s = "";
$(document).on('click', function(event){
  var index = $('.dropdown-content').length;
  var name = $(event.target).prop("tagName").toLowerCase(); //tag in cui avviene l'evento "a"
 
  if(!event.target.matches('.drop-element')) {
	  
	  	$(".dropdown-content").eq(index).html($('.dropdown-content').fadeOut());  //il 3 va calcolato
	  	s="";
  } else {
    $(name).each(function() { //cicla per ogni tag 'a' (migliorare)
 
	    	if($(this).siblings().attr('class') == 'dropdown-content'){ //entra solo nei div (fratelli di a) con "dropdown-content"		
	    		if($(event.target).siblings().attr("id") != $(this).siblings().attr('id')) {
	    	  		if($(event.target).siblings().attr("id") != s)
	    	  			$(".dropdown-content").fadeOut();
	        
	    	  		s = $(event.target).siblings().attr("id");
	      		}
	    		
	    	}
    	});
    
		var id = $(event.target).siblings().prop("id").toLowerCase(); //tag corrente il fratello
		$('#'+id).fadeToggle();
    }
});