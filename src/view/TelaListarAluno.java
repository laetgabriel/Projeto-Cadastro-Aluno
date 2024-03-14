package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Aluno;
import view.fabricacomponentes.FabricaIcones;
import view.fabricacomponentes.FabricaJLabel;
import view.fabricacomponentes.FabricaJMenuBar;

public class TelaListarAluno extends TelaPadrao {

    public TelaListarAluno() {
        super("LISTA - ALUNOS");
        configurarComponentes();
        setVisible(true);
    }
    
    @Override
    public void configurarComponentes() {
    	adicionarLabels();
        exibirAlunos();
        adicionarIcones();
        adicionarMenuBar();
    }

    private void exibirAlunos() {
        ArrayList<Aluno> listaDeAlunos = getAlunoController().getAlunoServices().getTodosOsAlunos();
        
        String[] colunas = {"Nome", "Email", "Matrícula"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for (Aluno aluno : listaDeAlunos) {
            String nome = aluno.getNome();
            String matricula = aluno.getMatricula();
            String email = aluno.getEmail();
            modelo.addRow(new Object[]{nome, email, matricula});
        }

        JTable tabelaAlunos = new JTable(modelo);
        tabelaAlunos.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        scrollPane.setBounds(280, 310, 350, 350);
        add(scrollPane);
    }

    private void adicionarIcones() {
        JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 170, 70, 94);
        add(iconeIf);
        
        JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
        add(imagemFundo);
    }
    

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("ALUNOS", 440, 200, 200, 30, Color.BLACK, 30);
		add(lTitulo);
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}
	
   
    public static void main(String[] args) {
        new TelaListarAluno();
    }
}