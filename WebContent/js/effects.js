// dropmenu 
$(document).ready(function(){
	
	$('ol.nav li.dropdown').hover(
	function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
	}, function() {
  		$(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
	})	
	
});
    
$(document).ready(function(){
	  $(this).find('ol.nav li.dropdown #navbar-text').click(
	  function(){
	      $(this).css('background-color', '#0066ff');
	  }) 
});

$(document).ready(function(){

	  $(this).find('ol.nav li.dropdown #navbar-text').mouseleave(
	  function(){    
	      $(this).css('background-color', '#0099ff');
	  })
});

//login
$(document).ready(function(){
	    
		$('input[type="submit"]').mousedown(function(){
		  $(this).css('background', '#2ecc71');
		});
		$('input[type="submit"]').mouseup(function(){
		  $(this).css('background', '#1abc9c');
		});

		$('#loginform').click(function(){
		  $('.login').fadeToggle('slow');
		  $(this).toggleClass('green');
		})
			
		$(document).mouseup(function (e)
		{
		    var container = $(".login");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length === 0) // ... nor a descendant of the container
		    {
		        container.hide();
		        $('#loginform').removeClass('green');
		    }
		})
	
	});




