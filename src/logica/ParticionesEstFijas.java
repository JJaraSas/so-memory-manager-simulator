package logica;

import java.awt.Color;

public class ParticionesEstFijas {
	
	//Constructor por defecto
	public ParticionesEstFijas(int asignacion) {
		dividirMemoria();
	}
	
	//Contador que permite asignar PID a cada proceso
	int contadorPID = 1;
	//Metodo de asignacion
	int asignacion = 0;
	
	//Definicion de tama�o de la memoria, el sistema operativo
	//y de las particiones
	private int memoriaPpal = 16384;
	private Proceso SO = new Proceso(0, "S.O.", 2048, new Color(215, 215, 84));
	private int tamanoParticion = 1024;
	private Particion particiones[] = new Particion[((memoriaPpal-SO.getTamano())/tamanoParticion)+1];
	
	//Metodo que divide la memoria en partes iguales sun el tama�o mas la particion del S.O.
	public void dividirMemoria() {
			
		//Definicion del tama�o de la particion del S.0.
		particiones[0] = new Particion(0, false, SO.getTamano(), SO, 0);
		
		//Creacion de particiones disponibles, en un arreglo
		for(int i=1; i<particiones.length; i++) {
			particiones[i]= new Particion(i, true, tamanoParticion, null, 
										  particiones[i-1].getInicio()+particiones[i-1].getTamano());
		}
		
	}
	
	/**
	 * A�ade los procesos a matriz memoria:
	 * @param proceso
	 * @param asignacion:
	 *  1-Primer ajuste | 2-Mejor ajuste | 3-Peor Ajuste
	 */
	public boolean a�adirProceso(Proceso proceso, int asignacion) {
		
		int posicion = 0;
		proceso.setPID(contadorPID);
		
		//Primer ajuste: Asignar el primer fragmento libre que tenga el tama�o suficiente.
		if(asignacion==1){									
			for(int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true & particiones[i].getTamano() >= proceso.getTamano() ) {
					posicion = i;
					break;
				}
			}
			if(posicion != 0) {
				particiones[posicion].setProceso(proceso);
				particiones[posicion].setDisponible(false);
				contadorPID++;
				return true;
			}else {
				return false;
			}
		}
		
		//Mejor ajuste: Asignar el fragmento m�s peque�o que tenga el tama�o suficiente.
		if(asignacion==2){									
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
				contadorPID++;
				return true;
			}else {
				return false;
			}
		}		
		
		//Peor ajuste: Asignar el fragmento m�s grande.
		if(asignacion==3){									
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
				contadorPID++;
				return true;
			}else {
				return false;
			}
		}	
		
		return false;
		
	}
	
	
	/**
	 * Eliminar proceso
	 * @param PID
	 * @return verdadero si se realia el proceso
	 */
	public boolean eliminarProceso(int PID) {
		
		System.out.println(particiones.length);
		for(int i=1; i<particiones.length; i++) {
			if(particiones[i].getDisponible() == false) {
				if(particiones[i].getProceso().getPID() == PID) {
					particiones[i].setDisponible(true);
					particiones[i].setProceso(null);

					return true;	
				}
			}
		}
		return false;	
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
