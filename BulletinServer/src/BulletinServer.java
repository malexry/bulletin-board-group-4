import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BulletinServer 
{
	private Socket socket;
	private ServerSocket serverSocket;
	private ArrayList messageArray;
	private int count = 10;
	private int portNumber;

	
	public BulletinServer(int newPortNumber)
	{
		portNumber = newPortNumber;
		socket = null;
		serverSocket = null;
	}
	
	public static void main(String[] arg) throws IOException
	{
		BulletinServer bs = new BulletinServer(Integer.parseInt(arg[0]));
		bs.start();
		bs.acceptConnection();
		bs.terminate();
	}
	
	public void start() throws IOException
	{
		serverSocket = new ServerSocket(portNumber);
	}
	
	public void acceptConnection() throws IOException
	{
		if(count < 10)
		{
			socket = serverSocket.accept();
			BulletinConnectionHandler bch = new BulletinConnectionHandler(socket); 
			messageArray.add(bch);
			count++;
		}	
	}
	public void terminate() throws IOException
	{
		serverSocket.close();
		socket.close();
	}
}

