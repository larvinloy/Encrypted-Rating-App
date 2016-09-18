package app;

import java.io.IOException;
import java.util.HashMap;

import model.OrganizerEngine;
import model.Paillier;
import model.PublicEncryption;
import model.interfaces.RatingEngine;
import view.OrganizerView;

public class OrganizerApp
{

	public static void main(String[] args)
	{
		RatingEngine engine;
		int modLength = 512;
		try
		{
			Paillier paillier = new Paillier(modLength);
			PublicEncryption publicEncryption = new PublicEncryption(modLength,paillier.getN(),paillier.getG());
					
			engine = new OrganizerEngine(10001,paillier,publicEncryption);
			
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
		
//		try
//		{
//			OrganizerEngine organizerEngine = new OrganizerEngine(10001);
//			HashMap<String,String> categories = new HashMap<String,String>();
//			categories.put("Ease of course", "1");
//			categories.put("Ease of uni", "2");
//			organizerEngine.initCategories(categories);
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
