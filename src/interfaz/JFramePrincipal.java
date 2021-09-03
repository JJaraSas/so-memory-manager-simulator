package interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import logica.ProcesosDisponibles;

import javax.swing.JTextPane;
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame{

	private JPanel panelPrincipal;
	
	private JPanel panelProcesos;
	private JScrollPane scrollLista;
	private JList<String> listaProcesos;
	private JLabel lblProcesosDisp;
	
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
	private Label lblTitulo;
	private JLabel lblMemoriaPrincipalpal;
	private PanelDibujoMem dibujoMemoria;
	private PanelDibujoProc dibujoProcesos;
	private JLabel lblMemoriaLibre;
	private JLabel lblKB;
	private JToggleButton tglbtnON_OFF;

	
	public JFramePrincipal() {
		setTitle("Simulador Gestor de Memoria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		//Panel de seleccion de procesos
		tglbtnON_OFF = new JToggleButton("Iniciar");
		tglbtnON_OFF.setBounds(33, 10, 115, 21);
		panelProcesos.add(tglbtnON_OFF);
		
		panelProcesos = new JPanel();
		panelProcesos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProcesos.setBounds(10, 10, 180, 350);
		panelPrincipal.add(panelProcesos);
		panelProcesos.setLayout(null);
		
		lblProcesosDisp = new JLabel("Procesos Disponibles");
		lblProcesosDisp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProcesosDisp.setBounds(10, 50, 160, 13);
		panelProcesos.add(lblProcesosDisp);
		
		listaProcesos = new JList(generarListaProcesos());
		listaProcesos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaProcesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollLista = new JScrollPane();
		scrollLista.setViewportView(listaProcesos);
		scrollLista.setBounds(10, 70, 160, 131);
		panelProcesos.add(scrollLista);
		
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
		textPane.setEditable(false);
		textPane.setBounds(15, 25, 356, 143);
		panelMensajes.add(textPane);
		
		//Panel Grafico Memoria
		panelMemoria = new JPanel();
		panelMemoria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMemoria.setBounds(200, 10, 576, 350);
		panelPrincipal.add(panelMemoria);
		panelMemoria.setLayout(null);
		
		lblTitulo = new Label("Titulo");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setAlignment(Label.CENTER);
		lblTitulo.setBounds(5, 5, 566, 29);
		panelMemoria.add(lblTitulo);
		
		dibujoMemoria = new PanelDibujoMem();
		panelMemoria.add(dibujoMemoria);
		dibujoMemoria.setBounds(45, 45, 80, 150);
		
		dibujoProcesos = new PanelDibujoProc();
		panelMemoria.add(dibujoProcesos);
		dibujoProcesos.setBounds(25, 230, 531, 80);
		
		lblMemoriaPrincipalpal = new JLabel("Memoria Principal");
		lblMemoriaPrincipalpal.setBounds(25, 207, 126, 13);
		panelMemoria.add(lblMemoriaPrincipalpal);
		
		lblMemoriaLibre = new JLabel("Memoria Libre");
		lblMemoriaLibre.setBounds(135, 45, 115, 13);
		panelMemoria.add(lblMemoriaLibre);
		
		lblKB = new JLabel("KB");
		lblKB.setBounds(135, 68, 45, 13);
		panelMemoria.add(lblKB);
	}
	
	public String[] generarListaProcesos() {
		ProcesosDisponibles procesosDisponibles= new ProcesosDisponibles();
		
		String listaProcesos[] = new String[procesosDisponibles.getDisponibles().length];
		
		for (int i = 0; i < listaProcesos.length; i++ ) {
			listaProcesos[i] = procesosDisponibles.getDisponibles()[i].getNombre() 
							   + " ("+ procesosDisponibles.getDisponibles()[i].getTamano()
							   + "KB) ";
		}
		
		return listaProcesos;
	}
}
