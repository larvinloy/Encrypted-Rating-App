package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Map.Entry;

import model.interfaces.RatingEngine;
import view.components.UnitCategoryPanel;
import view.components.ClientPanel;
import view.components.CollectiveRatingsPanel;

public class SubmitButtonListener implements ActionListener
{
	private RatingEngine engine;
	private CollectiveRatingsPanel ratingsPanel;
	private ClientPanel clientPanel;
	
	public SubmitButtonListener(RatingEngine engine, ClientPanel clientPanel)
	{
		this.engine = engine;
		this.clientPanel = clientPanel;
		this.ratingsPanel = clientPanel.getRatingsPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		clientPanel.disableSubmit();
		
		for(UnitCategoryPanel c : ratingsPanel.getCategoryPanels())
		{
			if(!(c.getSelectedButtonText() == null))
				engine.addToRating(c.getCategoryCode(), new BigInteger(c.getSelectedButtonText()));
		}
		
	}

}
