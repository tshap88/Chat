import java.net.*;
import java.io.*;

public class Server {
    public static void main (String [] args) {
        String str;
        ServerSocket ssocket;
        try {
            ssocket = new ServerSocket(5343);
            System.out.println("Server is up");
            Socket socket = ssocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            while ((str = in.readLine()) != null) {
                System.out.println("Client: " + str);
                out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
