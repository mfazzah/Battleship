package model.Strategy;

//depending on choice made, random or ordered hit strategy will be used for computer player
public enum HitStrategy {
	RANDOM("model.Strategy.RandomHitShipStrategy"), ORDERED("model.Strategy.OrderedHitStrategy");

	private String fullClassName;

	private HitStrategy(String fullClassName) {
		this.fullClassName = fullClassName;
	}

	public String getFullClassName() {
		return this.fullClassName;
	}

}
