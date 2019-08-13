package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

public class RazorLeaf extends Move {
	static int projectileCount = 3;
	static double arcLength = Math.PI/3;

	public RazorLeaf(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new RazorLeafInstance(pokemon, this));
	}

	public class RazorLeafInstance extends MoveInstance {
		public RazorLeafInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			double angle = this.pokemon.getForwardVector().angle();
			for (int i = 0; i < RazorLeaf.projectileCount; i++) {
				Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
				p.velocity = Vector.fromAngle(angle - RazorLeaf.arcLength / 2 + RazorLeaf.arcLength * (double) i / (RazorLeaf.projectileCount - 1)).mul(Constants.projectileVelocity);
			}
		}

		@Override
		public void behavior() {

		}
	}
}