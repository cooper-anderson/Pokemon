package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public class Pokemon extends Entity {
	public Monster monster;
	private Stats valuesEffort = new Stats();
	private Stats valuesIndividual = new Stats();
	private Stats stats;
	private int walkCycle = 0;
	public Direction facing = Direction.DOWN;

	public Pokemon(World world, Monster monster) {
		super(world);
		this.monster = monster;
	}

	@Override
	public void update() {
		if (KeyListener.isKeyPressed(Keys.K)) this.destroy();
		boolean moving = false;
		double multiplier = 1;
		double speed = multiplier * ((KeyListener.isKeyPressed(Keys.W) && KeyListener.isKeyPressed(Keys.A)) || (KeyListener.isKeyPressed(Keys.W) && KeyListener.isKeyPressed(Keys.D)) ||
				(KeyListener.isKeyPressed(Keys.S) && KeyListener.isKeyPressed(Keys.A)) || (KeyListener.isKeyPressed(Keys.S) && KeyListener.isKeyPressed(Keys.D)) ? .707 : 1);
		if (KeyListener.isKeyPressed(Keys.W)) {
			this.facing = Direction.UP; moving = true;
			this.transform.position.y -= speed;
		} else if (KeyListener.isKeyPressed(Keys.S)) {
			this.facing = Direction.DOWN; moving = true;
			this.transform.position.y += speed;
		} if (KeyListener.isKeyPressed(Keys.A)) {
			this.facing = Direction.LEFT; moving = true;
			this.transform.position.x -= speed;
		} else if (KeyListener.isKeyPressed(Keys.D)) {
			this.facing = Direction.RIGHT; moving = true;
			this.transform.position.x += speed;
		}
		if (this.frame % (int) (30) == 0 || (moving && this.frame % (int) (15 / multiplier) == 0)) {
			this.walkCycle = 1 - walkCycle;
		}
	}

	@Override
	public void render(Graphics2D screen) {
		screen.drawImage(this.monster.spriteLayout.get(this.facing)[this.walkCycle], (int) this.transform.position.x, (int) this.transform.position.y, 64, 64, null);
	}
}
