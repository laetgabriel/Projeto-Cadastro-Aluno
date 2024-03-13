package controller;

import model.Aluno;
import model.Central;
import model.dao.AlunoDAO;
import model.dao.DB;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public class AlunoController {
	
	private static Aluno usuario;
	private DB dados = new DB();
	private Central central = dados.recuperarCentral();
	private AlunoDAO alunoDAO;
	
	public AlunoController() {
		this.alunoDAO = new AlunoDAO();
	}
	
	public Aluno cadastrarAluno(String nome, String email1, String email2,
			String senha1, String senha2, String matricula, String sex) 
			throws EmailInvalidoException,EmailDiferenteException, 
			SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException{
		return alunoDAO.cadastrarAluno(nome, email1, email2, senha1, senha2, matricula, sex);
	}
	
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		return alunoDAO.adicionarAluno(a);
	}
	
	public boolean excluirAluno(Aluno a) {
		return alunoDAO.excluirAluno(a);
	}
	
	public void editarAluno(Central central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException{
		alunoDAO.editarAluno(central, aluno, nome, email, senha, matricula, sex);
	}
	
	public String recuperarEmailPorMatricula(Central central, String matricula) {
		return alunoDAO.recuperarEmailPorMatricula(central, matricula);
	}
	
	public Aluno getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Aluno u) {
		usuario = u;
	}

	public DB getDados() {
		return dados;
	}
	
	public Central getAlunoDAO() {
		return central;
	}
	
}
