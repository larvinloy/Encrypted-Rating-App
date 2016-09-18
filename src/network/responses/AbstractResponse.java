package network.responses;

import java.io.Serializable;
import model.interfaces.RatingEngine;

public abstract class AbstractResponse implements Serializable
{
	
	public abstract void execute(RatingEngine engine);
}