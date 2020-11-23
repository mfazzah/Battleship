package model;

import model.Strategy.HitShipStrategy;
import model.Strategy.PlaceShipStrategy;
import model.factory.HitShipFactory;
import model.factory.PlaceShipFactory;

public class ComputerPlayer extends HumanPlayer {

	public PlaceShipStrategy shipStrategy;
	public HitShipStrategy hitShipStrategy;

	public ComputerPlayer() {
		super("Computer");
		this.readHitStrategyFromProp();
		this.readPlaceShipFromProp();
	}
	//places ships depending on the strategy used
	public void setShipsFromStrategy() {
		while (this.getShips().size() != MAX_SHIPS) {
			try {
				Ship generatedShip = shipStrategy.placeRandomShip();
				this.addShip(generatedShip);
			} catch (Exception e) {
			}
		}
	}

	public int hitShip() {
		return hitShipStrategy.hitShip();
	}
//from properties, decides upon which placement and shooting strategy to use 
	public void readHitStrategyFromProp() {
		hitShipStrategy = new HitShipFactory().getHitShipStrategy();
	}

	public void readPlaceShipFromProp() {
		shipStrategy = new PlaceShipFactory().getPlaceShipStrategy();
	}

}
