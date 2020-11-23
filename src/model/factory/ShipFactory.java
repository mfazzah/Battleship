package model.factory;
//factory that creates ships for board
public class ShipFactory {
	static Ships ship; 
	public static Ships buildShip(ShipTypes type) {
		ship = null;
		switch(type) {
		//cases are found in the enum ShipTypes.java
		case CARRIER:
			ship = new CarrierShipType();
			break;
		case BATTLESHIP:
			ship = new BattleshipShipType();
			break;
		case DESTROYER:
			ship = new DestroyerShipType();
			break;
		case SUBMARINE:
			ship = new SubmarineShipType();
			break;
		case PATROLBOAT:
			ship = new PatrolboatShipType();
			break;
		}
		return ship;
	}

}
