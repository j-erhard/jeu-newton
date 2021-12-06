import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class Multi extends Thread{
    private Socket s;
    InputStreamReader in;
    BufferedReader bf;


    Multi(Socket s) throws IOException{
        this.s=s;
        in = new InputStreamReader(s.getInputStream());
        bf = new BufferedReader(in);
    }
    public void run(){

        String str= "";
        boolean inLobby = true;

        // recupérer nom du client:
        try {
            str = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Pseudo enregistré: " + str);


        while (inLobby){

            try {
                str = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client: " + str);
            inLobby = str!=null;
        }
        try {
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
