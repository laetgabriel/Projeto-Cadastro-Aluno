package model.dto;

import model.Aluno;
import model.Sexo;

public record AlunoCadastroDTO(String nome, String email, String email2, String senha, String senha2, String matricula, Sexo sexo) {
	
	public Aluno toEntity() {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setSenha(senha);
		aluno.setSenha2(senha2);
		aluno.setSexo(sexo);
		aluno.setMatricula(matricula);
		return aluno;
	}
	
}
