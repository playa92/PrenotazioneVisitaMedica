function login() {
	
	if($("#inp").attr("value") == "Login") {
		$("#form-field").attr("method","POST");
		$("#form-field").attr("action","login");
	}
	else {
		$("#form-field").attr("method","GET");
		$("#form-field").attr("action","login?logout=true");
	}
}