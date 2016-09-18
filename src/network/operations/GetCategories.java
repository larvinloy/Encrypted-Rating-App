package network.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ReadOnlyBufferException;
import java.util.HashMap;

import model.PublicEncryption;
import model.interfaces.RatingEngine;
import network.responses.ReturnConfig;

public class GetCategories extends AbstractOperation
{
	
	@Override
	public void execute(RatingEngine engine, ObjectOutputStream oos) throws IOException
	{
		HashMap<String, String> categories = engine.getCategories();
		PublicEncryption publicKey = engine.getPublicKey();
		ReturnConfig returnCategoriesResponse = new ReturnConfig(categories,publicKey);
		oos.writeObject(returnCategoriesResponse);

	}

}
