function search() {
	  $.ajax({
		  type:"post",
          url:"ricerca",  
          data:{hexcode : $("#input").val()},
		  success:function(data) {
			var a = data.split(";");
			
			if(a[0] == "true") {
				$("#orario").text(a[1]);
//				$("#countdown").text(a[2]);
				orario_visita = a[1];
				orario_inizio_countdown = a[2];
				start();
			} else {
				$("#orario").text(a[1]);
				$("#orario").css({color:"red"});
			}
		  },
		  error:function(data){
			  alert("Errore");
		  }
	  });
}

//questi dati devono essere inizializzati con ajax
	var orario_visita = null;
	var orario_inizio_countdown = null;

	function start(){		
		
		var orario_corrente = moment(moment()).format("hh:mm:ss");
		if(orario_corrente >= orario_inizio_countdown && orario_corrente <= orario_visita){ 
			countDown();
			alert("si")
		}else{
			alert("no")
			
			//TODO se timer attivo disattivare
		}
	}
	
	function countDown() {
		
		var ms = moment(orario_visita,"HH:mm:ss").diff(moment(moment(),"HH:mm:ss"));
		var d = moment.duration(ms);
		var s = moment.utc(ms).format("mm:ss");

	    document.getElementById('countdown').innerHTML = s;
	    t = setTimeout(function () {
	        countDown()
	    }, 500);
	}
	
	function checkTime(i) {
	    if (i < 10) {
	        i = "0" + i;
	    }
	    return i;
	}
