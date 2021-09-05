package logica;

import java.awt.Color;

public class ProcesosDisponibles {
	
	public ProcesosDisponibles() {
		cargarProcesos();
	}
	
	private Proceso disponibles[] = new Proceso[11];

	/**
	 * Crea la lista de procesos diponibles para las pruebas
	 */
	private void cargarProcesos() {
		disponibles[0] = new Proceso(1, "VLC", 6144, new Color(235, 85, 0));
		disponibles[1] = new Proceso(2, "Chrome", 5120, new Color(234, 152, 91));
		disponibles[2] = new Proceso(3, "Word", 4096, new Color(91, 168, 234));
		disponibles[3] = new Proceso(4, "Excel", 3072, new Color(112, 193, 118));
		disponibles[4] = new Proceso(5, "Eclipse", 2048, new Color(143, 78, 215));
		disponibles[5] = new Proceso(6, "Spider", 1024, new Color(78, 218, 183));
		disponibles[6] = new Proceso(7, "PDFRead", 512, new Color(236, 158, 135));
		disponibles[7] = new Proceso(8, "Block", 256, new Color(234, 220, 161));
		disponibles[8] = new Proceso(9, "Calc", 128, new Color(215, 153, 84));
		disponibles[9] = new Proceso(10, "Hora", 64, new Color(209, 161, 234));
		disponibles[10] = new Proceso(11, "Fecha", 32, new Color(136, 184, 144));

	}
	
	public Proceso[] getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(Proceso[] disponibles) {
		this.disponibles = disponibles;
	}

}
