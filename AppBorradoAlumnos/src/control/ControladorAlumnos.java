package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import accesoDB.AlumnoPersistencia;
import model.Alumno;
import vista.VConsultaAlumnos;

public class ControladorAlumnos implements ActionListener, MouseListener {
	private VConsultaAlumnos vCons;
	private AlumnoPersistencia aluPers;
	public ControladorAlumnos(VConsultaAlumnos vCons) {
		super();
		this.vCons = vCons;
		aluPers = new AlumnoPersistencia();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Recoger alumnos selec y eliminarlos uno a uno
		ArrayList<Alumno> listaAlumnosSel = new ArrayList<Alumno>();
			int[]filaSel = vCons.getTblTablaAlumnos().getSelectedRows();
		Alumno alumno = null;
		String dni = "";
		String apeNom = "";
		String direc = "";
		String telef = "";
		for (int i = 0; i<filaSel.length;i++) {
			dni = (String) vCons.getTblTablaAlumnos().getModel().getValueAt(filaSel[1],0);
			apeNom = (String) vCons.getTblTablaAlumnos().getModel().getValueAt(filaSel[1],1);
			direc = (String) vCons.getTblTablaAlumnos().getModel().getValueAt(filaSel[1],2);
			telef = (String) vCons.getTblTablaAlumnos().getModel().getValueAt(filaSel[1],3);
			alumno = new Alumno(dni, apeNom, direc, telef);
			listaAlumnosSel.add(alumno);
		}
		String msg = "";
		for (Alumno al : listaAlumnosSel) {
			msg = aluPers.borrarAlumnoPorDni(al.getDni());
			JOptionPane.showMessageDialog(vCons, "Resultado de borrar el alumno " + al.getDni() + ": " + msg,
					"ResultadoAccion",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		vCons.cargarTabla();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int [] filaSel = vCons.getTblTablaAlumnos().getSelectedRows();
		if(filaSel.length>0) {
			vCons.getBtnEliminarAlumnos().setEnabled(true);
		}
		else {
			vCons.getBtnEliminarAlumnos().setEnabled(false);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
