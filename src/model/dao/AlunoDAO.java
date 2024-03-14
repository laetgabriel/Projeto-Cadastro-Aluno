package model.dao;

import java.util.ArrayList;

import model.Aluno;
import model.AlunoServices;
import model.dto.AlunoCadastroDTO;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaMuitoPequenaException;

public class AlunoDAO implements DAO{

	private DB dados = new DB();
	private AlunoServices aServices = dados.recuperarDados();

	
	@Override
	public void cadastrarAluno(AlunoCadastroDTO a) throws AlunoJaMatriculadoException, EmailJaCadastradoException {
		Aluno aluno = new Aluno(a);
		adicionarAluno(aluno);
		dados.salvarDados(aServices);
		
	}
	
	@Override
	public void adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!aServices.getTodosOsAlunos().isEmpty()) {
			aServices.verificarMatricula(a.getMatricula());
			aServices.emailExiste(a.getEmail()); 
		}
		aServices.getTodosOsAlunos().add(a);
	}

	@Override
	public void excluirAluno(AlunoDTO a) {
		for (Aluno aluno: aServices.getTodosOsAlunos()) {
			if (aluno.getMatricula().equals(a.matricula())) {
				aServices.getTodosOsAlunos().remove(aluno);
				dados.salvarDados(aServices);
				break;
			}
		}
	}
	

	@Override
	public void editarAluno(AlunoCadastroDTO alunoCadastroDTO) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		for(Aluno aluno:aServices.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(alunoCadastroDTO.matricula())){
				aluno.setNome(alunoCadastroDTO.nome());
				aluno.setEmail(alunoCadastroDTO.email());
				aluno.setSenha(alunoCadastroDTO.senha2());
				aluno.setMatricula(alunoCadastroDTO.matricula());
				aluno.setSexo(alunoCadastroDTO.sexo());
				dados.salvarDados(aServices);
				break;
			}
		}

	}	
	

	public DB getDados() {
		return dados;
	}

	public AlunoServices getAlunoServices() {
		return aServices;
	}

	public ArrayList<Aluno> getAlunos() {
		return aServices.getTodosOsAlunos();
	}

}
