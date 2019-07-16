package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.tile.Tile;

public interface Biome {
	public Tile getTile(double x, double y);
}
