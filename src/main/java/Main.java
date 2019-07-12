import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.pokemon.client.Launch;
import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.move.Move;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.Random;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Launch.run();
	}
	/*private static Game game = new Game();

	public static void main(String[] args) throws InterruptedException {
		Debug.info("Starting Pokemon");
		game.setSize(800, 600);
		game.setTitle("Pokemon");

		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();
		//TestObject test = game.instantiate(new TestObject());
		//TestObject2 test2 = game.instantiate(new TestObject2());
		while (game.running) {
			if (KeyListener.isKeyPressed(Keys.SPACE)) game.instantiate(new TestObject());
			game.update();
			game.repaint();
			ninja.cooperstuff.pokemon.init.Init.initialize();
			System.out.print(Moves.bubble.points);
			for (Type t : Moves.bubble.type.getEffOut().keySet()) {
				System.out.print(t.name);
			}
			System.out.println("\n");
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}*/

	/*public static void main(String[] args) throws InterruptedException {
		Debug.info("Starting Pokemon");
		Debug.level = 0;
		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();
		for (Monster m : Monster.monsters) {
			Debug.debug(m);
		}
		Debug.log(Monster.monsters.size());
		Debug.debug("test");
		Debug.log("test");
		Debug.info("test");
		Debug.warn("test");
		Debug.error("test");
		Noise.seed = 8193;
		for (double i = 0; i < 200; i++) {
			String x = "";
			for (double j = 0; j < 150; j++) {
				int value = (int) Math.round((Noise.noise(Noise.seed + i / 100, Noise.seed + j / 100) + 1) * 5);
				String c;
				if (value < 4) c = " ";
				else if (value < 6) c = "o";
				else c = " ";
				x = String.format("%s%s", x, c);
			}
			Debug.log(x);
		}
	}*/
}