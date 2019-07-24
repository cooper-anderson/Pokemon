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
	public static final Tile forestGrass = new Tile("ForestGrass", "forest/grass.png", true);
	public static final Tile forestGrassTall = new Tile("ForestGrassTall", "forest/tall_grass1.png", true);
	public static final MultiTile forestTree = new MultiTile("ForestTree", "forest/tree.png", false, false);
	public static final Tile snowGrass = new Tile("SnowGrass", "snow/snow.png", true);
	public static final Tile snowGrassTall = new Tile("SnowGrassTall", "snow/grass.png", true);
	public static final MultiTile snowTree = new MultiTile("SnowTree", "snow/tree.png", false, false);
	public static final ConnectedTile snowCliff = new ConnectedTile("snowCliff", "snow/cliff.png", false, true);
	public static final ConnectedTile snowSlush = new ConnectedTile("SnowSlush", "snow/slush.png", true, true);
	public static final Tile woodsGrass = new Tile("WoodsGrass", "woods/grass.png", true);
	public static final ConnectedTile woodsMud = new ConnectedTile("WoodsMud", "woods/mud.png", true, true);
	public static final MultiTile woodsTree = new MultiTile("WoodsTree", "woods/tree.png", false, false);
	public static final Tile woodsFlowers = new Tile("WoodsFlowers", "woods/flowers.png", true);
	public static final Tile woodsGrassTall = new Tile("WoodsGrassTall", "woods/grass_tall.png", true);
	public static final Tile bogGrass = new Tile("BogGrass", "bog/grass.png", true);
	public static final Tile bogGrassTall = new Tile("BogGrassTall", "bog/grass_tall.png", true);
	public static final ConnectedTile bogPuddle = new ConnectedTile("BogPuddle", "Bog/Puddle.png", true, true);
	public static final Tile bogStump = new Tile("BogStump", "bog/stump.png", true);
	public static final Tile bogLily1 = new Tile("bogLily1", "bog/lily1.png", true);
	public static final Tile bogLily2 = new Tile("bogLily2", "bog/lily2.png", true);
	public static final MultiTile bogTree = new MultiTile("BogTree", "bog/tree.png", false, false);
	public static final MultiTile bogTreeWet = new MultiTile("BogTreeWet", "bog/tree_wet.png", false, false);

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

	public static void setTree(MultiTile tree) {
		tree.getTile(1).setCollisionCorner1(0, 8);
		tree.getTile(0).setCollisionCorner1(8, 8);
		tree.getTile(3).setCollisionCorner1(8, 0);
		tree.getTile(6).setCollisionCorner1(8, 0).setCollisionCorner2(16, 8);
		tree.getTile(2).setCollisionCorner1(0, 8).setCollisionCorner2(8, 16);
		tree.getTile(5).setCollisionCorner2(8, 16);
		tree.getTile(8).setCollisionCorner2(8, 8);
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		setCliff(cliff, 8, 2);
		setCliff(marshCliff, 12, 2);
		setCliff(snowCliff, 12, 2);
		setTree(marshTree);
		setTree(forestTree);
		setTree(snowTree);
		setTree(woodsTree);
		setTree(bogTree);
		setTree(bogTreeWet);
	}

	@Override
	public void postInit() {

	}
}
