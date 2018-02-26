$(document).ready(function() {
	    
		$("#search").click(function() {
			
			if($("#info:visible")) {
				$("#info").hide();
			}
			
			if($("#input").val() == "") {
				$("#error").show();
				$("#error").text("Campo vuoto");
			}
			else {	
				$("#fountainG").show();
				setTimeout(function() {
				   search();  
				}, 2000);
			}
		})
		
		$("#input").focus(function() {
		    $("#error").hide();
		});
});


function search() {

	  $.ajax({
		  type:"post",
          url:"../cercaPrenotazione",  
          data:{hexcode : $("#searchInput").val().toUpperCase()},
		  success:function(data) {
			var a = data.split(";");

			if(a[0] == "true") {
				$("#orario").text(a[1]);
				orario_visita = a[1];
				orario_inizio_countdown = a[2];
				$("#hex").text(a[3]);
				$("#text").val(a[3]);
				makeCode();
								
				start();
				scrollingAndShow();
					
			} else {
				$("#fountainG").hide();
				$("#err").modal("show");
				$("#message").text(a[1]);
			}
		  },
	  });
}

//questi dati devono essere inizializzati con ajax
var orario_visita = null;
var orario_inizio_countdown = null;

function start() {		
	
//	var orario_corrente = moment(moment()).format("HH:mm:ss");
	
//	if(orario_corrente >= orario_inizio_countdown && orario_corrente <= orario_visita){ 
		countDown();
//	} else {
//		 document.getElementById('countdown').innerHTML = "non disponibile"
//	     $("#countdown").css({color:"red"});
//		//TODO se timer attivo disattivare
//	}
}

function countDown() {
	
	var ms = moment(orario_visita,"HH:mm:ss").diff(moment(moment(),"HH:mm:ss"));
	var d = moment.duration(ms);
	var s = moment.utc(ms).format("mm:ss");

    document.getElementById('countdown').innerHTML = s;
    t = setTimeout(function () {
        countDown()
    }, 1000);
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function scrollingAndShow() {
	
	setTimeout(function() {
	    $("html, body").animate({ scrollTop: 800}, 1200);
		  $("#fountainG").hide();		
		  
		  setTimeout(function() {  
			  $("#info").show();
		  }, 400);
	}, 2000);
}
