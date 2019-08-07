package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.Random;

public class Psywave extends MoveAreaOfEffect {
	public Psywave(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new PsywaveInstance(pokemon, this));
	}

	public class PsywaveInstance extends MoveInstance {
		public PsywaveInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.spawnProjectile(new MoveAreaOfEffectProjectile(this, this.move));
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			return pokemon.damage((int) (this.pokemon.getLevel() * (new Random().nextDouble() + 0.5)));
		}
	}
}