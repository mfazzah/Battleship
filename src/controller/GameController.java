package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.BattleshipModel;
import model.ModelException;
import view.BattleshipView;

public class GameController {
	private BattleshipModel model;
	private BattleshipView view;

	public GameController() {
		this.setUpGame();
	}

	
	/* EVENT HANDLING */
	public void setUpGame() {
		view = new BattleshipView();
		model = new BattleshipModel(view.getPlayerName());
		view.getHumanBoard().addMouseClickListener(new MouseClickHandler());
		view.getGameFrame().addMouseClickListenerToStartButton(new StartButtonHandler());
		view.getGameFrame().addMouseClickListenerToSettingsButton(new SettingsHandler());
	}

	//event handling for settings button
	private class SettingsHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isSettingsButtonEnabled()) {
				view.disableSettingsButton();
				view.openSettingsJFrame();
			}
		}
	}

	//upon clicking start button, determine ship placement and hit strategy based upon user options
	private class StartButtonHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isStartButtonEnabled()) {
				try {
					model.startGame();
					model.readHitStrategyFromProp();
					model.readPlacesShipStrategyFromProp();
					computerGenerateShips();
					if (view.getSettingsFrame() != null && view.isShipsVisible()) {
						showGeneratedShips();
					}
					view.disableStartButton();
					view.disableHumanBoardJPanel();
					view.getComputerBoard().addMouseClickListener(new ShootClickHandler());
					updateNameFieldComputer();
					updateNamefieldHuman();
					if (view.isSettingsButtonEnabled()) {
						view.disableSettingsButton();
					}
				} catch (Exception e) {
					view.showError(e.getMessage());
				}
			}
		}
	}

	//event handling for how to handle attacks/shots when taking turns 
	private class ShootClickHandler extends MouseAdapter {

		ArrayList<Integer> computerShipNumbers = (ArrayList<Integer>) model.getAllComputerShipNumbers();
		ArrayList<Integer> humanShipNumbers = (ArrayList<Integer>) model.getAllHumanPlayerShipNumbers();

		public void mouseClicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			int beginNumber = -1;

			for (int i = 0; i < view.getComputerBoardSize(); i++) {
				if (view.isClikcedComputerBoard(i, x, y)) {
					beginNumber = i;
					if (!view.getOccupiedSquaresComputerBoard(beginNumber)) {
						view.setOccupiedSquaredComputerBoard(beginNumber);
						if (computerShipNumbers.contains(beginNumber)) {
							if (model.addHitNumberToComputerShip(beginNumber)) {
								for (Integer integer : model.allNumbersfDestroyedShipsOfComputer()) {
									view.shipColorComputerBoard(integer, Color.RED);
								}
							} else {
								view.shipColorComputerBoard(beginNumber, Color.YELLOW);
							}

						} else {
							view.shipColorComputerBoard(beginNumber, Color.BLUE);
						}
						updateNamefieldHuman();
						if (model.getIfGameOverComputer()) {
							view.showMessage("Game over!\n" + model.getHumanName() + " won with "
									+ model.getHumanPlayerScore() + " points.");
							endGame();
						} else {
							this.computerShoots();
						}
						break;
					}
				}
			}

		}
//event handling for computer's turn
		public void computerShoots() {
			int shot = model.getComputerShot();

			if (humanShipNumbers.contains(shot)) {
				if (model.addHitNumberToHumanPlayerShip(shot)) {
					for (Integer integer : model.allNumbersfDestroyedShipsOfHumanPlayer()) {
						view.shipColorHumanBoard(integer, Color.RED);
					}
				} else {
					view.shipColorHumanBoard(shot, Color.YELLOW);
				}
			} else {
				view.shipColorHumanBoard(shot, Color.BLUE);
			}
			updateNameFieldComputer();
			if (model.getIfGameOverHumanPlayer()) {
				view.showMessage("Game over!\n" + model.getComputerName() + " won met "
						+ model.getComputerName() + " punten...");
				endGame();
			}
		}

	}

	public void updateNamefieldHuman() {
		view.updateNameFieldHuman(model.getHumanName() + " (" + model.getHumanPlayerScore() + "):");
	}

	public void updateNameFieldComputer() {
		view.updateNameFieldComputer(model.getComputerName() + " (" + model.getComputerPlayerScore() + "):");
	}

	public void endGame() {
		view.closeApplication();
		this.setUpGame();
	}

	private class MouseClickHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isHumanBoardEnabled()) {

				int x = event.getX();
				int y = event.getY();
				int beginNumber = -1;
				for (int i = 0; i < view.getHumanBoardSize(); i++) {
					if (view.isClickedHumanBoard(i, x, y)) {
						beginNumber = i;
						try {
							model.addShipToHumanPlayer(view.getSelectedShipType(), view.getSelectedDirection(),
									beginNumber);
							for (Integer shipNumber : model.getLastAddedShipToHumanPlayer().getShipNumbers()) {
								view.shipColorHumanBoard(shipNumber, Color.WHITE);
							}
						} catch (ModelException e) {
							view.showError(e.getMessage());
						}
						break;
					}
				}
			}
		}
	}

	private void computerGenerateShips() {
		model.computerGenerateShips();
	}
//if player chooses for computer ships to be shown, method will trigger ships to be shown as white squares
	private void showGeneratedShips() {
		for (Integer number : model.getAllComputerShipNumbers()) {
			view.shipColorComputerBoard(number, Color.WHITE);
		}
	}

}
