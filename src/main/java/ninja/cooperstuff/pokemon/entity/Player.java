package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

public class Player extends Pokemon {
	public Player(World world, Monster monster) {
		super(world, monster);
	}

	@Override
	public void update() {
		super.update();
		double multiplier = 1;
		double superSpeed = KeyListener.isKeyHeld(Keys.SHIFT) ? 5 : 1;
		double speed = superSpeed * multiplier * ((KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.D)) ||
				(KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.D)) ? .707 : 1);
		boolean noclip = KeyListener.isKeyHeld(Keys.CTRL);
		Vector velocity = new Vector();
		if (KeyListener.isKeyHeld(Keys.W)) velocity.y = -speed;
		else if (KeyListener.isKeyHeld(Keys.S)) velocity.y = +speed;
		if (KeyListener.isKeyHeld(Keys.A)) velocity.x = -speed;
		else if (KeyListener.isKeyHeld(Keys.D)) velocity.x = +speed;

		Vector pos = this.transform.position;
		IntVector tile = pos.getTile();
		Vector newPos = new Vector(this.transform.position.x + velocity.x, this.transform.position.y + velocity.y);
		IntVector newTile = newPos.getTile();
		TileData tileData = this.world.getTileData(newTile.x, newTile.y);
		TileData tileX = this.world.getTileData(newTile.x, tile.y);
		TileData tileY = this.world.getTileData(tile.x, newTile.y);

		boolean x1 = newPos.x < newTile.x * 32 + 2 * tileData.getCollisionCorner1().x,
				x2 = newPos.x > newTile.x * 32 + 2 * tileData.getCollisionCorner2().x,
				y1 = newPos.y < newTile.y * 32 + 2 * tileData.getCollisionCorner1().y,
				y2 = newPos.y > newTile.y * 32 + 2 * tileData.getCollisionCorner2().y,
				xx1 = newPos.x < newTile.x * 32 + 2 * tileX.getCollisionCorner1().x,
				xx2 = newPos.x > newTile.x * 32 + 2 * tileX.getCollisionCorner2().x,
				xy1 = pos.y <= tile.y * 32 + 2 * tileX.getCollisionCorner1().y,
				xy2 = pos.y > tile.y * 32 + 2 * tileX.getCollisionCorner2().y,
				yx1 = pos.x < tile.x * 32 + 2 * tileY.getCollisionCorner1().x,
				yx2 = pos.x > tile.x * 32 + 2 * tileY.getCollisionCorner2().x,
				yy1 = newPos.y < newTile.y * 32 + 2 * tileY.getCollisionCorner1().y,
				yy2 = newPos.y > newTile.y * 32 + 2 * tileY.getCollisionCorner2().y;

		Vector finalVelocity = new Vector();
		if (noclip || tileData.getWalkable() || x1 || x2 || y1 || y2) {
			finalVelocity.x = velocity.x;
			finalVelocity.y = velocity.y;
		} else {
			if (tileX.getWalkable() || xx1 || xx2 || xy1 || xy2) finalVelocity.x = velocity.x;
			if (tileY.getWalkable() || yx1 || yx2 || yy1 || yy2) finalVelocity.y = velocity.y;
		}

		if (finalVelocity.y != 0) {
			this.facing = finalVelocity.y < 0 ? Direction.UP : Direction.DOWN;
			this.transform.position.y += finalVelocity.y;
			this.moving = true;
		}
		if (finalVelocity.x != 0) {
			this.facing = finalVelocity.x < 0 ? Direction.LEFT : Direction.RIGHT;
			this.transform.position.x += finalVelocity.x;
			this.moving = true;
		}

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
	}
}
