package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.util.DirectionFlag;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

import java.util.Random;

public class Marsh implements Biome {
	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 0);
		return noise < 0.1 ? 0 : (noise < 0.4 ? 1 : 2);
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		double noise = Noise.noise(100 + x / 10.0, 100 + y / 10.0);
		if (world.heightNoise(x, y, 3, 0.2) < -0.25 && (x + new Random(y).nextInt(3) - 1) % 3 == 0 && y % 3 == 0) return Tiles.marshTree.getTile(4);
		if (height == 0) return noise < 0 ? Tiles.grass : Tiles.dirt.tile;
		return Tiles.marshCliff.tile;
	}

	@Override
	public Tile getDetail(World world, int height, int x, int y) {
		DirectionFlag cliffFlags = new DirectionFlag();
		DirectionFlag dirtFlags = new DirectionFlag();
		TileData center = world.getTileData(x, y);
		Tile tree = null;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				TileData tileData = world.getTileData(x + i, y + j);
				if (tileData == null) {
					cliffFlags.setFlag(i, j, false);
					dirtFlags.setFlag(i, j, false);
				} else if (tileData.getGround() == Tiles.marshTree.getTile(4)) tree = Tiles.marshTree.getTile(i, j);
				else {
					cliffFlags.setFlag(i, j, (tileData.getBiome() == this && height > -1 && tileData.getHeight() > height));
					dirtFlags.setFlag(i, j, (tileData.getBiome() == this && center.getGround() == Tiles.grass && Tiles.dirt.isCenter(tileData.getGround())));
				}
			}
		}
		int cliffId = cliffFlags.getId();
		int dirtId = dirtFlags.getId();
		if (dirtId == -2) center.setGround(Tiles.dirt.tile);
		else if (dirtId != -1) center.setGround(Tiles.dirt.getTile(dirtId));
		if (tree != null) return tree;
		if (cliffId == -2) return Tiles.marshCliff.tile;
		if (cliffId != -1) return Tiles.marshCliff.getTile(cliffId);
		return null;
	}
}
