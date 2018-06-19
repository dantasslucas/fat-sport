package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.AlunoDAO;
import dao.AulaDAO;
import dao.InscricaoDAO;
import dao.ProfessorDAO;
import models.Aluno;
import models.Aula;
import models.Coletivo;
import models.Individual;
import models.Inscricao;
import models.Professor;

public class Main {
	
	public static void main(String[] args) {
		Set<Professor> professores = null;
		Set<Aluno> alunos = null;
		Set<Aula> aulas = null;
		Set<Inscricao> inscricoes = null;
		
		JOptionPane.showMessageDialog(null, "Escolha o CSV de Professores");
		
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(new JFrame("Escolha o CSV de professores"));
		if (result == 0) {
			File f = fc.getSelectedFile();
			professores = ProfessorDAO.readProfessores(f.getAbsolutePath());
		}
		
		JOptionPane.showMessageDialog(null, "Escolha o CSV de Alunos");
		
		result = fc.showOpenDialog(new JFrame("Escolha o CSV de alunos"));
		if (result == 0) {
			File f = fc.getSelectedFile();
			alunos = AlunoDAO.readAlunos(f.getAbsolutePath());
		}
		
		JOptionPane.showMessageDialog(null, "Escolha o CSV de Aulas");
		
		result = fc.showOpenDialog(new JFrame("Escolha o CSV de aulas"));
		if (result == 0) {
			File f = fc.getSelectedFile();
			aulas = AulaDAO.readAula(f.getAbsolutePath());
		}
		
		JOptionPane.showMessageDialog(null, "Escolha o CSV de Inscricoes");
		
		result = fc.showOpenDialog(new JFrame("Escolha o CSV de inscricoes"));
		if (result == 0) {
			File f = fc.getSelectedFile();
			inscricoes = InscricaoDAO.readInscricoes(f.getAbsolutePath());
		}
		
		try {
			FileWriter writer = new FileWriter("D:\\Meus Arquivos\\Documentos\\Projeto - FATSports\\CSVs\\CSVs\\relatorio.csv");
			
			for (Aluno aluno : alunos) {
				List<Inscricao> inscricoesAluno = inscricoes.stream()
						.filter(i -> i.getAlunoID() == aluno.getId())
						.collect(Collectors.toList());
				Set<Aula> aulasAluno = new HashSet<>();
				
				for (Inscricao inscricao : inscricoesAluno) {
					Aula aula = aulas.stream()
							.filter(a -> a.getId() == inscricao.getAulaID()).findFirst().get();
					aulasAluno.add(aula);
				}
				
				for (Aula aula : aulasAluno) {
					List<Aula> conflitos = aulasAluno.stream()
							.filter(a -> (a.getHorario().equals(aula.getHorario()) &&
							a.getDia() == aula.getDia() &&
							a.getId() != aula.getId()))
							.collect(Collectors.toList());
					
					if (conflitos.size() >= 1) {
						JOptionPane.showMessageDialog(null,
								"Conflito de Horario para Aluno " + aluno.getNome() +
								" Entre as aulas: " + aula.getId() + " e " + conflitos.stream().findFirst().get().getId());
					}
				}
				
			}
			
			for (Aula aula : aulas) {
				
				if (aula instanceof Coletivo) {
					Coletivo aulaColetiva = (Coletivo) aula;
					
					int quantidadeAlunos = 0;
					
					for (Inscricao inscricao : inscricoes) {
						if (inscricao.getAulaID() == aulaColetiva.getId()) {
							quantidadeAlunos++;
						}
					}
	
					if (quantidadeAlunos >= aulaColetiva.getMinimo()) {
						writer.append("Confirmada");
					} else {
						writer.append("Cancelada");
					}
					
					writer.append(",");
					writer.append(aulaColetiva.getModalidade());
					writer.append(",");
					writer.append(String.valueOf(aulaColetiva.getDia()));
					writer.append(",");
					
					Optional<Professor> professor = professores.stream().parallel()
							.filter(p -> p.getId() == aulaColetiva.getProfessorID()).findFirst();
					
					List<Aula> aulasIguaisProfessor = aulas.stream().filter(
							a -> (a.getProfessorID() == aulaColetiva.getProfessorID() && 
							a.getDia() == aulaColetiva.getDia() &&
							a.getHorario().equals(aulaColetiva.getHorario()) && 
							a.getId() != aulaColetiva.getId())).collect(Collectors.toList());
					
					if (aulasIguaisProfessor.size() >= 1) {
						writer.append("(CONFLITO DE HORARIO)");
					}
					
					writer.append(professor.get().getNome());
					writer.append(",");
					writer.append(aulaColetiva.getHorario());
					writer.append(",");
					writer.append(String.valueOf(quantidadeAlunos));
					
					
					
			        writer.append('\n');
				}
				else {
					Individual aulaIndividual = (Individual) aula;
					writer.append("Confirmada");
					writer.append(",");
					writer.append(aulaIndividual.getModalidade());
					writer.append(",");
					writer.append(String.valueOf(aulaIndividual.getDia()));
					writer.append(",");
					
					Optional<Professor> professor = professores.stream().parallel()
							.filter(p -> p.getId() == aulaIndividual.getProfessorID()).findFirst();
					
					List<Aula> aulasIguaisProfessor = aulas.stream().filter(
							a -> (a.getProfessorID() == aulaIndividual.getProfessorID() && 
							a.getDia() == aulaIndividual.getDia() &&
							a.getHorario().equals(aulaIndividual.getHorario()) && 
							a.getId() != aulaIndividual.getId())).collect(Collectors.toList());
					
					if (aulasIguaisProfessor.size() >= 1) {
						writer.append("(CONFLITO DE HORARIO)");
					}
					
					writer.append(professor.get().getNome());
					writer.append(",");
					writer.append(aulaIndividual.getHorario());
					writer.append(",");
					writer.append("1");
					writer.append(",");
					writer.append(aulaIndividual.getProposito());
					
					writer.append("\n");
				}
			}
			
			writer.flush();
	        writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
