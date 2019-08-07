package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.AreaOfEffect;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.type.Type;

import java.awt.*;

public class Haze extends Move {
	public Haze(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new HazeInstance(pokemon, this));
	}

	public class HazeInstance extends MoveInstance {
		public HazeInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, false, true);
			this.pokemon.resetStats();
			this.spawnProjectile(new HazeProjectile(this, this.move));
		}

		@Override
		public int onCollision(Pokemon pokemon, Projectile projectile) {
			pokemon.resetStats();
			return super.onCollision(pokemon, projectile);
		}

		@Override
		public void behavior() {

		}
	}

	public class HazeProjectile extends AreaOfEffect {
		int opacity = 255;

	    public HazeProjectile(MoveInstance owner, Move move) {
	    	super(owner, move);
		    this.position = this.owner.transform.position;
	    }

	    @Override
		public void behavior() {
		    this.radius++;
		    this.opacity = (int) Math.max(0, (255.0 * (1 - (double) this.frame / (double) this.owner.lifetime)));
		    this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.opacity);
	    }
	}
}