package network.responses;

import java.math.BigInteger;
import java.util.HashMap;

import model.interfaces.RatingEngine;

public class ReturnAverages extends AbstractResponse
{
	private HashMap<String,String> averages;
	private HashMap<String,Integer> voteCount;
	
	public ReturnAverages(HashMap<String,String> averages, HashMap<String,Integer> voteCount)
	{
		this.averages = averages;
		this.voteCount = voteCount;
	}
	
	@Override
	public void execute(RatingEngine engine)
	{
		engine.setAverageRatings(averages,voteCount);

	}

}
