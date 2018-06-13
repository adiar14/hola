package accesoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Alumno;

public class AlumnoPersistencia {
	
	private AccesoDB acceso;

	public AlumnoPersistencia() {
		acceso = new AccesoDB();
	}
	
	public ArrayList<Alumno> consultaAlumnos() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try {
			con = acceso.getConexion();
			
			stmt = con.createStatement();
			String query = "SELECT * FROM ALUMNO";
			
			rslt = stmt.executeQuery(query);
			
			String dni = "";
			String apeNom = "";
			String direc = "";
			String telef = "";
			Alumno alumno = null;
			while (rslt.next()) {
				dni = rslt.getString(1);
				apeNom = rslt.getString(2);
				direc = rslt.getString(3);
				telef = rslt.getString(4);
				alumno = new Alumno(dni, apeNom, direc, telef);
				listaAlumnos.add(alumno);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				
				if (stmt != null) stmt.close();
				
				if (con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return listaAlumnos;
	}
	
	// método que elimina un alumno. mensaje con resultado de accion
	public String borrarAlumnoPorDni(String dni) {
		String res = "";
		int iRes = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConexion();
			
			String query = "DELETE FROM ALUMNO WHERE DNI = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dni);
			
			iRes = pstmt.executeUpdate();
			if (iRes>0) {
				res = "Se ha eliminado un User";
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			res = "No se ha conectado bn";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = "No se ha conectado bn";
		}
		finally {
			try {
				
				if (pstmt != null) pstmt.close();
				
				if (con != null) con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return res;
	}
	
	
}
