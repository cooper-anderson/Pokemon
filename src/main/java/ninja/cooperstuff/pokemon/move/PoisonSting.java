package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class PoisonSting extends Move {
	public PoisonSting(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new PoisonStingInstance(pokemon, this));
	}

	public class PoisonStingInstance extends MoveInstance {
		public PoisonStingInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new PoisonStingProjectile(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}
	}

	public class PoisonStingProjectile extends Projectile {
		public PoisonStingProjectile(MoveInstance owner, Move move) {
			super(owner, move);
		}

		@Override
		public void behavior() {

		}

		@Override
		public void render(Graphics2D screen) {
			IntVector pos = Vector.mul(this.velocity, 3).getIntVector();
			screen.setStroke(new BasicStroke(3));
			screen.setColor(Constants.shadowColor);
			screen.drawLine(0, 0, pos.x, pos.y);
			screen.setColor(this.move.type.color);
			screen.drawLine(0, -11, pos.x, pos.y - 11);
		}
	}
}