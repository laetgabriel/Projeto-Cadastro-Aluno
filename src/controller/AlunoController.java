package controller;

import model.Aluno;
import model.Central;
import model.Sexo;
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
	
	public void cadastrarAluno(String nome, String email1, String email2, String senha1, String senha2, String matricula, String sex) throws EmailInvalidoException,EmailDiferenteException, SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException{
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
			alunoDAO.cadastrarAluno(nome, email1, email2, senha1, senha2, matricula, sex);
		}
		
	}
	
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!central.getTodosOsAlunos().isEmpty()) {
			central.verificarMatricula(a.getMatricula());
			central.emailExiste(a.getEmail()); 
		}
		return alunoDAO.adicionarAluno(a);
	}
	
	public boolean excluirAluno(Aluno a) {
		return alunoDAO.excluirAluno(a);
	}
		
	
	public void editarAluno(Central central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException{
		if(nome.isBlank() || email.isBlank() || senha.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if(senha.length() < 8){
			throw new SenhaMuitoPequenaException();
		}
		central.emailExiste(email, aluno);
		Central.validarEmail(email);
		
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
