package vista;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import accesoDB.AlumnoPersistencia;
import control.ControladorAlumnos;
import model.Alumno;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class VConsultaAlumnos extends JFrame {
	private JTable tblTablaAlumnos;
	public JButton getBtnEliminarAlumnos() {
		return btnEliminarAlumnos;
	}
	private JLabel lblListaDeAlumnos;
	private JScrollPane spTabla;
	private DefaultTableModel dtm;
	private JButton btnEliminarAlumnos;

	public VConsultaAlumnos(String titulo) throws HeadlessException {
		super(titulo);
		inicializar();
	}

	private void inicializar() {
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getContentPane().setLayout(null);
		
		spTabla = new JScrollPane();
		spTabla.setBounds(29, 52, 374, 141);
		getContentPane().add(spTabla);
		
		tblTablaAlumnos = new JTable();
		cargarTabla();
		
		spTabla.setViewportView(tblTablaAlumnos);
		
		lblListaDeAlumnos = new JLabel("Lista de Alumnos");
		lblListaDeAlumnos.setBounds(29, 26, 156, 14);
		getContentPane().add(lblListaDeAlumnos);
		
		btnEliminarAlumnos = new JButton("Eliminar Alumnos");
		btnEliminarAlumnos.setEnabled(false);
		btnEliminarAlumnos.setBounds(292, 213, 111, 23);
		getContentPane().add(btnEliminarAlumnos);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// asignamos tamaño a la ventana 
		setPreferredSize(new Dimension(600, 400));  

		// Se obtienen las dimensiones en pixels de la pantalla.       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               

		// Se obtienen las dimensiones en pixels de la ventana.       
		Dimension ventana = this.getPreferredSize();               

		// Una cuenta para situar la ventana en el centro de la pantalla.       
		setLocation((pantalla.width - ventana.width) / 2,  
				(pantalla.height - ventana.height) / 2);
		
	}
	
	public JTable getTblTablaAlumnos() {
		return tblTablaAlumnos;
	}

	public void cargarTabla() {
		dtm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tblTablaAlumnos.setModel(dtm);
		dtm.addColumn("DNI");
		dtm.addColumn("Apellido y Nombre");
		dtm.addColumn("Dirección");
		dtm.addColumn("Teléfono");
		tblTablaAlumnos.getColumn("DNI").setPreferredWidth(75);
		tblTablaAlumnos.getColumn("Apellido y Nombre").setPreferredWidth(125);
		tblTablaAlumnos.getColumn("Dirección").setPreferredWidth(75);
		tblTablaAlumnos.getColumn("Teléfono").setPreferredWidth(75);
		//TODO Recuperar la lista de alumnos;
		AlumnoPersistencia ap = new AlumnoPersistencia();
		ArrayList<Alumno> listaAlumnos = ap.consultaAlumnos();
		Object[] fila = new Object[4];
		
		for(Alumno alumno : listaAlumnos) {
			fila[0] = alumno.getDni();
			fila[1] = alumno.getApeNom();
			fila[2] = alumno.getDirec();
			fila[3] = alumno.getTelef();
			dtm.addRow(fila);
		}
	}

	public void hacerVisible() {
		pack();
		setVisible(true);
	}
	public void setControlador(ControladorAlumnos control) {
		tblTablaAlumnos.addMouseListener(control);
		btnEliminarAlumnos.addActionListener(control);
	}
}
