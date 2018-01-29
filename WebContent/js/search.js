function search(){
	  $.ajax({
		  type: 'post',
          url: 'ricerca',  
          data: {
				hex : $("#input_h").val()
		  },
		  success: function(data) {
// 					  alert("success");
		       $('#countdown').text(data);
		  },
		  error: function(data){
// 					  alert("error");
		  }
	  });
}