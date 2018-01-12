
/* NAVBAR LEFT ELEMENTS*/
$(document).ready(function() {
		
	$('ol.nav li.dropdown').click(
		function() {
			$(this).children('.dropdown-menu').slideToggle('fast'),
	        $(this).toggleClass("dropdown-active");
			$(this).find('#navbar-text').css('background-color', '#0066ff');
	});
	
	$("ol.nav li.dropdown").focusout(
		function() {
			$(this).children('.dropdown-menu').slideUp('fast');
			$(this).find('#navbar-text').css('background-color', '#0099ff');
	});
		
});

/* NAVBAR LOGIN */
$(document).ready(function() {
	
	$("#login-form").click(function(e){
		$(".login").show();
		e.stopPropagation();
		$(this).css('background-color', '#0066ff');
	});
	
	$(".login").click(function(e){
		e.stopPropagation();
	});
});
$(document).click(function(){ // fa parte del NAVBAR LOGIN ma deve stare cosi
	$(".login").hide();
	$(this).find('ul.nav li.dropdown a#login-form').css('background-color', '#0099ff');
});

/* PRELOADER */
$(window).on('load', function () {
	 // executes when complete page is fully loaded, including all frames, objects and images
		$('#preloader').fadeOut('slow',function(){$(this).remove();});
});

function pulisci() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

var v = 0; //visibilità file
function controllo() {
	$('#form input').each(		
		function(index) {  			
			var input = $(this);
			var a = String(input.attr('type'));
				
			if(a == "text") {		
				if(input.val() == "") {				
//					alert("before " + input.attr('style'));
//					input.attr({'style': 'border:1px solid red'});
//					alert("after " + input.attr('style'));
					alert("Attenzione! Alcuni campi sono vuoti");
//					return false;
				}
			}		
	});
	return true;
}
