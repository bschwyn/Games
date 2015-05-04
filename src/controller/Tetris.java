package controller;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import view.TetrisCanvas;
import view.TetrisRect;
import view.TetrisRoot;

import model.Board;
import model.Piece;

public class Tetris {
	private static TetrisRoot root;
	private TetrisTimer tetristimer;
	private boolean running;
	private TetrisCanvas canvas;
	private Board board;
	int score;
	
	//sets up window
	public Tetris() {
		tetristimer = new TetrisTimer(this);
		root = new TetrisRoot();
		new_game();
		key_bindings();
	}
	
	public void set_board() {
		board = new Board(this); //canvas
		board.draw();
		
	}
	public Board board() {
		return board;
	}
	public TetrisRoot root() {
		return root;
	}
	
	private void key_bindings() {
		
		JPanel canvas = board.canvas();
		
		//piece movements
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "call_up");
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "call_left");
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "call_right");
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "call_space");
		//problem: the space map is redudant atm because of a problem elsewhere. See comment in line 134
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "call_down");
		
		canvas.getActionMap().put("call_up", new UpAction());
		canvas.getActionMap().put("call_left", new LeftAction());
		canvas.getActionMap().put("call_right", new RightAction());
		canvas.getActionMap().put("call_down", new DownAction());
		canvas.getActionMap().put("call_space", new SpaceAction());
		
		
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("N"), "newgame");
		canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "pause");
		
		canvas.getActionMap().put("newgame", new NewGameAction());
		canvas.getActionMap().put("pause", new PauseAction());
		
		
	}
	
	//new game and pause can be called during a game w/ buttons or keys
	
	//new game is called automatically for first game but may also be manually called by the user
	public void new_game() {
		root.getContentPane().removeAll();
		root.revalidate();
		set_board();
		key_bindings();
		running = true;
		run_game();	
	}
	
	public void pause() {
		if (running) {
			running = false;
			tetristimer.stop();
		} else {
			running = true;
			this.run_game();
		}
	}
	
	//update_view_score, run_game, is_running are called by the board.
	
	public void update_view_score() {
		int s = board.score();
		String string = "Current Score: "+ Integer.toString(s);
		JLabel x = board.canvas().label();
		board.canvas().label().setText(string);
	}
	
	public void run_game() {
		if (!(board.is_game_over() && running)) {
			tetristimer.stop();
			tetristimer.start(board.delay());
		}
	}
	
	public boolean is_running() {
		return running;
	}
	
	//main event loop
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Tetris game = new Tetris();
				root.setVisible(true);
			}
		});
		
	}
	
	//action methods called by various key bindings
	//TODO: space "clicks" whatever has the focus, but I would like it to only call the mapped action.
	//there is a workaround where I have the focus reset to the space-button.
	//I would like to remove this default.
	public class UpAction extends AbstractAction {
		private static final long serialVersionUID = 5857385121484029531L;

		public void actionPerformed(ActionEvent e) {
			board.rotate_counter_clockwise();
			board.canvas().space().requestFocus();
		}
	}
	
	public class DownAction extends AbstractAction{
		private static final long serialVersionUID = -5187116875614099794L;

		public void actionPerformed(ActionEvent e) {
			board.rotate_clockwise();
			board.canvas().space().requestFocus();
		}
	}
	
	public class LeftAction extends AbstractAction {
		private static final long serialVersionUID = -8224262578089314608L;

		public void actionPerformed(ActionEvent e) {
			board.move_left();
			board.canvas().space().requestFocus();
		}
	}
	
	public class RightAction extends AbstractAction {
		private static final long serialVersionUID = -8224262578089314608L;

		public void actionPerformed(ActionEvent e) {
			board.move_right();
			board.canvas().space().requestFocus();
		}
	}
	
	public class SpaceAction extends AbstractAction {
		private static final long serialVersionUID = -8224262578089314608L;

		public void actionPerformed(ActionEvent e) {
			board.drop_all_the_way();
			board.canvas().space().requestFocus();
		}
	}

	public class NewGameAction extends AbstractAction {
		
		private static final long serialVersionUID = -6995060148830454100L;

		public void actionPerformed(ActionEvent e) {
			new_game();
			board.canvas().space().requestFocus();
		}
	}
	
	public class PauseAction extends AbstractAction {
		
		private static final long serialVersionUID = -6911739538301850453L;

		public void actionPerformed(ActionEvent e) {
			pause();
			board.canvas().space().requestFocus();
		}
	}
}

