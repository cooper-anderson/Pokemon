package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

public class SonicBoom extends Move {
	public SonicBoom(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new SonicBoomInstance(pokemon, this));
	}

	public class SonicBoomInstance extends MoveInstance {
		public SonicBoomInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			this.spawnProjectile(new MoveAreaOfEffect.MoveAreaOfEffectProjectile(this, this.move));
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage(20);
		}
	}
}