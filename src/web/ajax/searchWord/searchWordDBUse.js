var xhr = null;

	function getXHR() {
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else { // ie6 이하 
			xhr = new ActiveXObject('Microsoft.XMLHTTP');
		}
	}

	function startMethod() {
		var word = document.getElementById('word');
		if(word.value.length > 0){
			var param = '?word=' + word.value;
		
			getXHR();
			xhr.open('get', 'searchWordDB.jsp' + param, true);
			xhr.onreadystatechange = callback;
			xhr.send(null);
		} else {
			document.getElementById('popupPart').style.display = "none";
		}
	}

	function callback() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = document.getElementById('popupPart');
			
			var json = eval("(" + xhr.responseText + ")"); // 문자열을 json객체로 바꿔줌
			
			var msg = "";
			if(json == null){
				msg = "No Data";
			} else {		
				for (var i = 0; i < json.length; i++) {
					msg += json[i] + "<br>";
				}
			}
			result.innerHTML = msg;
			result.style.display = "block";
		}
	}
