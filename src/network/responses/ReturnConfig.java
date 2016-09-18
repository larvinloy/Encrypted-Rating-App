package network.responses;

import java.util.HashMap;

import model.PublicEncryption;
import model.interfaces.RatingEngine;


public class ReturnConfig extends AbstractResponse
{
	private HashMap<String,String> categories;
	private PublicEncryption publicKey;
	
	public ReturnConfig(HashMap<String,String> categories,PublicEncryption publickey)
	{
		this.publicKey = publickey;
		this.categories = categories;
	}
	
	@Override
	public void execute(RatingEngine engine)
	{
		engine.initConfiguration(categories,publicKey);

	}

}
