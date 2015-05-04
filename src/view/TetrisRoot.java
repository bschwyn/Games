package view;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import model.Board;


//should be named frame
//jframe is the window
public class TetrisRoot extends JFrame{
	

	

	public TetrisRoot() {
		int width = 250;
		int height = 600;

		setTitle("Tetris");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//??
		//setVisible(true);
		
		
	}
	/*
	public void run() {
		TetrisRoot ex = new TetrisRoot();
		ex.setVisible(true);
	}
	
	//bind character to callback
	
	public static void main(String[] args) {
		
		//TetrisRoot ex = new TetrisRoot();
		//ex.setVisible(true);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				TetrisRoot ex = new TetrisRoot();
				ex.setVisible(true);
			}
		});
		
		
	}*/
	
	
	public void unplace() {
		
	}
	public void delete() {
		
	}
}
