package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.type.Type;

public class SelfDestruct extends Move {
	public SelfDestruct(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		pokemon.damage(1000000);
		return pokemon.game.instantiate(new SelfDestructInstance(pokemon, this));
	}

	public class SelfDestructInstance extends MoveInstance {
		public SelfDestructInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			this.spawnProjectile(new MoveAreaOfEffect.MoveAreaOfEffectProjectile(this, this.move));
		}

		@Override
		public void behavior() {

		}
	}
}