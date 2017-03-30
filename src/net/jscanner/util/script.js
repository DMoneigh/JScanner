function scan() {
	var file = document.getElementById("file");
	if (file.value !== "") {
		var map = getMap();
		var fr = new FileReader();
		fr.onload = function(e) {
			var base64 = e.target.result.split("base64,")[1];
			var array = new Array();
			array.push(base64);
			map["base64"] = array;
			sendData(map);
		};
		fr.readAsDataURL(file.files[0]);
	} else {
		alert("Select a file before scanning!");
	}
}

function sendData(map) {
	var data = JSON.stringify(map);
	var http = new XMLHttpRequest();
	http.onreadystatechange = function() {
		if (http.readyState === 4 && http.status === 200) {
			alert(http.responseText);
		}
	};
	http.open("POST", "scan", true);
	http.send(data);
}

function getMap() {
	var map = {};
	var classes = document.getElementsByName("class");
	for (var i = 0; i < classes.length; i++) {
		var clazz = classes[i];
		if (clazz.checked) {
			var methods = clazz.parentNode.getElementsByTagName("ul")[0].getElementsByTagName("input");
			var checked = new Array();
			for (var j = 0; j < methods.length; j++) {
				var method = methods[j];
				if (method.checked) {
					checked.push(method.value);
				}
			}
			map[clazz.value] = checked;
		}
	}
	return map;
}