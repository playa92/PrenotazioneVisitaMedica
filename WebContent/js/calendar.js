var date = new Date();
var day = date.getDate();
var year = date.getFullYear();

var month = date.getMonth();
var monthLabels = ["Gennaio", "Febbraio",  "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"];

var weekDay = date.getDay();
var weekDayLabels = ["Domenica", "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato"];

//create function to grab day, month, date, year
function Day() {
	month = monthLabels[month];
	weekDay = weekDayLabels[weekDay];
	  
	document.getElementById("day").innerHTML = "Oggi" + "<span>" + " è " + "<span>" + weekDay.toUpperCase() + "</span>";
	document.getElementById("date").innerHTML = month.toUpperCase() + " " + "<span>" + day + "</span>" + year;  
}
Day();