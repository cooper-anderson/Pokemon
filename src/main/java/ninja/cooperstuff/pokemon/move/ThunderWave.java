package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.AreaOfEffect;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class ThunderWave extends Move {
	public ThunderWave(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new ThunderWaveInstance(pokemon, this));
	}

	public class ThunderWaveInstance extends MoveInstance {
		public ThunderWaveInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			Projectile p = this.spawnProjectile(new ThunderWaveProjectile(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}
	}

	public class ThunderWaveProjectile extends AreaOfEffect {
		int opacity = 128;

		public ThunderWaveProjectile(MoveInstance owner, Move move) {
			super(owner, move);
			this.position = this.owner.transform.position.clone();
		}

		@Override
		public void behavior() {
			this.radius++;
			this.opacity = (int) Math.max(0, (128 * (1 - (double) this.frame / (double) this.owner.lifetime)));
			this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.opacity);
		}

		@Override
		public void render(Graphics2D screen) {
			screen.setColor(this.color);
			screen.fillOval((int) -radius, (int) -radius, (int) radius * 2, (int) (radius * 2));
		}
	}
}