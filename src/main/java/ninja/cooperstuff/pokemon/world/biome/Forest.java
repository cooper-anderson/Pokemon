package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

public class Forest implements Biome {
	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 1);
		return noise < 0.5 ? 0 : 1;
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		if (world.heightNoise(x, y, 8, 0.2) < -0.15 && x % 3 == 0 && y % 3 == 0) return Tiles.forestTree.getTile(4);
		return Tiles.forestGrass;
	}

	@Override
	public Tile getDetail(World world, int height, int x, int y) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				TileData tileData = world.getTileData(x + i, y + j);
				if (tileData != null && (tileData.getGround() == Tiles.forestTree.getTile(4))) return Tiles.forestTree.getTile(i, j);
			}
		}
		if (height != 0 && world.getTileData(x, y).getGround() != Tiles.forestTree.tile) return Tiles.forestGrassTall;
		return null;
	}
}
