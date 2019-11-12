document.addEventListener( "DOMContentLoaded", init , false );

var socket;
var messageBox;
var messageBtn;
var messageContainer;

function init(){

	messageBox = document.getElementById("messageBox");
	messageBtn = document.getElementById("messageBtn");
	messageContainer = document.getElementById("messageContainer");
	
	socket = new WebSocket("ws://localhost:8080/WebSocketsEjemplo/server");
	
	socket.onopen = function(){
		socket.send("Hola");
	};
	
	socket.onmessage = function( event ){
		let recibido = event.data.toString();
		messageContainer.innerHTML += "<p>" + recibido + "</p>"; 
	};
	
	socket.onclose = function(){
		
	};
	
	messageBtn.addEventListener("click", ()=>{
		let msg = messageBox.value;
		messageBox.value = "";
		socket.send(msg);
	});
	
	window.onbeforeunload = function(){
		   socket.close();
		};
	
}