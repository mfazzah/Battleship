package model;

import java.util.ArrayList;
import model.factory.ShipFactory;

public class Ship {

	private ArrayList<Integer> shipNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> shipNumbershit = new ArrayList<Integer>();
	private Direction direction;
	private ShipType shipType;
	private ShipFactory factory; 

	//Constructor
	public Ship(ShipType shipType, Direction direction, int startSquare) {
		this.setDirection(direction);
		this.setShipType(shipType);
		this.setAllShipNumber(startSquare);
	}
	//sets ship number from starting square, direction, and the ship type's length
	private void setAllShipNumber(int startSquare) {
		//calculate numbers on grid ship takes up for horizontal direction
		if (this.getDirection().equals(Direction.HORIZONTAL)) {
			int endSquare = startSquare + (10 * (shipType.getNumSqaures() - 1));
			if (endSquare < 100) {
				for (int i = startSquare; i <= endSquare; i += 10) {
					shipNumbers.add(i);
				}
			} else {
				throw new ModelException("Ship cannot be placed horizontally!");
			}
		} else { //vertical direction
			int endSquare = startSquare + shipType.getNumSqaures() - 1;
			if ((endSquare % 10) > (startSquare % 10)) {
				for (int i = startSquare; i <= endSquare; i++) {
					shipNumbers.add(i);
				}
			} else {
				throw new ModelException("Ship cannot be place horizontally!");
			}
		}
	}
	//get numbers for all adjacent squares to which ships belong
	public ArrayList<Integer> getNumbersSurroundingShip() {
		ArrayList<Integer> numbersSurrounding = new ArrayList<Integer>();

		int firstNumber = this.getShipNumbers().get(0);
		int lastNumber = this.getShipNumbers().get(this.getShipNumbers().size() - 1);
		//horizontal orientation
		if (this.getDirection().equals(Direction.HORIZONTAL)) {
			for (Integer integer : this.getShipNumbers()) {
				if (integer == firstNumber) {
					if ((integer % 10) != 0) {
						numbersSurrounding.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						numbersSurrounding.add(integer + 1);
					}
					if (integer - 10 > 0) {
						numbersSurrounding.add(integer - 10);
					}
					if ((integer - 10) > 0 && (integer % 10) != 0) {
						numbersSurrounding.add(integer - 11);
					}
					if ((integer - 10) > 0 && (integer % 10) != 9) {
						numbersSurrounding.add(integer - 9);
					}
				} else if (integer == lastNumber) {
					if ((integer + 10) < 100) {
						numbersSurrounding.add(integer + 10);
					}
					if ((integer % 10) != 0) {
						numbersSurrounding.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						numbersSurrounding.add(integer + 1);
					}
					if ((integer + 10) < 100 && (integer % 10) != 0) {
						numbersSurrounding.add(integer + 9);
					}
					if ((integer + 10) < 100 && (integer % 10) != 9) {
						numbersSurrounding.add(integer + 11);
					}
				} else {
					if ((integer % 10) != 0) {
						numbersSurrounding.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						numbersSurrounding.add(integer + 1);
					}
				}
			}
		} else { //vertical orientation
			for (Integer integer : this.getShipNumbers()) {
				if (integer == firstNumber) {
					if ((integer % 10) != 0) {
						numbersSurrounding.add(integer - 1);
					}
					if ((integer + 10) < 100) {
						numbersSurrounding.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						numbersSurrounding.add(integer - 10);
					}
					if ((integer % 10) != 0 && (integer - 10) > 0) {
						numbersSurrounding.add(integer - 11);
					}
					if ((integer % 10) != 0 && (integer + 10) < 100) {
						numbersSurrounding.add(integer + 9);
					}

				} else if (integer == lastNumber) {
					if ((integer + 10) < 100) {
						numbersSurrounding.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						numbersSurrounding.add(integer - 10);
					}
					if ((integer % 10) != 9) {
						numbersSurrounding.add(integer + 1);
					}
					if ((integer % 10) != 9 && (integer - 10) > 0) {
						numbersSurrounding.add(integer - 9);
					}
					if ((integer % 10) != 9 && (integer + 10) < 100) {
						numbersSurrounding.add(integer + 11);
					}
				} else {
					if ((integer + 10) < 100) {
						numbersSurrounding.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						numbersSurrounding.add(integer - 10);
					}
				}
			}
		}
		return numbersSurrounding;
	}
	//if ship gets hit, add number of square to list
	public void addNumberHit(int number) {
		if (!this.getShipNumbers().contains(number)) {
			throw new ModelException("Cannot hit! (Ship class)");
		}
		this.getShipNumbersHit().add(number);
	}
	//getters and setters
	private void setDirection(Direction direction) {
		this.direction = direction;
	}

	private void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public ArrayList<Integer> getShipNumbers() {
		return shipNumbers;
	}

	public ArrayList<Integer> getShipNumbersHit() {
		return shipNumbershit;
	}

	public Direction getDirection() {
		return direction;
	}

	public ShipType getShipType() {
		return shipType;
	}

}
