package ninja.cooperstuff.pokemon.world;

import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.world.biome.Biome;

public class TileData {
	private Biome biome;
	private int height;
	private Tile ground;
	private Tile detail;

	public TileData(World world, Biome biome, int height, Tile ground, Tile detail) {
		this.biome = biome;
		this.height = height;
		this.ground = ground;
		this.detail = detail;
	}

	public TileData(World world, Biome biome, int height, int x, int y) {
		this(world, biome, height, biome.getTile(world, height, x, y), null);
	}

	public TileData setBiome(Biome biome) {
		this.biome = biome;
		return this;
	}

	public Biome getBiome() {
		return this.biome;
	}

	public TileData setHeight(int height) {
		this.height = height;
		return this;
	}

	public int getHeight() {
		return this.height;
	}

	public TileData setGround(Tile ground) {
		this.ground = ground;
		return this;
	}

	public Tile getGround() {
		return this.ground;
	}

	public TileData setDetail(Tile detail) {
		this.detail = detail;
		return this;
	}

	public Tile getDetail() {
		return this.detail;
	}
}
