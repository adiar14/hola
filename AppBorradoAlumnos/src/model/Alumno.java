package model;

public class Alumno {

	private String dni;
	public String getDni() {
		return dni;
	}

	public String getApeNom() {
		return apeNom;
	}

	public String getDirec() {
		return direc;
	}

	public String getTelef() {
		return telef;
	}

	private String apeNom;
	private String direc;
	private String telef;
	
	public Alumno(String dni, String apeNom, String direc, String telef) {
		this.dni = dni;
		this.apeNom = apeNom;
		this.direc = direc;
		this.telef = telef;
	}
	
	
}
