package view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import view.Background;

import model.Board;
import view.TetrisRect;

//jpanel is the gray part that goes on top of the window and where stuff gets drawn
public class TetrisCanvas extends JPanel{
	
	private TetrisRoot root;
	///private Background background;
	private Board board;
	private JButton leftButton;
	private JButton rightButton;
	private JButton downButton;
	private JButton spaceButton;
	private JButton upButton;
	
	private JLabel scorelabel;
	
	public TetrisCanvas(TetrisRoot r, Board b) {
		board = b;
		root = r;

		place(500, 240, 10, 10);
		setLayout(null);
		addButtons();
		addLabel();
		//TODO focus doesn't shift until pressing buttons or keys changes it.
		spaceButton.requestFocus(); //doesn't actually do anything.
		
	}
	
	//overrides paint component to paint rectangles and background
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw background rectangle

		g.setColor(Color.gray);
		g.fillRect(50, 50, board.block_size()*10, board.block_size()*27);
		g.setColor(Color.BLACK);
		g.drawRect(50, 50, board.block_size()*10, board.block_size()*27);
		
		//get alllll the information about everything to be painted from the board.
		//board.draw() ---> Tetris.draw_piece()--->Canvas.paint() (repaints everything
		//should be Model ---> view so board.draw()---->Tetris.paint(). Way better. Why the else is Tetris.draw_piece a thing anyways?
		//draw everything in the grid
		TetrisRect[][] grid = board.grid();
		for (int i = 0; i < grid.length; i++) {
			for (int j= 0; j < grid[0].length; j++) {
				if (grid[i][j] != null) {
					TetrisRect square = grid[i][j];
					drawSquare(g,square);
				}
			}
		}
		
		//draw current piece
		TetrisRect[] current_block = board.current_pos();
		for (int i = 0; i < 4; i++) {
			TetrisRect square = current_block[i];
			drawSquare(g,square);
			
		}
		
		TetrisRect[] current = board.current_pos();
	}
	
	
	public void drawSquare(Graphics g, TetrisRect square) {
		int x = square.x();
		int y = square.y();
		int w = square.w();
		int h = square.h();
		Color c = square.color();
		
		g.setColor(Color.BLACK);
		g.drawRect(x+50, y+50, w, h);
		g.setColor(c);
		g.fillRect(x+50,y+50,w,h);
	}
	
	//this determines the size of the window for the actual game
	//the place where the blocks fall and such.
	private void place(int height, int width, int x, int y) {
		
		root.getContentPane().add(this);
		setSize(width,height);
		setBackground(Color.WHITE);
	}
	
	private void addLabel() {
		scorelabel = new JLabel("Current Score: 0");
		add(scorelabel);
		scorelabel.setBounds(50, 35, 150, 10);
	}
	
	private void addButtons() {
		JButton leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.move_left();
			}
		});
		
		JButton rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.move_right();
			}
		});
		
		JButton downButton = new JButton("\\/");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.rotate_clockwise();
			}
		});
		
		JButton upButton = new JButton("^");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.rotate_counter_clockwise();
			}
		});
		
		spaceButton = new JButton("_");
		spaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.drop_all_the_way();
			}
		});
		
		JButton newgameButton = new JButton ("New");
		newgameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.game().new_game();
			}
		});
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.game().new_game();
			}
		});
		
		add(leftButton);
		add(rightButton);
		add(upButton);
		add(downButton);
		add(spaceButton);
		add(newgameButton);
		add(pauseButton);
		
		leftButton.setBounds(50, 50 + board.block_size()*27+50, 45, 20);
		rightButton.setBounds(150,50 + board.block_size()*27+50,45, 20);
		downButton.setBounds(100, 50 + board.block_size()*27+75, 45, 20);
		upButton.setBounds(100,50 + board.block_size()*27+25,45, 20);
		spaceButton.setBounds(100, 50 + board.block_size()*27+50, 45, 20);
		newgameButton.setBounds(10,10,80,20);
		pauseButton.setBounds(100, 10, 80, 20);
	}
	
	
	public JButton space() {
		return spaceButton;
	}
	
	public JLabel label() {
		return scorelabel;
	}
	


}