package view.components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UnitResultPanel extends JPanel
{
	private JLabel category;
	private JLabel avgRating;
	private JLabel voteCount;
	
	public UnitResultPanel(String categoryName)
	{
		this.setLayout(new GridLayout(1, 3,10,10));
		
		category = new JLabel(categoryName);
		avgRating = new JLabel("-");
		voteCount = new JLabel("-");
		
		this.add(category);
		this.add(avgRating);
		this.add(voteCount);
	}
	
	public void updateAvgRating(String avgResult)
	{
		this.avgRating.setText(avgResult);
	}
	
	public void updateVoteCount(String voteCount)
	{
		this.voteCount.setText(voteCount);
	}
	
}
