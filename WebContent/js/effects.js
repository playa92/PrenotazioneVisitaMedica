
//INPUT LOGIN COLOR
	$(function(){
        $('input:required,textarea:required').on('blur', function(){
           if($(this).val()!==''){  //assuming the form doesn't have some fields populated by default.
             $(this).addClass('green-border');
           } else {
             $(this).removeClass('green-border');
           }
        });
   });

//PRELOADER
	$(window).on("load", function () {
		
		// executes when complete page is fully loaded, including all frames, objects and images
		$("#preloader").fadeOut("slow",function() {
			$(this).remove();
		});
	});

//RESERVATION FORM
	function avviso() {
		alert("Attenzione! I dati inseriti verrano puliti");
	}

//HEX CODE GENERATING	
	function generate() {
		
		var rand = randHex(12);
	//	var resultValue = "http://api.qrserver.com/v1/create-qr-code/?data=" + rand;
	//    image.setAttribute("src", resultValue);
		document.getElementById("hex").value = rand;
	}

	function randHex(len) {
		
	  var maxlen = 8,
	      min = Math.pow(16,Math.min(len,maxlen)-1) 
	      max = Math.pow(16,Math.min(len,maxlen)) - 1,
	      n   = Math.floor( Math.random() * (max-min+1) ) + min,
	      r   = n.toString(16);
	  while(r.length < len) {  
		  r = r + randHex( len - maxlen );
	  }
	  return r.toUpperCase();
	}

//FORM INPUT DATA SEND TO SERVLET
	$(document).ready(function() {
		
	    frm.submit(function(e) {
	    	$.ajax({
	            type: frm.method('method'),
	            url: frm.action('action'), 
	            data: frm.serializable(), 
		        success: function(data, status) {
		            	//do something
		        }
	    	});
	        e.preventDefault();
	//        return false;  ??
	    	});
	});
