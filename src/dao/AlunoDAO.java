package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import models.Aluno;

public class AlunoDAO {
	
	private static final String SEPARADOR = ",";
	
	public static Set<Aluno> readAlunos(String caminhoDoArquivo)
	{
		Set<Aluno> alunos = new HashSet<>();
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoDoArquivo)));
			String linha = null;
			
			while((linha = reader.readLine()) != null)
			{
				String[] atributos = linha.split(SEPARADOR);
				int id = Integer.parseInt(atributos[0]);
				String nome = atributos[1];
				String cpf = atributos[2];
				
				alunos.add(new Aluno(id, nome, cpf));
				//System.out.println(id+","+nome+","+cpf); //apenas para teste
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return alunos;
	}
}
