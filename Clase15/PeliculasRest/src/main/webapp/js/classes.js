class Actor{
	
	constructor(nombre, nacionalidad){
		this.id = 0;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		Object.seal();
	}
	
	toJson(){
		return JSON.stringify(this);
	}
	
	
	
}