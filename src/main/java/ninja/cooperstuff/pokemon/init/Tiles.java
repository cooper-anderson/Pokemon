package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.tile.ConnectedTile;
import ninja.cooperstuff.pokemon.tile.Tile;

public class Tiles implements Init.Initializer {
	public static final Tile ground1 = new Tile("Ground1", "cave/ground1.png");
	public static final Tile ground2 = new Tile("Ground2", "cave/ground2.png");
	public static final Tile ground3 = new Tile("Ground3", "cave/ground3.png");
	public static final ConnectedTile cliff = new ConnectedTile("Cliff", "cave/cliff.png");
	public static final Tile grass = new Tile("Grass", "marsh/grass.png");
	public static final ConnectedTile dirt = new ConnectedTile("Dirt", "marsh/dirt.png");
	public static final ConnectedTile marshCliff = new ConnectedTile("MarshCliff", "marsh/cliff.png");

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}
}
