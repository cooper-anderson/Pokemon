package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.Random;

public class Flamethrower extends Move {
	public Flamethrower(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new FlamethrowerInstance(pokemon, this));
	}

	public class FlamethrowerInstance extends MoveInstance {
		public FlamethrowerInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 40 && this.frame % 10 == 0) {
				Projectile p = this.spawnProjectile(new FlamethrowerProjectile(this, this.move));
				p.velocity = Vector.fromAngle(this.pokemon.getForwardVector().angle() + Math.PI / 24 * Math.sin(Math.PI / 2 * this.frame / 10), 5);
			}
		}
	}

	public class FlamethrowerProjectile extends Projectile {
		int sign = new Random().nextInt(2) * 2 - 1;

		public FlamethrowerProjectile(MoveInstance owner, Move move) {
			super(owner, move);
		}

		@Override
		public void behavior() {
			Vector normal = new Vector(-this.velocity.y, this.velocity.x);
			this.position.add(Vector.mul(normal, this.sign * Math.sin(8 * Math.PI * (double) this.frame / (double) this.owner.lifetime) / 5.0));
		}
	}
}