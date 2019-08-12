package ninja.cooperstuff.pokemon.entity.particle;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.Random;

public class Coin extends Particle {
	private double deltaAlpha = 1;
	private double startY;
	private double rotation;
	private double angularVelocity;

	public Coin(World world, double startY) {
		super(world);
		Random r = new Random();
		double angle = -Math.PI * r.nextDouble();
		this.rotation = 2 * Math.PI * r.nextDouble();
		this.angularVelocity = 0.01 + r.nextDouble() / 10.0;
		double minVelocity = 1;
		double maxVelocity = 2;
		this.velocity = new Vector(Math.cos(angle), Math.sin(angle) * 2).mul(minVelocity + (r.nextDouble() * (maxVelocity - minVelocity)));
		this.deltaAlpha += r.nextDouble() * 2;
		this.size += r.nextInt(5);
		this.startY = startY + r.nextInt(10) - 5;
		this.lifetime = 100 + r.nextInt(50);
	}

	@Override
	public void behavior() {
		this.rotation += this.angularVelocity;
		this.color = new Color(255, 217, 76, (int) Math.max(128, this.color.getAlpha() - this.deltaAlpha));
		this.velocity.x /= 1.1;
		this.velocity.y = this.velocity.y + 0.1;
		if (this.transform.position.y > this.startY) {
			this.transform.position.y = startY;
			this.angularVelocity = 0;
			this.velocity.y = 0;
		}
		if (this.frame >= this.lifetime) this.destroy();
	}

	@Override
	public void render(Graphics2D screen) {
		IntVector offset = new Vector(Math.cos(this.rotation), Math.sin(this.rotation)).mul(2).getIntVector();
		screen.setStroke(new BasicStroke(2));
		screen.setColor(this.color);
		screen.drawLine(-offset.x, -offset.y, offset.x, offset.y);
	}
}
