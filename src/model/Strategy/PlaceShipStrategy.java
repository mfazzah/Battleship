package model.Strategy;

import model.Ship;
//tells computer how to place ships depending on strategy chosen
public interface PlaceShipStrategy {
	public Ship placeRandomShip();
}
