//SCRIVE SU FILE
function writeToFile(color) {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fh = fso.OpenTextFile("./navbar.txt", 8, false, 0);
    fh.WriteLine(color);
    fh.Close();
}

var submit = document.getElementById("submit");
submit.onclick = function () {
    var id      = document.getElementById("id").value;
    var content = document.getElementById("content").value;
    writeToFile(id, content);
}

//LEGGE DA FILE
function readFile() {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fh = fso.OpenTextFile("./navbar.txt", 1, false, 0);
    var lines = "";
    while (!fh.AtEndOfStream) {
        lines += fh.ReadLine() + "\r";
    }
    fh.Close();
    return lines;
}

var search = document.getElementById("search");
search.onclick = function() {
    var input   = document.getElementById("input").value;
    if (input != "") {
        var text    = readFile();
        var lines   = text.split("\r");
        lines.pop();
        var result;
        for (var i = 0; i < lines.length; i++) {
            if (lines[i].match(new RegExp(input))) {
                result = "Found: " + lines[i].split(",")[1];
            }
        }
        if (result) { alert(result); }
        else { alert(input + " not found!"); }
    }
}