package model;
import model.dao.DB;
import view.TelaLogin;

public class MainPrograma {
	public static void main(String[] args) {
		DB dados = new DB();
		Central central = dados.recuperarCentral();
		
		new TelaLogin();
	}
}
