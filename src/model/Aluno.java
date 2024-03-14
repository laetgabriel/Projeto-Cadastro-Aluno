package model;

import model.dto.AlunoCadastroDTO;
import model.dto.AlunoDTO;

public class Aluno{
	
	private String nome;
	private String email;
	private String senha;
	private String senha2;
	private String matricula;
	private Sexo sexo;
	
	public Aluno (AlunoCadastroDTO alunoCadastroDTO) {
		nome = alunoCadastroDTO.nome();
		email = alunoCadastroDTO.email();
		senha = alunoCadastroDTO.senha();
		matricula = alunoCadastroDTO.matricula();
		sexo = alunoCadastroDTO.sexo();
	}
	
	public Aluno(AlunoDTO alunoDTO) {
		nome = alunoDTO.nome();
		email = alunoDTO.email();
		senha = alunoDTO.senha();
		matricula = alunoDTO.matricula();
		sexo = alunoDTO.sexo();
	}
	
	public Aluno(){
		
	}
	
	public String toString() {
		return "Nome: " + getNome() + "\n" +
				"Sexo: " + getSexo().toString().toLowerCase() + "\n" +
				"Matricula: " + getMatricula() + "\n" +
				"Email: " + getEmail() + "\n";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String m) {
		matricula = m;
	}
	
	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}	
	

}