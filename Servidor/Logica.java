import java.awt.Dimension;
import java.awt.Rectangle;

public class Logica extends Thread {

	public boolean testarVitoria(int jogador, Bloco[] blocos) {
		if( blocos[0].quem == jogador && blocos[1].quem == jogador && blocos[2].quem == jogador ) {
			return true;
		}
		
		if( blocos[3].quem == jogador && blocos[4].quem == jogador && blocos[5].quem == jogador ) {
			return true;
		}
		
		if( blocos[6].quem == jogador && blocos[7].quem == jogador && blocos[8].quem == jogador ) {
			return true;
		}
		
		if( blocos[0].quem == jogador && blocos[3].quem == jogador && blocos[6].quem == jogador ) {
			return true;
		}
		
		if( blocos[1].quem == jogador && blocos[4].quem == jogador && blocos[7].quem == jogador ) {
			return true;
		}
		
		if( blocos[2].quem == jogador && blocos[5].quem == jogador && blocos[8].quem == jogador ) {
			return true;
		}
		
		if( blocos[0].quem == jogador && blocos[4].quem == jogador && blocos[8].quem == jogador ) {
			return true;
		}
		
		if( blocos[2].quem == jogador && blocos[4].quem == jogador && blocos[6].quem == jogador ) {
			return true;
		}
		
		return false;
	}

	public class Bloco {
		int quem = 0;
	}

}