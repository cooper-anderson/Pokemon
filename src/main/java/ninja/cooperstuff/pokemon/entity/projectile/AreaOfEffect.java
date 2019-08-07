package ninja.cooperstuff.pokemon.entity.projectile;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.move.Move;

import java.awt.*;

public abstract class AreaOfEffect extends Projectile {
	public double radius = 10;

	public AreaOfEffect(MoveInstance owner, Move move) {
		super(owner, move);
	}

	@Override
	public boolean checkCollision(Pokemon pokemon) {
		return Vector.distanceSquared(this.position, pokemon.transform.position) < Math.pow(this.radius, 2);
	}

	@Override
	public void render(Graphics2D screen) {
		screen.setColor(this.color);
		screen.drawOval((int) -radius, (int) -radius, (int) radius * 2, (int) (radius * 2));
	}
}
