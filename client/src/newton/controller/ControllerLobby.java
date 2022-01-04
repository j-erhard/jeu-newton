package newton.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ControllerLobby extends Controller{
    public ControllerLobby() {
        super();
    }

    @FXML TextArea port;
    @FXML TextArea adresse;
    @FXML TextArea pseudo;
    @FXML TextArea message;
    @FXML TextArea chat;
    @FXML
    Button DragAndDrop;

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;

    @FXML
    public void initialize() {
        adresse.setText("localhost");
        port.setText("6666");
        pseudo.setText("Julien");
    }

    @FXML
    public void switchToJouer(ActionEvent event) throws IOException {
        if (out !=null) {
            out.println("/play");
            out.flush();
        }
        switchToScene(event);
    }

    public void connection(ActionEvent actionEvent) throws IOException {
        clientSocket = new Socket(adresse.getText(), Integer.parseInt(port.getText()));
        //flux pour envoyer
        out = new PrintWriter(clientSocket.getOutputStream());
        //flux pour recevoir
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        initPseudo();
        recevoir();
    }

    public void initPseudo() {
        //envoie du pseudo
        String text = pseudo.getText();
        out.println(text);
        out.flush();
    }

    public void envoieMsg() {
        if (out !=null) {
            out.println(message.getText());
            out.flush();
        }
    }

    public void recevoir() {
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
                                        switchToSceneJouer((Stage) DragAndDrop.getScene().getWindow());
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

    public void addText(String msg) {
        chat.appendText("\n" + msg);
    }
}
