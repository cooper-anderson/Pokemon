package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.util.DirectionFlag;
import ninja.cooperstuff.pokemon.util.PokemonWeight;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

import java.util.Random;

public class Woods implements Biome {
	private PokemonWeight pokemon = new PokemonWeight()
			.add(Monsters.caterpie)
			.add(Monsters.metapod, 3)
			.add(Monsters.weedle)
			.add(Monsters.kakuna, 3)
			.add(Monsters.pidgey)
			.add(Monsters.rattata)
			.add(Monsters.rattata, 3)
			.add(Monsters.spearow)
			.add(Monsters.ekans)
			.add(Monsters.pikachu, 2)
			.add(Monsters.oddish, 4)
			.add(Monsters.gloom, 2)
			.add(Monsters.paras, 8)
			.add(Monsters.parasect, 2)
			.add(Monsters.venonat, 5)
			.add(Monsters.venomoth, 2)
			.add(Monsters.exeggcute, 4)
			.add(Monsters.scyther, 1)
			.add(Monsters.pinsir, 1)
			.add(Monsters.snorlax, 1);

	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 0);
		return noise < 0.1 ? 0 : (noise < 0.4 ? 1 : 2);
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		double noise = Noise.noise(100 + x / 10.0, 100 + y / 10.0);
		if (world.heightNoise(x, y, 5, 0) < 0.25 && (x + new Random(y).nextInt(3) - 1) % 3 == 0 && y % 3 == 0) return Tiles.woodsTree.getTile(4);
		return noise < 0 ? Tiles.woodsGrass : Tiles.woodsMud.tile;
	}

	@Override
	public Tile getDetail(World world, int height, int x, int y) {
		DirectionFlag dirtFlags = new DirectionFlag();
		TileData center = world.getTileData(x, y);
		Tile tree = null;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				TileData tileData = world.getTileData(x + i, y + j);
				if (tileData == null) dirtFlags.setFlag(i, j, false);
				else if (tileData.getGround() == Tiles.woodsTree.getTile(4)) tree = Tiles.woodsTree.getTile(i, j);
				else dirtFlags.setFlag(i, j, (tileData.getBiome() == this && center.getGround() == Tiles.woodsGrass && Tiles.woodsMud.isCenter(tileData.getGround())));
			}
		}
		int mudId = dirtFlags.getId();
		if (mudId == -2) center.setGround(Tiles.woodsMud.tile);
		else if (mudId != -1) center.setGround(Tiles.woodsMud.getTile(mudId));
		if (tree != null) return tree;
		if (center.getGround() != Tiles.woodsTree.tile) {
			double noise = Noise.noise(150 + x / 1.5, 150 + y / 1.5);
			if (noise > 0.3 && center.getGround() == Tiles.woodsGrass) return Tiles.woodsFlowers;
			else if (noise > 0.2 && (center.getGround() == Tiles.woodsGrass || Tiles.woodsMud.isCenter(center.getGround()))) return Tiles.woodsGrassTall;
		}
		return null;
	}

	@Override
	public Monster getPokemon() {
		return this.pokemon.get();
	}
}
