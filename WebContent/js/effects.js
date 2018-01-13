
///* NAVBAR LEFT ELEMENTS*/
//$(document).ready(function() {
//		
//	$('ol.nav li.dropdown').click(
//		function(e) {
//			$(this).children('.dropdown-menu').slideToggle('fast'),
//			e.stopPropagation();
//			$(this).find('#navbar-text').css('background-color', '#0066ff');
//	});
//	
//	$("ol.nav li.dropdown ul.dropdown-menu").focusout(
//		function(e) {
//			$(this).children('.dropdown-menu').slideUp('fast');
//			$(this).find('#navbar-text').css('background-color', '#0099ff');
//	});
//		
//});

///* NAVBAR LOGIN */
//$(document).ready(function() {
//	
//	$("#login-form").click(function(e){
//		$(".login").fadeToggle();
//		e.stopPropagation();
//		$(this).css('background-color', '#0066ff');
//	});
//	
//	$(".login").click(function(e){
//		e.stopPropagation();
//	});
//});
//$(document).click(function(){
//	$(".login").fadeOut();
//	$(this).find('ul.nav li.dropdown a#login-form').css('background-color', '#0099ff');
//});

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
