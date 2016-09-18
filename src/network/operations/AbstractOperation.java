package network.operations;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.interfaces.RatingEngine;

public abstract class AbstractOperation implements Serializable
{
	public abstract void execute(RatingEngine engine, ObjectOutputStream oos) throws IOException;
}
