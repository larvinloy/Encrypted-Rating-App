package view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CollectiveRatingsPanel extends JPanel
{
	private HashMap<String,String> categories;
	private ArrayList<UnitCategoryPanel> unitCategoryPanels;
	
	private JPanel headingPanel = new JPanel();
	private JPanel ratingNoPanel = new JPanel();
	private JPanel categoryPanel = new JPanel();
	
	public CollectiveRatingsPanel(HashMap<String,String> categories)
	{
		this.categories = categories;
		this.unitCategoryPanels = new ArrayList<UnitCategoryPanel>();
		
		this.setLayout(new GridLayout(categories.size() + 1, 1,10,10));
		headingPanel.setLayout(new GridLayout(1, 2,10,10));
		
		categoryPanel.add(new JLabel("Category"));
		ratingNoPanel.add(new JLabel("Ratings"));
		headingPanel.add(categoryPanel);
		headingPanel.add(ratingNoPanel);
		
		
		this.add(headingPanel);

		for(Entry<String,String> e : categories.entrySet())
		{
			UnitCategoryPanel temp = new UnitCategoryPanel(e.getKey(), e.getValue());
			unitCategoryPanels.add(temp);
			this.add(temp);
		}
	}
	
	public ArrayList<UnitCategoryPanel> getCategoryPanels()
	{
		return unitCategoryPanels;
	}
}
