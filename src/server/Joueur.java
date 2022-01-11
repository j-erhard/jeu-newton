package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Joueur {
    private Socket s;
    private String pseudo;
    private int score;

    public InputStreamReader in;
    public BufferedReader bf;
    public PrintWriter out;

    public Joueur(Socket s) throws IOException {
        this.s = s;
        in = new InputStreamReader(s.getInputStream());
        bf = new BufferedReader(in);
        out = new PrintWriter(s.getOutputStream());

        initPseudo();
        getMessages();
    }

    private void getMessages() {
        Thread recevoir= new Thread(new Runnable() {
            String msg ;
            @Override
            public void run() {
                boolean inLobby = true;

                while (inLobby){

                    try {
                        msg = bf.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Serveur.redistribuerMessage(Joueur.this, msg);

                    System.out.println("client: " + pseudo + " " + msg);

                    inLobby = msg!=null;

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        recevoir.start();
    }

    private void initPseudo() {
        // recupérer nom du client:
        try {
            pseudo = bf.readLine();
            System.out.println("Pseudo enregistré: " + pseudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String pseudo, String msg) {
        out.println(pseudo + " " + msg);
        out.flush();
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
