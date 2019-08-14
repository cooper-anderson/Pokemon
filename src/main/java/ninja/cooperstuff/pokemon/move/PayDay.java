package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.particle.Coin;
import ninja.cooperstuff.pokemon.entity.particle.Particle;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class PayDay extends Move {
	private static final int coinCount = 10;

	public PayDay(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new PayDayInstance(pokemon, this));
	}

	public class PayDayInstance extends MoveInstance {
		public PayDayInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getAimVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			for (int i = 0; i < PayDay.coinCount; i++) {
				Particle d = this.pokemon.game.instantiate(new Coin(this.world, pokemon.transform.position.y));
				d.transform.position = pokemon.transform.position.clone();
			}
			return super.onCollision(pokemon, projectile);
		}

		@Override
		public void behavior() {

		}
	}
}