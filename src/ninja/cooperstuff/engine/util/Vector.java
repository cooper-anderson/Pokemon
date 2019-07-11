package ninja.cooperstuff.engine.util;

public class Vector {
	public double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector() {
		this(0.0, 0.0);
	}

	public double magnitude() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	public Vector normal() {
		double magnitude = this.magnitude();
		if (magnitude == 0) return new Vector();
		else return Vector.div(this, magnitude);
	}

	public Vector add(Vector other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	public Vector sub(Vector other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector mul(double other) {
		this.x *= other;
		this.y *= other;
		return this;
	}

	public Vector div(double other) {
		if (other == 0) {
			this.x = 0;
			this.y = 0;
			return this;
		}
		this.x /= other;
		this.y /= other;
		return this;
	}

	public double dot(Vector other) {
		return this.x * other.x + this.y * other.y;
	}

	public static Vector add(Vector left, Vector right) {
		return new Vector(left.x + right.x, left.y + right.y);
	}

	public static Vector sub(Vector left, Vector right) {
		return new Vector(left.x - right.x, left.y - right.y);
	}

	public static Vector mul(Vector left, Double scalar) {
		return new Vector(left.x * scalar, left.y * scalar);
	}

	public static Vector div(Vector left, Double scalar) {
		if (scalar == 0) return new Vector();
		return new Vector(left.x / scalar, left.y / scalar);
	}

	public static Double dot(Vector left, Vector right) {
		return left.x * right.x + left.y * right.y;
	}
}
