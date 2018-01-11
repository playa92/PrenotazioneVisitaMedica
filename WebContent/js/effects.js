$(document).ready(function() {
	
	$('ol.nav li.dropdown').hover(
	function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
	}, function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
	})	
});

$(document).ready(function() {
	
	$(this).find('ol.nav li.dropdown #navbar-text').click(
	function(){
		$(this).css('background-color', '#0066ff');
	}) 
});

$(document).ready(function() {

	$(this).find('ol.nav li.dropdown #navbar-text').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})
});

// questi riguardano solo la navbar del login
$(document).ready(function() {

	$(this).find('ul.nav li.dropdown a#login-form').click(
	function() {    
	   $(this).css('background-color', '#0066ff');
	})
});

$(document).ready(function() {
 
	$(this).find('ul.nav li.dropdown a#login-form').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

function controllo() {
	
	var valori = ["nome", "cognome", "matricola", "handicap"];
	
	for(var i = 0; i < 4; i++) {
		
		var v = document.forms["form"][valori[i]].value;
	
		if(v == "") {  
			
			alert("Attenzione! Alcuni campi sono vuoti");  
			return false;  
		}
	}
	return true;
} 

//login form
$(document).ready(function() {

	$('#login-form').click(function() {
	  $('.login').fadeToggle('slow');
	  $(this).toggleClass('green');
	})
		
	$(document).mouseup(function (e)
	{
	    var container = $(".login");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... not a descendant of the container
	    {
	        container.hide();
	        $('#login-form').removeClass('green');
	    }
	})
});

//loader
$(document).ready(function(){
	$("#stop").delay(5000).fadeOut(300); 
});
