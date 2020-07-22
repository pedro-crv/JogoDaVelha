import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Rede {
    Socket socket = null;
    ObjectInputStream is = null;
    ObjectOutputStream os = null;
    JogoDaVelha jogo;
    boolean temDados = true;

    public Rede(JogoDaVelha jogo, String IP, int porto) {
        this.jogo = jogo;
        
        try {
            socket = new Socket(IP, porto);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(jogo, "Servidor não encontrado!\n   " + e, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(jogo, "Não pode trocar dados com o servidor!\n   " + e, "Erro",
            JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public String recebeMsg() {
        try {
            return is.readUTF();
        } catch (IOException e) {
            temDados = false;
            return "";
        }
    }

    public void enviaClique(Bloco bloco) {
        try {
            os.writeObject(bloco);
        } catch (IOException e) {
            temDados = false;
        }
    }

    public boolean continua() {
        return temDados;
    }

    public void descarregaEnvio() {
        try {
            os.flush();
        } catch (IOException e) {
            temDados = false;
        }
    }

}