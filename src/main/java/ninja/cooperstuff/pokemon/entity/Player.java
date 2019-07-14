package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public class Player extends Pokemon {
	private int canMove = 0;
	public static boolean collisionMode = false;

	public Player(World world, Monster monster) {
		super(world, monster);
	}

	@Override
	public void update() {
		super.update();
		double multiplier = 1;
		if (KeyListener.isKeyDown(Keys.SPACE)) canMove = 1 - canMove;
		double speed = canMove * multiplier * ((KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.W) && KeyListener.isKeyHeld(Keys.D)) ||
				(KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.A)) || (KeyListener.isKeyHeld(Keys.S) && KeyListener.isKeyHeld(Keys.D)) ? .707 : 1);
		if (KeyListener.isKeyHeld(Keys.W)) {
			this.facing = Direction.UP; moving = true;
			this.transform.position.y -= speed;
		} else if (KeyListener.isKeyHeld(Keys.S)) {
			this.facing = Direction.DOWN; moving = true;
			this.transform.position.y += speed;
		} if (KeyListener.isKeyHeld(Keys.A)) {
			this.facing = Direction.LEFT; moving = true;
			this.transform.position.x -= speed;
		} else if (KeyListener.isKeyHeld(Keys.D)) {
			this.facing = Direction.RIGHT; moving = true;
			this.transform.position.x += speed;
		}

		int x=0, y=0, h=0;
		if (KeyListener.isKeyTyped(Keys.I)) y = -1;
		if (KeyListener.isKeyTyped(Keys.K)) y = 1;
		if (KeyListener.isKeyTyped(Keys.J)) x = -1;
		if (KeyListener.isKeyTyped(Keys.L)) x = 1;
		if (KeyListener.isKeyTyped(Keys.Y)) h = 1;
		if (KeyListener.isKeyTyped(Keys.H)) h = -1;

		if (KeyListener.isKeyDown(Keys.TILDE)) collisionMode = !collisionMode;

		if (collisionMode) {
			if (KeyListener.isKeyTyped(Keys.O)) {
				this.monster.setCollisionCorner1(Vector.add(this.monster.getCollisionCorner1(), new Vector(1, 0)));
				this.monster.setCollisionCorner2(Vector.add(this.monster.getCollisionCorner2(), new Vector(-1, 0)));
			} else if (KeyListener.isKeyTyped(Keys.U)) {
				this.monster.setCollisionCorner1(Vector.add(this.monster.getCollisionCorner1(), new Vector(-1, 0)));
				this.monster.setCollisionCorner2(Vector.add(this.monster.getCollisionCorner2(), new Vector(1, 0)));
			}
			if (KeyListener.isKeyHeld(Keys.SHIFT)) {
				this.monster.setCollisionCorner1(Vector.add(this.monster.getCollisionCorner1(), new Vector(x, y)));
				this.monster.setCollisionCorner2(Vector.add(this.monster.getCollisionCorner2(), new Vector(x, y)));
			} else if (KeyListener.isKeyHeld(Keys.CTRL)) this.monster.setCollisionCorner2(Vector.add(this.monster.getCollisionCorner2(), new Vector(x, y)));
			else this.monster.setCollisionCorner1(Vector.add(this.monster.getCollisionCorner1(), new Vector(x, y)));
		} else {
			if (KeyListener.isKeyTyped(Keys.O)) this.monster.setShadowSize(this.monster.getShadowSize() + .1);
			if (KeyListener.isKeyTyped(Keys.U)) this.monster.setShadowSize(this.monster.getShadowSize() - .1);
			if (KeyListener.isKeyHeld(Keys.SHIFT)) {
				if (KeyListener.isKeyTyped(Keys.P)) {
					this.monster.setSpriteOffset(Direction.UP, new Vector());
					this.monster.setSpriteOffset(Direction.DOWN, new Vector());
					this.monster.setSpriteOffset(Direction.LEFT, new Vector());
					this.monster.setSpriteOffset(Direction.RIGHT, new Vector());
					this.monster.setBobHeight(Direction.UP, 0);
					this.monster.setBobHeight(Direction.DOWN, 0);
					this.monster.setBobHeight(Direction.LEFT, 0);
					this.monster.setBobHeight(Direction.RIGHT, 0);
				}
				this.monster.setSpriteOffset(Direction.UP, Vector.add(this.monster.getSpriteOffset(Direction.UP), new Vector(x, y)));
				this.monster.setSpriteOffset(Direction.DOWN, Vector.add(this.monster.getSpriteOffset(Direction.DOWN), new Vector(x, y)));
				this.monster.setSpriteOffset(Direction.LEFT, Vector.add(this.monster.getSpriteOffset(Direction.LEFT), new Vector(x, y)));
				this.monster.setSpriteOffset(Direction.RIGHT, Vector.add(this.monster.getSpriteOffset(Direction.RIGHT), new Vector(x, y)));
				this.monster.setBobHeight(Direction.UP, this.monster.getBobHeight(Direction.UP) + h);
				this.monster.setBobHeight(Direction.DOWN, this.monster.getBobHeight(Direction.DOWN) + h);
				this.monster.setBobHeight(Direction.LEFT, this.monster.getBobHeight(Direction.LEFT) + h);
				this.monster.setBobHeight(Direction.RIGHT, this.monster.getBobHeight(Direction.RIGHT) + h);
			} else if (KeyListener.isKeyHeld(Keys.CTRL)) {
				if (KeyListener.isKeyTyped(Keys.P)) {
					this.monster.setSpriteOffset(this.facing, new Vector());
					this.monster.setSpriteOffset(this.facing.getBack(), new Vector());
					this.monster.setBobHeight(this.facing, 0);
					this.monster.setBobHeight(this.facing.getBack(), 0);
				}
				this.monster.setSpriteOffset(this.facing, Vector.add(this.monster.getSpriteOffset(this.facing), new Vector(x, y)));
				this.monster.setSpriteOffset(this.facing.getBack(), Vector.add(this.monster.getSpriteOffset(this.facing.getBack()), new Vector(-x, y)));
				this.monster.setBobHeight(this.facing, this.monster.getBobHeight(this.facing) + h);
				this.monster.setBobHeight(this.facing.getBack(), this.monster.getBobHeight(this.facing.getBack()) + h);
			} else {
				if (KeyListener.isKeyTyped(Keys.T)) this.monster.setAnimationSpeed(this.monster.getAnimationSpeed() + 1);
				if (KeyListener.isKeyTyped(Keys.G)) this.monster.setAnimationSpeed(this.monster.getAnimationSpeed() - 1);
				if (KeyListener.isKeyTyped(Keys.P)) {
					this.monster.setSpriteOffset(this.facing, new Vector());
					this.monster.setBobHeight(this.facing, 0);
				}
				this.monster.setSpriteOffset(this.facing, Vector.add(this.monster.getSpriteOffset(this.facing), new Vector(x, y)));
				this.monster.setBobHeight(this.facing, this.monster.getBobHeight(this.facing) + h);
			}
		}
	}

	@Override
	public void render(Graphics2D screen) {
		super.render(screen);
		double scale = 2.0;
		Vector up = this.monster.getSpriteOffset(Direction.UP);
		Vector down = this.monster.getSpriteOffset(Direction.DOWN);
		Vector left = this.monster.getSpriteOffset(Direction.LEFT);
		Vector right = this.monster.getSpriteOffset(Direction.RIGHT);
		screen.translate(-this.transform.position.x, -this.transform.position.y);
		screen.scale(scale, scale);
		screen.drawString(this.monster.name, 50, 205);
		screen.drawString(String.format("Shadow: %s", Math.round(this.monster.getShadowSize() * 10) / 10.0), 150, 205);
		screen.drawString(String.format("BobHeight: <%d,%d,%d,%d>", this.monster.getBobHeight(Direction.UP), this.monster.getBobHeight(Direction.DOWN), this.monster.getBobHeight(Direction.LEFT), this.monster.getBobHeight(Direction.RIGHT)), 250, 205);
		screen.drawString(String.format("OffsetUp: <%d, %d>", (int) up.x, (int) up.y), 90, 230);
		screen.drawString(String.format("OffsetDown: <%d, %d>", (int) down.x, (int) down.y), 90, 255);
		screen.drawString(String.format("OffsetLeft: <%d, %d>", (int) left.x, (int) left.y), 210, 230);
		screen.drawString(String.format("OffsetRight: <%d, %d>", (int) right.x, (int) right.y), 210, 255);
		screen.drawString(String.format("Animation: %s", this.monster.getAnimationSpeed()), 50, 280);
		screen.drawString(String.format("CollCorner1: <%d, %d>", (int) this.monster.getCollisionCorner1().x, (int) this.monster.getCollisionCorner1().y), 130, 280);
		screen.drawString(String.format("CollCorner2: <%d, %d>", (int) this.monster.getCollisionCorner2().x, (int) this.monster.getCollisionCorner2().y), 260, 280);
		screen.scale(1/scale, 1/scale);
		screen.translate(this.transform.position.x, this.transform.position.y);
	}
}
