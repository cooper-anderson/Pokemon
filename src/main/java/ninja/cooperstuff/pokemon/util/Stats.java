package ninja.cooperstuff.pokemon.util;

public class Stats {
	public int health;
	public int attackPhysical;
	public int attackSpecial;
	public int defensePhysical;
	public int defenseSpecial;
	public int speed;

	public Stats() {
		this(0, 0, 0, 0, 0, 0);
	}

	public Stats(int health, int attackPhysical, int attackSpecial, int defensePhysical, int defenseSpecial, int speed) {
		this.health = health;
		this.attackPhysical = attackPhysical;
		this.attackSpecial = attackSpecial;
		this.defensePhysical = defensePhysical;
		this.defenseSpecial = defenseSpecial;
		this.speed = speed;
	}

	public Stats clone() {
		return new Stats(this.health, this.attackPhysical, this.attackSpecial, this.defensePhysical, this.defenseSpecial, this.speed);
	}

	public static Stats randomEffort() {
		return null;
	}
}
