import java.net.*;
import java.io.*;


public class GreetingServer extends Thread {
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

    }

    public void run () {
        while(true) {
            try{
                System.out.println("Waiting for a client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Connected to" + server.getRemoteSocketAddress());
                DataInputStream input = new DataInputStream(server.getInputStream());

                System.out.println(input.readUTF());
                DataOutputStream output = new DataOutputStream(server.getOutputStream());
                output.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress());
                server.close();
            }
            catch (SocketTimeoutException s) {
                System.out.println("Socket timed out");
                break;

            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try{
            Thread thread = new GreetingServer(port);
            thread.start();

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}