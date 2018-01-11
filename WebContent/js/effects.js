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

// questi riguardano solo la navbar del login
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
	  $('.login').fadeToggle('slow');
	})

//loader
	$("#show").hide(); 
	$("#stop").delay(1000).fadeOut(300); 
	$("#show").delay(1001).fadeIn(300);
	
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
