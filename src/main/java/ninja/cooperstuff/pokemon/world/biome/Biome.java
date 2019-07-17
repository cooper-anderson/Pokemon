package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.world.World;

public interface Biome {
	Biome cave = new Cave();

	Tile getTile(World world, int x, int y);

	Tile getDetail(World world, int x, int y);
}
