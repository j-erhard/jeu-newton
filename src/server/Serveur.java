package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serveur {

    public static ArrayList<Joueur> joueurs = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(6666);

        while (true) {

            System.out.println("Server is Awaiting a new client");
            Socket s = ss.accept();
            Joueur j = new Joueur(s);
            joueurs.add(j);
            Serveur.redistribuerMessage(j, "nous a rejoint !");
            System.out.println(joueurs);
        }
    }

    public static void redistribuerMessage(Joueur expediteur, String msg) {
        for (Joueur joueur : joueurs) {
            joueur.sendMessage(expediteur.getPseudo(), msg);
        }
    }
}
