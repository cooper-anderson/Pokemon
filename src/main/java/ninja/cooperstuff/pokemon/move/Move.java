package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.type.Type;

import java.util.HashSet;

public class Move {
	public static HashSet<Move> moves = new HashSet<>();
	public enum AttackType {PHYSICAL, SPECIAL, STATUS}

	public String name;
	public Type type;
	public int power;
	public int accuracy;
	public int points;
	public AttackType attackType;

	public Move(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		this.name = name;
		this.type = type;
		this.attackType = attackType;
		this.power = power;
		this.accuracy = accuracy;
		this.points = points;
		Move.moves.add(this);
	}

	public String toString() {
		return String.format("Move(\"%s\", %s, %s, %d, %d, %d)", this.name, this.type.name, this.attackType, this.power, this.accuracy, this.points);
	}

	/*protected Move setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return this.name;
	}

	protected Move setPower(int power) {
		this.power = power;
		return this;
	}

	public int getPower() {
		return this.power;
	}

	protected Move setAccuracy(int accuracy) {
		this.accuracy = accuracy;
		return this;
	}

	protected Move setPoints(int points) {
		this.points = points;
		return this;
	}*/
}
