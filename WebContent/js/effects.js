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

  var name = $(event.target).prop("tagName").toLowerCase();
  
  if(!event.target.matches('.dropbtn')) {
    
	  $(".dropdown-content").eq(2).html($('.dropdown-content').fadeOut());  
    s="";
    
  } else {
    $(name).each(function() {
    		if(event.target.id != $(this).attr('id') && $(this).attr('id') != undefined) {
        
    	  		if(event.target.id != s)
    	  			$(".dropdown-content").fadeOut();
        
    	  		s = event.target.id;
      		}
    	});
    
		var tag = $(event.target).prop("id").toLowerCase();
		var curr = $(name+'#'+tag).siblings().attr('id');
		$('#'+curr).fadeToggle();
    }
});