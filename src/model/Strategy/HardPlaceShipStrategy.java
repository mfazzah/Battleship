package model.Strategy;

import model.Direction;
import model.Ship;
import model.ShipType;

import java.util.ArrayList;
import java.util.Random;
/* If user sets the game in 'hard' mode, the computer will have a more realized strategy, as follows below
 * Instead of placing things randomly, the computer will have a set probability to choose certain ships and their directions.
 * In addition, no ships will be set on the edges (columns 1 and 10), or in the center (column 5)*/

public class HardPlaceShipStrategy implements PlaceShipStrategy {

	@Override
	public Ship placeRandomShip() {
		Random random = new Random();
		ShipType shipType = this.getType(random);
		Direction direction = this.getDirection(random);
		int startSquare = this.getSquare();
		return new Ship(shipType, direction, startSquare);
	}

	private ShipType getType(Random random) {
		ShipType shipType;
		if (random.nextInt(7) == 0) {
			// Computer has 20% chance of choosing to place a larger shipship (carrier = 0,
			// battleship = 1)
			shipType = ShipType.values()[random.nextInt(1)];
		} else {
			// Computer has 80% chance of choosing a smaller ship
			// randint as is chooses length of smaller ship from min length=3 to max
			// length=5
			shipType = ShipType.values()[random.nextInt(ShipType.values().length - 2 + 1) + 2];
		}
		return shipType;
	}

	// ship has 60% chance of vertical placement (0-5), or a 40% chance of
	// horizontal placement (6-9
	private Direction getDirection(Random random) {
		Direction direction;
		if (random.nextInt(5) == 0) { // vertical placement
			direction = Direction.values()[0];
		} else { // horizontal placement
			direction = Direction.values()[1];
		}
		return direction;
	}

	private int getSquare() {
		int startSquare = (int) (Math.random() * 99);
		// try to avoid side and middle columns (columns 1, 5, and 10)
		ArrayList<Integer> squaresToAvoid = this.getSquaresToAvoid();
		boolean optimalsquare = false;
		while (optimalsquare != true) {
			startSquare = (int) (Math.random() * 99);
			if (!squaresToAvoid.contains(startSquare)) {
				optimalsquare = true;
			}
		}
		return startSquare;
	}

	// tells computer to avoid columns 1, 5, and 10 when placing ships in hard mode
	private ArrayList<Integer> getSquaresToAvoid() {
		ArrayList<Integer> squaresToAvoid = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			squaresToAvoid.add(i); // col 1
			squaresToAvoid.add(i + 40); //col 5
			squaresToAvoid.add(i + 90); //col 10
		}
		return squaresToAvoid;
	}

}