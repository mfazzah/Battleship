package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square {
	// data members
	private int x, y, side;
	private Color color;
	public boolean isOccupied;
	private Color borderColor;

	// constructor
	public Square(int x, int y, int side, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.side = side;
		this.color = color;
		isOccupied = false;
		borderColor = Color.BLACK;
	}

	// changes color of square
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, side, side);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g.setColor(borderColor);
		g.drawRect(x, y, side, side);
	}

	/* GETTERS AND SETTERS */

	public Color getColor() {
		return color;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied() {
		this.isOccupied = true;
	}
	//check if specific sqaure has been clicked, and change status if it has
	public boolean isClicked(int x, int y) {
		boolean isClicked = this.x <= x && this.x + side >= x && this.y <= y && this.y + side >= y;
		return isClicked;
	}
}
