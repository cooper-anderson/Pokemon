package ninja.cooperstuff.engine.components;

import ninja.cooperstuff.engine.util.Vector;

public class Transform {
	GameObject gameObject;
	public Vector position = new Vector();
	public double rotation = 0.0;
	public Vector scale = new Vector(1.0, 1.0);

	public Transform(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
