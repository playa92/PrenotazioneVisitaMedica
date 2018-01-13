/* NAVBAR LEFT ELEMENTS*/
var click_l = 0, click_r = 0; //visiblità file
$(document).ready(function() {
		
	$('ol.nav li.dropdown').click(
		function(e) {
			$(this).children('.dropdown-menu').slideToggle('fast'),
	        $(this).toggleClass("dropdown-active");
			
			//TODO GIOVANNI: bisogna semplificarlo
			if(e.target.id == 'slide-navbar-l') {
				
				if(click_l == 0) {
					click_l = 1;
					$(this).find('#slide-navbar-l').css('background-color', '#0066ff');
				}
				else {
					click_l = 0;
					$(this).find('#slide-navbar-l').css('background-color', '#0099ff');
				}
				return;
			}
			
			if(e.target.id == 'slide-navbar-r') {
				
				if(click_r == 0) {
					click_r = 1;
					$(this).find('#slide-navbar-r').css('background-color', '#0066ff');
				}
				else {
					click_r = 0;
					$(this).find('#slide-navbar-r').css('background-color', '#0099ff');
				}
				return;
			}
			$(this).find('#navbar-text').css('background-color', '#0066ff');
			///////////////////////////////////////////////////////////////
	});
	
	$("ol.nav li.dropdown").focusout(
		function() {
			$(this).children('.dropdown-menu').slideUp('fast');
			$(this).children('.dropdown-menu').stop(true, true);
			$(this).find('#navbar-text').css('background-color', '#0099ff');
			$(this).find('#slide-navbar-l').css('background-color', '#0099ff');
			$(this).find('#slide-navbar-r').css('background-color', '#0099ff');
			
			/* resetto i valori per gli eventi successivi */
			click_l = 0;
			click_r = 0;
	});
		
});

/* NAVBAR LOGIN */
$(document).ready(function() {
	
	$("#login-form").click(function(e) {
		$(".login").show();
		e.stopPropagation();
		$(this).css('background-color', '#0066ff');
	});
	
	$(".login").click(function(e) {
		e.stopPropagation();
	});
});

$(document).click(function() { // fa parte del NAVBAR LOGIN ma deve stare cosi
	$(".login").hide();
	$(this).find('ul.nav li.dropdown a#login-form').css('background-color', '#0099ff');
});

/* PRELOADER */
$(window).on('load', function() {
	 // executes when complete page is fully loaded, including all frames, objects and images
		$('#preloader').fadeOut('slow',function(){$(this).remove();});
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

var entra = false;
function controllo() {
	
	$('#form input').each(		
		function(index) {  			
			var input = $(this);
			var a = String(input.attr('type'));
				
			if(a == "text") {		
				if(input.val() == "") {				
					//TODO GIOVANNI: al momento funziona solo con FIREFOX
					entra = true;
					input.attr({'style': 'border:1px solid red'});
				}
			}		
	});
	
	if(entra) 
		alert("Attenzione! Alcuni campi sono vuoti");
}
