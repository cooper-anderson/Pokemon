package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.debug.Debug;

public class Init {
	protected interface Initializer {
		void preInit();

		void init();

		void postInit();
	}

	private static final Types types = new Types();
	private static final Sounds sounds = new Sounds();
	private static final Moves moves = new Moves();
	private static final Monsters monsters = new Monsters();
	private static final Tiles tiles = new Tiles();

	public static void preInit() {
		Debug.log("Starting preInitialization");
		types.preInit();
		sounds.preInit();
		moves.preInit();
		monsters.preInit();
		tiles.preInit();
		Debug.log("Finished preInitialization");
	}

	public static void init() {
		Debug.log("Starting initialization");
		types.init();
		sounds.init();
		moves.init();
		monsters.init();
		tiles.init();
		Debug.log("Finished initialization");
	}

	public static void postInit() {
		Debug.log("Starting postInitialization");
		types.postInit();
		sounds.postInit();
		moves.postInit();
		monsters.postInit();
		tiles.postInit();
		Debug.log("Finished postInitialization");
	}
}
