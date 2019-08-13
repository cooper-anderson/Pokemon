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

public class PinMissile extends Move {
	public PinMissile(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new PinMissileInstance(pokemon, this));
	}

	public class PinMissileInstance extends MoveInstance {
		private int count = new Random().nextInt(4) + 2;

		public PinMissileInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, true, true, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 10 * (this.count - 1) && this.frame % 10 == 0) {
				Projectile p = this.spawnProjectile(new PinMissileProjectile(this, this.move));
				p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
			}
		}
	}

	public static class PinMissileProjectile extends Projectile {
	    public PinMissileProjectile(MoveInstance owner, Move move) {
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