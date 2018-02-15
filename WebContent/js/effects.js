////PRELOADER
//$(window).on("load", function() {
//	// executes when complete page is fully loaded, including all frames, objects and images
//	$("#preloader").fadeOut("slow",function() {
//		$(this).remove();
//	});
//});

//INPUT LOGIN COLOR
$(function() {
    $('input:required,textarea:required').on('blur', function() {
       if($(this).val()!=='') {  //assuming the form doesn't have some fields populated by default.
         $(this).addClass('green-border');
       } else {
         $(this).removeClass('green-border');
       }
   });
});

$(document).ready(function() {
	$("#cambiaColore").click(function() {
	    $("#pannelloColori").toggle();
	});
});

function changeColor(c) {	
	 $(".navbar").css("background-color",c); 
	 $(".nav-item #navbar-text").css("background-color",c); 
}
	


