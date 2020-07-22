import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class ServindoCliente extends Thread {
    Socket clientSocket;
    StreamsDeSaida saida;
    Logica logica;
    int numJogador, numJogadorAdversario;
    int x, y;
    boolean clienteVivo[] = { true, true };

    public ServindoCliente(Socket clientSocket, StreamsDeSaida saida, Logica logica) {
        this.clientSocket = clientSocket;
        this.saida = saida;
        this.logica = logica;
        numJogador = saida.cont++;
        numJogadorAdversario = 1 - numJogador;
    }

    public void run() {
        try {
        ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
        saida.os[numJogador] = new ObjectOutputStream(clientSocket.getOutputStream());

        System.out.println("Run ServindoCliente");

        
        System.out.println(is.readObject());

        saida.os[numJogador].close();
        is.close();
        clientSocket.close();

        } catch(IOException e) {

            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch(NoSuchElementException e) {

        } catch(ClassNotFoundException e) {
        
        }
    }

    public void enviaContagemRegressiva() {
        try {
            envia("3");
            sleep(1000);
            envia("2");
            sleep(1000);
            envia("1");
            sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public void preparaTemporizador() {
        new Thread() {
            public void run() {
                while (clienteVivo[numJogador] && clienteVivo[numJogadorAdversario]) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }

    private void envia(String s) {
        if (clienteVivo[numJogador]) {
            try {
                saida.os[numJogador].writeUTF(s);
            } catch (IOException e) {
                clienteVivo[numJogador] = false;
            }
        }
    }

};
