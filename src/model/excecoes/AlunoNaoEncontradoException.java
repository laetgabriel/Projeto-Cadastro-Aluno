package model.excecoes;

public class AlunoNaoEncontradoException extends Exception {
	
	public String getMessage() {
		return "Aluno informado não encontrado!";
	}
}
