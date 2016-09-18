package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.AddButtonListener;


public class CategoryCreationPanel extends JPanel
{
	private HashMap<String,String> categories = new HashMap<String,String>();
	private JButton addButton;
	private JTextField categoryText;
	private JLabel categoryCountTextLabel;
	private JLabel categoryCountLabel;
	private static int categoryCount = 0;
	
	
	public CategoryCreationPanel()
	{
		categoryText = new JTextField();
		categoryText.setPreferredSize(new Dimension(300,20));
		categoryText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		categoryCountTextLabel = new JLabel("Categories added: ");
		categoryCountLabel = new JLabel();
		categoryCountLabel.setPreferredSize(new Dimension(20,20));
		categoryCountLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addButton = new JButton("Add Category");
		addButton.addActionListener(new AddButtonListener(this));
	
		this.add(categoryText);
		this.add(addButton);
		this.add(categoryCountTextLabel);
		this.add(categoryCountLabel);
		
	}
	
	public String getCategoryText()
	{
		return categoryText.getText();
	}
	
	public void addNewCategory(String category)
	{
		categories.put(String.valueOf(categoryCount), category);
		categoryCount++;
		categoryCountLabel.setText(String.valueOf(categoryCount));
	}
	
	public HashMap<String,String> getCategories()
	{
		return categories;
	}
	
	public void disableAdd()
	{
		this.addButton.setEnabled(false);
	}
}
