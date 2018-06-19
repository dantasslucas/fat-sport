package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import models.Inscricao;

public class InscricaoDAO {
	
private static final String SEPARADOR = ",";
	
	public static Set<Inscricao> readInscricoes(String caminhoDoArquivo)
	{
		Set<Inscricao> inscricoes = new HashSet<>();
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoDoArquivo)));
			String linha = null;
			
			while((linha = reader.readLine()) != null)
			{
				String[] atributos = linha.split(SEPARADOR);
				int alunoID = Integer.parseInt(atributos[0]);
				int aulaID = Integer.parseInt(atributos[1]);
				
				inscricoes.add(new Inscricao(alunoID, aulaID));
				//System.out.println(id+","+nome+","+cpf); //apenas para teste
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return inscricoes;
	}
}
