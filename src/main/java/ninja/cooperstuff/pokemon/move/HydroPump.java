package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.Random;

public class HydroPump extends Move {
	public HydroPump(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new HydroPumpInstance(pokemon, this));
	}

	public class HydroPumpInstance extends MoveInstance {
		public HydroPumpInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 40 && this.frame % 5 == 0) {
				Projectile p = this.spawnProjectile(new HydroPumpProjectile(this, this.move));
				p.velocity = Vector.fromAngle(this.pokemon.getAimVector().angle() + Math.PI / 24 * Math.sin(Math.PI / 2 * this.frame / 5), 5);
			}
		}
	}

	public class HydroPumpProjectile extends Projectile {
		int sign = new Random().nextInt(2) * 2 - 1;

	    public HydroPumpProjectile(MoveInstance owner, Move move) {
	    	super(owner, move);
	    }

		@Override
		public void behavior() {
			Vector normal = new Vector(-this.velocity.y, this.velocity.x);
			this.position.add(Vector.mul(normal, this.sign * Math.sin(8 * Math.PI * (double) this.frame / (double) this.owner.lifetime) / 5.0));
		}
	}
}