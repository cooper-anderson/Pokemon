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

public class Bog implements Biome {
	private PokemonWeight pokemon = new PokemonWeight()
			.add(Monsters.squirtle, 1)
			.add(Monsters.caterpie)
			.add(Monsters.weedle)
			.add(Monsters.pidgey)
			.add(Monsters.spearow)
			.add(Monsters.nidoranF)
			.add(Monsters.nidoranM)
			.add(Monsters.oddish, 6)
			.add(Monsters.psyduck, 8)
			.add(Monsters.poliwag, 7)
			.add(Monsters.poliwhirl, 3)
			.add(Monsters.tentacool, 8)
			.add(Monsters.tentacruel, 3)
			.add(Monsters.slowpoke, 5)
			.add(Monsters.seel, 5)
			.add(Monsters.shellder, 5)
			.add(Monsters.krabby, 5)
			.add(Monsters.tangela, 5)
			.add(Monsters.horsea, 5)
			.add(Monsters.goldeen)
			.add(Monsters.staryu, 5)
			.add(Monsters.magikarp);

	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 0);
		return noise < 0.1 ? 0 : (noise < 0.4 ? 1 : 2);
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		double noise = Noise.noise(100 + x / 10.0, 100 + y / 10.0);
		if (world.heightNoise(x, y, 3, 0.2) < -0.25 && (x + new Random(y).nextInt(3) - 1) % 3 == 0 && y % 3 == 0) return Tiles.bogTree.getTile(4);
		return noise < 0 ? Tiles.bogGrass : Tiles.bogPuddle.tile;
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
				else if (tileData.getGround() == Tiles.bogTree.getTile(4)) tree = (j == -1 && Tiles.bogPuddle.isCenter(center.getGround()) ? Tiles.bogTreeWet : Tiles.bogTree).getTile(i, j);
				else dirtFlags.setFlag(i, j, (tileData.getBiome() == this && center.getGround() == Tiles.bogGrass && Tiles.bogPuddle.isCenter(tileData.getGround())));
			}
		}
		int puddleId = dirtFlags.getId();
		if (puddleId == -2) center.setGround(Tiles.bogPuddle.tile);
		else if (puddleId != -1) center.setGround(Tiles.bogPuddle.getTile(puddleId));
		if (tree != null) return tree;
		if (Tiles.bogPuddle.isCenter(center.getGround())) {
			double noise = Noise.noise(150 + x / 1.5, 150 + y / 1.5);
			if (noise > 0.3) return Tiles.bogLily1;
			else if (noise > 0.2) return Tiles.bogLily2;
		}
		return null;
	}

	@Override
	public Monster getPokemon() {
		return this.pokemon.get();
	}
}
