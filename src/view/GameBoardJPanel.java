package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameBoardJPanel extends JPanel {
	private List<Square> squares = new ArrayList<Square>();

	public GameBoardJPanel(int side, int amount) {
		int y = 0;
		int x = 0;
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < amount; j++) {
				squares.add(new Square(x + side * i, y + side * j, side, Color.LIGHT_GRAY));
			}
		}

	}
//event handling for clicking within the grid used for the board
	public void addMouseClickListener(MouseListener listener) {
		this.addMouseListener(listener);
	}

	public List<Square> getSquares() {
		return squares;
	}

	public void setColor(int nr, Color color) {
		squares.get(nr).setBorderColor(color);
		squares.get(nr).setColor(color);
		this.repaint();
	}
//paints squares in the grid
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		for (Square square : squares) {
			square.paint(g);
		}
	}
}
