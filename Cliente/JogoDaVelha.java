import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaVelha extends JFrame{
	
	ImageIcon iconCirculo = new ImageIcon(getClass().getResource("circulo.png"));
	ImageIcon iconX = new ImageIcon(getClass().getResource("x.png"));

	JPanel pTela = new JPanel(new GridLayout(3, 3, 10, 10));
	
	Botao[] botoes = new Botao[9];

	Bloco[] blocos = new Bloco[9];
	
	int rodadas = 0;

	int posInicial = 0;
	
	Rede rede = new Rede(this, "127.0.0.1", 12345);

	final int JOGADOR_1 = 1;
	final int JOGADOR_2 = 2;
	
	int jogadorVez = JOGADOR_1;
	
	JLabel lInformacao = new JLabel("Jogador " + JOGADOR_1);
	
	public JogoDaVelha() {
		configurarJanela();
		configurarTela();
	}
	
	public void configurarTela() {
		add(BorderLayout.CENTER,pTela);
		add(BorderLayout.NORTH,lInformacao);
		pTela.setBackground(Color.BLACK);
		lInformacao.setFont(new Font("Arial",Font.BOLD,35));
		lInformacao.setForeground(new Color(50,200,50));
		lInformacao.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < 9; i++) {
			Botao botao = new Botao();
			botoes[i] = botao;
			pTela.add(botao);
		}
	}
	
	public void mudarVez(){
		if(jogadorVez == 1) {
			jogadorVez = 2;
			lInformacao.setText("Jogador 2");
			lInformacao.setForeground(Color.RED);
		} else {
			jogadorVez = 1;
			lInformacao.setText("Jogador 1");
			lInformacao.setForeground(new Color(50, 200, 50));
		}
	}
	
	public void configurarJanela() {
		setTitle("Jogo da Velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JogoDaVelha();
	}
	
	public class Botao extends JButton{
		int quem = 0;
		int pos = posInicial++;
		public Botao() {
			setBackground(Color.WHITE);
			addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
					System.out.println("Action Perfomed");
					Bloco bloco = new Bloco(jogadorVez, pos);
					System.out.println(bloco);
					rede.enviaClique(bloco);
                }
			});
		}
	}
	
}

