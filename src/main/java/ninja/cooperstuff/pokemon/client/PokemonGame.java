package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.pokemon.entity.Player;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public class PokemonGame extends Game {
	public World world = new World(this);
	private Player p;

	private int generateCounter = 0;
	private boolean showDetails = true;

	public PokemonGame() {
		super();
		this.setSize(240 * 4 + 16, 160 * 4 + 39);
		this.setResizable(false);
		this.setTitle("Pokemon");
		this.p = this.instantiate(new Player(this.world, Monsters.charmander));
	}

	@Override
	public void update() {
		if (KeyListener.isKeyDown(Keys.SPACE)) this.showDetails = !this.showDetails;
		this.world.showDetails = this.showDetails;
		if (this.generateCounter == 0) {
			this.world.generate((int) Math.floor(this.p.transform.position.x / 32), (int) Math.floor(this.p.transform.position.y / 32));
			this.generateCounter = 2;
		}
		this.generateCounter--;
		super.update();
	}

	public void run() throws InterruptedException {
		Debug.level = 1;
		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();
		this.stopLoading();

		while (this.running) {
			this.update();
			this.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}

	@Override
	public void render(Graphics2D screen) {
		super.render(screen);
		world.render(screen);
		screen.fillRect(-1, -1, 2, 2);
	}
}