package model;
/* holds shit types, number of squares they take up, and number of squares allowed Replaced by factory */ 
import java.util.ArrayList;

public enum ShipType {
    CARRIER			(5, 1),
    BATTLESHIP 		(4, 2),
    DESTROYER		(3, 3),
    SUBMARINE		(3, 3),
    PATROLBOAT		(2, 4);

	private final int numberSquares;
	private final int numberTotalShipsAllowed;

	private ShipType(int numberSquares, int numberTotalShipsAllowed) {
		this.numberSquares = numberSquares;
		this.numberTotalShipsAllowed = numberTotalShipsAllowed;
	}

	public int getNumSqaures() {
		return numberSquares;
	}

	public int getNumberTotalShipsAllowed() {
		return numberTotalShipsAllowed;
	}

	public static ArrayList<ShipType> getAllShipTypes() {
		ArrayList<ShipType> allShipTypes = new ArrayList<ShipType>();
		for (ShipType shipType : ShipType.values()) {
			allShipTypes.add(shipType);
		}
		return allShipTypes;
	}

}