/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.*;
import java.io.*;

/**
 *
 * @author Jorde
 */
public class Greeting {

    public static void main(String[] args) {
        String server = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            System.out.println("Connecting to " + server);
            System.out.println("On port: " + port);
            Socket client = new Socket(server, port);
            
          
          System.out.println("Established connection with: " + client.getRemoteSocketAddress()); 
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out = new DataOutputStream(outToServer);
          
          out.writeUTF("Hello from: " + client.getLocalSocketAddress());
          InputStream inFromServer = client.getInputStream();
          DataInputStream in = new DataInputStream(inFromServer);
          
          System.out.println("Servers response: " + in.readUTF());
          client.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        

    }

}
