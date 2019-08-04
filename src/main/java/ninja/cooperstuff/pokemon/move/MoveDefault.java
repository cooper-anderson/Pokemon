package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.entity.projectile.ProjectileDefault;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Stats;

import java.util.HashMap;
import java.util.Random;

public class MoveDefault extends Move {
	private int projectileCountMin = 1;
	private int projectileCountMax = 1;
	private int projectileDelay = 10;

	public MoveDefault(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return pokemon.game.instantiate(new MoveDefaultInstance(pokemon, this, this.modifiers, this.projectileCountMin, this.projectileCountMax, this.projectileDelay));
	}

	public MoveDefault setProjectileCount(int count) {
		this.projectileCountMin = Math.max(1, count);
		this.projectileCountMax = Math.max(1, count);
		return this;
	}

	public MoveDefault setProjectileCount(int min, int max) {
		this.projectileCountMin = Math.max(1, min);
		this.projectileCountMax = Math.max(1, max);
		return this;
	}

	public MoveDefault setProjectileDelay(int delay) {
		this.projectileDelay = delay;
		return this;
	}

	public class MoveDefaultInstance extends MoveInstance {
		private int projectileCount;
		private int projectileDelay;

		public MoveDefaultInstance(Pokemon pokemon, Move move, HashMap<Stats.Stat, StatModification> effects, int projectileCountMin, int projectileCountMax, int projectileDelay) {
			super(pokemon, move, effects, true, true, true);
			this.projectileCount = projectileCountMin == projectileCountMax ? projectileCountMin : projectileCountMin + new Random().nextInt(projectileCountMax - projectileCountMin + 1);
			this.projectileDelay = projectileDelay;
		}

		@Override
		public void behavior() {
			if (this.frame <= this.projectileDelay * (this.projectileCount - 1) && this.frame % this.projectileDelay == 0) {
				Projectile p = this.spawnProjectile(new ProjectileDefault(this, this.move));
				p.velocity = this.pokemon.getForwardVector().clone().mul(5);
			}
		}
	}
}
