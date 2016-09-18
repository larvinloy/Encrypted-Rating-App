package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import javax.swing.SwingUtilities;
import model.ServerEngine;
import model.interfaces.RatingEngine;
import network.operations.AbstractOperation;
import network.responses.ReturnConfig;

public class Server
{
	int port = 10001;
	RatingEngine engine;
	
	public Server()
	{
		try
		{
			engine = new ServerEngine();
			handleConnection();
		} 
		catch (IOException e)
		{
			System.out.println("Server failed to start");
			e.printStackTrace();
		}
	}
	
	public void handleConnection() throws IOException
	{
		System.out.println("Handling Client connection");
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			serverSocket.setSoTimeout(0);
			
			while (true)
			{
				Socket socket = serverSocket.accept();
				new ClientMessageThread(socket, this).start();
			}
		} 
		catch (Exception e)
		{
			System.out.println("unexpected error occured in handling connnection");
		}
	}
	
	public RatingEngine getEngine()
	{
		return engine;
	}	
	
	public static void handleMessage(Socket socket, Server slaveServer) throws IOException
	{
		ObjectOutputStream outputToClient;
		ObjectInputStream inputFromClient;
		inputFromClient = new ObjectInputStream(socket.getInputStream());
		outputToClient = new ObjectOutputStream(socket.getOutputStream());

		while (true)
		{
			AbstractOperation operation;
			try
			{
				operation = (AbstractOperation) inputFromClient.readObject();
				operation.execute(slaveServer.getEngine(), outputToClient);
			} 
			catch (ClassNotFoundException e)
			{
				System.out.println(socket + ": unexpected error occured");
				break;
			}
			catch (IOException e)
			{
				System.out.println(socket + ": disconnected");
				break;
			}
		}
	}
	
	
	private class ClientMessageThread extends Thread
	{
		private Socket socket;
		private Server slaveServer;

		public ClientMessageThread(Socket socket,Server slaveServer)
		{
			this.socket = socket;
			this.slaveServer = slaveServer;
		}

		public void run()
		{
			try
			{
				Server.handleMessage(socket,slaveServer);
			} 
			catch (IOException e)
			{
				
			}
		}
	}
}
