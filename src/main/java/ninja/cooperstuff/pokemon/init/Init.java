package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.debug.Debug;

public class Init {
	protected interface Initializer {
		void preInit();

		void init();

		void postInit();
	}

	private static final Types types = new Types();
	private static final Moves moves = new Moves();
	private static final Monsters monsters = new Monsters();

	public static void preInit() {
		Debug.log("Starting preInitialization");
		types.preInit();
		moves.preInit();
		monsters.preInit();
		Debug.log("Finished preInitialization");
	}

	public static void init() {
		Debug.log("Starting initialization");
		types.init();
		moves.init();
		monsters.init();
		Debug.log("Finished initialization");
	}

	public static void postInit() {
		Debug.log("Starting postInitialization");
		types.postInit();
		moves.postInit();
		monsters.postInit();
		Debug.log("Finished postInitialization");
	}
}