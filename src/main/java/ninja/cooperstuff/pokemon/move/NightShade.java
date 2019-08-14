package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class NightShade extends Move {
	public NightShade(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new NightShadeInstance(pokemon, this));
	}

	public class NightShadeInstance extends MoveInstance {
		public NightShadeInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getAimVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage(this.pokemon.getLevel());
		}

		@Override
		public void behavior() {

		}
	}
}