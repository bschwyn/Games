package model;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import controller.Tetris;
import view.TetrisRect;

public class BoardTest {
	/*
	@Test
	public void testInitializeTetris() {
		System.out.println("testInitializeTetris");
		Tetris g = new Tetris();
	}
*/
	@Test
	public void testCreateBoard() {
		System.out.println("testCreateBoard");
		Tetris g = new Tetris();
		Board b = new Board(g);
		
		assertFalse(b.is_game_over());
		assertEquals(0, b.score());
		Piece.next_piece(b);		
		Piece p = b.current_block();
		int[][] tpiece = {{0,0},{0,-1},{0,1},{-1,1}};
	
		assertTrue(Arrays.deepEquals(p.current_rotation(), tpiece));
		//System.out.println(Arrays.deepToString(p.current_rotation()));
		
		//test that the grid is empty. also check row and column size
		Integer[][] emptyGridForComparison = new Integer[27][10];
		assertTrue(Arrays.deepEquals(b.grid(),emptyGridForComparison));
	}
/*	
	@Test
	public void testGameOver() {
		System.out.println("testGameOver");
		Tetris g = new Tetris();
		Board b = new Board(null);
		//gameover should be false at the beginning of the game
		assertFalse(b.is_game_over());
		
		//test if the game is over when the top row has stuff in it.
		//fill the grid. This needs to be something that I do by just setting up a mock grid.
		//setup
		
		b.current_block().move(0, 2, 0);
		b.draw();
		b.store_current();
		
		assertTrue(b.is_game_over());
		
	}
	
	@Test
	public void testRun() {
		System.out.println("testRun");
		//test that it moves the current piece down by one
		//and updates score correctly
		//test and see if it stores a piece that is at the bottom
		//and updates score correctly
		Tetris g = new Tetris();
		Board b = new Board(null);
		//tests drop by one or store
	}
	
	
	//this is more of a piece test, but there is not much that is testable except "draw" in Board
	@Test
	public void testMoveLeft() {
		System.out.println("testMoveLeft");
		Tetris g = new Tetris();
		Board b = new Board(g);
		//moves current block to the left
		//put a piece at the left side of the grid and make sure that it does not move.
		int[] xy1 = {5,0};
		assertTrue(Arrays.equals(b.current_block().position(), xy1));
		b.move_left();
		int[] xy2 = {4,0};
		assertTrue(Arrays.equals(b.current_block().position(),xy2));
		
		//run it into the wall.
		b.move_left();
		b.move_left();
		b.move_left();
		b.move_left();
		b.move_left();
		b.move_left();
		int[] xy3 = {1,0}; //L shape has 1 extra thingy to the left of it's center so this is as close as it can get to the wall.
		assertTrue(Arrays.equals(b.current_block().position(),xy3)); 
		
	}
	
//not testing move right, rotate clockwise, and rotate counterclockwise
	
	@Test
	public void testDropAllTheWay() {
		System.out.println("testDropAllTheWay");
		Tetris g = new Tetris();
		Board b = new Board(g);
	
		//stored at the botttom
		TetrisRect[][] grid_with_L = new TetrisRect[27][10];
		
		grid_with_L[24][5] = null;
		grid_with_L[25][5] = null;
		grid_with_L[26][5] = null;
		grid_with_L[26][4] = null;
		
		b.drop_all_the_way();
		assertEquals(b.score(),25);
		
		//worked with ints
		assertTrue(Arrays.deepEquals(grid_with_L,b.grid()));
		
		
		//still stuff to test with getting the next piece
		
	}
	
	@Test
	public void testNextPiece() { //for completion's sake
		System.out.println("testNextPiece");
		Board b = new Board(null);
		Piece.next_piece(b);		
		Piece p = b.current_block();
		int[][] lpiece = {{0,0},{0,-1},{0,1},{-1,1}};
		assertTrue(Arrays.deepEquals(p.current_rotation(), lpiece));
	}
	
	//TODO current test still with Integer, not with TetrisRect
	@Test
	public void testStoreCurrent() {
		Board b = new Board(null);
		
		
		//to store in the first place possible, first need to drop_by_one, otherwise out_of bounds
		//drop by two to avoid weird end game stuff---deal with that separately.
		
		//note that board is actually only able to access the store
		b.current_block().move(0, 2, 0); //**********************   !!!
		b.draw();
		b.store_current();
		
		TetrisRect[][] grid_with_L = new TetrisRect[27][10];
		grid_with_L[2][5] = new TetrisRect(b.canvas(), 1,2,3,4, null);
		grid_with_L[1][5] = new TetrisRect(b.canvas(), 1,2,3,4, null);
		grid_with_L[3][5] = new TetrisRect(b.canvas(), 1,2,3,4, null);
		grid_with_L[3][4] = new TetrisRect(b.canvas(), 1,2,3,4, null);
		assertTrue(Arrays.deepEquals(grid_with_L, b.grid()));
		//TODO still untested lines referring to remove_filled
	}
	
	@Test
	public void testEmptyAt() {
		System.out.println("testEmptyAt");
		Board b = new Board(null);
		//test bounds
		int[] p1 = {-1,-1};
		int[] p2 = {5,0};
		int[] p3 = {2,25};
		int[] p4 = {2,30};
		assertFalse(b.empty_at(p1));
		assertTrue(b.empty_at(p2));
		assertTrue(b.empty_at(p3));
		assertFalse(b.empty_at(p4));

		//test at stored piece
		b.current_block().move(0,2,0);
		b.draw();
		b.store_current();
		int[] q1 = {5,2};
		assertFalse(b.empty_at(q1));
	}
	
	@Test
	public void testRemoveFilled() {
		System.out.println("testRemoveFilled");
		Board b = new Board(null);
		//will be a pain to test by creating pieces and stuff.
		b.remove_filled();
		
		assertEquals(10,b.score());
	}
	
	@Test
	public void testMyRand() {
		System.out.println("testMyRand");
		Board b = new Board(null);
		long seed = 1;
		Random testR = new Random(seed);
		assertEquals(b.myRand().nextInt(1000), testR.nextInt(1000));
	}
	*/
}
