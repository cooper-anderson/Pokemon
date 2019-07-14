package ninja.cooperstuff.pokemon.util;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;

	public Direction getBack() {
		switch (this) {
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			default:
				return LEFT;
		}
	}

	public Direction getLeft() {
		switch (this) {
			case UP:
				return LEFT;
			case DOWN:
				return RIGHT;
			case LEFT:
				return DOWN;
			default:
				return UP;
		}
	}

	public Direction getRight() {
		switch (this) {
			case UP:
				return RIGHT;
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
			default:
				return DOWN;
		}
	}
}
