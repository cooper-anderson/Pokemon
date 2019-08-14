package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;
import ninja.cooperstuff.pokemon.util.Status;

import java.util.Random;

public class TriAttack extends Move {
	public TriAttack(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new TriAttackInstance(pokemon, this));
	}

	public class TriAttackInstance extends MoveInstance {
		public TriAttackInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getAimVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			double r = new Random().nextDouble();
			double step = 0.20 / 3;
			if (r < step) pokemon.setStatus(Status.PARALYZED);
			else if (r < 2 * step) pokemon.setStatus(Status.BURNED);
			else if (r < 3 * step) pokemon.setStatus(Status.FROZEN);
			return 0;
		}
	}
}