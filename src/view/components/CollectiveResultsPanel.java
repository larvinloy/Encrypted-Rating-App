package view.components;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CollectiveResultsPanel extends JPanel
{
	private HashMap<String,String> categories;
	private HashMap<String,UnitResultPanel> unitResultPanels;
	
	private JPanel headingPanel = new JPanel();
	
	public CollectiveResultsPanel(HashMap<String,String> categories)
	{
		this.setLayout(new GridLayout(categories.size() + 1, 1,10,10));
		unitResultPanels = new HashMap<String,UnitResultPanel>();
		
		for(Entry<String,String> e : categories.entrySet())
		{
			unitResultPanels.put(e.getKey(), new UnitResultPanel(e.getValue()));
		}
		
		headingPanel.setLayout(new GridLayout(1, 3,10,10));
		
		headingPanel.add(new JLabel("Category"));
		headingPanel.add(new JLabel("Results"));
		headingPanel.add(new JLabel("Votes"));
		
		this.add(headingPanel);
		for(Entry<String,UnitResultPanel> e : unitResultPanels.entrySet())
		{
			this.add(e.getValue());
		}
		
	}
	
	public UnitResultPanel getUnitResultPanel(String categoryCode)
	{
		return unitResultPanels.get(categoryCode);
	}
}
