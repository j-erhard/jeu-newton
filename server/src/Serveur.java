import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Serveur {

    public static void main(String args[]) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(6666);
        while (true) {

            System.out.println("Server is Awaiting");
            Socket s = ss.accept();
            Multi t = new Multi(s);
            t.start();

            Thread.sleep(2);
            //ss.close();
        }
    }
}
