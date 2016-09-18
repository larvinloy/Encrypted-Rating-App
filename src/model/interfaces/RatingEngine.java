package model.interfaces;

import java.math.BigInteger;
import java.util.HashMap;

import model.PublicEncryption;

public interface RatingEngine
{

	void addToRating(String category, BigInteger rating);

	HashMap<String,String> getAverageRatings();
	
	void setAverageRatings(HashMap<String,String> averages,HashMap<String,Integer> voteCount);
	
	void caluclateAverageRatings();
	
	HashMap<String,Integer> getVoteCounts();
	
	void initConfiguration(HashMap<String,String> categories, PublicEncryption publicKey);
	
	PublicEncryption getPublicKey();
	
	HashMap<String,String> getCategories();

}