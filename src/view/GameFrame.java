package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private GameBoardJPanel panel1;
	private GameBoardJPanel panel2;
	private JPanel humanNamePanel = new JPanel();
	private JPanel computerNamePanel = new JPanel();
	private ShipBoardJPanel shipBoardJPanel;
	private JButton startButton;

	private JButton settingsButton;

	private JLabel humanNameLabel;
	private JLabel computerNameLabel;
	//set dimensions for the frame holding the game
	public final static int HEIGHT_FRAME = 500; 
	public final static int WIDTH_FRAME = (int) (HEIGHT_FRAME * 2.5);
	public final static int PANEL_SIZE = (int) (HEIGHT_FRAME * 4 / 5);
	public final static int NUM_ROWS = 10; //
	public boolean tryToStart = false;

	public GameFrame() {
		super();
	}
//launches frame with all of its components
	public void launch(String name) {
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Battleship");

		this.completeSettingsShipBoardJPanel();

		this.completeSettingsNamePanel1(name);
		this.completeSettingsNamePanel2();

		this.completeSettingsHuman();
		this.completeSettingsComputer();

		this.setStartButton();
		this.setSettingsButton();
	}
	//settings buttom setup
	private void setSettingsButton() {
		this.settingsButton = new JButton("Settings");
		this.settingsButton.setLocation(25, PANEL_SIZE - 10);
		this.settingsButton.setSize(new Dimension(350, 40));
		this.add(this.settingsButton);
	}

	//start button setup
	private void setStartButton() {
		this.startButton = new JButton("Start");
		this.startButton.setLocation(25, PANEL_SIZE - 50);
		this.startButton.setSize(new Dimension(350, 40));
		this.add(startButton);

	}
//adds listener to start button
	public void addMouseClickListenerToStartButton(MouseListener listener) {
		this.startButton.addMouseListener(listener);
	}
//adds listener to settings button
	public void addMouseClickListenerToSettingsButton(MouseListener listener) {
		this.settingsButton.addMouseListener(listener);
	}
//initializes settings component
	private void completeSettingsShipBoardJPanel() {
		this.shipBoardJPanel = new ShipBoardJPanel();
		this.shipBoardJPanel.setSize(new Dimension(PANEL_SIZE - 25, PANEL_SIZE / 2));
		this.shipBoardJPanel.setLocation(25, 50);
		this.add(shipBoardJPanel);
	}
	//human player setup
	private void completeSettingsHuman() {
		panel1 = new GameBoardJPanel((PANEL_SIZE / NUM_ROWS), NUM_ROWS);
		panel1.setBackground(Color.GRAY);
		panel1.setSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		panel1.setLocation(PANEL_SIZE, 50);
		panel1.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(panel1);
	}


	//computer player setup 
	private void completeSettingsComputer() {
		panel2 = new GameBoardJPanel((PANEL_SIZE / NUM_ROWS), NUM_ROWS);
		panel2.setBackground(Color.GRAY);
		panel2.setSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		panel2.setLocation(2 * PANEL_SIZE + 25, 50);
		panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(panel2);
	}


	public GameBoardJPanel getHumanBoard() {
		return panel1;
	}


	public GameBoardJPanel getComputerBoard() {
		return panel2;
	}


	public ShipBoardJPanel getShipBoardJPanel() {
		return shipBoardJPanel;
	}

//settings for human name panel
	public void completeSettingsNamePanel1(String n) {
		String name = n;
		if (n == null || n.isEmpty()) {
			name = "Player1";
		}
		this.humanNameLabel = new JLabel(name + ":", SwingConstants.LEFT);
		humanNamePanel.add(humanNameLabel);
		humanNamePanel.setSize(new Dimension(100, 25));
		humanNamePanel.setLocation(PANEL_SIZE, 25);
		this.add(humanNamePanel);
	}

	public void updateNameFieldHuman(String n) {
		this.humanNameLabel.setText(n);
	}

	public void updateNameFieldComputer(String n) {
		this.computerNameLabel.setText(n);
	}
//settings for computer player's name panel
	public void completeSettingsNamePanel2() {
		this.computerNameLabel = new JLabel("Computer:");
		computerNamePanel.add(computerNameLabel);
		computerNamePanel.setSize(new Dimension(100, 25));
		computerNamePanel.setLocation(2 * PANEL_SIZE + 25, 25);

		this.add(computerNamePanel);
	}

	public void disableStartButton() {
		this.startButton.setEnabled(false);
	}

	public void disableSettingsButton() {
		this.settingsButton.setEnabled(false);
	}

	public boolean isStartButtonEnabled() {
		return this.startButton.isEnabled();
	}

	public boolean isSettingsButtonEnabled() {
		return this.settingsButton.isEnabled();
	}

	public void disableGameBoardJPanel1() {
		this.getHumanBoard().setEnabled(false);
	}

	public boolean isGameBoardJPanel1Enabled() {
		return this.getHumanBoard().isEnabled();
	}

	public void closeApplication() {
		setVisible(false);
		dispose();
	}

}
