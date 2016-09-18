package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.PublicEncryption;
import model.interfaces.RatingEngine;
import view.components.OrganizerPanel;

public class OrganizerView extends JFrame
{
	private OrganizerPanel organizerPanel;
	
	public OrganizerView(RatingEngine engine)
	{
		super("Organizer");
		this.setMinimumSize(new Dimension(700,400));
//		setLayout(new BorderLayout(10,10));
		setBounds(100, 100, 1280, 600);
		organizerPanel = new OrganizerPanel(engine);
		
		this.add(organizerPanel);
		this.setVisible(true);
		this.pack();
	}
}
