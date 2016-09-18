package view.components;

import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.*;

import Controller.SubmitButtonListener;

public class UnitCategoryPanel extends JPanel
{
	private String categoryCode;
	private String categoryName;
	private JRadioButton value1;
	private JRadioButton value2;
	private JRadioButton value3;
	private JRadioButton value4;
	private JRadioButton value5;
	private ButtonGroup ratingRadioGroup;
	private JPanel buttonsPanel;
	private JLabel categoryLabel;
	
	public UnitCategoryPanel(String categoryCode, String categoryName)
	{
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 5,10,10));
		this.setLayout(new GridLayout(1, 2,10,10));
		
		categoryLabel = new JLabel(categoryName);
		
		value1 = new JRadioButton("1");
		value2 = new JRadioButton("2");
		value3 = new JRadioButton("3");
		value4 = new JRadioButton("4");
		value5 = new JRadioButton("5");
		
		
		ratingRadioGroup = new ButtonGroup();
		ratingRadioGroup.add(value1);
		ratingRadioGroup.add(value2);
		ratingRadioGroup.add(value3);
		ratingRadioGroup.add(value4);
		ratingRadioGroup.add(value5);
		
		buttonsPanel.add(value1);
		buttonsPanel.add(value2);
		buttonsPanel.add(value3);
		buttonsPanel.add(value4);
		buttonsPanel.add(value5);
		
		this.add(categoryLabel);
		this.add(buttonsPanel);

	}
	
	public String getCategoryCode()
	{
		return categoryCode;
	}
	
	public String getSelectedButtonText() 
	{
        for (Enumeration<AbstractButton> buttons = ratingRadioGroup.getElements(); buttons.hasMoreElements();) 
        {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) 
                return button.getText();
        }

        return null;
    }
	
}
