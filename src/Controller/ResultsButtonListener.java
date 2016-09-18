package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;

import model.interfaces.RatingEngine;
import view.components.OrganizerPanel;

public class ResultsButtonListener implements ActionListener
{
	private RatingEngine engine;
	private HashMap<String,String> averages;
	private HashMap<String,Integer> voteCount;
	private OrganizerPanel organizerPanel;
	
	public ResultsButtonListener(RatingEngine engine,OrganizerPanel organizerPanel)
	{
		this.engine = engine;
		this.organizerPanel = organizerPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//organizerPanel.disableResults();
		engine.caluclateAverageRatings();
		averages = engine.getAverageRatings();
		voteCount = engine.getVoteCounts();
		
		for(Entry<String,String> entry : averages.entrySet())
		{
			organizerPanel.getCollectiveResultsPanel().getUnitResultPanel(entry.getKey())
				.updateAvgRating(entry.getValue());
			
		}
		
		for(Entry<String,Integer> entry : voteCount.entrySet())
		{
			organizerPanel.getCollectiveResultsPanel().getUnitResultPanel(entry.getKey())
				.updateVoteCount(String.valueOf(entry.getValue()));
			
		}

	}

}
