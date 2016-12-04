
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class BulletinConnectionHandler {
    private Socket connection;
    private InputStream clientInput;
    private OutputStream clientOutput;
    private Scanner sc;
    private OutputStreamWriter osw;
    
    public BulletinConnectionHandler(Socket conn) throws IOException
    {
        connection = conn;
        try
        {
            clientInput = connection.getInputStream();
            clientOutput = connection.getOutputStream();
            sc = new Scanner(clientInput);
            osw = new OutputStreamWriter(clientOutput);
        }
        catch(IOException e)
        {
            System.out.println("Error setting up I/O's");
        }
        finally
        {
            System.out.println("Unknowen Error");
        }
    }
    public void closeAll() throws IOException
    {
         osw.close();
         clientInput.close();
         connection.close();
    }
    public String write(String s)
    {
        String r=(s+"/r/n");// so it will always start on the next line
        return r;
    }
    public void run()
    {
        try
        {
            osw.write("Welcome to the Bulletin Board!");
            osw.flush();
            
           
        } catch (IOException ex) {
            Logger.getLogger(BulletinConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
