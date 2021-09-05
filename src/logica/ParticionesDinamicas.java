package logica;

import java.awt.Color;
import java.util.ArrayList;

public class ParticionesDinamicas {
	
	//Constructor por defecto
	public ParticionesDinamicas(int asignacion) {
		dividirMemoria();
	}
	
	//Contador que permite asignar PID a cada proceso
	int contadorPID = 1;
	//Metodo de asignacion
	int asignacion = 0;
	
	//Definicion de tamaño de la memoria, el sistema operativo
	//y de las particiones
	private int memoriaPpal = 16384;
	private Proceso SO = new Proceso(0, "S.O", 2048, new Color(215, 215, 84));
	private int tamanoParticionMax = 7168;
	private int tamanoParticionMin = 112;
	private ArrayList<Particion> particionesAr= new ArrayList<Particion>();
	private Particion particiones[];
	
	
	//Metodo que divide la memoria en particion del S.O. y las memoria libre
	public void dividirMemoria() {
			
		//Definicion del tamaño de la particion del S.0.
		particionesAr.add(new Particion(0, false, SO.getTamano(), SO, 0));
		
		int particionLibre = memoriaPpal - SO.getTamano();
		particionesAr.add(new Particion(0, true, particionLibre, null, 0));
		
		particiones = particionesAr.toArray(new Particion[0]);
		/*
		//Creacion de particiones disponibles, en un arreglo
		for(int i=1; i<particiones.length; i++) {
			
			particiones[i]= new Particion(i, true, tamParticion, null,
					  particiones[i-1].getInicio()+particiones[i-1].getTamano());
			
			if(tamParticion > tamanoParticionMin) {
				tamParticion = tamParticion/2;
			}
			
		}
		*/	
		
		//imprimir();
		/*Proceso proc1 = new Proceso(1, "Prueba", 980, new Color(215, 153, 84));
		añadirProceso(proc1, 3);
		System.out.println("---");
		imprimir();
		*/
		/*eliminarProceso(1);
		System.out.println("---");
		imprimir();*/
		
	}
	
	/**
	 * Añade los procesos a matriz memoria:
	 * @param proceso
	 * @param asignacion:
	 *  1-Primer ajuste | 2-Mejor ajuste | 3-Peor Ajuste
	 */
	public boolean añadirProceso(Proceso proceso, int asignacion) {
		
		int posicion = 0;
		proceso.setPID(contadorPID);
		
		System.out.println("No. Part: " + particiones.length);
		
		//Primer ajuste: Asignar el primer fragmento libre que tenga el tamaño suficiente.
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
				imprimir();					//*************************
				return true;
			}else {
				return false;
			}
		}
		
		//Mejor ajuste: Asignar el fragmento más pequeño que tenga el tamaño suficiente.
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
		
		//Peor ajuste: Asignar el fragmento más grande.
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
					imprimir();
					return true;	
				}
			}
		}
		return false;	
	}
	
	/**
	 * Calcula el numero de particiones segun el maximo y minimo establecido
	 * @return noParticiones
	 */
	public int calcularNoParticiones() {
		int memoriaTemp = SO.getTamano();
		int noParticiones = 0;
		int tamPaticion = tamanoParticionMax;
		
		while(memoriaTemp < memoriaPpal) {
			memoriaTemp = memoriaTemp + tamPaticion;
			if(tamPaticion >= tamanoParticionMin) {
				tamPaticion = tamPaticion/2;
			}
			noParticiones++;
		}
		
		return noParticiones;
		
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

	public Particion[] getParticiones() {
		return particiones;
	}

	public void setParticiones(Particion[] particiones) {
		this.particiones = particiones;
	}

	public int getTamanoParticionMax() {
		return tamanoParticionMax;
	}

	public void setTamanoParticionMax(int tamanoParticionMax) {
		this.tamanoParticionMax = tamanoParticionMax;
	}

	public int getTamanoParticionMin() {
		return tamanoParticionMin;
	}

	public void setTamanoParticionMin(int tamanoParticionMin) {
		this.tamanoParticionMin = tamanoParticionMin;
	}

}
