package network.operations;

import java.io.ObjectOutputStream;
import java.math.BigInteger;

import model.interfaces.RatingEngine;

public class AddRating extends AbstractOperation
{
	private BigInteger rating;
	private String category;
	
	public AddRating(String category, BigInteger rating)
	{
		this.category = category;
		this.rating = rating;
	}

	@Override
	public void execute(RatingEngine engine, ObjectOutputStream oos)
	{
		engine.addToRating(category, rating);

	}

}
