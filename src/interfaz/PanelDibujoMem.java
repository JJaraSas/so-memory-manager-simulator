package interfaz;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelDibujoMem extends JPanel{
	
	private Color verde=new Color(86, 186, 7);
	
	public PanelDibujoMem() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(verde);
		
	}

}
