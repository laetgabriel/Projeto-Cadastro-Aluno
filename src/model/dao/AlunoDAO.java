package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Central;
import model.Sexo;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public class AlunoDAO implements DAO{

	private DB dados = new DB();
	private Central central = dados.recuperarCentral();
	private ArrayList<Aluno> alunos = central.getTodosOsAlunos();

	
	@Override
	public void cadastrarAluno(String nome, String email1, String email2, String senha1, String senha2, String matricula, String sex)  {
		dados.salvarCentral(central);
}
	
	
	@Override
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		central.getTodosOsAlunos().add(a);
		return true;
	}

	@Override
	public boolean excluirAluno(Aluno a) {
		for (Aluno aluno: central.getTodosOsAlunos()) {
			if (aluno.getMatricula().equals(a.getMatricula())){
				central.getTodosOsAlunos().remove(aluno);
				dados.salvarCentral(central);
				break;
			}
		}
		
		return true;
	}
	@Override
	public void editarAluno(Central central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		Sexo sexo = Sexo.valueOf(sex.toUpperCase());
		for(int i = 0; i < alunos.size(); i++) {
			if(alunos.get(i).getMatricula().equals(aluno.getMatricula())){
				Aluno a = alunos.get(i);
				a.setEmail(email);
				a.setNome(nome);
				a.setSenha(senha);
				a.setSexo(sexo);
				a.setMatricula(matricula);
				alunos.set(i, a);
				central.setTodosOsAlunos(alunos);
				return;
			}
		}
		dados.salvarCentral(central);
		
	}	

	@Override
	public String recuperarEmailPorMatricula(Central central, String matricula) {
		for(Aluno aluno: central.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(matricula)) {
				return aluno.getEmail();
			}
		}
		return null;
	}
	

}
