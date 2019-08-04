package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.util.Status;

import java.awt.*;
import java.util.HashMap;

public class Bonemerang extends Move {
	public Bonemerang(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new BonemerangInstance(pokemon, this, this.modifiers, this.effects));
	}

	public class BonemerangInstance extends MoveInstance {
		public BonemerangInstance(Pokemon pokemon, Move move, HashMap<Stats.Stat, StatModification> modifiers, HashMap<Status, Double> effects) {
			super(pokemon, move, modifiers, effects, true, false, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 20 && this.frame % 20 == 0) {
				this.spawnProjectile(new BonemerangProjectile(this, this.move, this.frame / 10 - 1));
			}
		}
	}

	public class BonemerangProjectile extends Projectile {
		Vector v0;
		Vector v1;
		Vector v2;

		public BonemerangProjectile(MoveInstance owner, Move move, int side) {
			super(owner, move);
			Vector facing = this.owner.pokemon.getForwardVector();
			this.v0 = this.owner.transform.position.clone();
			this.v1 = Vector.add(Vector.add(v0, Vector.mul(this.owner.pokemon.getForwardVector(), 500.0)), new Vector(-facing.y, facing.x).mul(25 * side));
			this.v2 = this.owner.pokemon.transform.position;
		}

		@Override
		public void behavior() {
			double percent = (double) this.frame / (double) (this.owner.lifetime - 20);
			this.position = Vector.bezier(this.v0, this.v1, this.v2, percent);
			if (percent >= 1.0) this.destroy();
		}

		@Override
		public void render(Graphics2D screen) {
			double angle = 8 * Math.PI * (double) this.frame / (double) (this.owner.lifetime - 20);
			IntVector offset = new Vector(Math.cos(angle), Math.sin(angle)).mul(6).getIntVector();
			screen.setStroke(new BasicStroke(3));
			screen.setColor(Constants.shadowColor);
			screen.drawLine(-offset.x, -offset.y, offset.x, offset.y);
			screen.setColor(Color.white);
			screen.drawLine(-offset.x, -offset.y - 11, offset.x, offset.y - 11);
		}
	}
}