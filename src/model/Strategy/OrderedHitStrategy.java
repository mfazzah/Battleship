package model.Strategy;

//ordered hit strategy tells computer to follow choose hits in an ordered manner
//order is top-down, from left to right 
public class OrderedHitStrategy implements HitShipStrategy {

	private int lastInteger = -1;

	@Override
	public int hitShip() {
		if (lastInteger < 99) {
			lastInteger++;
		}
		return lastInteger;

	}

}
