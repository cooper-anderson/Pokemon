package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;

public class Launch {
	private static Game game = new Game();

	public static void run() throws InterruptedException {
		Debug.info("Starting Pokemon");
		game.setSize(800, 600);
		game.setTitle("Pokemon");

		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();

		while (game.running) {
			game.update();
			game.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}
}
