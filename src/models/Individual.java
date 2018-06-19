package models;

public class Individual extends Aula {
	
	private String proposito;
	private String modalidade;
	
	public Individual(int id, int dia, String horario, int professorID, String proposito, String modalidade) {
		super(id, dia, horario, professorID);
		this.proposito = proposito;
		this.modalidade = modalidade;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

}
