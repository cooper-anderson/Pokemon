package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;

public class Ember extends Move {
	public Ember(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new EmberInstance(pokemon, this));
	}

	public class EmberInstance extends MoveInstance {
		public EmberInstance(Pokemon owner, Move move) {
			super(owner, move);
		}

		@Override
		public void behavior() {
			this.transform.position = this.pokemon.transform.position.clone();
			if (this.frame <= 20 && this.frame % 10 == 0) {
				Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
				p.velocity = this.pokemon.getForwardVector().clone().mul(5);
			}
		}

		@Override
		public void onCollision(Pokemon pokemon) {

		}
	}
}
