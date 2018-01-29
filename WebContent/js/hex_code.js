//HEX CODE GENERATING	
	var codes = localStorage.getItem("array");

	function generate() {
		var rand = randHex(12);
		
		if(codes == null) {
			codes = new Array();
		}
		while(codes.indexOf(rand) != -1) {
			rand = randHex(12);
		}
		codes.push(rand);
		localStorage.setItem("array", codes);
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
	
	