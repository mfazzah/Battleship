package model.state;

import model.BattleshipGame;
import model.ModelException;

public class StartedGameState implements GameState {
	private BattleshipGame game;

	public StartedGameState(BattleshipGame game) {
		this.game = game;
	}

	@Override
	public void newGame() {
		this.game.setCurrentGameState(this.game.getNewGameState());
	}

	@Override
	public void start() {
		throw new ModelException("A game is already underway!");
	}

}
