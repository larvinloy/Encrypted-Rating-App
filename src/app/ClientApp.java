package app;

import java.io.IOException;

import model.ClientEngine;
import network.Client;
import view.ClientView;

public class ClientApp
{
	public static void main(String[] args)
	{
		Client client = new Client(10001);
	}
}
