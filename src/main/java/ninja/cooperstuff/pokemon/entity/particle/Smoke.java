package ninja.cooperstuff.pokemon.entity.particle;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.Random;

public class Smoke extends Particle {
	private double deltaAlpha = 2;

	public Smoke(World world) {
		super(world);
		Random r = new Random();
		double angle = 2 * Math.PI * r.nextDouble();
		double minVelocity = 1;
		double maxVelocity = 2;
		this.velocity = new Vector(Math.cos(angle), Math.sin(angle)).mul(minVelocity + (r.nextDouble() * (maxVelocity - minVelocity)));
		this.deltaAlpha += r.nextDouble() * 2;
		this.size += r.nextInt(5);
	}

	@Override
	public void behavior() {
		this.color = new Color(128, 128, 128, (int) Math.max(0, this.color.getAlpha() - this.deltaAlpha));
		this.velocity.x /= 1.1;
		this.velocity.y = Math.max(-2, this.velocity.y - 0.1);
		if (this.color.getAlpha() == 0) this.destroy();
	}
}
