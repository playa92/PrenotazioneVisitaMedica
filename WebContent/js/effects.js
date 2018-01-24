

//PRELOADER
$(window).on("load", function () {
	// executes when complete page is fully loaded, including all frames, objects and images
	$("#preloader").fadeOut("slow",function() {
		$(this).remove();
	});
});

//RESERVATION FORM
function avviso() {
	alert("Attenzione! I dati inseriti verrano puliti");
}

//HEX CODE GENERATING	
function generate() {
	
	var rand = randHex(12);
//	var resultValue = "http://api.qrserver.com/v1/create-qr-code/?data=" + rand;
//    image.setAttribute("src", resultValue);
	document.getElementById("hex").value = rand;
}

function randHex(len) {
	
  var maxlen = 8,
      min = Math.pow(16,Math.min(len,maxlen)-1) 
      max = Math.pow(16,Math.min(len,maxlen)) - 1,
      n   = Math.floor( Math.random() * (max-min+1) ) + min,
      r   = n.toString(16);
  while(r.length < len) {  
	  r = r + randHex( len - maxlen );
  }
  return r.toUpperCase();
}

$(document).ready(function() {
	
    frm.submit(function(e) {
    	$.ajax({
            type: frm.method('method'),
            url: frm.action('action'), 
            data: frm.serializable(), 
	        success: function(data, status) {
	            	//do something
	        }
    	});
        e.preventDefault();
//        return false;
    	});
});

//SLIDESHOW
//var slideIndex = 1;
//showSlides(slideIndex);
//
//function plusSlides(n) {
//  showSlides(slideIndex += n);
//}
//
//function currentSlide(n) {
//  showSlides(slideIndex = n);
//}
//
//function showSlides(n) {
//  var i;
//  var slides = document.getElementsByClassName("mySlides");
//  var dots = document.getElementsByClassName("dot");
//  if (n > slides.length) {slideIndex = 1}    
//  if (n < 1) {slideIndex = slides.length}
//  for (i = 0; i < slides.length; i++) {
//      slides[i].style.display = "none";  
//  }
//  for (i = 0; i < dots.length; i++) {
//      dots[i].className = dots[i].className.replace(" active", "");
//  }
//  slides[slideIndex-1].style.display = "block";  
//  dots[slideIndex-1].className += " active";
//}

//COUNTDOWN
//  var count = 0;
//  var counter = null;
//
//  window.onload = function() {
//    initCounter();
//  };
//
//function initCounter() {
//  // get count from localStorage, or set to initial value of 1000
//  count = getLocalStorage('count') || 20;
//  counter = setInterval(timer, 1000); //1000 will  run it every 1 second
//}
//
//function setLocalStorage(key, val) {
//  if (window.localStorage) {
//    window.localStorage.setItem(key, val);
//  }
//
//  return val;
//}
//
//function getLocalStorage(key) {
//  return window.localStorage ? window.localStorage.getItem(key) : '';
//}
//
//function timer() {
//  count = setLocalStorage('count', count - 1);
//  if (count <= -1) {
//    clearInterval(counter);
//	  localStorage.removeItem("count");
//    return;
//  }
//
//  var seconds = count % 60;
//  var minutes = Math.floor(count / 60);
//  var hours = Math.floor(minutes / 60);
//  minutes %= 60;
//  hours %= 60;
//
//  document.getElementById("timer").innerHTML = minutes +  " : "   + seconds;
//  
//}