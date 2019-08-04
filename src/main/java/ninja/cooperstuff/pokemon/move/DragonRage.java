package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class DragonRage extends Move {
	public DragonRage(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new DragonRageInstance(pokemon, this));
	}

	public class DragonRageInstance extends MoveInstance {
		public DragonRageInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage(40);
		}
	}
}