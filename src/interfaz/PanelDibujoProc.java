package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.ParticionesEstFijas;

@SuppressWarnings("serial")
public class PanelDibujoProc extends JPanel{
	
	private Color verde=new Color(86, 186, 7);
	private Color amarillo=new Color(215, 215, 84);
	private Color negro=new Color(0, 0, 0);
	
	private ParticionesEstFijas partcionesEstFijas;
	
	/*
	public PanelDibujoProc() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(verde);
	}*/
	
	/**
	 * Pintar el recuadro de procesos en memoria
	 */
    public void paint(Graphics g) {
    	
    	//Dibujando la memoria y el borde
    	/*
    	g.setColor(verde);
    	g.fillRect(0, 0, getWidth()-1, getHeight());
    	g.setColor(negro);
    	g.drawRect(0, 0, getWidth()-1, getHeight()-1);
    	*/

    	partcionesEstFijas = new ParticionesEstFijas();
    	
    	int pos = 0; 	//Llava la posicion de donde se va a dibujar
    	g.setFont(new Font("Tahoma", Font.BOLD, 7));		//Tamaño del texto en el dibujo
    	
    	//Calculando tamaño del S.O. para ser dibujada
    	double tamanoSO = (partcionesEstFijas.getSO().getTamano()*100.0)/partcionesEstFijas.getMemoriaPpal();
    	int drawSO = (int) (getWidth()*(tamanoSO/100));
    	System.out.println(drawSO);

    	g.setColor(amarillo);
    	g.fillRect(pos, 0, drawSO, getHeight());
    	g.setColor(negro);
    	g.drawRect(pos, 0, drawSO, getHeight()-1);
    	
    	g.drawString(partcionesEstFijas.getParticiones()[0].getProceso().getNombre(), 5, (getHeight()*2)/5);
    	g.drawString("PID=" + partcionesEstFijas.getParticiones()[0].getProceso().getPID(), 5, ((getHeight()*4)/5));
    	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
    	
    	pos = pos + drawSO;
    	
    	//Recorrer el arreglo de particiones para ir dibujando cada una
    	for(int i=1; i<partcionesEstFijas.getParticiones().length;i++) {
    		
    		int tamDibujo = cacularTamDibujo(i);
    		
    		if(partcionesEstFijas.getParticiones()[i].getDisponible() == true)
    			g.setColor(verde);
    		else
    			g.setColor(partcionesEstFijas.getParticiones()[i].getProceso().getColor());
    		
        	g.fillRect(pos, 0, tamDibujo, getHeight());
        	g.setColor(negro);
        	g.drawRect(pos, 0, tamDibujo, getHeight()-1);
        	
        	if(partcionesEstFijas.getParticiones()[i].getDisponible() == false) {
            	g.drawString(partcionesEstFijas.getParticiones()[i].getProceso().getNombre(), pos+5, (getHeight()*2)/5);
            	g.drawString("PID=" + partcionesEstFijas.getParticiones()[i].getProceso().getPID(), pos+5, ((getHeight()*4)/5));
            	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
        	}
        	pos = pos + tamDibujo;		//Se suma el area dibujada a la posiscion
    	}
    	
    }
    
    /**
     * Calcula el tamaño de la particion a dibujar
     * @param posicion
     * @return tamaño del recuadro a dibujar
     */
    public int cacularTamDibujo(int posicion) {
    	double tamano = (partcionesEstFijas.getParticiones()[posicion].getTamano()*100.0)/partcionesEstFijas.getMemoriaPpal();
    	int draw = (int) (getWidth()*(tamano/100));
		return draw;
    }

}
