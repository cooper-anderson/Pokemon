package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;
import java.util.ArrayList;

public class TailWhip extends Move {
	public TailWhip(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new TailWhipInstance(pokemon, this));
	}

	public class TailWhipInstance extends MoveInstance {
		private ArrayList<Projectile> projectileList = new ArrayList<>();
		private double angle;
		private int count = 10;

		public TailWhipInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			this.angle = this.pokemon.facing.getAngle() + 2 * Math.PI / 3;
			this.lifetime = 25;
			for (int i = 0; i < this.count; i++) this.projectileList.add(this.spawnProjectile(new TailWhipProjectile(this, this.move, this.angle, i, count)));
		}

		@Override
		public void behavior() {

		}

		@Override
		public void render(Graphics2D screen) {
			IntVector pos = Vector.sub(this.projectileList.get(this.count - 1).position, this.transform.position).getIntVector();
			screen.setStroke(new BasicStroke(5));
			screen.setColor(Constants.shadowColor);
			screen.drawLine(0, 0, pos.x, pos.y);
			screen.setColor(this.move.type.color);
			screen.drawLine(0, -11, pos.x, pos.y - 11);
		}
	}

	public class TailWhipProjectile extends Projectile {
		private double angle;
		private int index;
		private int count;

		public TailWhipProjectile(MoveInstance owner, Move move, double angle, int index, int count) {
			super(owner, move);
			this.angle = angle;
			this.index = index;
			this.count = count;
		}

		@Override
		public void behavior() {
			double angle = this.angle + 2 * Math.PI / 3 * (double) this.frame / (double) this.owner.lifetime;
			double dist = 75 * ((double) this.index / (double) this.count);
			this.position = Vector.add(this.owner.transform.position, new Vector(Math.cos(angle), Math.sin(angle)).mul(dist));
		}

		@Override
		public void render(Graphics2D screen) {

		}
	}
}