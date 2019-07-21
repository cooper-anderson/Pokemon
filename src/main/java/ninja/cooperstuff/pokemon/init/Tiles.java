package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.tile.ConnectedTile;
import ninja.cooperstuff.pokemon.tile.MultiTile;
import ninja.cooperstuff.pokemon.tile.Tile;

public class Tiles implements Init.Initializer {
	public static final Tile ground1 = new Tile("Ground1", "cave/ground1.png", true);
	public static final Tile ground2 = new Tile("Ground2", "cave/ground2.png", true);
	public static final Tile ground3 = new Tile("Ground3", "cave/ground3.png", true);
	public static final ConnectedTile cliff = new ConnectedTile("Cliff", "cave/cliff.png", false, true);
	public static final Tile grass = new Tile("Grass", "marsh/grass.png", true);
	public static final ConnectedTile dirt = new ConnectedTile("Dirt", "marsh/dirt.png", true, true);
	public static final ConnectedTile marshCliff = new ConnectedTile("MarshCliff", "marsh/cliff.png", false, true);
	public static final MultiTile marshTree = new MultiTile("MarshCliff", "marsh/tree.png", false, false);

	private static void setCliff(ConnectedTile cliff, int cliffSize, int margin) {
		cliff.getTile(0).setCollisionCorner1(0, margin).setCollisionCorner2(16, 16 - margin);
		cliff.getTile(1).setCollisionCorner1(0, margin).setCollisionCorner2(16, cliffSize - margin);
		cliff.getTile(2).setCollisionCorner1(16 - cliffSize + margin, 0).setCollisionCorner2(16 - margin, 16);
		cliff.getTile(3).setCollisionCorner1(margin, 0).setCollisionCorner2(cliffSize - margin, 16);
		cliff.getTile(4).setCollisionCorner1(16 - cliffSize + margin, margin);
		cliff.getTile(5).setCollisionCorner1(0, margin).setCollisionCorner2(cliffSize - margin, 16);
		cliff.getTile(6).setCollisionCorner1(16 - cliffSize + margin, 0).setCollisionCorner2(16, cliffSize - margin);
		cliff.getTile(7).setCollisionCorner2(cliffSize - margin, cliffSize - margin);
		cliff.getTile(8).setCollisionCorner2(16 - margin, 16 - margin);
		cliff.getTile(9).setCollisionCorner1(margin, 0).setCollisionCorner2(16, 16 - margin);
		cliff.getTile(10).setCollisionCorner1(0, margin).setCollisionCorner2(16 - margin, 16);
		cliff.getTile(11).setCollisionCorner1(margin, margin);
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		setCliff(cliff, 8, 2);
		setCliff(marshCliff, 12, 2);

		marshTree.getTile(1).setCollisionCorner1(0, 8);
		marshTree.getTile(0).setCollisionCorner1(8, 8);
		marshTree.getTile(3).setCollisionCorner1(8, 0);
		marshTree.getTile(6).setCollisionCorner1(8, 0).setCollisionCorner2(16, 8);
		marshTree.getTile(2).setCollisionCorner1(0, 8).setCollisionCorner2(8, 16);
		marshTree.getTile(5).setCollisionCorner2(8, 16);
		marshTree.getTile(8).setCollisionCorner2(8, 8);
	}

	@Override
	public void postInit() {

	}
}
