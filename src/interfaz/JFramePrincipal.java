package interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import logica.Particion;
import logica.Proceso;
import logica.ProcesosDisponibles;

import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame implements ActionListener{

	private String mensaje = "";		//manejo de los mensajes a mostrar
	private String activos [];			//Lita de procesos activos
	private int modelo = 1;				//Modelo seleccionado
	private int asignacion = 1;			//Algoritmos de asignacion
	private int tamOcupado = 0;			//label tamaño ocupado
	private boolean compactacion = false;	//Compactacion activa/no activa
	
	private JPanel panelPrincipal;
	
	private JPanel panelProcesos;
	private JToggleButton tglbtnON_OFF;
	private JLabel lblProcesosDisp;
	private JScrollPane scrollLista;
	private JList<String> listaProcesos;
	
	private JPanel panelModMemoria;
	private ButtonGroup btgModMemoria;
	private JRadioButton rdbtnPEstaticaFijas;
	private JRadioButton rdbtnPEstaticasVariables;
	private JRadioButton rdbtnPDinamicas;
	
	private JPanel panelAsignacion;
	private ButtonGroup btgAsignacion;
	private JRadioButton rdbtnPrimerAjuste;
	private JRadioButton rdbtnMejorAjuste;
	private JRadioButton rdbtnPeorAjuste;
	private JCheckBox chckbxCompactacion;
	
	private JPanel panelMensajes;
	private JTextPane textMensajes;
	private JScrollPane scrollMensajes;
	
	private JPanel panelMemoria;
	private Label lblTitulo;
	private JLabel lblMemoriaPrincipalpal;
	private PanelDibujoMem dibujoMemoria;
	private PanelDibujoProc dibujoProcesos;
	private JLabel lblMemoriaLibre;
	private JLabel lblKB;
	
	private JLabel lblprocesosActivos;
	private JScrollPane scrollActivos;
	private JList<String> listaActivos;
	private JButton btnAdd;
	private JButton btnQuit;

	
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
		panelProcesos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProcesos.setBounds(10, 10, 180, 350);
		panelPrincipal.add(panelProcesos);
		panelProcesos.setLayout(null);
		
		tglbtnON_OFF = new JToggleButton();
		tglbtnON_OFF.setSelectedIcon(new ImageIcon(JFramePrincipal.class.getResource("/img/detener.png")));
		tglbtnON_OFF.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/img/iniciar.png")));
		tglbtnON_OFF.setForeground(Color.WHITE);
		tglbtnON_OFF.setBounds(31, 10, 100, 30);
		tglbtnON_OFF.addActionListener(this);
		panelProcesos.add(tglbtnON_OFF);
		
		lblProcesosDisp = new JLabel("Proc. Disponibles");
		lblProcesosDisp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcesosDisp.setBounds(10, 50, 142, 13);
		panelProcesos.add(lblProcesosDisp);
		
		listaProcesos = new JList(generarListaProcesos());
		listaProcesos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaProcesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollLista = new JScrollPane();
		scrollLista.setViewportView(listaProcesos);
		scrollLista.setBounds(10, 75, 160, 120);
		panelProcesos.add(scrollLista);
		
		
		//Panel Modelos de Memoria
		panelModMemoria = new JPanel();
		panelModMemoria.setBorder(new TitledBorder(null, "Modelo de Memoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelModMemoria.setBounds(10, 370, 180, 183);
		panelPrincipal.add(panelModMemoria);
		panelModMemoria.setLayout(null);
		
		rdbtnPEstaticaFijas = new JRadioButton("<html>Particiones estaticas<br />fijas</html>");
		rdbtnPEstaticaFijas.setSelected(true);
		rdbtnPEstaticaFijas.setBounds(10, 24, 152, 29);
		panelModMemoria.add(rdbtnPEstaticaFijas);
		
		rdbtnPEstaticasVariables = new JRadioButton("<html>Particiones estaticas<br />variables</html>");
		rdbtnPEstaticasVariables.setBounds(10, 55, 152, 29);
		panelModMemoria.add(rdbtnPEstaticasVariables);
		
		rdbtnPDinamicas = new JRadioButton("Particiones Dinamicas");
		rdbtnPDinamicas.setBounds(10, 86, 152, 21);
		panelModMemoria.add(rdbtnPDinamicas);
		
		chckbxCompactacion = new JCheckBox("Compactaci\u00F3n");
		chckbxCompactacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxCompactacion.setBounds(35, 109, 127, 21);
		panelModMemoria.add(chckbxCompactacion);
		
		btgModMemoria = new ButtonGroup();
		btgModMemoria.add(rdbtnPEstaticaFijas);
		btgModMemoria.add(rdbtnPEstaticasVariables);
		btgModMemoria.add(rdbtnPDinamicas);
		
		//Panel Algoritmo Asignacion
		panelAsignacion = new JPanel();
		panelAsignacion.setBorder(new TitledBorder(null, "Algoritmo de Asignación", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAsignacion.setBounds(200, 370, 180, 183);
		panelPrincipal.add(panelAsignacion);
		panelAsignacion.setLayout(null);
		
		rdbtnPrimerAjuste = new JRadioButton("Primer Ajuste");
		rdbtnPrimerAjuste.setSelected(true);
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
		
		textMensajes = new JTextPane();
		textMensajes.setEditable(false);
		
		scrollMensajes = new JScrollPane();
		scrollMensajes.setViewportView(textMensajes);
		scrollMensajes.setBounds(15, 25, 356, 143);
		panelMensajes.add(scrollMensajes);
		
		//Panel Grafico Memoria
		panelMemoria = new JPanel();
		panelMemoria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMemoria.setBounds(200, 10, 576, 350);
		panelPrincipal.add(panelMemoria);
		panelMemoria.setLayout(null);
		
		lblTitulo = new Label("Modelo de asignacion");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setAlignment(Label.CENTER);
		lblTitulo.setBounds(5, 5, 566, 29);
		panelMemoria.add(lblTitulo);
		
		//dibujoProcesos();
	}
	
	//Dibujar particiones
	public void dibujoProcesos() {
			
		//Proccesos en memoria dibujo
		lblMemoriaPrincipalpal = new JLabel("Memoria Principal");
		lblMemoriaPrincipalpal.setBounds(25, 207, 126, 13);
		panelMemoria.add(lblMemoriaPrincipalpal);
		
		dibujoProcesos = new PanelDibujoProc(modelo, asignacion);
		panelMemoria.add(dibujoProcesos);
		dibujoProcesos.setBounds(25, 230, 531, 80);
		
		//Dibujo mamoria libre y ocupada
		lblMemoriaLibre = new JLabel("Memoria Ocupada");
		lblMemoriaLibre.setBounds(135, 45, 115, 13);
		lblMemoriaLibre.setVisible(true);
		panelMemoria.add(lblMemoriaLibre);
		
		lblKB = new JLabel("KB");
		lblKB.setBounds(135, 68, 65, 13);
		panelMemoria.add(lblKB);
		
		dibujoMemoria = dibujoProcesos.getDibujoMemLibre();
		panelMemoria.add(dibujoMemoria);
		dibujoMemoria.setBounds(45, 45, 80, 150);
		
		//Lista de procesos activos
		lblprocesosActivos = new JLabel("Procesos Activos");
		lblprocesosActivos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblprocesosActivos.setBounds(10, 215, 160, 13);
		panelProcesos.add(lblprocesosActivos);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAdd.setBounds(116, 53, 54, 21);
		btnAdd.addActionListener(this);
		panelProcesos.add(btnAdd);
		
		btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnQuit.setBounds(116, 217, 54, 21);
		btnQuit.addActionListener(this);
		panelProcesos.add(btnQuit);
		
		actualizarActivos();
		
		listaActivos = new JList(activos);
		listaActivos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaActivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollActivos = new JScrollPane();
		scrollActivos.setViewportView(listaActivos);
		scrollActivos.setBounds(10, 240, 160, 100);
		panelProcesos.add(scrollActivos);
	}
	
	//Manejo eventos
	public void actionPerformed(ActionEvent event) {
		
		//Boton iniciar/detener
		if(event.getSource() == tglbtnON_OFF){
					
			JToggleButton tglbON_OFF = (JToggleButton)event.getSource();
			
            if(tglbON_OFF.isSelected()){
            	
            	//Modelo seleccionado
            	if(rdbtnPEstaticaFijas.isSelected())
            		modelo = 1;
            	else if(rdbtnPEstaticasVariables.isSelected())
            		modelo = 2;
            	else if(rdbtnPDinamicas.isSelected())
            		modelo = 3;
            	
            	//Metodo de asignacion
            	if(rdbtnPrimerAjuste.isSelected())
            		asignacion = 1;
            	else if(rdbtnMejorAjuste.isSelected())
            		asignacion = 2;
            	else if(rdbtnPeorAjuste.isSelected())
            		asignacion = 3;
            	
            	//Compactacion
            	compactacion = chckbxCompactacion.isSelected();
            	
            	//Segun modelo seleccionado se asigna el titulo
            	if(modelo == 1) {
            		lblTitulo.setText("Particiones Estaticas Fijas");
            	}else if(modelo == 2) {
            		lblTitulo.setText("Particiones Estaticas Variables");
            	}else if(modelo == 3){
            		lblTitulo.setText("Particiones Dinamicas");
            	}
            	
            	desabilitarIniciado();
            	dibujoProcesos();
            	
            	
            	mensaje = "Sistema Operativo cargado.";
            	textMensajes.setText(mensaje);
            	
            	panelProcesos.revalidate();
            	panelProcesos.repaint();
            	
            }else {
            	mensaje = mensaje + "\nSistema Operativo detenido.";
            	textMensajes.setText(mensaje);
            	//habilitarDetenido();
            	this.dispose();
            	JFramePrincipal frame = new JFramePrincipal();
            	frame.setVisible(true);
            }
            
        	actualizarActivos();
        	panelMemoria.repaint();
        	listaActivos.setListData(activos);
        	scrollActivos.revalidate();
            
            tamOcupado = dibujoMemoria.calcularTamOcupado();
            lblKB.setText(Integer.toString(tamOcupado) + " KB");
		}
		
		//Boton Agregar proceso
		if(event.getSource() == btnAdd){
			
        	int seleccionado = listaProcesos.getSelectedIndex();
        	
        	boolean agregado = false;
        	
        	if (seleccionado != -1) {
    			
            	//Proceso seleccionado
            	Proceso proceso = new ProcesosDisponibles().getDisponibles()[seleccionado];

            	//Asigancion segun modelo
            	if(modelo == 1) {
            		agregado = dibujoProcesos.getParticionesEstFijas().añadirProceso(proceso, asignacion);
            	}else if(modelo == 2) {
            		agregado = dibujoProcesos.getParticionesEstVariables().añadirProceso(proceso, asignacion);
            	}else {
            		agregado = dibujoProcesos.getParticionesDinamicas().añadirProceso(proceso, asignacion, compactacion);
            		dibujoProcesos.getParticionesDinamicas().imprimir();
            	}
            	
            	if(agregado)
            		mensaje = mensaje + "\n" + proceso.getNombre() + " - PID:" + proceso.getPID() + " agregado.";
            	else
            		mensaje = mensaje + "\nProceso no agregado (Sin memoria o excede tamaño de particiones)";
            	textMensajes.setText(mensaje);
            	
            	actualizarActivos();
            	panelMemoria.repaint();
            	listaActivos.setListData(activos);
            	scrollActivos.revalidate();
            	
        	}else {
        		mensaje = mensaje + "\nSeleccione Proceso";
        		textMensajes.setText(mensaje);
        	}	
        	
        	tamOcupado = dibujoMemoria.calcularTamOcupado();
        	lblKB.setText(Integer.toString(tamOcupado) + " KB");
		}
		
		//Boton quitar proceso
		if(event.getSource() == btnQuit){
		
			int seSelecciono = listaActivos.getSelectedIndex();
					
			boolean eliminado = false;
        	
        	if (seSelecciono != -1 ) {
    			//Extraer PID del proceso seleccionado
    			String seleccionado = listaActivos.getSelectedValue();
    			System.out.println("Eliminando: "+ seleccionado + "SeSelecciono: " + seSelecciono);
    			int indice = seleccionado.indexOf("PID:");
    			int PID = Integer.parseInt(seleccionado.substring(indice+4, seleccionado.length()));
    			
    			//Asigancion segun modelo
            	if(modelo == 1) {
            		eliminado = dibujoProcesos.getParticionesEstFijas().eliminarProceso(PID);
            	}else if (modelo == 2) {
            		eliminado = dibujoProcesos.getParticionesEstVariables().eliminarProceso(PID);
            	}else if (modelo == 3) {
            		eliminado = dibujoProcesos.getParticionesDinamicas().eliminarProceso(PID);
            	}
        		
        		
        		if(eliminado)
            		mensaje = mensaje + "\n" + seleccionado + " eliminado.";
            	else
            		mensaje = mensaje + "\nSeleccione un proceso activo (S.O. No se puede eliminar).";
            	textMensajes.setText(mensaje);
            	
            	actualizarActivos();
            	panelMemoria.repaint();
            	listaActivos.setListData(activos);
            	scrollActivos.revalidate();
        	}
        	
        	tamOcupado = dibujoMemoria.calcularTamOcupado();
        	lblKB.setText(Integer.toString(tamOcupado) + " KB");
		}
		
	}
	
	/**
	 * Actualiza la lista de procesos activos
	 */
	public void actualizarActivos() {
		//Generar lista de procesos activos a partir de las particiones
		if(modelo == 1)
			activos = generarListaActivos(dibujoProcesos.getParticionesEstFijas().getParticiones());
		else if (modelo == 2)
			activos = generarListaActivos(dibujoProcesos.getParticionesEstVariables().getParticiones());
		else if (modelo == 3)
			activos = generarListaActivos(dibujoProcesos.getParticionesDinamicas().getParticiones());
	}
	
	/**
	 * Genera la lista de procesos disponibles
	 * @return Arreglo de String con procesos disponibles
	 */
	public String[] generarListaProcesos() {
		ProcesosDisponibles procesosDisponibles = new ProcesosDisponibles();
		
		String listaProcesos[] = new String[procesosDisponibles.getDisponibles().length];
		
		for (int i = 0; i < listaProcesos.length; i++ ) {
			listaProcesos[i] = procesosDisponibles.getDisponibles()[i].getNombre() 
							   + " ("+ procesosDisponibles.getDisponibles()[i].getTamano()
							   + "KB) ";
		}
		
		return listaProcesos;
	}
	
	//Generar lista de procesos activos
	public String[] generarListaActivos(Particion[] particiones) {
		
		ArrayList<String> lista = new ArrayList<String>();
		
		for(int i = 0; i<particiones.length; i++) {
			if(particiones[i].getDisponible()==false) {
				lista.add(particiones[i].getProceso().getNombre()
						  + " (" + particiones[i].getProceso().getTamano() + "KB)"
						  + " PID:" + particiones[i].getProceso().getPID());
			}
		}
		//Canversion ArrayList en Array
		String[] listaActivos = lista.toArray(new String[0]);
		
		return listaActivos;
	}
	
	/**
	 * Desabilitar paneles que no deben estar activos
	 */
	public void desabilitarIniciado() {
		rdbtnPEstaticaFijas.setEnabled(false);
		rdbtnPEstaticasVariables.setEnabled(false);
		rdbtnPDinamicas.setEnabled(false);
		chckbxCompactacion.setEnabled(false);
		
		rdbtnPrimerAjuste.setEnabled(false);
		rdbtnMejorAjuste.setEnabled(false);
		rdbtnPeorAjuste.setEnabled(false);
	}
	
	
	/**
	 * Habilitar paneles que deben estar activos
	 */
	public void habilitarDetenido() {
		rdbtnPEstaticaFijas.setEnabled(true);
		rdbtnPEstaticasVariables.setEnabled(true);
		
		rdbtnPrimerAjuste.setEnabled(true);
		rdbtnMejorAjuste.setEnabled(true);
		rdbtnPeorAjuste.setEnabled(true);
	}
}
