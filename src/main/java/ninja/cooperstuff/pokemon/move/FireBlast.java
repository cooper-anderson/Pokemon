package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

public class FireBlast extends Move {
	private static final int projectileCount = 5;

	public FireBlast(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new FireBlastInstance(pokemon, this));
	}

	public class FireBlastInstance extends MoveInstance {
		public FireBlastInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.lifetime = 150;
			for (int i = 0; i < FireBlast.projectileCount; i++) {
				this.spawnProjectile(new FireBlastProjectile(this, this.move, i));
			}
		}

		@Override
		public void behavior() {

		}
	}

	public class FireBlastProjectile extends Projectile {
		int index;
		double angle;

		public FireBlastProjectile(MoveInstance owner, Move move, int index) {
			super(owner, move);
			this.index = index;
			this.angle = this.owner.pokemon.facing.getAngle() + Math.PI/2.0 + 2 * Math.PI * this.index / FireBlast.projectileCount;
		}

		@Override
		public void behavior() {
			double angle = this.angle + 3 * (double) this.frame / (double) this.owner.lifetime;
			double dist = 4.0 * (-1 / 100.0 * Math.pow(this.frame - 50.0, 2) + 25.0);
			this.position = new Vector(Math.cos(angle), Math.sin(angle)).mul(dist).add(this.owner.transform.position);
		}
	}
}