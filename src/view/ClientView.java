package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.interfaces.RatingEngine;
import view.components.*;

public class ClientView extends JFrame
{
	private HashMap<String,String> categories;
	private ClientPanel clientPanel;
	
	public ClientView(RatingEngine engine, HashMap<String,String> categories)
	{
		super("Rater");
		//setLayout(new BorderLayout(2,2));
		setBounds(100, 100, 1280, 600);
		
		this.categories = categories;
		clientPanel = new ClientPanel(engine, categories);
		this.add(clientPanel);
		this.pack();
		this.setVisible(true);
		
		
	}
}
