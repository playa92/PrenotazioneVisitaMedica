let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
		scanner.addListener('scan', function (content) {
		validate(content);
});
		
function start() {
   
   Instascan.Camera.getCameras().then(function (cameras) {
     if (cameras.length > 0) {
       scanner.start(cameras[0]);
     } else {
       console.error('No cameras found.');
     }
   }).catch(function (e) {
     console.error(e);
   });
}

function stop() {
	scanner.stop();
}