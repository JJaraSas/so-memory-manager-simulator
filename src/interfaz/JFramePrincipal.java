package interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.Canvas;
import java.awt.Color;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame{

	private JPanel panelPrincipal;
	
	private JPanel panelProcesos;
	private JList<String> listaProcesos;
	private JLabel lblProcesos;
	
	private JPanel panelModMemoria;
	private ButtonGroup btgModMemoria;
	private JRadioButton rdbtnPEstaticaFijas;
	private JRadioButton rdbtnPEstaticasVariables;
	
	private JPanel panelAsignacion;
	private ButtonGroup btgAsignacion;
	private JRadioButton rdbtnPrimerAjuste;
	private JRadioButton rdbtnMejorAjuste;
	private JRadioButton rdbtnPeorAjuste;
	
	private JPanel panelMensajes;
	private JTextPane textPane;
	
	private JPanel panelMemoria;
	private Canvas cvsMemoria;
	
	public JFramePrincipal() {
		setTitle("Simulador Gestor de Memoria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		//Panel de seleccion de procesos
		panelProcesos = new JPanel();
		panelProcesos.setBounds(10, 10, 180, 350);
		panelPrincipal.add(panelProcesos);
		panelProcesos.setLayout(null);
		
		lblProcesos = new JLabel("Procesos");
		lblProcesos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProcesos.setBounds(10, 10, 70, 13);
		panelProcesos.add(lblProcesos);
		
		String procesos[] = { "VLC Player (6MB)", "Chrome (5MB)", 
							  "Word (4MB)", "Excel (3MB)", "Eclipse (2MB)", 
							  "Solitario (1MB)", "PDFReader (512KB)"};
		listaProcesos = new JList(procesos);
		listaProcesos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaProcesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProcesos.setBounds(10, 33, 160, 307);
		panelProcesos.add(listaProcesos);
		
		//Panel Modelos de Memoria
		panelModMemoria = new JPanel();
		panelModMemoria.setBorder(new TitledBorder(null, "Modelo de Memoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelModMemoria.setBounds(10, 370, 180, 183);
		panelPrincipal.add(panelModMemoria);
		panelModMemoria.setLayout(null);
		
		rdbtnPEstaticaFijas = new JRadioButton("<html>Particiones estaticas<br />fijas</html>");
		rdbtnPEstaticaFijas.setSelected(true);
		rdbtnPEstaticaFijas.setBounds(10, 24, 152, 35);
		panelModMemoria.add(rdbtnPEstaticaFijas);
		
		rdbtnPEstaticasVariables = new JRadioButton("<html>Particiones estaticas<br />variables</html>");
		rdbtnPEstaticasVariables.setBounds(10, 61, 152, 35);
		panelModMemoria.add(rdbtnPEstaticasVariables);
		
		btgModMemoria = new ButtonGroup();
		btgModMemoria.add(rdbtnPEstaticaFijas);
		btgModMemoria.add(rdbtnPEstaticasVariables);
		
		//Panel Algoritmo Asignacion
		panelAsignacion = new JPanel();
		panelAsignacion.setBorder(new TitledBorder(null, "Algoritmo de Asignación", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAsignacion.setBounds(200, 370, 180, 183);
		panelPrincipal.add(panelAsignacion);
		panelAsignacion.setLayout(null);
		
		rdbtnPrimerAjuste = new JRadioButton("Primer Ajuste");
		rdbtnPrimerAjuste.setBounds(10, 34, 103, 21);
		panelAsignacion.add(rdbtnPrimerAjuste);
		
		rdbtnMejorAjuste = new JRadioButton("Mejor Ajuste");
		rdbtnMejorAjuste.setBounds(10, 74, 103, 21);
		panelAsignacion.add(rdbtnMejorAjuste);
		
		rdbtnPeorAjuste = new JRadioButton("Peor Ajuste");
		rdbtnPeorAjuste.setBounds(10, 114, 103, 21);
		panelAsignacion.add(rdbtnPeorAjuste);
		
		btgAsignacion = new ButtonGroup();
		btgAsignacion.add(rdbtnPrimerAjuste);
		btgAsignacion.add(rdbtnMejorAjuste);
		btgAsignacion.add(rdbtnPeorAjuste);
		
		//Panel de Mensajes
		panelMensajes = new JPanel();
		panelMensajes.setBorder(new TitledBorder(null, "Mensajes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMensajes.setBounds(390, 370, 386, 183);
		panelPrincipal.add(panelMensajes);
		panelMensajes.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 20, 366, 153);
		panelMensajes.add(textPane);
		
		panelMemoria = new JPanel();
		panelMemoria.setBounds(200, 10, 576, 350);
		panelPrincipal.add(panelMemoria);
		panelMemoria.setLayout(null);
		
		cvsMemoria = new Canvas();
		cvsMemoria.setBounds(54, 10, 100, 149);
		panelMemoria.add(cvsMemoria);
		cvsMemoria.setBackground(Color.green);

	}
}
