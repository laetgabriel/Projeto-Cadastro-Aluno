package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;

import controller.AlunoController;
import model.dto.AlunoCadastroDTO;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.CredenciaisInvalidasException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.EmailNaoEncontradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;


public class AlunoServices{

	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();

	public static boolean validarEmail(String login) throws EmailInvalidoException {
		String padraoEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(padraoEmail, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(login);
		if (!matcher.matches()) {
			throw new EmailInvalidoException();
		}
		return matcher.matches();
	}

	public boolean emailExiste(String email) throws EmailJaCadastradoException {
		for (Aluno aluno : todosOsAlunos) {
			if (aluno.getEmail().equalsIgnoreCase(email)) {
				throw new EmailJaCadastradoException();
			}
		}
		return false;
	}

	public boolean emailExiste(AlunoCadastroDTO alunoDTO) throws EmailJaCadastradoException {
		for (Aluno a : todosOsAlunos) {
			if (!a.getMatricula().equals(alunoDTO.matricula())) {
				if (a.getEmail().equalsIgnoreCase(alunoDTO.email2())) {
					throw new EmailJaCadastradoException();
				}
			}
		}
		return false;
	}

	public boolean verificarMatricula(String matricula) throws AlunoJaMatriculadoException {
		for (Aluno aluno: todosOsAlunos) {
			if (aluno.getMatricula().equals(matricula)){
				throw new AlunoJaMatriculadoException();
			}

		}return false;
	}

	public boolean verificarMatricula(AlunoDTO alunoDTO, String matricula) throws AlunoJaMatriculadoException {
		Aluno a = new Aluno(alunoDTO);
		for (Aluno aluno: todosOsAlunos) {
			if (aluno != a) {
				if (aluno.getMatricula().equals(matricula)){
					throw new AlunoJaMatriculadoException();
				}
			}

		}return false;
	}

	public String recuperarSenhaPeloEmail (String email) throws EmailNaoEncontradoException {
	
		for (Aluno aluno: todosOsAlunos) {
			if (aluno.getEmail().equalsIgnoreCase(email)) {
				return aluno.getSenha();
			}
		}
		throw new EmailNaoEncontradoException();
	}



	public AlunoDTO login(String email, String senha, AlunoServices alunoServices) throws CredenciaisInvalidasException {

		for(Aluno a: alunoServices.getTodosOsAlunos()) {
			if (a.getEmail().equalsIgnoreCase(email) && a.getSenha().equals(senha)) {
				AlunoDTO alunoDTO = new AlunoDTO(a.getNome(), a.getEmail(), a.getSenha(), a.getMatricula(), a.getSexo());				
				return alunoDTO;
			}
		}throw new CredenciaisInvalidasException();
	}
	

	public void mandarSenhaPorEmail(String email) throws EmailNaoEncontradoException, EmailException{
		String senha = recuperarSenhaPeloEmail(email);
		Mensageiro.enviarEmail(email, "Recuperação de Senha" ,"Sua senha atual é: " + senha);
	}
	
	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}

	public void setTodosOsAlunos(ArrayList<Aluno> alunos) {
		todosOsAlunos = alunos;
	}

}