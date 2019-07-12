package ninja.cooperstuff.engine.components;

import ninja.cooperstuff.engine.util.Physics;
import ninja.cooperstuff.engine.util.Vector;

public class Rigidbody extends Component {
	public Vector velocity = new Vector();
	public double angularVelocity = 0.0;

	public void update() {
		this.velocity.add(Vector.div(Physics.gravity, 60.0));
		this.gameObject.transform.position.add(Vector.div(this.velocity, 60.0));
		this.gameObject.transform.rotation += angularVelocity / 60.0;
	}
}
