package logica;

import java.awt.Color;

public class Paginacion {
	
	public Paginacion() {
		dividirMemoria();
	}
	
	private int memoriaPpal = 16384;
	private int memTotalLibre = 0;
	private int tamanoMarco = 256;
	private Proceso SO = new Proceso(0, "S.O.", 2048, new Color(215, 215, 84));
	private Particion particiones[] = new Particion[((memoriaPpal-SO.getTamano())/tamanoMarco)+1];
	
	//Metodo que divide la memoria en particion del S.O. y las memoria libre
	public void dividirMemoria() {
			
		//Definicion del tamaño de la particion del S.0.
		particiones[0] = new Particion(0, false, SO.getTamano(), SO, 0);
		
		//Creacion de particiones disponibles, en un arreglo
		for(int i=1; i<particiones.length; i++) {
			particiones[i]= new Particion(i, true, tamanoMarco, null, 
										  particiones[i-1].getInicio()+particiones[i-1].getTamano());
		}
		
		imprimir();
		/*Proceso proc1 = new Proceso(1, "Prueba", 980, new Color(215, 153, 84));
		añadirProceso(proc1, 3);
		System.out.println("---");
		imprimir();
		*/
		/*eliminarProceso(1);
		System.out.println("---");
		imprimir();*/
		
	}

	public void imprimir() {
		System.out.println("///////////////");
		System.out.println("Tamaño: " + particiones.length);
		for(int i=0; i<particiones.length; i++) {
			System.out.print("i: " + i + " | ");
			System.out.print("ID: " + particiones[i].getId());
			System.out.print(" | Disp: " + particiones[i].getDisponible());
			if(particiones[i].getDisponible()==false) {
				System.out.print(" | PID: " + particiones[i].getProceso().getPID());
				System.out.print(" | Proc: " + particiones[i].getProceso().getNombre());
			}
			System.out.print(" | Tam: " + particiones[i].getTamano());
			System.out.print(" | Ini: " + particiones[i].getInicio());
			System.out.println();
		}
		System.out.println("FINN FOR");
	}

	
	public int getMemoriaPpal() {
		return memoriaPpal;
	}

	public void setMemoriaPpal(int memoriaPpal) {
		this.memoriaPpal = memoriaPpal;
	}

	public int getMemTotalLibre() {
		return memTotalLibre;
	}

	public void setMemTotalLibre(int memTotalLibre) {
		this.memTotalLibre = memTotalLibre;
	}

	public int getTamanoMarco() {
		return tamanoMarco;
	}

	public void setTamanoMarco(int tamanoMarco) {
		this.tamanoMarco = tamanoMarco;
	}

	public Proceso getSO() {
		return SO;
	}

	public void setSO(Proceso sO) {
		SO = sO;
	}

	public Particion[] getParticiones() {
		return particiones;
	}

	public void setParticiones(Particion[] particiones) {
		this.particiones = particiones;
	}
}
