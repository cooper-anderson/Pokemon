package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Stats;

public class Acid extends Move {
	public Acid(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new AcidInstance(pokemon, this));
	}

	public class AcidInstance extends MoveInstance {
		public AcidInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(5);
		}

		@Override
		public void behavior() { }

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			pokemon.modifyDefenseSpecial(Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
			return super.onCollision(pokemon, projectile);
		}
	}
}