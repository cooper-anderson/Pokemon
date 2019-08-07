package ninja.cooperstuff.pokemon.entity.projectile;

import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.Entity;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.move.Move;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public abstract class Projectile {
	public MoveInstance owner;
	public Move move;
	public Vector position;
	public Vector velocity = new Vector();
	public Color color;
	public int seed = Math.abs(new Random().nextInt(100));
	public double offset = Math.abs(new Random().nextDouble()) % 5;
	public HashSet<Pokemon> pokemonHit = new HashSet<>();
	public int frame = 0;

	public Entity.Shadow shadow = new Entity.Shadow(0.4);

	public Projectile(MoveInstance owner, Move move) {
		this.owner = owner;
		this.move = move;
		this.position = owner.transform.position.clone();
		this.color = this.move.type.color;
		this.pokemonHit.add(this.owner.pokemon);
	}

	public void destroy() {
		this.owner.projectilesDeleteQueue.add(this);
	}

	public void update() {
		this.behavior();
		this.position.add(Vector.mul(this.velocity, Math.min(((double) this.frame / 20), 1)));
		this.frame++;
	}

	public boolean checkCollision(Pokemon pokemon) {
		Vector col1 = Vector.add(pokemon.transform.position, pokemon.monster.collisionCorner1);
		Vector col2 = Vector.add(pokemon.transform.position, pokemon.monster.collisionCorner2);
		return this.position.x > col1.x && this.position.x < col2.x && this.position.y > col1.y && this.position.y < col2.y;
	}

	public void render(Graphics2D screen) {
		int particleCount = 3;
		if (this.shadow.scale != 0) this.shadow.render(screen);
		screen.setColor(this.color);
		screen.fillOval(-5, -16, 10, 10);
		for (int i = 1; i <= particleCount; i++) {
			screen.fillOval(-2 + (int) (20 * Noise.noise(i * offset + (double) this.frame / 50, seed)), -13 + (int) (20 * Noise.noise(seed, i * offset + (double) this.frame / 50)), 5, 5);
		}
	}

	public abstract void behavior();
}
