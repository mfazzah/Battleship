package model.state;

import model.BattleshipGame;
import model.ModelException;

public class NewGameState implements GameState {
	private BattleshipGame game;

	public NewGameState(BattleshipGame game) {
		this.game = game;
	}

	@Override
	//prevents a new game from starting if a game is already in session
	public void newGame() {
		throw new ModelException("Cannot start a new game if a game is already in session!");
	}

	@Override
	//prevents a game from starting until player has placed 5 ships on their board
	public void start() {
		if (game.getHumanPlayer().getNumberOfShips() != 5) {
			throw new ModelException("Five ships must be placed before game can begin!");
		}
		game.setCurrentGameState(game.getStartedGameState());
	}

}
