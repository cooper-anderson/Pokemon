package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.particle.Bezier;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;
import java.util.Random;

public class Absorb extends Move {
	static int particleCount = 3;

	public Absorb(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new AbsorbInstance(pokemon, this));
	}

	public class AbsorbInstance extends MoveInstance {
		public AbsorbInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(5);
		}

		@Override
		public void behavior() { }

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			int damage = super.onCollision(pokemon, projectile);
			this.pokemon.heal(damage / 2);
			for (int i = 0; i < particleCount; i++) {
				Bezier b = this.game.instantiate(new Bezier(this.world, 30));
				Random r = new Random();
				b.color = this.move.type.color;
				b.color = new Color(b.color.getRed(), b.color.getGreen(), b.color.getBlue(), 128);
				b.v0 = Vector.add(pokemon.transform.position, new Vector(r.nextDouble() * 2 - 1, r.nextDouble() * 2 - 1).mul(15));
				b.v2 = this.pokemon.transform.position;
				Vector diff = Vector.sub(b.v0, b.v2);
				b.v1 = Vector.add(Vector.div(Vector.add(b.v0, b.v2), 2.0),
					Vector.mul(new Vector(diff.y, -diff.x), Math.min(Constants.maxBezierMagnitude, diff.magnitude()) * (r.nextDouble() * 2 - 1) / 1000.0)
				);
			}
			return damage;
		}
	}
}
