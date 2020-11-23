package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class HumanPlayer extends Observable {
	private String name;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	public static final int MAX_SHIPS = 5;
	
	public HumanPlayer() {
		this("defaultName");
	}

	public HumanPlayer(String name) {
		this.setName(name);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			this.name = "Player1";
		} else {
			this.name = name;
		}
	}

	public Ship getShipContainsNumber(int number) {
		for (Ship ship : this.getShips()) {
			if (ship.getShipNumbers().contains(number)) {
				return ship;
			}
		}
		return null;
	}


	public boolean addHitToShip(int number) {
		// returns if ship was destroyed
		Ship ship = this.getShipContainsNumber(number);
		if (ship == null) {
			throw new ModelException("Ship not found!");
		}
		ship.addNumberHit(number);
		boolean destroyed = false;
		if (ship.getShipNumbersHit().containsAll(ship.getShipNumbers())) {
			destroyed = true;
		}
		this.setChanged();
		this.notifyObservers();

		return destroyed;
	}

	public ArrayList<Ship> getAllDestroyedShips() {
		ArrayList<Ship> destroyedShips = new ArrayList<Ship>();
		for (Ship ship : this.getShips()) {
			if (ship.getShipNumbersHit().containsAll(ship.getShipNumbers())) {
				destroyedShips.add(ship);
			}
		}
		return destroyedShips;
	}

	public boolean isGameOver() {
		boolean gameOver = false;
		if (this.getAllDestroyedShips().containsAll(this.getShips())) {
			gameOver = true;
		}
		return gameOver;
	}

	public ArrayList<Integer> allNumbersOfDestroyedShips() {
		ArrayList<Integer> destroyedNumbers = new ArrayList<Integer>();
		for (Ship s : this.getAllDestroyedShips()) {
			destroyedNumbers.addAll(s.getShipNumbers());
		}
		return destroyedNumbers;
	}

	public void addHitToShip(int number, Ship schiep) {
		Ship ship = schiep;
		if (ship == null) {
			throw new ModelException("Ship not found!");
		}
		ship.addNumberHit(number);
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public int getNumberOfShips() {
		return this.getShips().size();
	}

	public List<Integer> getAllShipNumbers() {
		List<Integer> shipnumbers = new ArrayList<Integer>();
		for (Ship ship : ships) {
			shipnumbers.addAll(ship.getShipNumbers());
		}
		return shipnumbers;
	}

	public void addShip(Ship ship) {
		if (maxNumberOfShips()) {
			throw new ModelException("You can't place more than 5 ships!");
		}
		if (!this.maxNumberOfShipTypes(ship)) {
			throw new ModelException("It is not possible to place more ships of this type!");
		}
		if (!this.hasNoOverlapWithOtherShip(ship)) {
			throw new ModelException("This ship has been placed too close to another ship!");
		}
		this.ships.add(ship);
	}

	public void addShip(ShipType shipType, Direction direction, int startBox) {
		Ship ship = new Ship(shipType, direction, startBox);
		this.addShip(ship);
	}

	public Ship getlastAddedShip() {
		return ships.get(ships.size() - 1);
	}

	private boolean maxNumberOfShips() {
		return ships.size() == MAX_SHIPS;
	}

	private boolean maxNumberOfShipTypes(Ship ship) {
		int num = 0;
		for (Ship s : this.getShips()) {
			if (s.getShipType().equals(ship.getShipType())) {
				num++;
			}
		}
		//returns true if another ship can be added
		return ship.getShipType().getNumberTotalShipsAllowed() > num;
	}

	//checks that ship being placed has no overlap with another ship
	private boolean hasNoOverlapWithOtherShip(Ship ship) {
		for (Ship s : this.getShips()) {
			for (Integer i : ship.getShipNumbers()) {
				if (s.getNumbersSurroundingShip().contains(i) || s.getShipNumbers().contains(i)) {
					return false;
				}
			}
		}
		return true;
	}

}
