package view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.SubmitButtonListener;
import model.interfaces.RatingEngine;

public class ClientPanel extends JPanel
{
	private HashMap<String,String> categories;
	private CollectiveRatingsPanel ratingsPanel;
	private JLabel headingLabel;
	private JButton submitButton;
	
	public ClientPanel(RatingEngine engine, HashMap<String,String> categories)
	{
		this.categories = categories;
		
		this.setLayout(new BorderLayout());
		headingLabel = new JLabel("Please submit your ratings");
		ratingsPanel = new CollectiveRatingsPanel(categories);
		
		JPanel submitButtonPanel = new JPanel();
		JPanel headingLabelPanel = new JPanel();
		JPanel ratingsPanelPanel = new JPanel();
		
		submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new SubmitButtonListener(engine, this));
		
		submitButtonPanel.add(submitButton);
		headingLabelPanel.add(headingLabel);
		ratingsPanelPanel.add(ratingsPanel);
		
		this.add(headingLabelPanel,BorderLayout.NORTH);
		this.add(ratingsPanelPanel,BorderLayout.CENTER);
		this.add(submitButtonPanel, BorderLayout.SOUTH);
	}
	
	public CollectiveRatingsPanel getRatingsPanel()
	{
		return this.ratingsPanel;
	}
	
	public void disableSubmit()
	{
		this.submitButton.setEnabled(false);
	}
}
