
import java.awt.EventQueue;

import control.ControladorAlumnos;
import vista.VConsultaAlumnos;

public class Exe {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			
			public void run() {
				VConsultaAlumnos vCons = new VConsultaAlumnos("Consulta Alumnos");
				
				ControladorAlumnos control = new ControladorAlumnos(vCons);
				vCons.setControlador(control);
				vCons.hacerVisible();
			}
		});

	}

}
