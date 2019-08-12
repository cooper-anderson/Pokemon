package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;
import java.util.Random;

public class HyperBeam extends Move {
	public HyperBeam(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new HyperBeamInstance(pokemon, this));
	}

	public class HyperBeamInstance extends MoveInstance {
		Projectile p;

		public HyperBeamInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.p = this.spawnProjectile(new HyperBeamProjectile(this, this.move));
			this.p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public void render(Graphics2D screen) {
			if (this.pokemonHit.size() == 0) {
				IntVector pos = Vector.sub(this.p.position, this.transform.position).getIntVector();
				screen.setStroke(new BasicStroke(5));
				screen.setColor(Constants.shadowColor);
				screen.drawLine(0, 0, pos.x, pos.y);
				screen.setColor(this.move.type.color);
				screen.drawLine(0, -11, pos.x, pos.y - 11);
			}
		}
	}

	public class HyperBeamProjectile extends Projectile {
		int sign = new Random().nextInt(2) * 2 - 1;

		public HyperBeamProjectile(MoveInstance owner, Move move) {
			super(owner, move);
		}

		@Override
		public void behavior() {
			this.position = Vector.add(this.owner.transform.position, Vector.mul(this.velocity, (double) this.frame));
			Vector normal = new Vector(-this.velocity.y, this.velocity.x);
			this.position.add(Vector.mul(normal, this.sign * Math.sin(8 * Math.PI * (double) this.frame / (double) this.owner.lifetime)));
		}
	}
}