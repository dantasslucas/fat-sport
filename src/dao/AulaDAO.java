package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import models.Aluno;
import models.Aula;
import models.Coletivo;
import models.Individual;
import models.Professor;

public class AulaDAO {
	
	private static final String SEPARADOR = ",";

	public static Set<Aula> readAula(String caminhoDoArquivo)
	{
		Set<Aula> aulas = new HashSet<>();
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoDoArquivo)));
			String linha = null;
			
			while((linha = reader.readLine()) != null)
			{
				String[] atributos = linha.split(SEPARADOR);
				String modalidade = atributos[0];
				
				if (modalidade.equals("T")) {
					int id = Integer.parseInt(atributos[1]);
					int dia = Integer.parseInt(atributos[2]);
					String horario = atributos[3];
					
					int professorID = Integer.parseInt(atributos[4]);
					
					String proposito = atributos[5];
					aulas.add(new Individual(id, dia, horario, professorID, proposito, modalidade));
				}
				else {
					int id = Integer.parseInt(atributos[1]);
					int dia = Integer.parseInt(atributos[2]);
					String horario = atributos[3];
					
					int professorID = Integer.parseInt(atributos[4]);
					
					int minimo = Integer.parseInt(atributos[5]);
					int maximo = Integer.parseInt(atributos[6]);
					
					aulas.add(new Coletivo(id, dia, horario, professorID, minimo, maximo, modalidade));
				}
				
				//aulas.add(new Aluno(id, nome, cpf));
				//System.out.println(id+","+nome+","+cpf); //apenas para teste
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return aulas;
	}
}
