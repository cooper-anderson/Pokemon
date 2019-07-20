package ninja.cooperstuff.engine.util;

public class IntVector {
	public int x;
	public int y;

	public IntVector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public IntVector(double x, double y) {
		this((int) Math.floor(x), (int) Math.floor(y));
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
