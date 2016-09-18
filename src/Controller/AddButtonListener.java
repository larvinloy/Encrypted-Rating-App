package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.components.CategoryCreationPanel;

public class AddButtonListener implements ActionListener
{
	private CategoryCreationPanel categoryCreationPanel;
	
	public AddButtonListener(CategoryCreationPanel categoryCreationPanel)
	{
		this.categoryCreationPanel = categoryCreationPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		categoryCreationPanel.addNewCategory(categoryCreationPanel.getCategoryText());
	}

}
