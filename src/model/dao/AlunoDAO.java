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
	public Aluno cadastrarAluno(String nome, String email1, String email2, String senha1, String senha2, String matricula, String sex) 
			throws EmailInvalidoException,EmailDiferenteException, SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException, AlunoJaMatriculadoException, EmailJaCadastradoException {
		nome.trim();
		email1.trim();
		email2.trim();
		sex.trim();
		matricula.trim();
		Sexo sexo = Sexo.valueOf(sex.toUpperCase());
		if(nome.isBlank() || email1.isBlank() || email2.isBlank() || senha1.isBlank() || senha2.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if (!email1.equals(email2)) {
			throw new EmailDiferenteException();
		}else if(senha1.length() < 8){
			throw new SenhaMuitoPequenaException();
		}else if (!senha1.equals(senha2)) {
			throw new SenhaDiferenteException();
		} else{
			Central.validarEmail(email1);
			Aluno a = new Aluno(nome, sexo, matricula, email1, senha1);
			adicionarAluno(a);
			dados.salvarCentral(central);
			return a;
		}
	}
	
	
	@Override
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!central.getTodosOsAlunos().isEmpty()) {
			central.verificarMatricula(a.getMatricula());
			central.emailExiste(a.getEmail()); 
		}
		central.getTodosOsAlunos().add(a);
		return true;
	}

	@Override
	public boolean excluirAluno(Aluno a) {
		System.out.println(a);
		for (Aluno aluno: alunos) {
			if (aluno.getMatricula().equals(a.getMatricula())){
				alunos.remove(aluno);
				dados.salvarCentral(central);
				return true;
			}
		}
		return false;
	}

	@Override
	public void editarAluno(Central central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		Sexo sexo = Sexo.valueOf(sex.toUpperCase());
		if(nome.isBlank() || email.isBlank() || senha.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if(senha.length() < 8){
			throw new SenhaMuitoPequenaException();
		}
		central.emailExiste(email, aluno);
		Central.validarEmail(email);
		
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
