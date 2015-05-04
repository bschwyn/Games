package model;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class PieceTest {
	
	@Test
	public void testCreateFirstPiece() {
		Board b = new Board(null);

		Piece p = b.current_block();
		int[][] tpiece = {{0,0},{0,-1},{0,1},{-1,1}};
		assertTrue(Arrays.deepEquals(p.current_rotation(), tpiece));
		System.out.println(Arrays.deepToString(p.current_rotation()));
		int[] xy = {5,0};
		assertTrue(Arrays.equals(p.position(),  xy));
		
	}
	
	@Test
	public void testMove() {
		Board b = new Board(null);
		Piece p = b.current_block();
		
		//moving left
		int[] xy1 = {5,0};
		assertTrue(Arrays.equals(p.position(), xy1));
		assertTrue(p.move(-1,0,0));
		
		int[] xy2 = {4,0};
		assertTrue(Arrays.equals(p.position(),xy2));
		
		//run it into the wall.
		p.move(-3, 0, 0);
		int[] xy3 = {1,0};
		assertTrue(Arrays.equals(p.position(),xy3));
		
		assertFalse(p.move(-1, 0, 0));
		assertFalse(p.move(-100, 0, 0));
		
		//move right
		p.move(7,0,0);
		int[] xy4 = {8,0};
		assertTrue(Arrays.equals(p.position(), xy4));
		
		//rotate counter clockwise
		p.move(-3,0,0); //centering
		p.move(0, 0, 1);
		assertTrue(Arrays.equals(p.position(),xy1)); //making sure it's still in the same place
		//I'm not sure what it is supposed to look like so I'm just going to print it out and see if it is correct from there.
		//rotation = {{bla bla bla}}
		//assertTrue (arrays.equals current_rotation with rotation
		
		//rotate clockwise
		
		//drop all the way to the bottom
		assertFalse(p.move(0, 100, 0));
		
		
	}
	

}
