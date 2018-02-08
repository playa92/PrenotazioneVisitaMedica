var qrcode = new QRCode("qrcode");

function makeCode() {      
    var elText = document.getElementById("text");
    
    if (!elText.value) {
        alert("Nessun codice hex");
        elText.focus();
        return;
    }
    qrcode.makeCode(elText.value);
}

//makeCode();

//$("#text").
//	on("blur", function () {
//		makeCode();
//	}).
//	on("keydown", function (e) {
//    if(e.keyCode == 12) {
//        makeCode();
//    }
//});