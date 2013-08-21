import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        Socket socket;
        String str;
        try {
            socket = new Socket("localhost", 5343);
            System.out.println("Сonnection to the server was successful!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while ((str = keyboard.readLine()) != null) {
                out.println(str); // сообщение серверу
            }

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}