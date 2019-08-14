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
			p.velocity = this.pokemon.getAimVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {

		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			if (this.pokemon.isPlayer()) movePlayer(this.pokemon, pokemon);
			else if (pokemon.isPlayer()) movePlayer(pokemon, this.pokemon);
			else {
				Vector pos = pokemon.transform.position.clone();
				pokemon.transform.position = this.pokemon.transform.position.clone();
				this.pokemon.transform.position = pos;
			}
			return 0;
		}

		private void movePlayer(Pokemon user, Pokemon target) {
			Vector pos = target.transform.position.clone();
			target.transform.position = user.transform.position.clone();
			boolean moving = true;
			while (moving) {
				Vector current = Vector.sub(pos, user.transform.position);
				if (current.magnitude() > 10) current = current.normalized().mul(10);
				else moving = false;
				user.transform.position.add(current);
				IntVector tile = user.transform.position.getTile();
				user.world.generate(tile.x, tile.y);
			}
			this.game.camera.follow(user.transform.position, Vector.zero);
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