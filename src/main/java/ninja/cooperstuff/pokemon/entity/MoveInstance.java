package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.move.Move;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.util.Status;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public abstract class MoveInstance extends Entity {
	public Pokemon pokemon;
	public Move move;
	public HashMap<Stats.Stat, Move.StatModification> modifiers;
	public HashMap<Status, Double> effects;
	public HashSet<Pokemon> pokemonHit = new HashSet<>();
	public HashSet<Projectile> projectiles = new HashSet<>();
	public HashSet<Projectile> projectilesDeleteQueue = new HashSet<>();
	public int lifetime = 150;

	public boolean multihit = false;
	public boolean destroyProjectiles = false;

	public MoveInstance(Pokemon pokemon, Move move, HashMap<Stats.Stat, Move.StatModification> modifiers, HashMap<Status, Double> effects, boolean multihit, boolean destroyProjectiles, boolean copyPosition) {
		super(pokemon.world);
		this.transform.position = copyPosition ? pokemon.transform.position : pokemon.transform.position.clone();
		this.pokemon = pokemon;
		this.move = move;
		this.modifiers = modifiers;
		this.effects = effects;
		this.multihit = multihit;
		this.destroyProjectiles = destroyProjectiles;
	}

	public MoveInstance(Pokemon pokemon, Move move, HashMap<Stats.Stat, Move.StatModification> modifiers, HashMap<Status, Double> effects) {
		this(pokemon, move, modifiers, effects, false, false, true);
	}

	public Projectile spawnProjectile(Projectile projectile) {
		this.projectiles.add(projectile);
		return projectile;
	}

	@Override
	public void update() {
		if (this.frame > this.lifetime) this.destroy();
		this.behavior();
		for (Projectile projectile : this.projectiles) {
			projectile.update();
			for (Pokemon pokemon : this.world.pokemon) {
				if ((this.multihit || !this.pokemonHit.contains(pokemon)) && !projectile.pokemonHit.contains(pokemon)) {
					Vector col1 = Vector.add(pokemon.transform.position, pokemon.monster.collisionCorner1);
					Vector col2 = Vector.add(pokemon.transform.position, pokemon.monster.collisionCorner2);
					if (projectile.position.x > col1.x && projectile.position.x < col2.x && projectile.position.y > col1.y && projectile.position.y < col2.y) {
						projectile.pokemonHit.add(pokemon);
						this.pokemonHit.add(pokemon);
						this.onCollision(pokemon, projectile);
						if (this.destroyProjectiles) this.projectilesDeleteQueue.add(projectile);
					}
				}
			}
		}
		for (Projectile projectile : this.projectilesDeleteQueue) {
			this.projectiles.remove(projectile);
		}
		this.projectilesDeleteQueue.clear();
	}

	public abstract void behavior();

	public int onCollision(Pokemon pokemon, Projectile projectile) {
		for (Stats.Stat stat : this.modifiers.keySet()) {
			Move.StatModification data = this.modifiers.get(stat);
			pokemon.modifyStat(stat, data.modifier, data.sign, data.chance);
		}
		for (Status status : this.effects.keySet()) {
			if (new Random().nextDouble() < this.effects.get(status)) pokemon.setStatus(status);
		}
		return pokemon.damage(this.move.power);
	}

	public void render(Graphics2D screen) {
		Color c = screen.getColor();
		for (Projectile p : this.projectiles) {
			IntVector pos = Vector.sub(p.position, this.transform.position).getIntVector();
			screen.translate(pos.x, pos.y);
			p.render(screen);
			screen.translate(-pos.x, -pos.y);
		}
		screen.setColor(c);
	}
}
