package ninja.cooperstuff.pokemon.util;

public class Stats {
	public enum Modifier {
		NORMAL(1), HARSH(2), SEVERE(3);

		private final int value;
		Modifier(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
	public enum Sign {
		RISE(1), FALL(-1);

		private final int value;
		Sign(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

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

	public static double getMultiplier(int level) {
		switch (level) {
			case -6: return 2.0 / 8.0;
			case -5: return 2.0 / 7.0;
			case -4: return 2.0 / 6.0;
			case -3: return 2.0 / 5.0;
			case -2: return 2.0 / 4.0;
			case -1: return 2.0 / 3.0;
			case 1: return 3.0 / 2.0;
			case 2: return 4.0 / 2.0;
			case 3: return 5.0 / 2.0;
			case 4: return 6.0 / 2.0;
			case 5: return 7.0 / 2.0;
			case 6: return 8.0 / 2.0;
			default: return 1.0;
		}
	}
}
