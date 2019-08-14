package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class Swift extends Move {
	public Swift(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new SwiftInstance(pokemon, this));
	}

	public class SwiftInstance extends MoveInstance {
		public SwiftInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, true, true, true);
		}

		@Override
		public void behavior() {
			if (this.frame <= 20 && this.frame % 10 == 0) {
				Projectile p = this.spawnProjectile(new SwiftProjectile(this, this.move));
				p.velocity = this.pokemon.getAimVector().clone().mul(Constants.projectileVelocity);
			}
		}
	}

	public class SwiftProjectile extends Projectile {
	    public SwiftProjectile(MoveInstance owner, Move move) {
	    	super(owner, move);
	    }

	    @Override
		public void behavior() {

	    }

		@Override
		public void render(Graphics2D screen) {
			double sizeOuter = 10.0;
			double sizeInner = sizeOuter * 0.382;
			double angle = 4 * Math.PI * (double) this.frame / (double) (this.owner.lifetime - 20);
			double offset = 2 * Math.PI / 5;
			IntVector a = Vector.fromAngle(angle).mul(sizeOuter).getIntVector();
			IntVector b = Vector.fromAngle(angle + 0.5 * offset).mul(sizeInner).getIntVector();
			IntVector c = Vector.fromAngle(angle + 1.0 * offset).mul(sizeOuter).getIntVector();
			IntVector d = Vector.fromAngle(angle + 1.5 * offset).mul(sizeInner).getIntVector();
			IntVector e = Vector.fromAngle(angle + 2.0 * offset).mul(sizeOuter).getIntVector();
			IntVector f = Vector.fromAngle(angle + 2.5 * offset).mul(sizeInner).getIntVector();
			IntVector g = Vector.fromAngle(angle + 3.0 * offset).mul(sizeOuter).getIntVector();
			IntVector h = Vector.fromAngle(angle + 3.5 * offset).mul(sizeInner).getIntVector();
			IntVector i = Vector.fromAngle(angle + 4.0 * offset).mul(sizeOuter).getIntVector();
			IntVector j = Vector.fromAngle(angle + 4.5 * offset).mul(sizeInner).getIntVector();
			int[] x = new int[] {a.x, b.x, c.x, d.x, e.x, f.x, g.x, h.x, i.x, j.x};
			int[] y = new int[] {a.y, b.y, c.y, d.y, e.y, f.y, g.y, h.y, i.y, j.y};
			screen.setColor(Constants.shadowColor);
			screen.fillPolygon(x, y, 10);
			screen.setColor(Color.yellow);
			screen.translate(0, -11);
			screen.fillPolygon(x, y, 10);
			screen.translate(0, 11);

		}
	}
}