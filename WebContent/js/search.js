
$(document).ready(function () {
	    
		$("#search").click(function(){
			
			if($("#input").val() == ""){
				$("#error").text("Codice non valido");
			}
			else {	
				$("#fountainG").show();
				setTimeout(function(){
				   search(); 
				}, 2000);
			}
		})
		
		 $(window).scroll(function () {
	        if ($(this).scrollTop() > 200) {
	            $('.scrollup').fadeIn();
	        } else {
	            $('.scrollup').fadeOut();
	        }
	    });

	    $('.scrollup').click(function () {
	        $("html, body").animate({
	            scrollTop: 0
	        }, 600);
	        return false;
	    });
		
// 		$("#input").click(function(){
// 			if($("#input")is(":empty"))){
// 				$("#error").hide();
// 			}
// 		})

	});


function search() {
		
	  $.ajax({
		  type:"post",
          url:"ricercaPrenotazione",  
          data:{hexcode : $("#input").val()},
		  success:function(data) {
			var a = data.split(";");
			
			if(a[0] == "true") {
				$("#orario").text(a[1]);
				orario_visita = a[1];
				orario_inizio_countdown = a[2];
				start();
				scrollingAndShow();
//				alert("true")
					
			} else {
				$("#error").text(a[1]);
				$("#fountainG").hide();
//				alert("false")
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
		
		var orario_corrente = moment(moment()).format("HH:mm:ss");
		
		if(orario_corrente >= orario_inizio_countdown && orario_corrente <= orario_visita){ 
			countDown();
//			alert("si")
		}else{
//			alert("no")
			 document.getElementById('countdown').innerHTML = "non disponibile"
		     $("#countdown").css({color:"red"});
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
	    }, 1000);
	}
	
	function checkTime(i) {
	    if (i < 10) {
	        i = "0" + i;
	    }
	    return i;
	}
	
	//viene chiamato da search.js
	function scrollingAndShow(){
		
		setTimeout(function(){
		    $("html, body").animate({ scrollTop: 800}, 1200);
			  $("#fountainG").remove();		
			  
			  setTimeout(function(){  
				  $("#info").show();
			  }, 400);
			 
			}, 2000);
	}