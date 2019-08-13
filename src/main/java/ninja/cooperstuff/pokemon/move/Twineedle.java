package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class Twineedle extends Move {
	public Twineedle(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new TwineedleInstance(pokemon, this));
	}

	public class TwineedleInstance extends MoveInstance {
		public TwineedleInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 10 && this.frame % 10 == 0) {
				Projectile p = this.spawnProjectile(new PinMissile.PinMissileProjectile(this, this.move));
				p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
			}
		}
	}
}