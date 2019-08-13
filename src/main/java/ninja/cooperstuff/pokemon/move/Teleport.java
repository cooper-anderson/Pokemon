package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.AreaOfEffect;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;

public class Teleport extends Move {
	public Teleport(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new TeleportInstance(pokemon, this));
	}

	public class TeleportInstance extends MoveInstance {
		public TeleportInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			Projectile p = this.spawnProjectile(new TeleportProjectile(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			Vector old = pokemon.transform.position.clone();
			pokemon.transform.position = this.pokemon.transform.position.clone();
			boolean moving = true;
			while (moving) {
				Vector current = Vector.sub(old, this.pokemon.transform.position);
				if (current.magnitude() > 10) current = current.normalized().mul(10);
				else moving = false;
				this.pokemon.transform.position.add(current);
				IntVector tile = this.pokemon.transform.position.getTile();
				this.pokemon.world.generate(tile.x, tile.y);
			}
			this.game.camera.follow(this.pokemon.transform.position, Vector.zero);
			return 0;
		}
	}

	public class TeleportProjectile extends AreaOfEffect {
		int opacity = 128;

		public TeleportProjectile(MoveInstance owner, Move move) {
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