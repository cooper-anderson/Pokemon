package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class Wrap extends Move {
	public Wrap(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new WrapInstance(pokemon, this));
	}

	public class WrapInstance extends MoveInstance {
		public WrapInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {
			if (this.frame == 150 && this.pokemonHit.size() == 0) this.destroy();
			if (this.frame % 50 == 0) {
				for (Pokemon pokemon : this.pokemonHit) {
					if (pokemon.getHealth() != 0) {
						pokemon.damage(pokemon.monster.baseStats.health / 16);
					}
				}
			}
		}
	}
}