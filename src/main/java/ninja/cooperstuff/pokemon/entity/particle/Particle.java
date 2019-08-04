package ninja.cooperstuff.pokemon.entity.particle;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.Entity;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public abstract class Particle extends Entity {
	public Vector velocity = new Vector();
	public Color color = new Color(128, 128, 128, 128);
	public int size = 10;
	public int lifetime = 100;

	public Particle(World world) {
		super(world);
	}

	@Override
	public void update() {
		this.behavior();
		this.transform.position.add(this.velocity);
		this.lifetime--;
		if (this.lifetime == 0) this.destroy();
	}

	public abstract void behavior();

	@Override
	public void render(Graphics2D screen) {
		screen.setColor(this.color);
		screen.fillOval((int) (-this.size / 2.0), (int) (-this.size / 2.0), size, size);
	}
}
