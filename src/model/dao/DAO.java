package model.dao;

import model.Aluno;
import model.Central;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public interface DAO {
	
	void cadastrarAluno(String nome, String email1, String email2, String senha1, String senha2, String matricula, String sex) ;
	
	boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException;

	boolean excluirAluno(Aluno a);
	
	void editarAluno(Central central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException;
	
	String recuperarEmailPorMatricula(Central central, String matricula);
}
