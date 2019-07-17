package ninja.cooperstuff.pokemon.util;

import java.util.HashMap;

public class DirectionFlag {
	private HashMap<Direction, Boolean> flags = new HashMap<>();

	public DirectionFlag setFlag(int x, int y, boolean flag) {
		Direction asdf = Direction.parse(x, y);
		System.out.println(asdf);
		this.flags.put(asdf, flag);
		return this;
	}

	public boolean getFlag(Direction dir) {
		return this.flags.getOrDefault(dir, false);
	}

	public boolean getFlag(int x, int y) {
		return this.getFlag(Direction.parse(x, y));
	}

	public int getId() {
		if (!this.flags.containsValue(true)) return -1;
		if (this.getFlag(Direction.DOWN) && this.getFlag(Direction.LEFT) && this.getFlag(Direction.RIGHT)) return -2;
		if (this.getFlag(Direction.UP) && this.getFlag(Direction.LEFT) && this.getFlag(Direction.RIGHT)) return -2;
		if (this.getFlag(Direction.UP) && this.getFlag(Direction.DOWN) && this.getFlag(Direction.LEFT)) return -2;
		if (this.getFlag(Direction.UP) && this.getFlag(Direction.DOWN) && this.getFlag(Direction.RIGHT)) return -2;
		if (this.getFlag(Direction.UP)) {
			if (this.getFlag(Direction.LEFT)) return 4;
			if (this.getFlag(Direction.RIGHT)) return 5;
			return 0;
		} else if (this.getFlag(Direction.DOWN)) {
			if (this.getFlag(Direction.LEFT)) return 6;
			if (this.getFlag(Direction.RIGHT)) return 7;
			return 1;
		} else if (this.getFlag(Direction.LEFT)) {
			if (this.getFlag(Direction.UP)) return 4;
			if (this.getFlag(Direction.DOWN)) return 6;
			return 2;
		} else if (this.getFlag(Direction.RIGHT)) {
			if (this.getFlag(Direction.UP)) return 5;
			if (this.getFlag(Direction.DOWN)) return 7;
			return 3;
		} else if (this.getFlag(Direction.UP_LEFT)) return 8;
		else if (this.getFlag(Direction.UP_RIGHT)) return 9;
		else if (this.getFlag(Direction.DOWN_LEFT)) return 10;
		else if (this.getFlag(Direction.DOWN_RIGHT)) return 11;
		return -1;
	}

	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		UP_LEFT,
		UP_RIGHT,
		DOWN_LEFT,
		DOWN_RIGHT;

		public static Direction parse(int x, int y) {
			if (y == -1) {
				if (x == -1) return UP_LEFT;
				else if (x == 1) return UP_RIGHT;
				return UP;
			} else if (y == 1) {
				if (x == -1) return DOWN_LEFT;
				else if (x == 1) return DOWN_RIGHT;
				return DOWN;
			}
			if (x == -1) return LEFT;
			else if (x == 1) return RIGHT;
			return null;
		}
	}
}
