package ninja.cooperstuff.pokemon.world.biome;

import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.util.DirectionFlag;
import ninja.cooperstuff.pokemon.util.PokemonWeight;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

public class Cave implements Biome {
	private PokemonWeight pokemon = new PokemonWeight()
			.add(Monsters.charmander, 1)
			.add(Monsters.ekans, 5)
			.add(Monsters.nidoranF, 5)
			.add(Monsters.nidoranM, 5)
			.add(Monsters.clefairy, 5)
			.add(Monsters.vulpix, 5)
			.add(Monsters.zubat)
			.add(Monsters.golbat, 7)
			.add(Monsters.diglett)
			.add(Monsters.dugtrio, 7)
			.add(Monsters.mankey, 7)
			.add(Monsters.growlithe, 2)
			.add(Monsters.machop, 5)
			.add(Monsters.geodude)
			.add(Monsters.graveler, 7)
			.add(Monsters.ponyta, 2)
			.add(Monsters.doduo, 3)
			.add(Monsters.onix, 2)
			.add(Monsters.drowzee, 7)
			.add(Monsters.cubone, 2)
			.add(Monsters.rhyhorn, 2);

	@Override
	public int getHeight(World world, int x, int y) {
		double noise = world.heightNoise(x, y, 2, 0);
		return noise <= 0.15 ? (noise > -0.4 ? 0 : -1) : (noise < 0.4 ? 1 : 2);
	}

	@Override
	public Tile getTile(World world, int height, int x, int y) {
		if (height == 0) return Tiles.ground2;
		if (height == -1) return Tiles.ground1;
		return Tiles.ground3;
	}

	@Override
	public Tile getDetail(World world, int height, int x, int y) {
		DirectionFlag flag = new DirectionFlag();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				TileData tileData = world.getTileData(x + i, y + j);
				if (tileData == null) flag.setFlag(i, j, false);
				else flag.setFlag(i, j, (tileData.getBiome() == this && height > -1 && tileData.getHeight() > height));
			}
		}
		int id = flag.getId();
		if (id == -2) return Tiles.ground3;
		if (id != -1) return Tiles.cliff.getTile(id);
		return null;
	}

	@Override
	public Monster getPokemon() {
		return this.pokemon.get();
	}
}
