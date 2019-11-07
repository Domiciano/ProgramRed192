document.addEventListener('DOMContentLoaded', init, false);

var altura;
var peso;
var resultado;
var consola;
var postreq;
var postresult;

function init(){
	alert("Hola mundo!");
	
	altura = document.getElementById("altura");
	peso = document.getElementById("peso");
	resultado = document.getElementById("resultado");
	consola = document.getElementById("consola");
	postreq = document.getElementById("postreq");
	postresult = document.getElementById("postresult");
	
	resultado.addEventListener("click", calcularIMC);
	postreq.addEventListener("click", sendPOST);
	
}

function calcularIMC(){
	var imc = parseFloat(peso.value)/(Math.pow(parseFloat(altura.value),2));
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	consola.innerHTML = consola.innerHTML
	    	+"<p>Su IMC es: "+imc+" .Caculado a las: "+this.responseText+"</p>";
	    }
	  };
	  xhttp.open("GET", "web/date/complete");
	  xhttp.send();
}

function sendPOST(){
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	postresult.innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("POST", "web/first/data");
	  xhttp.setRequestHeader("Content-Type", "application/json");
	  xhttp.send("{\"clave\":\"valor\"}");
}
