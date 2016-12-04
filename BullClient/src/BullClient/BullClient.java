package BullClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author alexmasse
 */
public class BullClient {
    public Socket clientSocket = null;
    public String serverIP= null;
    public int serverPort= 0;
    

    public BullClient(String serverIP, int serverPort) throws IOException
    {  
        this.serverIP= serverIP; 
        this.serverPort= serverPort;
        clientSocket = new Socket(serverIP, serverPort);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String serverIP=
                 JOptionPane.showInputDialog("Enter IP:"); 
                    
        int serverPort= 
                Integer.parseInt(JOptionPane.showInputDialog("Enter Port Number:"));
        try
           {
                BullClient bClient = new BullClient(serverIP, serverPort);
                OutputStream outputStream = bClient.clientSocket.getOutputStream();
                InputStream inputStream = bClient.clientSocket.getInputStream();
                
                OutputStreamWriter osw = new OutputStreamWriter(outputStream);//send out data
                Scanner sc = new Scanner(inputStream);//get in data  
                
                String msg=sc.nextLine();
                System.out.println(msg);
                Scanner keySc = new Scanner(System.in);
                String word="";
                
                while (!word.equalsIgnoreCase("exit"))
                {
                    word=keySc.next();
                    if (!word.equalsIgnoreCase("exit"))
                    {
                        osw.write(word + "\r\n");
                        //writes to the server  /r/n needed to return and line save
                        osw.flush();//sends message and then clears buffer
                        msg=sc.nextLine();
                        System.out.println(msg);
                    }
                    else
                    {
                        osw.write(word + "\r\n");
                        osw.flush();
                        osw.close();
                        outputStream.close();
                        bClient.clientSocket.close();
                        System.out.println("Client: Connection Closed");
                    }
                }
            }
        catch(IOException e)
        {
            System.out.println("Connection Failed\nUnable to connect to server\nError:\t"+ e);
        }
    }
}