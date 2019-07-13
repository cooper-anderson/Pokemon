package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.world.World;

public class Launch {
	private static Game game = new Game();

	private static int id = 1;
	private static Pokemon p;
	private static int wait = 0;

	public static void run() throws InterruptedException {
		Debug.info("Starting Pokemon");
		game.setSize(800, 600);
		game.setTitle("Pokemon");

		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();

		p = game.instantiate(new Pokemon(new World(), Monster.ids.get(id)));

		while (game.running) {
			game.update();
			game.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
			if (KeyListener.isKeyPressed(Keys.P) && wait == 0) {
				id++; if (id == Monster.ids.size()) id = 1;
				respawn();
			} else if (KeyListener.isKeyPressed(Keys.O) && wait == 0) {
				id--; if (id == 0) id = Monster.ids.size() - 1;
				respawn();
			}
			if (wait > 0) wait--;
		}

		Debug.info("Closing Pokemon");
	}

	static void respawn() {
		p.monster = Monster.ids.get(id);
		wait = 10;
	}
}
