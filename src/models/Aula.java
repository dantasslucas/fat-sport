package models;

public class Aula {
	
	private int id;
	private int dia;
	private String horario;
	private int professorID;
	
	public Aula(int id, int dia, String horario, int professorID) {
		super();
		this.id = id;
		this.dia = dia;
		this.horario = horario;
		this.professorID = professorID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProfessorID() {
		return professorID;
	}
	
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}
	
}
