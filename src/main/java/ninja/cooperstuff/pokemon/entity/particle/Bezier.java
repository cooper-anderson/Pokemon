package ninja.cooperstuff.pokemon.entity.particle;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public class Bezier extends Particle {
	public Vector v0 = new Vector();
	public Vector v1 = new Vector();
	public Vector v2 = new Vector();

	public Bezier(World world, int lifetime) {
		super(world);
		this.lifetime = lifetime;
		this.color = Color.blue;
	}

	@Override
	public void behavior() {
		double percent = (double) this.frame / (double) this.lifetime;
		this.transform.position = Vector.bezier(this.v0, this.v1, this.v2, percent);
	}
}
