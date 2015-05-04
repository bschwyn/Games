package view;
import java.awt.Color;
import java.awt.Graphics;

public class TetrisRect {
	private int x;
	private int y;
	private int w;
	private int h;
	private Color c;
	private Graphics g;

	public TetrisRect(TetrisCanvas canvas, int a, int b, int width, int height, Color color) {
		//in the Ruby file this would call a TkcRectangle
		//which would draw it. In my case I am going to write my own.
		x = a;
		y = b;
		w = width;
		h = height;
		c = color;
		
		canvas.repaint();
	}
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	public void changey(int newy) {
		y = y+newy;
	}
	public int w() {
		return w;
	}
	public int h() {
		return h;
	}
	public Color color() {
		return c;
	}
	
	public void move(int dx, int dy) {
		//canvas.repaint();
		g.translate(dx,dy);
	}
}
