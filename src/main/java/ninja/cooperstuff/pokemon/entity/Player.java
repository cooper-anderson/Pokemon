package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.client.PokemonGame;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.world.World;

public class Player extends Pokemon {
	public int selected = 0;

	public Player(World world, Monster monster) {
		super(world, monster);
		this.isPlayer(true);
	}

	@Override
	public void update() {
		super.update();
		double multiplier = 1;
		double superSpeed = 2;//KeyListener.isKeyHeld(Keys.SHIFT) ? 5 : 1;
		double speed = superSpeed * multiplier * ((KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.D)) ||
				(KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.D)) ? .707 : 1);
		this.noclip = KeyListener.isKeyHeld(Keys.CTRL);
		if (KeyListener.isKeyHeld(Keys.W)) this.input.y = -speed;
		else if (KeyListener.isKeyHeld(Keys.S)) this.input.y = +speed;
		else this.input.y = 0;
		if (KeyListener.isKeyHeld(Keys.A)) this.input.x = -speed;
		else if (KeyListener.isKeyHeld(Keys.D)) this.input.x = +speed;
		else this.input.x = 0;

		this.game.camera.follow(this, new Vector(10, 10));
		this.game.camera.lagFollow(this.transform.position);

		//if (KeyListener.isKeyTyped(Keys.SPACE)) this.world.tempMove.use(this);
		/*if (KeyListener.isKeyHeld(Keys.H)) this.useMove(0);
		if (KeyListener.isKeyHeld(Keys.J)) this.useMove(1);
		if (KeyListener.isKeyHeld(Keys.K)) this.useMove(2);
		if (KeyListener.isKeyHeld(Keys.L)) this.useMove(3);*/
		int temo = this.selected;
		if (KeyListener.isKeyTyped(Keys.UP) || KeyListener.isKeyTyped(Keys.I)) this.selected = (this.selected + 2) % 4;
		if (KeyListener.isKeyTyped(Keys.DOWN) || KeyListener.isKeyTyped(Keys.K)) this.selected = (this.selected + 2) % 4;
		if (KeyListener.isKeyTyped(Keys.LEFT) || KeyListener.isKeyTyped(Keys.J)) this.selected = (4 + this.selected - 1) % 4;
		if (KeyListener.isKeyTyped(Keys.RIGHT) || KeyListener.isKeyTyped(Keys.L)) this.selected = (4 + this.selected + 1) % 4;
		if (KeyListener.isKeyHeld(Keys.SPACE)) this.useMove(this.selected);

		if (this.stats.health == 0 && this.healthAnimation == 0) ((PokemonGame) this.game).respawn();
	}

	public int getHealthAnimation() {
		return this.healthAnimation;
	}

	public int getHealthMax() {
		return this.healthMax;
	}
}
