document.addEventListener('DOMContentLoaded', init, false);


//Que significa var, let

var sumaa;
var sumab;
var botonsumar;
var resultado;


function init(){
	//Este es un comentario
	//alert('Hola desde Javascript');
	
	sumaa = document.getElementById("sumaa");
	sumab = document.getElementById("sumab");
	botonsumar = document.getElementById("botonsumar");
	resultado = document.getElementById("resultado");

	botonsumar.addEventListener('click', function(){
			let a = sumaa.value;
			let b = sumab.value;
			let intA = parseInt(a);
			let intB = parseInt(b);
			sumar(intA, intB);
		}
	);
	
}

function sumar(a,b){
	let suma = a+b;
	let mensaje = "El resulado es "+suma;
	resultado.innerHTML = resultado.innerHTML + "<p><button id='alfa'>"+ mensaje + "</button></p>";
}