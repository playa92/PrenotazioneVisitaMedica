function search() {
	  $.ajax({
		  type:"post",
          url:"ricerca",  
          data:{hexcode : $("#input").val()},
		  success:function(data) {
// 			alert("success");
			var a = data.split(";");
			
			if(a[0] == "true") {
				$("#countdown").text(a[1]);
			} else {
//				$("#countdown").css({"style":"color:red"});
				$("#countdown").text(a[1]);
			}
		  },
		  error:function(data){
// 			alert("error");
		  }
	  });
}