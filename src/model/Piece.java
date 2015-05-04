package model;

import java.awt.Color;



public class Piece {

	//instance fields
	//shouldn't these be private?
	private int[][][] all_rotations; //not static
	private int rotation_index;
	private Color color;
	private int[] base_position; //row column possible better as a pair
	private Board board;
	private boolean moved;
	
	//helper functions
	
	//random integer from int array
	public int getRandomInt(int[] array, Board b) {
		int rnd  = b.myRand().nextInt(array.length);
		return array[rnd];
	}
	//random string from string array
	public String getRandomStr(String[] array, Board b) {
		int rnd = b.myRand().nextInt(array.length);
		return array[rnd];
	}
	public static int[][][] getRandomPiece(int[][][][] array, Board b) {
		int rnd = b.myRand().nextInt(array.length);
		return array[rnd];
	}
	//array from [0..high)
	public int[] generateRange(int low, int high) {
		int[] array = new int[high-low+1];
		for (int i = 0; i < high-low; i++) {
			array[i] = low + i;
		}
		return array;
	}
	
	//creates a new Piece from the given point array,
	//holding the board to determine if movement is possible for the
	//piece, and gives the piece a color, rotation, and starting position.
	
	public Piece(int[][][] p_a, Board b) { //also needs board, but later
		all_rotations = p_a;
		rotation_index = getRandomInt(generateRange(0,all_rotations.length), b);
		color = all_colors[getRandomInt(generateRange(0,all_colors.length),b)];
		//color = getRandomStr(all_colors,b);
		base_position = new int[]{5,1};
		board = b;
		moved = true;
	}
	
	public int[][] current_rotation() {
		return all_rotations[rotation_index];
	}
	
	public int[] position() {
		return base_position;
	}
	public Color color() {
		return color;
	}
	public boolean getMoved() {
		return moved;
	}
	public boolean drop_by_one() {
		moved = move(0,1,0);
		return moved;
	}
	
	
	//moved
	/*takes intended movement in x, y and rotation and checks to see if the movemen
	 * is possible. If it is, makes this movement and returns true.
	 * Otherwise returns false
	 */
	
	public boolean move(int delta_x, int delta_y, int delta_rotation) {
		//Alters intended rotation to stay within bounds of rotation array
		//potential is potential piece
		moved = true;
		//extra 4 so that mod value is positive
		int[][] potential = all_rotations[(rotation_index + delta_rotation+ all_rotations.length) % all_rotations.length]; //?????
		for (int i = 0; i < potential.length; i++) {
			int x = potential[i][0] + delta_x + base_position[0];
			int y = potential[i][1] + delta_y + base_position[1];
			int[] posn = {x,y};
			if (!(board.empty_at(posn)))
				moved = false;
		}
		if (moved) {
			base_position[0] += delta_x;
			base_position[1] += delta_y;
			rotation_index = (rotation_index + delta_rotation+ all_rotations.length) % all_rotations.length;	
		}
		return moved;
	}
	
	public static int[][][] rotations(int[][] point_array) {
		int[][] rotate1 = new int[4][2];
		int[][] rotate2 = new int[4][2];
		int[][] rotate3 = new int[4][2];
		for (int i = 0; i < 4; i++) { //rotate 90 clockwise
			int[] temp = point_array[i];
			rotate1[i][0] = -temp[1]; 
			rotate1[i][1] = temp[0];
		}
		for (int j = 0; j < 4; j++) { //rotate 180
			int[] temp = point_array[j];
			rotate2[j][0] = -temp[0];
			rotate2[j][1] = -temp[1];
		}
		for (int k = 0; k<4; k++) { //rotate 270 clockwise
			int[] temp = point_array[k];
			rotate3[k][0] = temp[1];
			rotate3[k][1] = -temp[0];
		}
		int[][][] rotations = {point_array, rotate1, rotate2, rotate3};
		return rotations;
	}
	
	public static Piece next_piece(Board b) {
		return new Piece(getRandomPiece(all_pieces,b),b);
	}
	
	static int[][][] square = {{{0,0},{1,0},{0,1},{1,1}}};
	static int[][]   tpiece = {{0,0},{-1,0},{1,0},{0,-1}};
	static int[][][] t_all = rotations(tpiece);
	static int[][][] long_all = {{{0,0},{-1,0},{1,0},{2,0}},
			{{0,0},{0,-1},{0,1},{0,2}}};
	static int[][]   lpiece = {{0,0},{0,-1},{0,1},{1,1}};
	static int[][][] l_all = rotations(lpiece);
	static int[][]   l2piece = {{0,0},{0,-1},{0,1},{-1,1}};
	static int[][][] l2_all = rotations(l2piece);
	static int[][]   spiece = {{0,0},{-1,0},{0,-1},{1,-1}};
	static int[][][] s_all = rotations(spiece);
	static int[][]   zpiece = {{0,0},{1,0},{0,-1},{-1,-1}};
	static int[][][] z_all = rotations(zpiece);
	
	 static int[][][][] all_pieces = {square, t_all, long_all, l_all, l2_all, 
			s_all, z_all};
	
	 Color all_colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
	            new Color(102, 204, 102), new Color(102, 102, 204), 
	            new Color(204, 204, 102), new Color(204, 102, 204), 
	            new Color(102, 204, 204), new Color(218, 170, 0)
	        };

	
}
