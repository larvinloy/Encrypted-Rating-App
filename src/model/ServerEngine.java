package model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;

import model.interfaces.RatingEngine;

/**
 * Rating engine implementation for the server
 * @author larvinloy
 *
 */
public class ServerEngine implements RatingEngine
{
	private PublicEncryption publicEncryption;
	private HashMap<String,Integer> voteCount;
	private HashMap<String,BigInteger> ratingTotals;
	private HashMap<String,BigInteger> ratingAverages;
	private HashMap<String, String> categories;
	
	public ServerEngine()
	{
		ratingTotals = new HashMap<String,BigInteger>();
		voteCount = new HashMap<String,Integer>();
		ratingAverages = new HashMap<String,BigInteger>();
	}
	
	@Override
	public void addToRating(String category, BigInteger rating)
	{
		System.out.println("Rated " + category + "-> " + rating.toString());
		if(ratingTotals.containsKey(category))
			ratingTotals.put(category,ratingTotals.get(category).multiply(rating)
					.mod(publicEncryption.getNsquare()));
		else
			ratingTotals.put(category, rating);
		if(voteCount.containsKey(category))
			voteCount.put(category, voteCount.get(category)+1);
		else
			voteCount.put(category,1);
	}

	@Override
	synchronized public void initConfiguration(HashMap<String, String> categories, PublicEncryption publicKey)
	{
		System.out.println("Configuration set!");
		this.publicEncryption = publicKey;
		this.categories = categories;
		this.notifyAll();
		
	}

	@Override
	synchronized public HashMap<String, String> getCategories()
	{
		if(this.categories == null)
		{
			try
			{
				this.wait();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		return categories;
	}

	@Override
	public HashMap<String, String> getAverageRatings()
	{
		System.out.println(ratingAverages.size());
		HashMap<String,String> ratingAveragesInString = new HashMap<String,String>();
		
		for(Entry<String,BigInteger> e : ratingAverages.entrySet())
		{
			ratingAveragesInString.put(e.getKey(), e.getValue().toString());
		}
		return ratingAveragesInString;
	}

	@Override
	public PublicEncryption getPublicKey()
	{
		return this.publicEncryption;
	}

	@Override
	public HashMap<String, Integer> getVoteCounts()
	{
		return this.voteCount;
	}


	@Override
	public void caluclateAverageRatings()
	{
		System.out.println("Calculating Results");
		double fdivisor;

		for(Entry<String,BigInteger> e : ratingTotals.entrySet())
		{
			fdivisor = (double) (1.0/(double)voteCount.get(e.getKey()));
			
			while(fdivisor < 1000000) 
				fdivisor*=10;
			
			BigInteger divisor = new BigInteger(String.valueOf((int)fdivisor));
			BigInteger average = e.getValue().modPow(divisor, publicEncryption.getNsquare());
			ratingAverages.put(e.getKey(), average);
		}
	}

	@Override
	public void setAverageRatings(HashMap<String, String> averages,HashMap<String,Integer> voteCount)
	{
		
	}


	
	
	
}
