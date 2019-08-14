package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

public class FireSpin extends Move {
	private static final int projectileCount = 5;

	public FireSpin(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new FireSpinInstance(pokemon, this));
	}

	public class FireSpinInstance extends MoveInstance {
		Vector velocity;

		public FireSpinInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, false);
			this.velocity = this.pokemon.getAimVector();
			for (int i = 0; i < FireSpin.projectileCount; i++) {
				this.spawnProjectile(new FireSpinProjectile(this, this.move, i));
			}
		}

		@Override
		public void behavior() {
			this.transform.position.add(Vector.mul(this.velocity, 5 * Math.min(((double) this.frame / 20), 1)));
		}
	}

	public class FireSpinProjectile extends Projectile {
		double angle;

		public FireSpinProjectile(MoveInstance owner, Move move, int index) {
			super(owner, move);
			this.angle = 2 * Math.PI * (double) index / (double) FireSpin.projectileCount;
		}

		@Override
		public void behavior() {
			double angle = this.angle + 5 * (double) this.frame / (double) this.owner.lifetime;
			this.position = Vector.add(this.owner.transform.position, new Vector(Math.cos(angle), Math.sin(angle)).mul(50));
		}
	}
}