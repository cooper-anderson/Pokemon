package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;
import java.util.ArrayList;

public class VineWhip extends Move {
	public VineWhip(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new VineWhipInstance(pokemon, this));
	}

	public class VineWhipInstance extends MoveInstance {
		private ArrayList<Projectile> projectileList = new ArrayList<>();
		private double angle;
		private int count = 10;
		private int lifetime = 50;

		public VineWhipInstance(Pokemon pokemon, Move move) {
			super(pokemon, move);
			this.angle = this.pokemon.facing.getAngle() + Math.PI;
			for (int i = 0; i < this.count; i++) this.projectileList.add(this.spawnProjectile(new ProjectileDefault(this, this.move)));
		}

		@Override
		public void behavior() {
			this.transform.position = this.pokemon.transform.position.clone();
			for (int i = 0; i < this.count; i++) {
				double percent = (double) this.frame / (double) this.lifetime;
				double dist = 100 * ((double) i / (double) this.count) * Math.sin(percent * Math.PI);
				this.projectileList.get(i).position = Vector.add(this.transform.position, new Vector(dist * Math.cos(this.angle + percent * 2 * Math.PI), dist * Math.sin(this.angle + percent * 2 * Math.PI)));
			}
			if (this.frame > this.lifetime) this.destroy();
		}

		@Override
		public void render(Graphics2D screen) {
			IntVector pos = Vector.sub(this.projectileList.get(this.count - 1).position, this.transform.position).getIntVector();
			screen.setStroke(new BasicStroke(5));
			screen.setColor(new Color(0, 0, 0, Constants.shadowOpacity));
			screen.drawLine(0, 0, pos.x, pos.y);
			screen.setColor(this.move.type.color);
			screen.drawLine(0, -11, pos.x, pos.y - 11);
		}
	}
}
