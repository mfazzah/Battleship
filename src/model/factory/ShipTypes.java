package model.factory;
//enumerates the number of squares and amount of each type of ship allowed in the game 
public enum ShipTypes {
    CARRIER			(5, 1),
    BATTLESHIP 		(4, 2),
    DESTROYER		(3, 3),
    SUBMARINE		(3, 3),
    PATROLBOAT		(2, 4);	
	
	private final int numberSquares;
	private final int numberTotalShipsAllowed;

	private ShipTypes(int numberSquares, int numberTotalShipsAllowed) {
		this.numberSquares = numberSquares;
		this.numberTotalShipsAllowed = numberTotalShipsAllowed;
	}
}


