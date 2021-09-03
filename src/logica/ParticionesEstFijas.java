package logica;

import java.awt.Color;

public class ParticionesEstFijas {
	
	//Constructor por defecto
	public ParticionesEstFijas() {
		dividirMemoria();
	}
	
	//Definicion de tamaño de la memoria, el sistema operativo
	//y de las particiones
	private int memoriaPpal = 16384;
	private Proceso SO = new Proceso(0, "S.O", 2048, new Color(215, 215, 84));
	private int tamanoParticion = 1024;
	private Particion particiones[] = new Particion[((memoriaPpal-SO.getTamano())/tamanoParticion)+1];
	
	//Metodo que divide la memoria en partes iguales sun el tamaño mas la particion del S.O.
	public void dividirMemoria() {
			
		//Definicion del tamaño de la particion del S.0.
		particiones[0] = new Particion(0, false, SO.getTamano(), SO, 0);
		
		//Creacion de particiones disponibles, en un arreglo
		for(int i=1; i<particiones.length; i++) {
			particiones[i]= new Particion(i, true, tamanoParticion, null, 
										  particiones[i-1].getInicio()+particiones[i-1].getTamano());
		}
				
		imprimir();
		
		
		Proceso proc1 = new Proceso(1, "Prueba", 980, new Color(215, 215, 84));
		añadirProceso(proc1, 3);
		System.out.println("---");
		imprimir();
		
		eliminarProceso(1);
		System.out.println("---");
		imprimir();
		
	}
	
	/**
	 * Añade los procesos a matriz memoria:
	 * @param proceso
	 * @param metodo
	 *  1-Primer ajuste | 2-Mejor ajuste | 3-Peor Ajuste
	 */
	public boolean añadirProceso(Proceso proceso, int metodo) {
		
		int posicion = 0;
		
		//Primer ajuste: Asignar el primer fragmento libre que tenga el tamaño suficiente.
		if(metodo==1){									
			for(int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true & particiones[i].getTamano() >= proceso.getTamano() ) {
					posicion = i;
					break;
				}
			}
			if(posicion != 0) {
				particiones[posicion].setProceso(proceso);
				particiones[posicion].setDisponible(false);
				return true;
			}else {
				return false;
			}
		}
		
		//Mejor ajuste: Asignar el fragmento más pequeño que tenga el tamaño suficiente.
		if(metodo==2){									
			for(int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true & particiones[i].getTamano() >= proceso.getTamano() ) {
					if(posicion != 0) {
						if(particiones[posicion].getTamano() > particiones[i].getTamano())
							posicion = i;
					}else {
						posicion = i;
					}
					
				}
			}
			if(posicion != 0) {
				particiones[posicion].setProceso(proceso);
				particiones[posicion].setDisponible(false);
				return true;
			}else {
				return false;
			}
		}		
		
		//Peor ajuste: Asignar el fragmento más grande.
		if(metodo==3){									
			for(int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true & particiones[i].getTamano() >= proceso.getTamano() ) {
					if(posicion != 0) {
						if(particiones[posicion].getTamano() < particiones[i].getTamano())
							posicion = i;
					}else {
						posicion = i;
					}
					
				}
			}
			if(posicion != 0) {
				particiones[posicion].setProceso(proceso);
				particiones[posicion].setDisponible(false);
				return true;
			}else {
				return false;
			}
		}	
		
		return false;
		
	}
	
	public boolean eliminarProceso(int PID) {
		for(int i=1; i<particiones.length; i++) {
			if(particiones[i].getProceso().getPID() == PID ) {
				particiones[i].setDisponible(true);
				particiones[i].setProceso(null);
				break;
			}
		}
		return false;	
	}
	
	public void imprimir() {
		for(int i=0; i<particiones.length; i++) {
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
	}
	
	//getter y setter
	public int getMemoriaPpal() {
		return memoriaPpal;
	}

	public void setMemoriaPpal(int memoriaPpal) {
		this.memoriaPpal = memoriaPpal;
	}

	public Proceso getSO() {
		return SO;
	}

	public void setSO(Proceso SO) {
		this.SO = SO;
	}

	public int getTamanoParticion() {
		return tamanoParticion;
	}

	public void setTamanoParticion(int tamanoParticion) {
		this.tamanoParticion = tamanoParticion;
	}

	public Particion[] getParticiones() {
		return particiones;
	}

	public void setParticiones(Particion[] particiones) {
		this.particiones = particiones;
	}
	
}
