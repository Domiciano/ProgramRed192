package cliente.model;

public class Mensaje {
	
	private String mensaje;
	private String fecha;
	
	public Mensaje() {}
	
	public Mensaje(String mensaje, String fecha) {
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
