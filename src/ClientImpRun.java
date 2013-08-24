import java.net.Socket;

public class ClientImpRun implements Runnable {
    Socket socket;

    public ClientImpRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
