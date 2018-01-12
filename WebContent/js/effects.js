
/* NAVBAR LEFT ELEMENTS*/
$(document).ready(function() {
	
	$("ol.nav li.dropdown").focusout(
	function() {
		$(this).children('.dropdown-menu').slideUp('fast');
	});
	
	$('ol.nav li.dropdown').click(
	function() {
		$(this).children('.dropdown-menu').slideToggle('fast'),
        $(this).toggleClass("dropdown-active");
	});
	
	$(this).find('ol.nav li.dropdown #navbar-text').click(
	function(){
		$(this).css('background-color', '#0066ff');
	}) 

	$(this).find('ol.nav li.dropdown #navbar-text').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})
	
});

/* NAVBAR LOGIN */
$(document).ready(function() {
	
	$(this).find('ul.nav li.dropdown a#login-form').click(
	function() {    
	   $(this).css('background-color', '#0066ff');
	})
	
	$(this).find('ul.nav li.dropdown a#login-form').mouseleave(
	function() {    
	   $(this).css('background-color', '#0099ff');
	})
	
	$("#login-form").click(function(e){
		$(".login").show();
		e.stopPropagation();
	});
	
	$(".login").click(function(e){
		e.stopPropagation();
	});
});
$(document).click(function(){ // fa parte del NAVBAR LOGIN ma deve stare cosi
	$(".login").hide();
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
