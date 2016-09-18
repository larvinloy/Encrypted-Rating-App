package network.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.RatingEngine;
import network.responses.ReturnAverages;

public class GetAverages extends AbstractOperation
{
	public GetAverages()
	{
	
	}
	
	@Override
	public void execute(RatingEngine engine, ObjectOutputStream oos) throws IOException
	{
		engine.caluclateAverageRatings();
		ReturnAverages returnAverages = new ReturnAverages(engine.getAverageRatings(),engine.getVoteCounts());
		oos.writeObject(returnAverages);

	}

}
