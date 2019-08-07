package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.particle.Bezier;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Constants;

import java.awt.*;
import java.util.Random;

public class LeechSeed extends Move {
	public LeechSeed(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new LeechSeedInstance(pokemon, this));
	}

	public class LeechSeedInstance extends MoveInstance {
		public LeechSeedInstance(Pokemon pokemon, Move move) {
			super(pokemon, move, false, true, true);
			this.lifetime = 250;
			Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
			p.velocity = this.pokemon.getForwardVector().clone().mul(Constants.projectileVelocity);
		}

		@Override
		public void behavior() {
			if (this.frame == 150 && this.pokemonHit.size() == 0) this.destroy();
			if (this.frame % 50 == 0) {
				for (Pokemon pokemon : this.pokemonHit) {
					if (pokemon.getHealth() != 0) {
						pokemon.damage(pokemon.monster.baseStats.health / 16);
						this.pokemon.heal(pokemon.monster.baseStats.health / 16);
						Bezier b = this.game.instantiate(new Bezier(this.world, 30));
						Random r = new Random();
						b.color = this.move.type.color;
						b.color = new Color(b.color.getRed(), b.color.getGreen(), b.color.getBlue(), 128);
						b.v0 = Vector.add(pokemon.transform.position, new Vector(r.nextDouble() * 2 - 1, r.nextDouble() * 2 - 1).mul(15));
						b.v2 = this.pokemon.transform.position;
						Vector diff = Vector.sub(b.v0, b.v2);
						b.v1 = Vector.add(Vector.div(Vector.add(b.v0, b.v2), 2.0),
								Vector.mul(new Vector(diff.y, -diff.x), Math.min(Constants.maxBezierMagnitude, diff.magnitude()) * (r.nextDouble() * 2 - 1) / 1000.0)
						);
					}
				}
			}
		}
	}
}