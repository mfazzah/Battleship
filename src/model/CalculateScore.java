package model;

import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class CalculateScore extends Observable implements Observer {
	private int score = 0;
	@SuppressWarnings("unused")
	private Observable observable;
	//sets default/initial score to nineteen
	public CalculateScore(Observable observable) {
		this.setScore(19);
		this.observable = observable;
		observable.addObserver(this);
	}

	//whenever a ship object is hit, for either player, subtract one point from the score
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof HumanPlayer || o instanceof ComputerPlayer) {
			this.setScore(score - 1);
		}

	}
	//score getter and setter
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
