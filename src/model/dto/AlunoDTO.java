package model.dto;


import model.Sexo;

public record AlunoDTO(String nome, String email, String senha, String matricula, Sexo sexo) {

}
