package ninja.cooperstuff.engine.util;

public class IntVector {
	public static IntVector zero = new IntVector(0, 0);

	public int x;
	public int y;

	public IntVector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public IntVector(double x, double y) {
		this((int) Math.floor(x), (int) Math.floor(y));
	}

	public IntVector add(IntVector other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	public IntVector sub(IntVector other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public IntVector mul(double other) {
		this.x *= other;
		this.y *= other;
		return this;
	}

	public IntVector div(double other) {
		if (other == 0) {
			this.x = 0;
			this.y = 0;
			return this;
		}
		this.x /= other;
		this.y /= other;
		return this;
	}

	@Override
	public IntVector clone() {
		return new IntVector(this.x, this.y);
	}

	@Override
	public int hashCode() {
		return (String.format("%s,%s", this.x, this.y)).hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return (o != null && o.getClass() == this.getClass() && o.hashCode() == this.hashCode());
	}

	public String toString() {
		return String.format("IntVector(%s, %s)", this.x, this.y);
	}
}
