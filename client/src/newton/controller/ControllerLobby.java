package newton.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ControllerLobby extends Controller{
    public ControllerLobby() {
        super();
    }

    @FXML TextArea port;
    @FXML TextArea adresse;
    @FXML TextArea message;

    Socket socketConnection;
    PrintWriter pr;

    @FXML
    public void initialize() {
        adresse.setText("localhost");
        port.setText("6666");
    }

    public void connection(ActionEvent actionEvent) throws IOException {
        socketConnection = new Socket(adresse.getText(), Integer.parseInt(port.getText()));
        pr = new PrintWriter(socketConnection.getOutputStream());

        String text ="Client -> julien1";
        pr.println(text);
        pr.flush();
    }

    public void envoieMsg() {
        if (pr!=null) {
            pr.println("Client -> julien1: " + message.getText());
            pr.flush();
            System.out.println(message.getText());
        }
    }
}
