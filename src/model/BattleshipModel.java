package model;
/* Handles all of the updates in the data layer, responsible for loading data */
import java.util.ArrayList;
import java.util.List;

public class BattleshipModel {

	private BattleshipGame game;

	public BattleshipModel(String name) {
		this.game = new BattleshipGame(name);
	}

	//sets human player's name
	public void setHumanName(String name) {
		this.getHumanPlayer().setName(name);
	}
	//returns string with name of players 
	public String getHumanName() {
		return this.getHumanPlayer().getName();
	}

	public String getComputerName() {
		return this.getComputerPlayer().getName();
	}

	public ArrayList<Ship> getHumanShips() {
		return this.getHumanPlayer().getShips();
	}
//adds one ship to human board after placement conditions are met
	public void addShipToHumanPlayer(Ship ship) {
		this.getHumanPlayer().addShip(ship);
	}

	public void addShipToHumanPlayer(ShipType shipType, Direction direction, int startSquare) {
		this.getHumanPlayer().addShip(shipType, direction, startSquare);
	}

	public HumanPlayer getHumanPlayer() {
		return this.game.getHumanPlayer();
	}

	public Ship getLastAddedShipToHumanPlayer() {
		return this.getHumanPlayer().getlastAddedShip();
	}

	public ComputerPlayer getComputerPlayer() {
		return this.game.getComputerPlayer();
	}
//add ships to computer's board
	public void computerGenerateShips() {
		this.getComputerPlayer().setShipsFromStrategy();
	}
//list of squares where ships for both players exist
	public List<Integer> getAllComputerShipNumbers() {
		return this.getComputerPlayer().getAllShipNumbers();
	}

	public List<Integer> getAllHumanPlayerShipNumbers() {
		return this.getHumanPlayer().getAllShipNumbers();
	}
//if opponent chooses square a ship is in, change boolean value to true
	public boolean addHitNumberToComputerShip(int number) {
		return this.getComputerPlayer().addHitToShip(number);
	}

	public boolean addHitNumberToHumanPlayerShip(int number) {
		return this.getHumanPlayer().addHitToShip(number);
	}

	public BattleshipGame getGame() {
		return game;
	}

	public void startGame() {
		this.getGame().start();
	}

	public void newGame() {
		this.getGame().newGame();
	}

	public ArrayList<Integer> allNumbersfDestroyedShipsOfComputer() {
		return this.getComputerPlayer().allNumbersOfDestroyedShips();
	}

	public ArrayList<Integer> allNumbersfDestroyedShipsOfHumanPlayer() {
		return this.getHumanPlayer().allNumbersOfDestroyedShips();
	}

	public int getComputerShot() {
		return this.getGame().getComputerPlayer().hitShip();
	}
	//keeps track of players' scores 
	public int getHumanPlayerScore() {
		return this.getGame().getScoreHumanPlayer().getScore();
	}

	public int getComputerPlayerScore() {
		return this.getGame().getScoreComputerPlayer().getScore();
	}
	
	//returns game over if game over conditions are met 
	public boolean getIfGameOverHumanPlayer() {
		return this.getHumanPlayer().isGameOver();
	}

	public boolean getIfGameOverComputer() {
		return this.getComputerPlayer().isGameOver();
	}
	//methods read which hit and ship placement property for computer to use from selected settings 
	public void readHitStrategyFromProp() {
		this.getComputerPlayer().readHitStrategyFromProp();
	}

	public void readPlacesShipStrategyFromProp() {
		this.getComputerPlayer().readPlaceShipFromProp();
	}

}
