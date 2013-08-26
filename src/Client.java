import java.net.*;
import java.io.*;

public class Client {
    private static int port = 3553;                                         //params are hard-coded
    public static void main(String[] ar) {
        Socket socket;
        try {
            System.out.println("Сlient starting.");
            socket = new Socket("localhost", port);                         //params are hard-coded
            System.out.println("Сonnection to the server was successful.");
            Thread threadc = new Thread(new ClientImpRun(socket));
            threadc.start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
