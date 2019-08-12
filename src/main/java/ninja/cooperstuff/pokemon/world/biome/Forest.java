package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.util.PokemonWeight;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

import java.util.Random;

public class Forest implements Biome {
	private PokemonWeight pokemon = new PokemonWeight()
			.add(Monsters.bulbasaur, 1)
			.add(Monsters.caterpie)
			.add(Monsters.weedle)
			.add(Monsters.pidgey)
			.add(Monsters.rattata)
			.add(Monsters.spearow)
			.add(Monsters.ekans)
			.add(Monsters.pikachu, 1)
			.add(Monsters.nidoranF)
			.add(Monsters.nidoranM)
			.add(Monsters.jigglypuff, 5)
			.add(Monsters.oddish, 5)
			.add(Monsters.meowth, 5)
			.add(Monsters.mankey, 3)
			.add(Monsters.abra, 2)
			.add(Monsters.bellsprout, 8)
			.add(Monsters.farfetchd, 4)
			.add(Monsters.exeggcute, 4)
			.add(Monsters.tangela, 3)
			.add(Monsters.snorlax, 1);

	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 1);
		return noise < 0.5 ? 0 : 1;
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		if (world.heightNoise(x, y, 8, 0.2) < -0.15 && (x + new Random(y).nextInt(3) - 1) % 3 == 0 && y % 3 == 0) return Tiles.forestTree.getTile(4);
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

	@Override
	public Monster getPokemon() {
		return this.pokemon.get();
	}
}
