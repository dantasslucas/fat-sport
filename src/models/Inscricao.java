package models;

public class Inscricao {
	
	private int alunoID;
	private int aulaID;
	
	
	public Inscricao(int alunoID, int aulaID) {
		super();
		this.alunoID = alunoID;
		this.aulaID = aulaID;
	}
	
	public int getAlunoID() {
		return alunoID;
	}
	public void setAlunoID(int alunoID) {
		this.alunoID = alunoID;
	}
	public int getAulaID() {
		return aulaID;
	}
	public void setAulaID(int aulaID) {
		this.aulaID = aulaID;
	}
}
