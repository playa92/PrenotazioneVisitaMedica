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