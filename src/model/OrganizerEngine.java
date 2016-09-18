package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import model.interfaces.RatingEngine;
import network.operations.AddRating;
import network.operations.GetAverages;
import network.operations.GetCategories;
import network.operations.SetCategories;
import network.responses.ReturnAverages;
import network.responses.ReturnConfig;
import view.ClientView;
import view.OrganizerView;

/**
 * Rating Engine implementation for the organizer
 * @author larvinloy
 *
 */
public class OrganizerEngine implements RatingEngine
{
	private Socket socket;
	private ObjectOutputStream outputToServer;
	private ObjectInputStream inputFromServer;
	private OrganizerView organizerView;
	private Paillier paillier;
	private PublicEncryption publicEncryption;
	private HashMap<String,String> categories;
	private HashMap<String,Integer> voteCount;
	private HashMap<String,String> encryptedAverages;
	private HashMap<String,String> decryptedAverages;
	
	public OrganizerEngine(int serverPort,Paillier paillier, PublicEncryption publicEncryption) throws IOException
	{
		try
		{
			this.paillier = paillier;
			this.publicEncryption = publicEncryption;
			decryptedAverages = new HashMap<String,String>();
			socket = new Socket("localhost", serverPort);
		
			outputToServer = new ObjectOutputStream(socket.getOutputStream());
			outputToServer.flush();
			inputFromServer = new ObjectInputStream(socket.getInputStream());
			organizerView = new OrganizerView(this);
			
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
	

	}

	@Override
	public void initConfiguration(HashMap<String, String> categories, PublicEncryption publicEncryption)
	{
		this.categories = categories;
		SetCategories setCategoriesOperation = new SetCategories(categories,publicEncryption);
		try
		{
			outputToServer.writeObject(setCategoriesOperation);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public HashMap<String, String> getCategories()
	{
		return this.categories;
	}

	@Override
	synchronized public HashMap<String, String> getAverageRatings()
	{
		if(encryptedAverages == null)
		{
			try
			{
				this.wait();
			} 
			catch (InterruptedException e2)
			{
				e2.printStackTrace();
			}
		}
		
		for(Entry<String,String> e : encryptedAverages.entrySet())
		{
			try
			{
				System.out.println("Encrypted average " + e.getValue());
				BigInteger decryptedValue = paillier.decrypt(new BigInteger(e.getValue()));
				String decryptedValueInString = decryptedValue.toString();
				String decryptedValueWithDot = decryptedValueInString.substring(0, 1);
				decryptedValueWithDot += ".";
				decryptedValueWithDot += decryptedValueInString.substring(1,decryptedValueInString
						.length());
				double decryptedValueInDouble = Double.valueOf(decryptedValueWithDot);
				int integerPart = (int)decryptedValueInDouble;
				double decimalPart = decryptedValueInDouble-integerPart;
				if(decimalPart > 0.99999)
					decryptedValueInDouble = (double)integerPart + 1.0;
				
				decryptedAverages.put(e.getKey(), String.valueOf(decryptedValueInDouble));
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return decryptedAverages;
	}

	@Override
	public PublicEncryption getPublicKey()
	{
		return this.publicEncryption;
	}

	@Override
	public HashMap<String, Integer> getVoteCounts()
	{
		if(voteCount == null)
		{
			try
			{
				this.wait();
			} 
			catch (InterruptedException e2)
			{
				e2.printStackTrace();
			}
		}
		return this.voteCount;
	}


	@Override
	public void caluclateAverageRatings()
	{
		
		try
		{
			GetAverages getAverages = new GetAverages();
			outputToServer.writeObject(getAverages);
			ReturnAverages returnAverages = (ReturnAverages) inputFromServer.readObject();
			returnAverages.execute(this);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	synchronized public void setAverageRatings(HashMap<String, String> averages, 
			HashMap<String,Integer> voteCount)
	{
		this.encryptedAverages = averages;
		this.voteCount = voteCount;
		this.notifyAll();
		
	}


	

}
