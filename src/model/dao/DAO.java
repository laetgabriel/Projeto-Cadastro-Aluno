package model.dao;

import model.Aluno;
import model.AlunoServices;
import model.dto.AlunoCadastroDTO;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public interface DAO {
	
	void cadastrarAluno(AlunoCadastroDTO a) 
			throws EmailInvalidoException,EmailDiferenteException, SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException;
	
	void adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException;

	void excluirAluno(AlunoDTO a);
	
	void editarAluno(AlunoCadastroDTO a) throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException;
	
}
