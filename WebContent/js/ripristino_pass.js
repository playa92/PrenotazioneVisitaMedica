function restore() {
	
	$.ajax({
		type:"post",
		url:"../ripristinoPassword",
		data:{
			username:$("input[name=username]").prop("value"),
			newPassword:$("input[name=newPassword]").prop("value")
		}, success:function(data) {
			
			$("#notice").modal('show');
			$("#message").text(data);
		}
	})
}