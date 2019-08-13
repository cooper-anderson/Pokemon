package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.AreaOfEffect;
import ninja.cooperstuff.pokemon.type.Type;

import java.awt.*;

public class Surf extends Move {
	public Surf(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new SurfInstance(pokemon, this));
	}

	public class SurfInstance extends MoveInstance {
		public SurfInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			this.spawnProjectile(new SurfProjectile(this, this.move));
		}

		@Override
		public void behavior() {

		}
	}

	public static class SurfProjectile extends AreaOfEffect {
		int opacity = 255;

		public SurfProjectile(MoveInstance owner, Move move) {
			super(owner, move);
			this.position = this.owner.transform.position;
		}

		@Override
		public void behavior() {
			this.radius++;
			this.opacity = (int) Math.max(0, (255.0 * (1 - (double) this.frame / (double) this.owner.lifetime)));
			this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.opacity);
		}

		@Override
		public void render(Graphics2D screen) {
			screen.setColor(this.color);
			screen.fillOval((int) -radius, (int) -radius, (int) radius * 2, (int) (radius * 2));
		}
	}
}