package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class DoubleKick extends Move {
	public DoubleKick(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new DoubleKickInstance(pokemon, this));
	}

	public class DoubleKickInstance extends MoveInstance {
		private Vector facing;

		public DoubleKickInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.facing = this.pokemon.getAimVector();
			this.lifetime = 50;
		}

		@Override
		public void behavior() {
			if (this.frame <= 20 && this.frame % 20 == 0) {
				this.spawnProjectile(new DoubleKickProjectile(this, this.move, this.facing, this.frame / 10 - 1));
			}
		}
	}

	public class DoubleKickProjectile extends Projectile {
		Vector v0;
		Vector v1;
		Vector v2;
		Vector facing;
		int side;

		public DoubleKickProjectile(MoveInstance owner, Move move, Vector facing, int side) {
			super(owner, move);
			this.v0 = this.owner.transform.position;
			this.v2 = this.owner.pokemon.transform.position;
			this.facing = facing;
			this.side = side;
		}

		@Override
		public void behavior() {
			double percent = (double) this.frame / (double) (this.owner.lifetime - 20);
			this.v1 = Vector.add(v0, Vector.add(facing, new Vector(-facing.y, facing.x).mul(side / 2.0)).mul(75));
			this.position = Vector.bezier(this.v0, this.v1, this.v2, percent);
			if (percent >= 1.0) this.destroy();
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