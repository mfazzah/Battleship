package model.state;

import model.BattleshipGame; 

public interface GameState {
	public static final BattleshipGame game = null; 
	
	public void newGame();
	public void start();
}
