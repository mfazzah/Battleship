package model;

import model.state.GameState;
import model.state.NewGameState;
import model.state.StartedGameState;


public class BattleshipGame {
	//data members 
	private HumanPlayer humanPlayer;
	private ComputerPlayer computerPlayer;

	private CalculateScore scoreHumanPlayer;
	private CalculateScore scoreComputerPlayer;

	private GameState newGameState = new NewGameState(this);
	private GameState StartedGameState = new StartedGameState(this);
	private GameState currentGameState = newGameState;

	//initializes objects for game set up 
	public BattleshipGame(String name) {
		humanPlayer = new HumanPlayer(name);
		computerPlayer = new ComputerPlayer();
		scoreHumanPlayer = new CalculateScore(humanPlayer);
		scoreComputerPlayer = new CalculateScore(computerPlayer);
	}

	//GETTERS AND SETTERS 
	public GameState getCurrentGameState() {
		return this.currentGameState;
	}

	public void setCurrentGameState(GameState gameState) {
		this.currentGameState = gameState;
	}

	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}

	public ComputerPlayer getComputerPlayer() {
		return computerPlayer;
	}

	public GameState getNewGameState() {
		return newGameState;
	}

	public GameState getStartedGameState() {
		return StartedGameState;
	}

	public void start() {
		this.currentGameState.start();
	}

	public void newGame() {
		this.currentGameState.newGame();
	}

	public CalculateScore getScoreHumanPlayer() {
		return this.scoreHumanPlayer;
	}

	public CalculateScore getScoreComputerPlayer() {
		return this.scoreComputerPlayer;
	}

}
