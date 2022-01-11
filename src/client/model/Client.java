package client.model;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import client.controller.ControllerLobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    @FXML public static TextArea chat;

    public static ControllerLobby controllerLobby;

    public static Socket clientSocket;
    public static BufferedReader in;
    public static PrintWriter out;

    public static void connection(Socket clientSocket, PrintWriter out, BufferedReader in, ControllerLobby controllerLobby) {
        Client.clientSocket = clientSocket;
        Client.in = in;
        Client.out = out;

        Client.controllerLobby = controllerLobby;
    }

    public static void initPseudo(String pseudo) {
        //envoie du pseudo
        out.println(pseudo);
        out.flush();
    }

    public static void envoieMessage(String msg) {
        out.println(msg);
        out.flush();
    }

    public static void recevoir() {
        Thread recevoir = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null){
                        if (msg.contains("/play")) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        controllerLobby.switchToSceneJouer((Stage) controllerLobby.DragAndDrop.getScene().getWindow());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        addText(msg);
//                        System.out.println(msg);
                        msg = in.readLine();
                    }
                    System.out.println("Serveur déconecté");
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        recevoir.start();
    }

    public static void addText(String msg) {
        chat.appendText("\n" + msg);
    }
}
