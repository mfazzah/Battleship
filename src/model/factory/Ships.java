package model.factory;
//abstract class to create ships for factory module
import java.util.ArrayList;

public abstract class Ships {

	protected ShipTypes type;
	private int numberSquares;
	private int numberTotalShipsAllowed;

	public Ships(ShipTypes type) {
		this.setType(type);
	}

	public int getNumSqaures() {
		return numberSquares;
	}

	public int getNumberTotalShipsAllowed() {
		return numberTotalShipsAllowed;
	}

	public static ArrayList<ShipTypes> getAllShipTypes() {
		ArrayList<ShipTypes> allShipTypes = new ArrayList<ShipTypes>();
		for (ShipTypes shipType : ShipTypes.values()) {
			allShipTypes.add(shipType);
		}
		return allShipTypes;
	}

	public ShipTypes getType() {
		return type;
	}

	public void setType(ShipTypes type) {
		this.type = type;
	}
}