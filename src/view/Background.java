package view;

import java.awt.Color;

import javax.swing.JPanel;

public class Background extends JPanel{
	
	public Background(TetrisRoot root) {
		
		//place onto root

		root.getContentPane().add(this);
		int width = 200;
		int height = 600;
		setSize(width,height);
		//how to only get (what I think to be the panel) to change color, and not the entirety?
		setBackground(Color.RED);
		//place canvas onto background
		//addbuttons onto background
		
	}

}
