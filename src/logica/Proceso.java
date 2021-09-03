package logica;

import java.awt.Color;

public class Proceso {
	
	public Proceso() {
	}
	
	public Proceso(int PID, String nombre, int tamano, Color color) {
		this.PID = PID;
		this.nombre = nombre;
		this.tamano = tamano;
		this.color = color;
	}

	private int PID = -1;
	private String nombre = "";
	private int tamano = 0;
	private Color color = new Color(86, 186, 7);
	
	//getter y setter
	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
}
