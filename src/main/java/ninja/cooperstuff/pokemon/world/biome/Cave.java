package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.util.DirectionFlag;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

public class Cave implements Biome {
	@Override
	public Tile getTile(World world, int x, int y) {
		double scale = 10;
		double noise = Noise.noise(x / scale, y / scale);
		return noise < 0 ? Tiles.ground2 : Tiles.ground3;
	}

	@Override
	public Tile getDetail(World world, int x, int y) {
		DirectionFlag flag = new DirectionFlag();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				System.out.printf("%d %d%n", i, j);
				TileData tileData = world.getTileData(x + i, y + j);
				if (tileData == null) flag.setFlag(i, j, false);
				else flag.setFlag(i, j, (tileData.getBiome() == this && world.getTileData(x, y).getGround() == Tiles.ground2 && tileData.getGround() == Tiles.ground3));
			}
		}
		int id = flag.getId();
		if (id == -2) return Tiles.ground3;
		if (id != -1) return Tiles.cliff.getTile(id);
		return null;
	}
}
