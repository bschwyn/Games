package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import java.util.Random;
import controller.Tetris;
import view.TetrisRect;
import view.TetrisCanvas;
import view.TetrisRoot;
import view.Background;

public class Board {
	//Model: contains game information such as score, which piece is falling, locations of pieces,
	//and ability to move pieces on the board.
	

	private TetrisRect[][] grid;
	private Piece current_block;
	private int score;
	private Tetris game;
	private int delay;
	private TetrisRect[] current_pos = null;
	private long seed;
	private TetrisCanvas canvas;
	Random random;
	
	
	public Board(Tetris g) {
		game = g;
		createView(this);

		seed = 1;
		random = new Random();
		random.setSeed(seed);
		
		grid = new TetrisRect[num_rows()][num_columns()];
		for (int i =0; i < grid.length; i++) {
			Arrays.fill(grid[i],null);
		}
		current_block = Piece.next_piece(this);
		score = 0;
		delay = 1500;
	}
	
	private void createView(Board b) {
		TetrisRoot root = game.root();
		canvas = new TetrisCanvas(root, b);
		
	}
	
	public Random myRand() {
		return random;
	}
	
	//size of square in pixels
	public int block_size() {
		return 15;
	}
	public int num_columns() {
		return 10;
	}
	public int num_rows() {
		return 27;
	}
	public int score() {
		return score;
	}
	public TetrisCanvas canvas() {
		return canvas;
	}
	public Tetris game() {
		return game;
	}

	public TetrisRect[][] grid() {
		return grid;
	}
	public int delay() {
		// TODO this doens't belong here, move to controller? 
		return delay;
	}
	//check if top row has a piece stored.
	public boolean is_game_over() {
		//any nonzero in grid row 1?
		boolean any = false;
		//top row contains an object?
		for (int i = 0; i < num_columns(); i++) {
			if (grid[2][i]!= null) {
				any = true; 
			}
		}
		return any;
	}
	
	//moves the current piece down by one, if not possible, stores
	//current piece and replaces it with new one.
	//called by timer
	public void run() {
		boolean ran = current_block.drop_by_one();
		if (!ran) {
			store_current();
			if (!is_game_over()) {
				next_piece();
			}
		}
		game.update_view_score();
		draw();
	}
	
	public void move_left() {
		if (!is_game_over() && game.is_running()) {
			current_block.move(-1,0,0);
		}
		draw();
	}
	
	public void move_right() {
		if (!is_game_over() && game.is_running()) {
			current_block.move(1,0,0);
		}
		draw();
	}
	
	public void rotate_clockwise() {
		if (!is_game_over() && game.is_running()) {
			current_block.move(0,0,-1);
		}
		draw();
	}
	public void rotate_counter_clockwise() {
		if (!is_game_over() && game.is_running()) {
			current_block.move(0,0,1);
		}
		draw();
	}
	
	//drops piece to lowest location in the column
	//gets new piece
	//changes score
	public void drop_all_the_way() {
		//worked when tetrisrect was ints
		if (game.is_running()) {
			boolean ran = current_block.drop_by_one();
			
			while (ran) {
				score +=1;
				ran = current_block.drop_by_one();
			}
			draw();
			store_current();
			if (!is_game_over()) {
				next_piece();
			}
			game.update_view_score();
			draw();
		}
	}
	
	//get next piece
	public void next_piece() {
		current_block = Piece.next_piece(this);
		current_pos = null;
	}
	
	public void store_current() {

		int[][] locations = current_block.current_rotation();
		int[] displacement = current_block.position();
		for (int i = 0; i < 4; i++) {
			int[] current = locations[i]; //for every square within a full block (usually 4)
			grid[current[1] + displacement[1]][current[0] + displacement[0]]= current_pos[i];
		}
		
		remove_filled();
		//TODO implement delay in timer scheduling
		if (delay - 2 > 80) {
			delay -= 2;
		} else {
			delay = 80;
		}
	}
	
	//check if grid contains stored rectangle at coordinates (x,y)
	public boolean empty_at(int[] coordinate){
		if (!(coordinate[0] >= 0 && coordinate[0] < num_columns())) {
			return false;
		} else if (coordinate[1] < 1) { //top row should remain empty
			return true;
		} else if (coordinate[1] >= num_rows()) {
			return false;
		}
		//empty?
		return (grid[coordinate[1]][coordinate[0]] == null);
	}
	
	
	public void remove_filled() {
		int len = grid.length;
		for (int num=2; num < len; num++) {
			//see if row is full (has no nulls or zeros)
			if (is_row_full(grid[num])) {
				//remove  from canvas blocks in full row
				for (int num2 = 27-num +1; num2 < len; num2++) {
					for (int i = 0; i < 10; i++) {
						grid[len-num2 +1][i] = grid[len-num2][i];
						if (grid[len-num2+1][i] != null) {
							grid[len-num2+1][i].changey(block_size());
						}
					}
					//grid[len-num2 +1] = grid[len-num2];
					
				}

				//insert new blank row at top
				Arrays.fill(grid[0], null);
				score +=10;
			}
		}
	}
	
	//helper function
		private boolean is_row_full (TetrisRect[] gridrow) {
			boolean all = true;
			for (int i = 0; i < gridrow.length; i++) {
				if (gridrow[i]==null) {
					all = false;
				}
			}
			return all;
		}
		
	public void draw() {
		
		if (current_pos!= null && current_block.getMoved()) {
			for (int i = 0; i<current_pos.length; i++) {
				current_pos[i] = null;
			}
		}
		int size = block_size();
		int[][] blocks = current_block.current_rotation();
		int[] start = current_block.position();
		current_pos = new TetrisRect[4]; //size of block
		
		//change current_pos
		for (int i = 0; i < blocks.length; i++) {
			int[] block = blocks[i];
			/*int x = start[0]*size + block[0]*size + 3;
			int y = start[1]*size + block[1]*size;
			int w = start[0]*size + size + block[0]*size + 3;
			int h = start[1]*size + size + block[1]*size;*/
			int x = start[0]*size + block[0]*size + 3;
			int y = start[1]*size + block[1]*size;
			int w = size-3;
			int h = size-3;
			
			current_pos[i] = new TetrisRect(canvas, x, y,w,h, current_block.color());
		}

		canvas.repaint();

	}
	
	public Piece current_block() {
		return current_block;
	}
	public TetrisRect[] current_pos() {
		return current_pos;
	}
	
}
