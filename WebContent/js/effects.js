$(document).ready(function() {
	
	$('ol.nav li.dropdown').hover(
	function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
	}, function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
	})	
	
	$(this).find('ol.nav li.dropdown #navbar-text').click(
	function(){
		$(this).css('background-color', '#0066ff');
	}) 

	$(this).find('ol.nav li.dropdown #navbar-text').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})

//navbar login
	$(this).find('ul.nav li.dropdown a#login-form').click(
	function() {    
	   $(this).css('background-color', '#0066ff');
	})
 
	$(this).find('ul.nav li.dropdown a#login-form').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})

//login form
	$('#login-form').click(function() {
	  $('.login').fadeToggle("slow");
	})

});

$(window).on('load', function () {
	 // executes when complete page is fully loaded, including all frames, objects and images
		$('#preloader').fadeOut('slow',function(){$(this).remove();});
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

function controllo() {
	
	$('#form input').each(		
		function(index) {  			
			var input = $(this);
			var a = String(input.attr('type'));
				
			if(a == "text") {
				if(input.val() == "") {				
					alert("Attenzione! Alcuni campi sono vuoti"); 
					return false;
				}
			}		
	});
	return true;
}
