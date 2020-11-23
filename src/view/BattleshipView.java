package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Direction;
import model.ShipType;

public class BattleshipView {
	private GameFrame gameFrame;
	private SettingsJFrame settingsJFrame;
	private String playerName;

	public BattleshipView() {
		gameFrame = new GameFrame();
		gameFrame.launch(this.askPlayerName());
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	//ask for human player's name upon game starting
	public String askPlayerName() {
		playerName = JOptionPane.showInputDialog("What is your name?");

		return playerName;
	}
//controls view for settings frame 
	public void openSettingsJFrame() {
		settingsJFrame = new SettingsJFrame();
		settingsJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsJFrame.setVisible(true);
	}

	//GETTERS AND SETTERS 
	public String getPlayerName() {
		return playerName;
	}

	public GameBoardJPanel getHumanBoard() {
		return this.getGameFrame().getHumanBoard();
	}

	public GameBoardJPanel getComputerBoard() {
		return this.getGameFrame().getComputerBoard();
	}

	public int getHumanBoardSize() {
		return this.getHumanBoard().getSquares().size();
	}

	public int getComputerBoardSize() {
		return this.getComputerBoard().getSquares().size();
	}

	public boolean isClikcedComputerBoard(int i, int x, int y) {
		return this.getComputerBoard().getSquares().get(i).isClicked(x, y);
	}

	public boolean isClickedHumanBoard(int i, int x, int y) {
		return this.getHumanBoard().getSquares().get(i).isClicked(x, y);
	}

	public void shipColorHumanBoard(int number, Color color) {
		this.getHumanBoard().setColor(number, color);
	}

	public void shipColorComputerBoard(int number, Color color) {
		this.getComputerBoard().setColor(number, color);
	}

	public boolean getOccupiedSquaresHumanBoard(int number) {
		return this.getHumanBoard().getSquares().get(number).isOccupied();
	}

	public boolean getOccupiedSquaresComputerBoard(int number) {
		return this.getComputerBoard().getSquares().get(number).isOccupied();
	}

	public void setOccupiedSquaredComputerBoard(int number) {
		this.getComputerBoard().getSquares().get(number).setOccupied();
	}

	public ShipType getSelectedShipType() {
		return this.getGameFrame().getShipBoardJPanel().getSelectedShipType();
	}

	public Direction getSelectedDirection() {
		return this.getGameFrame().getShipBoardJPanel().getSelectedDirection();
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void disableStartButton() {
		this.getGameFrame().disableStartButton();
	}

	public boolean isStartButtonEnabled() {
		return this.getGameFrame().isStartButtonEnabled();
	}

	public void disableSettingsButton() {
		this.getGameFrame().disableSettingsButton();
	}

	public boolean isSettingsButtonEnabled() {
		return this.getGameFrame().isSettingsButtonEnabled();
	}

	public void disableHumanBoardJPanel() {
		this.getGameFrame().disableGameBoardJPanel1();
	}

	public boolean isHumanBoardEnabled() {
		return this.getGameFrame().isGameBoardJPanel1Enabled();
	}

	public void updateNameFieldHuman(String n) {
		this.getGameFrame().updateNameFieldHuman(n);
	}

	public void updateNameFieldComputer(String n) {
		this.getGameFrame().updateNameFieldComputer(n);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void closeApplication() {
		this.getGameFrame().closeApplication();
	}

	public boolean isShipsVisible() {
		return this.settingsJFrame.shipsVisible();
	}

	public SettingsJFrame getSettingsFrame() {
		return this.settingsJFrame;
	}

}
