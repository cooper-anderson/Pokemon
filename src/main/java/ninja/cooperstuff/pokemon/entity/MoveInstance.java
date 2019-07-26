package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.projectile.Projectile;
import ninja.cooperstuff.pokemon.move.Move;

import java.awt.*;
import java.util.HashSet;

public abstract class MoveInstance extends Entity {
	public Pokemon pokemon;
	public Move move;
	public HashSet<Pokemon> pokemonHit = new HashSet<>();
	public HashSet<Projectile> projectiles = new HashSet<>();
	public int lifetime = 150;

	public MoveInstance(Pokemon pokemon, Move move) {
		super(pokemon.world);
		this.transform.position = pokemon.transform.position.clone();
		this.pokemon = pokemon;
		this.move = move;
	}

	public Projectile spawnProjectile(Projectile projectile) {
		this.projectiles.add(projectile);
		return projectile;
	}

	@Override
	public void update() {
		if (this.frame > this.lifetime) this.destroy();
		this.behavior();
		for (Projectile p : this.projectiles) {
			p.update();
			if (false) { // TODO collisions
				Pokemon pokemon = this.pokemon;
				if (!this.pokemonHit.contains(pokemon) && pokemon != this.pokemon) {
					this.pokemonHit.add(pokemon);
					this.onCollision(pokemon);
				}
			}
		}
	}

	public abstract void behavior();

	public void onCollision(Pokemon pokemon) {
		pokemon.stats.health -= this.move.points;
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
