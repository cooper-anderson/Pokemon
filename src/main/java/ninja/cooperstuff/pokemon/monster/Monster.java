package ninja.cooperstuff.pokemon.monster;

import ninja.cooperstuff.pokemon.util.ResourceLoader;
import ninja.cooperstuff.pokemon.util.Stats;

import java.awt.image.BufferedImage;
import java.util.HashSet;

public class Monster {
	public static HashSet<Monster> monsters = new HashSet<>();

	public String name;
	public Stats baseStats;
	private BufferedImage image;
	public SpriteLayout spriteLayout;

	public Monster() {
		this.name = "Bulbasaur";
		this.image = ResourceLoader.load(String.format("pokemon/sprites/%s.png", this.name.toLowerCase()));
		this.spriteLayout = new SpriteLayout(this.image);
		Monster.monsters.add(this);
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
			//this.right[0] = sprite.getSubimage(size, 2*size, -size, size);
			//this.right[1] = sprite.getSubimage(2*size, 2*size, -size, size);
		}
	}
}
