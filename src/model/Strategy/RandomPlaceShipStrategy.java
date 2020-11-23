package model.Strategy;

import java.util.Random;

import model.Direction;
import model.Ship;
import model.ShipType;

//computer places ships randomly  if user chooses option
public class RandomPlaceShipStrategy implements PlaceShipStrategy {

	@Override
	public Ship placeRandomShip() {
		Random random = new Random();
		ShipType shipType = ShipType.values()[random.nextInt(ShipType.values().length)];
		Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
		int startSquare = (int) (Math.random() * 99);
		return new Ship(shipType, direction, startSquare);
	}

}
