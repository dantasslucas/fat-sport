package models;

public class Coletivo extends Aula {
	
	private int minimo;
	private int maximo;
	private String modalidade;
	
	public Coletivo(int id, int dia, String horario, int professorID, int minimo, int maximo, String modalidade) {
		super(id, dia, horario, professorID);
		this.minimo = minimo;
		this.maximo = maximo;
		this.setModalidade(modalidade);
	}


	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}


	public String getModalidade() {
		return modalidade;
	}


	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
}
