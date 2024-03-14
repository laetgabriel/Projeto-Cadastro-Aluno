package model.dto;

import model.Sexo;

public record AlunoCadastroDTO(String nome, String email, String email2, String senha, String senha2, String matricula, Sexo sexo) {
	
	
}
