package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.Random;

public class Pokemon extends Entity {
	public Monster monster;
	protected Stats valuesEffort = new Stats();
	protected Stats valuesIndividual = new Stats();
	protected Stats stats;
	protected boolean shiny = false;
	protected boolean useAI = false;
	private boolean isPlayer = false;
	protected int walkCycle = 0;
	private Vector lastPos = new Vector();
	public Direction facing = Direction.DOWN;
	protected boolean moving = false;
	protected boolean noclip = false;
	public Vector input = new Vector();
	private int seed = this.hashCode() % 10000;

	public Pokemon(World world, Monster monster) {
		super(world);
		this.setMonster(monster);
		this.shadow.scale = this.monster.getShadowSize();
		this.shiny = new Random().nextInt(8192) == 1;
		if (this.shiny) Debug.info(String.format("Shiny %s", this.monster.name));
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
		this.shadow.scale = this.monster.getShadowSize();
	}

	public boolean isShiny() {
		return this.shiny;
	}

	public Pokemon isShiny(boolean shiny) {
		this.shiny = shiny;
		return this;
	}

	public boolean useAI() {
		return this.useAI;
	}

	public Pokemon useAI(boolean useAI) {
		this.useAI = useAI;
		return this;
	}

	public boolean isPlayer() {
		return this.isPlayer;
	}

	public Pokemon isPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
		return this;
	}

	public Vector getVelocity() {
		return Vector.sub(this.transform.position, this.lastPos);
	}

	public Vector getForwardVector() {
		Vector velocity = this.getVelocity().normalized();
		if (velocity.equals(Vector.zero)) return Vector.fromDirection(this.facing);
		return velocity;
	}

	@Override
	public void update() {
		this.lastPos = this.transform.position.clone();
		if ((!this.moving && this.frame % (2 * this.monster.getAnimationSpeed()) == 0) || (this.moving && this.frame % this.monster.getAnimationSpeed() == 0)) this.walkCycle = 1 - walkCycle;
		this.moving = false;

		if (this.useAI) {
			double threshold = 0.2;
			double x = Noise.noise(seed + this.frame / 100.0);
			double y = Noise.noise(seed + 10000 + this.frame / 100.0);
			this.input.x = (x > threshold) ? 1 : (x < - threshold) ? -1 : 0;
			this.input.y = (y > threshold) ? 1 : (y < - threshold) ? -1 : 0;
			/*if (x > threshold) this.input.x = 1;
			else if (x < -threshold) this.input.x = -1;
			else this.input.x = 0;*/
		}

		Vector pos = this.transform.position;
		IntVector tile = pos.getTile();
		IntVector camTile = this.game.camera.position.getTile();
		IntVector dist = new IntVector(Math.abs(tile.x - camTile.x), Math.abs(tile.y - camTile.y));
		if (!this.isPlayer() && (dist.x > this.world.entityMaxDist.x || dist.y > this.world.entityMaxDist.y)) this.destroy();
		else if (dist.x <= this.world.getDetailSize().x && dist.y <= this.world.getDetailSize().y) {
			Vector newPos = new Vector(this.transform.position.x + this.input.x, this.transform.position.y + this.input.y);
			IntVector newTile = newPos.getTile();
			TileData tileData = this.world.getTileData(newTile.x, newTile.y);
			TileData tileX = this.world.getTileData(newTile.x, tile.y);
			TileData tileY = this.world.getTileData(tile.x, newTile.y);
			if (tileData == null || tileX == null || tileY == null) Debug.warn(String.format("Pokemon<%s> Out of bounds", this.monster.name));
			else {
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
				if (this.noclip || tileData.getWalkable() || x1 || x2 || y1 || y2) {
					finalVelocity.x = this.input.x;
					finalVelocity.y = this.input.y;
				} else {
					if (tileX.getWalkable() || xx1 || xx2 || xy1 || xy2) finalVelocity.x = this.input.x;
					if (tileY.getWalkable() || yx1 || yx2 || yy1 || yy2) finalVelocity.y = this.input.y;
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
			}
		}
	}

	@Override
	public void destroy() {
		if (!this.isPlayer()) this.world.pokemon.remove(this);
		super.destroy();
	}

	@Override
	public void render(Graphics2D screen) {
		super.render(screen);
		int size = 64;
		Vector offset = this.monster.getSpriteOffset(this.facing);
		int x = (int) (offset.x - size / 2);
		int y = (int) (offset.y - this.walkCycle * this.monster.getBobHeight(this.facing) - size + 4);
		screen.drawImage(this.monster.getSprite(this.facing, this.walkCycle, this.shiny), x, y, size, size, null);
		Vector col1 = this.monster.getCollisionCorner1();
		Vector col2 = this.monster.getCollisionCorner2();
	}
}
