
$(document).ready(function(){
		
		var rand = randHex(12);					
		var image = document.getElementById('resultImage');
		var hexcode = document.getElementById('resultHex');

	    var resultValue = "http://api.qrserver.com/v1/create-qr-code/?data=" + rand;
	    image.setAttribute("src", resultValue);
	    hexcode.innerHTML = rand;
	    return false; 
				
		function  randHex(len) {
			
			  var maxlen = 8,
			      min = Math.pow(16,Math.min(len,maxlen)-1) 
			      max = Math.pow(16,Math.min(len,maxlen)) - 1,
			      n   = Math.floor( Math.random() * (max-min+1) ) + min,
			      r   = n.toString(16);
			  while ( r.length < len ) {  
				  r = r + randHex( len - maxlen );
			  }
			  return r;
		}

}) 