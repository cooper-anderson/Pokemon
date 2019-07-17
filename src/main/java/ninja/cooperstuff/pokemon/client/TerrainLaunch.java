package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.pokemon.entity.Player;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public class TerrainLaunch {
	private static TerrainGame game = new TerrainGame();;

	public static void run() throws InterruptedException {

		Debug.level = 1;

		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();

		game.stopLoading();

		while (game.running) {
			game.update();
			game.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}

	private static class TerrainGame extends Game {
		public World world = new World(this);
		private Player p;

		private int generateCounter = 0;

		public boolean showDetails = true;

		public TerrainGame() {
			super();
			this.setSize(240 * 4 + 16, 160 * 4 + 39);
			this.setResizable(false);
			this.setTitle("Pokemon");
			this.p = this.instantiate(new Player(this.world, Monsters.bulbasaur));
			//this.camera.position = new Vector(-this.getWidth() / 2, -this.getHeight() / 2);
			//this.p.transform.position = new Vector(this.getWidth() / 2, this.getHeight() / 2);
		}

		@Override
		public void update() {
			if (KeyListener.isKeyHeld(Keys.R)) this.world.scaleMap *= 1.1;
			if (KeyListener.isKeyHeld(Keys.F)) this.world.scaleMap /= 1.1;
			if (KeyListener.isKeyHeld(Keys.I)) this.camera.position.y -= 1;
			if (KeyListener.isKeyHeld(Keys.K)) this.camera.position.y += 1;
			if (KeyListener.isKeyHeld(Keys.J)) this.camera.position.x -= 1;
			if (KeyListener.isKeyHeld(Keys.L)) this.camera.position.x += 1;

			if (KeyListener.isKeyDown(Keys.SPACE)) this.showDetails = !this.showDetails;
			this.world.showDetails = this.showDetails;
			if (this.generateCounter == 0) {
				this.world.generate((int) Math.floor(this.p.transform.position.x / 32), (int) Math.floor(this.p.transform.position.y / 32));
				this.generateCounter = 2;
			}
			this.generateCounter--;
			super.update();
		}

		@Override
		public void render(Graphics2D screen) {
			super.render(screen);
			world.render(screen);
			screen.fillRect(-1, -1, 2, 2);
		}
	}
}
