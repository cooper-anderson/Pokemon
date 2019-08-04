package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.world.World;
import ninja.cooperstuff.pokemon.world.biome.Biome;

public class Player extends Pokemon {
	public Player(World world, Monster monster) {
		super(world, monster);
		this.isPlayer(true);
	}

	@Override
	public void update() {
		super.update();
		double multiplier = 1;
		double superSpeed = KeyListener.isKeyHeld(Keys.SHIFT) ? 5 : 1;
		double speed = superSpeed * multiplier * ((KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.D)) ||
				(KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.D)) ? .707 : 1);
		this.noclip = KeyListener.isKeyHeld(Keys.CTRL);
		if (KeyListener.isKeyHeld(Keys.W)) this.input.y = -speed;
		else if (KeyListener.isKeyHeld(Keys.S)) this.input.y = +speed;
		else this.input.y = 0;
		if (KeyListener.isKeyHeld(Keys.A)) this.input.x = -speed;
		else if (KeyListener.isKeyHeld(Keys.D)) this.input.x = +speed;
		else this.input.x = 0;

		if (KeyListener.isKeyDown(Keys.C)) this.world.spawnPokemon(Biome.cave.getPokemon());

		if (KeyListener.isKeyTyped(45)) this.transform.scale.div(1.1);
		if (KeyListener.isKeyTyped(61)) this.transform.scale.mul(1.1);
		if (KeyListener.isKeyUp(Keys.E)) {
			this.setMonster(Monsters.charmander);
			Moves.ember.use(this);
		}
		if (KeyListener.isKeyUp(Keys.Q)) {
			this.setMonster(Monsters.bulbasaur);
			Moves.vineWhip.use(this);
		}

		this.game.camera.follow(this, new Vector(10, 10));
		this.game.camera.lagFollow(this.transform.position);

		if (KeyListener.isKeyDown(Keys.SPACE)) this.world.tempMove.use(this);
	}
}
