package ninja.cooperstuff.pokemon.monster;

import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.util.ResourceLoader;
import ninja.cooperstuff.pokemon.util.Stats;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Monster {
	public static HashSet<Monster> monsters = new HashSet<>();
	public static ArrayList<Monster> ids = new ArrayList<>();

	static {
		Monster.ids.add(null);
	}

	public String name;
	public Type type1;
	public Type type2;
	public Stats baseStats;
	private BufferedImage image;
	public SpriteLayout spriteLayout;
	public HashMap<Direction, Vector> spriteOffset = new HashMap<>();
	public HashMap<Direction, Integer> bobHeight = new HashMap<>();
	public Vector collisionCorner1 = new Vector(-16, -32);
	public Vector collisionCorner2 = new Vector(16, 0);
	private double shadowSize = 1;
	private int animationSpeed = 15;

	public Monster(String name, Type type1, Type type2, int health, int attackPhysical, int attackSpecial, int defensePhysical, int defenseSpecial, int speed) {
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.baseStats = new Stats(health, attackPhysical, attackSpecial, defensePhysical, defenseSpecial, speed);
		this.image = ResourceLoader.load(String.format("pokemon/sprites/%s.png", this.name.toLowerCase()));
		this.spriteLayout = new SpriteLayout(this.image);
		Monster.monsters.add(this);
		Monster.ids.add(this);
	}

	public Monster setSpriteOffset(Direction dir, Vector vector) {
		this.spriteOffset.put(dir, vector);
		return this;
	}

	public Vector getSpriteOffset(Direction dir) {
		if (this.spriteOffset.containsKey(dir)) return this.spriteOffset.get(dir);
		return new Vector();
	}

	public Monster setCollisionCorner1(Vector vector) {
		this.collisionCorner1 = vector;
		return this;
	}

	public Vector getCollisionCorner1() {
		return collisionCorner1;
	}

	public Monster setCollisionCorner2(Vector vector) {
		this.collisionCorner2 = vector;
		return this;
	}

	public Vector getCollisionCorner2() {
		return collisionCorner2;
	}

	public Monster setShadowSize(double size) {
		this.shadowSize = size;
		return this;
	}

	public double getShadowSize() {
		return this.shadowSize;
	}

	public Monster setBobHeight(Direction dir, Integer integer) {
		this.bobHeight.put(dir, integer);
		return this;
	}

	public int getBobHeight(Direction dir) {
		if (this.bobHeight.containsKey(dir)) return this.bobHeight.get(dir);
		return 1;
	}

	public Monster setAnimationSpeed(int speed) {
		this.animationSpeed = speed;
		return this;
	}

	public int getAnimationSpeed() {
		return this.animationSpeed;
	}

	public static class SpriteLayout {
		private static int size = 32;

		public BufferedImage[] up = new BufferedImage[2];
		public BufferedImage[] down = new BufferedImage[2];
		public BufferedImage[] left = new BufferedImage[2];
		public BufferedImage[] right = new BufferedImage[2];

		public SpriteLayout(BufferedImage sprite) {
			this.up[0] = sprite.getSubimage(0, 0, size, size);
			this.up[1] = sprite.getSubimage(size, 0, size, size);
			this.down[0] = sprite.getSubimage(0, size, size, size);
			this.down[1] = sprite.getSubimage(size, size, size, size);
			this.left[0] = sprite.getSubimage(0, 2*size, size, size);
			this.left[1] = sprite.getSubimage(size, 2*size, size, size);
			this.right[0] = sprite.getSubimage(size, 3*size, size, size);
			this.right[1] = sprite.getSubimage(0, 3*size, size, size);
		}

		public BufferedImage[] get(Direction dir) {
			switch (dir) {
				case UP:
					return this.up;
				case DOWN:
					return this.down;
				case LEFT:
					return this.left;
				default:
					return this.right;
			}
		}

		public BufferedImage get(Direction dir, int index) {
			BufferedImage[] bi = this.get(dir);
			return bi[index % bi.length];
		}
	}
}
