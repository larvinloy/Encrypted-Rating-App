package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.ResultsButtonListener;
import Controller.StartButtonListener;
import model.PublicEncryption;
import model.interfaces.RatingEngine;
import view.ClientView;

public class OrganizerPanel extends JPanel
{
	private JButton startButton;
	private JButton resultsButton;
	private CategoryCreationPanel categoryCreationPanel;
	private CollectiveResultsPanel collectiveResultsPanel;
	private JPanel controlPanel;
	
	public OrganizerPanel(RatingEngine engine)
	{
		
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
		controlPanel = new JPanel();
		categoryCreationPanel = new CategoryCreationPanel();
		
		startButton = new JButton("Start Survey");
		startButton.addActionListener(new StartButtonListener(engine, this, categoryCreationPanel));
		
		resultsButton = new JButton("Get Results");
		resultsButton.addActionListener(new ResultsButtonListener(engine,this));
		
		controlPanel.add(startButton);
		controlPanel.add(resultsButton);
		
		
		
		this.add(categoryCreationPanel,BorderLayout.NORTH);
		this.add(controlPanel,BorderLayout.SOUTH);
	}
	
	public void addCollectiveResultsPanel()
	{
		HashMap<String,String> categories = categoryCreationPanel.getCategories();
		this.collectiveResultsPanel = new CollectiveResultsPanel(categories);
		this.add(collectiveResultsPanel,BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}
	
	public CollectiveResultsPanel getCollectiveResultsPanel()
	{
		return this.collectiveResultsPanel;
	}
	
	public void disableStart()
	{
		this.startButton.setEnabled(false);
	}
	
	public void disableResults()
	{
		this.resultsButton.setEnabled(false);
	}
}
