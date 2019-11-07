document.addEventListener('DOMContentLoaded', init, false);

var nombreActorTXT;
var nacActorTXT;
var insertarActorBTN;
var insertSalida;
var containerActores;
	

function init(){
	
	nombreActorTXT = document.getElementById("nombreActorTXT");
	nacActorTXT = document.getElementById("nacActorTXT");
	insertarActorBTN = document.getElementById("insertarActorBTN");
	insertSalida = document.getElementById("insertSalida");
	containerActores = document.getElementById("containerActores");
	
	insertarActorBTN.addEventListener("click", ()=>{
			var actor = new Actor(nombreActorTXT.value,nacActorTXT.value);
			var json = actor.toJson();
			console.log(json);
			
			sendPost(json);
			
		}
	);
	
	listarActores();

}

function sendPost(json){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if( this.readyState == 4 && this.status == 200 ){
			//Todo bien
			insertSalida.innerHTML = this.responseText;
			listarActores();
		}
	}
	http.open("POST", "web/actor/insert");
	http.setRequestHeader("Content-Type","application/json");
	http.send(json);
	console.log(json);
}

function listarActores(){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if( this.readyState == 4 && this.status == 200 ){
			var actores = JSON.parse(this.responseText);
			console.log(actores);
		}
	}
	http.open("GET", "web/actor/get/all");
	http.send();
}









