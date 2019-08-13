package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class SeismicToss extends Move {
	public SeismicToss(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new SeismicTossInstance(pokemon, this));
	}

	public class SeismicTossInstance extends MoveInstance {
		public SeismicTossInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage(this.pokemon.getLevel());
		}
	}
}