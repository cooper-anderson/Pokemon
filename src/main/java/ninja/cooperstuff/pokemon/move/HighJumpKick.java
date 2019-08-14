package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class HighJumpKick extends Move {
	public HighJumpKick(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new HighJumpKickInstance(pokemon, this));
	}

	public class HighJumpKickInstance extends MoveInstance {
		public HighJumpKickInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.lifetime = 50;
			this.spawnProjectile(new HighJumpKickProjectile(this, this.move));
		}

		@Override
		public void behavior() {
			if (this.frame == this.lifetime) {
				if (this.projectiles.size() != 0) this.pokemon.damage(this.pokemon.monster.baseStats.health / 2);
			}
		}
	}

	public class HighJumpKickProjectile extends Projectile {
		Vector direction;

	    public HighJumpKickProjectile(MoveInstance owner, Move move) {
	    	super(owner, move);
	    	this.direction = this.owner.pokemon.getAimVector();
	    }

	    @Override
		public void behavior() {
	    	double angle = Math.PI * (double) this.frame / (double) this.owner.lifetime;
	    	this.position = Vector.add(this.owner.transform.position, Vector.mul(this.direction, 50 * Math.sin(angle)));
	    }

		@Override
		public void render(Graphics2D screen) {
			IntVector pos = Vector.sub(this.owner.transform.position, this.position).getIntVector();
			screen.setStroke(new BasicStroke(5));
			screen.setColor(Constants.shadowColor);
			screen.drawLine(0, 0, pos.x, pos.y);
			screen.setColor(this.move.type.color);
			screen.drawLine(0, -11, pos.x, pos.y - 11);
		}
	}
}