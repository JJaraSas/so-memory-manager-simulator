package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import logica.ParticionesDinamicas;
import logica.ParticionesEstFijas;
import logica.ParticionesEstVariables;

@SuppressWarnings("serial")
public class PanelDibujoProc extends JPanel{
	
	public PanelDibujoProc(int modelo, int asignacion) {
		this.modelo = modelo;
		this.asignacion = asignacion;
		inicialModelo();
		iniciarDibujoMemLibre();
	}
	
	private PanelDibujoMem dibujoMemLibre; 
	
	private int modelo = 0;
	private int asignacion = 0;
	
	private Color verde = new Color(86, 186, 7);
	private Color amarillo = new Color(215, 215, 84);
	private Color negro = new Color(0, 0, 0);
	
	private ParticionesEstFijas particionesEstFijas;
	private ParticionesEstVariables particionesEstVariables;
	private ParticionesDinamicas particionesDinamicas;
	
	/**
	 * Pintar el recuadro de procesos en memoria
	 */
    public void paint(Graphics g) {

    	//Modelo Particiones Estaticas Fijas
    	if(modelo == 1) {
        	
        	int pos = 0; 	//Llava la posicion de donde se va a dibujar
        	g.setFont(new Font("Tahoma", Font.BOLD, 7));		//Tamaño del texto en el dibujo
        	
        	//Calculando tamaño del S.O. para ser dibujada
        	double tamanoSO = (particionesEstFijas.getSO().getTamano()*100.0)/particionesEstFijas.getMemoriaPpal();
        	int drawSO = (int) (getWidth()*(tamanoSO/100));

        	g.setColor(amarillo);
        	g.fillRect(pos, 0, drawSO, getHeight());
        	g.setColor(negro);
        	g.drawRect(pos, 0, drawSO, getHeight()-1);
        	
        	g.drawString(particionesEstFijas.getParticiones()[0].getProceso().getNombre(), 5, (getHeight()*2)/5);
        	g.drawString("PID=" + particionesEstFijas.getParticiones()[0].getProceso().getPID(), 5, ((getHeight()*4)/5));
        	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
        	
        	pos = pos + drawSO;
        	
        	//Recorrer el arreglo de particiones para ir dibujando cada una
        	for(int i=1; i<particionesEstFijas.getParticiones().length;i++) {
        		
        		int tamDibujo = cacularTamDibujo(i);
        		int tamProceso = 0;
        		
        		g.setColor(verde);
        		
        		//Verificar si hay proceso en la particion, si lo hay calcular el tamaño
        		if(particionesEstFijas.getParticiones()[i].getDisponible() == true)
        			tamProceso = 0;	
        		else
        			tamProceso = cacularTamProceso(i);

            	g.fillRect(pos, 0, tamDibujo, getHeight());	//Pintar particion
            	
            	if (tamProceso != 0) {		//Si la particion tiene proceso pintar proceso
            		g.setColor(particionesEstFijas.getParticiones()[i].getProceso().getColor());
            		g.fillRect(pos, 0, tamProceso, getHeight());
            	}
            	
            	g.setColor(negro);			//Pintar borde
            	g.drawRect(pos, 0, tamDibujo, getHeight()-1);
            	
            	if(particionesEstFijas.getParticiones()[i].getDisponible() == false) {
                	g.drawString(particionesEstFijas.getParticiones()[i].getProceso().getNombre(), pos+5, (getHeight()*2)/5);
                	g.drawString("PID=" + particionesEstFijas.getParticiones()[i].getProceso().getPID(), pos+5, ((getHeight()*4)/5));
                	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
            	}
            	pos = pos + tamDibujo;		//Se suma el area dibujada a la posiscion
        	}
    	}
    	
    	//Modelo Particiones Estaticas Fijas
    	if(modelo == 2) {
        	
        	int pos = 0; 	//Llava la posicion de donde se va a dibujar
        	g.setFont(new Font("Tahoma", Font.BOLD, 7));		//Tamaño del texto en el dibujo
        	
        	//Calculando tamaño del S.O. para ser dibujada
        	double tamanoSO = (particionesEstVariables.getSO().getTamano()*100.0)/particionesEstVariables.getMemoriaPpal();
        	int drawSO = (int) (getWidth()*(tamanoSO/100));

        	g.setColor(amarillo);
        	g.fillRect(pos, 0, drawSO, getHeight());
        	g.setColor(negro);
        	g.drawRect(pos, 0, drawSO, getHeight()-1);
        	
        	g.drawString(particionesEstVariables.getParticiones()[0].getProceso().getNombre(), 5, (getHeight()*2)/5);
        	g.drawString("PID=" + particionesEstVariables.getParticiones()[0].getProceso().getPID(), 5, ((getHeight()*4)/5));
        	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
        	
        	pos = pos + drawSO;
        	
        	//Recorrer el arreglo de particiones para ir dibujando cada una
        	for(int i=1; i<particionesEstVariables.getParticiones().length;i++) {
        		
        		int tamDibujo = cacularTamDibujo(i);
        		int tamProceso = 0;
        		
        		g.setColor(verde);
        		
        		//Verificar si hay proceso en la particion, si lo hay calcular el tamaño
        		if(particionesEstVariables.getParticiones()[i].getDisponible() == true)
        			tamProceso = 0;	
        		else
        			tamProceso = cacularTamProceso(i);

            	g.fillRect(pos, 0, tamDibujo, getHeight());	//Pintar particion
            	
            	if (tamProceso != 0) {		//Si la particion tiene proceso pintar proceso
            		g.setColor(particionesEstVariables.getParticiones()[i].getProceso().getColor());
            		g.fillRect(pos, 0, tamProceso, getHeight());
            	}
            	
            	g.setColor(negro);			//Pintar borde
            	g.drawRect(pos, 0, tamDibujo, getHeight()-1);
            	
            	if(particionesEstVariables.getParticiones()[i].getDisponible() == false) {
                	g.drawString(particionesEstVariables.getParticiones()[i].getProceso().getNombre(), pos+5, (getHeight()*2)/5);
                	g.drawString("PID=" + particionesEstVariables.getParticiones()[i].getProceso().getPID(), pos+5, ((getHeight()*4)/5));
                	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
            	}
            	pos = pos + tamDibujo;		//Se suma el area dibujada a la posiscion
        	}
    	}
    	
    	//Modelo Particiones Dinamicas
    	if(modelo == 3) {
        	
        	int pos = 0; 	//Llava la posicion de donde se va a dibujar
        	g.setFont(new Font("Tahoma", Font.BOLD, 7));		//Tamaño del texto en el dibujo
        	
        	//Calculando tamaño del S.O. para ser dibujada
        	double tamanoSO = (particionesDinamicas.getSO().getTamano()*100.0)/particionesDinamicas.getMemoriaPpal();
        	int drawSO = (int) (getWidth()*(tamanoSO/100));

        	g.setColor(amarillo);
        	g.fillRect(pos, 0, drawSO, getHeight());
        	g.setColor(negro);
        	g.drawRect(pos, 0, drawSO, getHeight()-1);
        	
        	g.drawString(particionesDinamicas.getParticiones()[0].getProceso().getNombre(), 5, (getHeight()*2)/5);
        	g.drawString("PID=" + particionesDinamicas.getParticiones()[0].getProceso().getPID(), 5, ((getHeight()*4)/5));
        	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
        	
        	pos = pos + drawSO;
        	
        	//Recorrer el arreglo de particiones para ir dibujando cada una
        	for(int i=1; i<particionesDinamicas.getParticiones().length;i++) {
        		
        		int tamDibujo = cacularTamDibujo(i);
        		int tamProceso = 0;
        		
        		g.setColor(verde);
        		
        		//Verificar si hay proceso en la particion, si lo hay calcular el tamaño
        		if(particionesDinamicas.getParticiones()[i].getDisponible() == true)
        			tamProceso = 0;	
        		else
        			tamProceso = cacularTamProceso(i);

            	g.fillRect(pos, 0, tamDibujo, getHeight());	//Pintar particion
            	
            	if (tamProceso != 0) {		//Si la particion tiene proceso pintar proceso
            		g.setColor(particionesDinamicas.getParticiones()[i].getProceso().getColor());
            		g.fillRect(pos, 0, tamProceso, getHeight());
            	}
            	
            	g.setColor(negro);			//Pintar borde
            	g.drawRect(pos, 0, tamDibujo, getHeight()-1);
            	
            	if(particionesDinamicas.getParticiones()[i].getDisponible() == false) {
                	g.drawString(particionesDinamicas.getParticiones()[i].getProceso().getNombre(), pos+5, (getHeight()*2)/5);
                	g.drawString("PID=" + particionesDinamicas.getParticiones()[i].getProceso().getPID(), pos+5, ((getHeight()*4)/5));
                	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
            	}
            	pos = pos + tamDibujo;		//Se suma el area dibujada a la posiscion
        	}
    	}
    	
    }

    //Inicia la valriable del modelo
    public void inicialModelo() {
    	if(modelo == 1)
    		particionesEstFijas = new ParticionesEstFijas(asignacion);
    	else if(modelo == 2)
    		particionesEstVariables = new ParticionesEstVariables(asignacion);
    	else if(modelo == 3)
    		particionesDinamicas = new ParticionesDinamicas(asignacion);
    }
    
    public void iniciarDibujoMemLibre() {
    	
    	if(modelo == 1)
    		dibujoMemLibre = new PanelDibujoMem(particionesEstFijas.getParticiones(), particionesEstFijas.getMemoriaPpal());
    	else if(modelo == 2)
    		dibujoMemLibre = new PanelDibujoMem(particionesEstVariables.getParticiones(), particionesEstVariables.getMemoriaPpal());
    	else if(modelo == 3)
    		dibujoMemLibre = new PanelDibujoMem(particionesDinamicas.getParticiones(), particionesDinamicas.getMemoriaPpal());
    		
    }
    
    /**
     * Calcula el tamaño de la particion a dibujar
     * @param posicion
     * @return tamaño del recuadro a dibujar
     */
    public int cacularTamDibujo(int posicion) {
    	if(modelo == 1) {
    		double tamano = (particionesEstFijas.getParticiones()[posicion].getTamano()*100.0)/particionesEstFijas.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamano/100));
    		return draw;
    	}else if(modelo == 2) {
    		double tamano = (particionesEstVariables.getParticiones()[posicion].getTamano()*100.0)/particionesEstVariables.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamano/100));
    		return draw;
    	}else if(modelo == 3) {
    		double tamano = (particionesDinamicas.getParticiones()[posicion].getTamano()*100.0)/particionesDinamicas.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamano/100));
    		return draw;
    	}else {
    		return 0;
    	}

    }

    /**
     * Calcula el tamaño del proceso
     * @param posicion
     * @return tamProceso
     */
    public int cacularTamProceso(int posicion) {
    	if(modelo == 1) {
    		double tamProceso = (particionesEstFijas.getParticiones()[posicion].getProceso().getTamano()*100.0)/particionesEstFijas.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamProceso/100));
    		return draw;
    	}else if(modelo == 2) {
    		double tamProceso = (particionesEstVariables.getParticiones()[posicion].getProceso().getTamano()*100.0)/particionesEstVariables.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamProceso/100));
    		return draw;
    	}else if(modelo == 3) {
    		double tamProceso = (particionesDinamicas.getParticiones()[posicion].getProceso().getTamano()*100.0)/particionesDinamicas.getMemoriaPpal();
        	int draw = (int) (getWidth()*(tamProceso/100));
    		return draw;
    	}else {
    		return 0;
    	}
    	
    }
    
	public ParticionesEstFijas getParticionesEstFijas() {
		return particionesEstFijas;
	}
	public void setParticionesEstFijas(ParticionesEstFijas particionesEstFijas) {
		this.particionesEstFijas = particionesEstFijas;
	}

	public ParticionesEstVariables getParticionesEstVariables() {
		return particionesEstVariables;
	}

	public void setParticionesEstVariables(ParticionesEstVariables particionesEstVariables) {
		this.particionesEstVariables = particionesEstVariables;
	}
	
	public ParticionesDinamicas getParticionesDinamicas() {
		return particionesDinamicas;
	}

	public void setParticionesDinamicas(ParticionesDinamicas particionesDinamicas) {
		this.particionesDinamicas = particionesDinamicas;
	}

	public PanelDibujoMem getDibujoMemLibre() {
		return dibujoMemLibre;
	}

	public void setDibujoMemLibre(PanelDibujoMem dibujoMemLibre) {
		this.dibujoMemLibre = dibujoMemLibre;
	}
    
    

}
