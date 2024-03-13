package model;

import model.dto.AlunoDTO;

public class Aluno{
	private AlunoDTO alunoDTO = new AlunoDTO();
	
	private String nome;
	private String email;
	private String senha;
	private String matricula;
	private Sexo sexo;
	
	public Aluno(String nome, String email, String senha, Sexo sexo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.sexo = sexo;
	}
	public Aluno(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public Aluno(String nome, Sexo sexo, String matricula, String email, String senha) {
		this.nome = nome;
		this.sexo = sexo;
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		
	}
	
	public Aluno(String nome, String matricula, String email, String senha) {
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
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
}