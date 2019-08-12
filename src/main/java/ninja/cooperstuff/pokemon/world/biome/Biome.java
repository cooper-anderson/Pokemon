package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.world.World;

public interface Biome {
	Biome cave = new Cave();
	Biome marsh = new Marsh();
	Biome forest = new Forest();
	Biome snow = new Snow();
	Biome woods = new Woods();
	Biome bog = new Bog();

	int getHeight(World world, int x, int y);

	Tile getTile(World world, int height, int x, int y);

	Tile getDetail(World world, int height, int x, int y);

	Monster getPokemon();
}
