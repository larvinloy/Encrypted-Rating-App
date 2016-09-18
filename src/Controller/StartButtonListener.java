package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PublicEncryption;
import model.interfaces.RatingEngine;
import view.components.CategoryCreationPanel;
import view.components.CollectiveResultsPanel;
import view.components.OrganizerPanel;

public class StartButtonListener implements ActionListener
{
	private CategoryCreationPanel categoryCreationPanel;
	private RatingEngine engine;
	private OrganizerPanel organizerPanel;
	
	public StartButtonListener(RatingEngine engine,OrganizerPanel organizerPanel,
			CategoryCreationPanel categoryCreationPanel)
	{
		this.organizerPanel = organizerPanel;
		this.engine = engine;
		this.categoryCreationPanel = categoryCreationPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		categoryCreationPanel.disableAdd();
		organizerPanel.disableStart();
		organizerPanel.addCollectiveResultsPanel();
		engine.initConfiguration(categoryCreationPanel.getCategories(),engine.getPublicKey());
	}

}
