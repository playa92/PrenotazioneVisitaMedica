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
	
//	BUBBLE
    var $element = $('#bubble');
    var phrases = [
        'Prenotati subito!',
        'A cosa aspetti?',
        'Affrettati e assicurati la tua prenotazione...',
        '...in modo semplice e veloce.'
    ];
    var index = -1;
    (function loopAnimation() {
        index = (index + 1) % phrases.length;
        bubbleText({
            element: $element,
            newText: phrases[index],
            letterSpeed: 70,
            callback: function() {
                setTimeout(loopAnimation, 1200);
            },
        });
    })();

    //REMEMBER TUTORIAL
    if(localStorage.chkbox2 && localStorage.chkbox2 != '') {
        $('#rememberTutorial').attr('tutorial', 'checked');
    	$("#tutorialDialog").modal("show");
    } else {
        $('#rememberTutorial').removeAttr('tutorial');
    }

    $('#rememberTutorial').click(function() {
        if ($('#rememberTutorial').is(':checked')) {
            localStorage.chkbox2 = $('#rememberTutorial').val();
        } else {
            localStorage.chkbox2 = '';
        }
    });
   
});

// RESET COOKIES
function successSetting(s){
 	
		if(s == "cookies"){
	 		 $('#rememberTutorial').attr('tutorial', 'checked');
	 		 localStorage.chkbox2 = $('#rememberTutorial').val('checked');
	 		 $('#rememberTutorial').prop('checked', true);
		}
 		  // ANIMATION
 		  $(".check_mark").show();	
	 	  $(".sa-success").addClass("hide");
	 	  setTimeout(function() {
	 	    $(".sa-success").removeClass("hide");
	 	  }, 10);
}

//when any modal is closing
$('.modal').on('hide.bs.modal', function (e) {
	
	if(e.target.id == "settings"){
		 $(".check_mark").hide();	
	}
});


