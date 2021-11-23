package newton;

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

    Multi() throws IOException{}

    Multi(Socket s) throws IOException{
        this.s=s;
        in = new InputStreamReader(s.getInputStream());
        bf = new BufferedReader(in);
    }
    public void run(){

        String string= "";
        while (true){
            String str = null;
            try {
                str = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client: " + str);
        }
    }
}
