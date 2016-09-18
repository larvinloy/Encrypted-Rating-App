package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import model.ClientEngine;

public class Client
{
	int servrPort;
	ClientEngine clientEngine;
	
	public Client(int serverPort)
	{
		this.servrPort = serverPort;
		try
		{
			clientEngine = new ClientEngine(serverPort);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void handleConnection() throws IOException
//	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		while(true)
//		{
//			clientEngine.addToRating("1", BigInteger.ONE);
//			br.readLine();
//		}
//	}
}
