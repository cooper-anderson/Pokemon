package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.Random;

public class MoveOneHitKO extends Move {
	public MoveOneHitKO(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new MoveOneHitKOInstance(pokemon, this));
	}

	public class MoveOneHitKOInstance extends MoveInstance {
		public MoveOneHitKOInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			double angle = new Random().nextDouble() * 2 * Math.PI;
			Projectile p = this.spawnProjectile(new MoveOneHitKOProjectile(this, this.move));
			p.velocity = new Vector(Math.cos(angle), Math.sin(angle)).mul(5);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage(100000);
		}
	}

	public class MoveOneHitKOProjectile extends Projectile {
		int sign = new Random().nextInt(2) * 2 - 1;

		public MoveOneHitKOProjectile(MoveInstance owner, Move move) {
			super(owner, move);
		}

		@Override
		public void behavior() {
			Vector normal = new Vector(-this.velocity.y, this.velocity.x);
			this.position.add(Vector.mul(normal, this.sign * Math.sin(8 * Math.PI * (double) this.frame / (double) this.owner.lifetime) / 5.0));
		}
	}
}
