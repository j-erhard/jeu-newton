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
import newton.model.Client;

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
    @FXML public Button DragAndDrop;

    @FXML
    public void initialize() {
        adresse.setText("localhost");
        port.setText("6666");
        pseudo.setText("Julien");
    }

    @FXML
    public void switchToJouer(ActionEvent event) throws IOException {
        if (Client.out !=null) {
            Client.envoieMessage("/play");
        }
        switchToScene(event);
    }

    public void connection(ActionEvent actionEvent) throws IOException {
        Socket clientSocket = new Socket(adresse.getText(), Integer.parseInt(port.getText()));
        //flux pour envoyer
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        //flux pour recevoir
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Client.connection(clientSocket, out, in, this);
        Client.initPseudo(pseudo.getText());
        Client.chat = chat;
        Client.recevoir();
    }

    public void envoieMsg() {
        Client.envoieMessage(message.getText());
    }
}
