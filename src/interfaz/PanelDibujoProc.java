package interfaz;

import java.awt.Color;
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
	
    public void paint(Graphics g) {
    	
    	//Dibujando la memoria y el borde
    	g.setColor(verde);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	g.setColor(negro);
    	g.drawRect(0, 0, getWidth()-1, getHeight()-1);

    	partcionesEstFijas = new ParticionesEstFijas();

    	//Calculando tamaño del S.O. para ser dibujada
    	double tamanoSO = (partcionesEstFijas.getSO().getTamano()*100.0)/partcionesEstFijas.getMemoriaPpal();
    	int drawSO = (int) (getWidth()*(tamanoSO/100));
    	System.out.println(drawSO);

    	g.setColor(amarillo);
    	g.fillRect(0, 0, drawSO, getHeight());
    	g.setColor(negro);
    	g.drawRect(0, 0, drawSO, getHeight()-1);
    	
    	g.drawString(partcionesEstFijas.getParticiones()[0].getProceso().getNombre(), 5, (getHeight()*2)/5);
    	g.drawString("PID=" + partcionesEstFijas.getParticiones()[0].getProceso().getPID(), 5, ((getHeight()*4)/5));
    	//g.drawString("T: " + partcionesEstFijas.getParticiones()[0].getTamano() + "KB", 5, (getHeight()*3)/5);
    	
    	
    	
    }

}
