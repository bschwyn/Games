package controller;
import javax.swing.Timer;


import model.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO fix this error eclispse is picking up on
public class TetrisTimer implements ActionListener{
	
	//do these need to be specific instances of Board and Tetris?
	private Tetris tetris;
	private Board board;
	private Timer timer;
	
	public TetrisTimer(Tetris game) {
		tetris = game;
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tetris.board().run();
				tetris.run_game();
			}
		};
		int default_delay = 500; //appears to be the only delay that matters
		timer = new Timer(default_delay, taskPerformer);
		
	}
	
	public void stop() {
		timer.stop();
	}
	
	//instead of the start being passed the event I think it figures that out by itself.
	public void start(int delay) {
		//timer.setDelay(1500); //it appears that this delay does not do anyhing.
		timer.start();
	}

}
