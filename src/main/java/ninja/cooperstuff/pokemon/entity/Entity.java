package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.pokemon.world.World;

public abstract class Entity extends GameObject {
	private World world;

	public Entity(World world) {
		this.world = world;
	}

	@Override
	public void update() {

	}
}
