package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;
import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.type.Type;

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
			//if (KeyListener.isKeyPressed(Keys.SPACE)) game.instantiate(new TestObject());
			game.update();
			game.repaint();

			//System.out.print(Moves.bubble.points);
			//System.out.println("\n");
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}
}
