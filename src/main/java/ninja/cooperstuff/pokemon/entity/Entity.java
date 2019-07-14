package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;

public abstract class Entity extends GameObject {
	private World world;
	public Shadow shadow = new Shadow(this);

	public Entity(World world) {
		this.world = world;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D screen) {
		this.shadow.render(screen);
	}

	public static class Shadow {
		public Entity entity;
		public int opacity = 128;
		public Vector size = new Vector(32, 16);
		public double scale = 1;

		public Shadow(Entity entity) {
			this.entity = entity;
		}

		public void render(Graphics2D screen) {
			Color color = screen.getColor();
			screen.setColor(new Color(0, 0, 0, this.opacity));
			int x = (int) (-this.size.x * this.scale / 2);
			int y = (int) (-this.size.y * this.scale / 2);
			screen.fillOval(x, y, (int) (this.size.x * this.scale), (int) (this.size.y * this.scale));
			screen.setColor(color);
		}
	}
}
