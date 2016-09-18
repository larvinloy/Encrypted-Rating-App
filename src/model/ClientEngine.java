package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.interfaces.RatingEngine;
import network.operations.AddRating;
import network.operations.GetCategories;
import network.responses.ReturnConfig;
import view.ClientView;

/**
 * Rating engine implementation for the client
 * @author larvinloy
 *
 */
public class ClientEngine implements RatingEngine
{
	private Socket socket;
	private ObjectOutputStream outputToServer;
	private ObjectInputStream inputFromServer;
	
	private ClientView clientView;
	
	private PublicEncryption publicEncryption;
	private HashMap<String,String> categories;
	
	public ClientEngine(int serverPort) throws IOException
	{
		try
		{
			socket = new Socket("localhost", serverPort);
			//socket.setSoTimeout(3000);
			outputToServer = new ObjectOutputStream(socket.getOutputStream());
			outputToServer.flush();
			inputFromServer = new ObjectInputStream(socket.getInputStream());
			// reading categories
			try
			{
				GetCategories getCategoriesOperation = new GetCategories();
				outputToServer.writeObject(getCategoriesOperation);
				
				ReturnConfig returnCategoriesResponse = 
						(ReturnConfig) inputFromServer.readObject();
				returnCategoriesResponse.execute(this);
				
				System.out.println(this.categories.size());
				clientView = new ClientView(this, this.categories);
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("Unexpected error occured!");
				e.printStackTrace();
			}
		}
		catch (IOException ex)
		{
			if (socket != null)
				socket.close();
			System.out.println("Failed to connect client!");
			ex.printStackTrace();
		}
	}

	@Override
	public void addToRating(String category, BigInteger rating)
	{
		try
		{
			AddRating addRating = new AddRating(category, publicEncryption.encrypt(rating));
			outputToServer.writeObject(addRating);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initConfiguration(HashMap<String, String> categories, PublicEncryption publicEncryption)
	{
		this.publicEncryption = publicEncryption;
		this.categories = categories;
	}

	@Override
	public HashMap<String, String> getCategories()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getAverageRatings()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicEncryption getPublicKey()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Integer> getVoteCounts()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void caluclateAverageRatings()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAverageRatings(HashMap<String, String> averages,HashMap<String, Integer> voteCount)
	{
		// TODO Auto-generated method stub
		
	}

}
