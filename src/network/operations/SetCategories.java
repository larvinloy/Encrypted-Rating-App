package network.operations;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import model.PublicEncryption;
import model.interfaces.RatingEngine;

public class SetCategories extends AbstractOperation
{
	private HashMap<String, String> categories;
	private PublicEncryption publicKey;
	
	public SetCategories(HashMap<String, String> categories, PublicEncryption publicKey)
	{
		this.publicKey = publicKey;
		this.categories = categories;
	}

	@Override
	public void execute(RatingEngine engine, ObjectOutputStream oos)
	{
		engine.initConfiguration(categories,publicKey);
	}

}
