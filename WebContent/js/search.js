function search() {
	  $.ajax({
		  type:"post",
          url:"ricerca",  
          data:{hexcode : $("#input").val()},
		  success:function(data) {

			var a = data.split(";");
			
			if(a[0] == "true") {
				$("#countdown").text("Tempo rimasto: " + a[1]);
				$("#countdown").css({color:"black"});
				init();
			} else {
				$("#countdown").text(a[1]);
				$("#countdown").css({color:"red"});
			}
		  },
		  error:function(data){
			  alert("Errore");
		  }
	  });
}


var result = 0;

	function init(){
		result = document.getElementById('countdown').innerHTML;
		currentTime();
	}
	
// 	TEMPO CORRENTE
	function currentTime() {
		
		var then =  result;
		
		var ms = moment(then,"HH:mm:ss").diff(moment(moment(),"HH:mm:ss"));
		var d = moment.duration(ms);
		var s = Math.floor(d.asHours()) + moment.utc(ms).format(":mm:ss");

	    document.getElementById('countdown').innerHTML = s;
	    t = setTimeout(function () {
	        currentTime()
	    }, 1000);
	}
	
	function checkTime(i) {
	    if (i < 10) {
	        i = "0" + i;
	    }
	    return i;
	}
