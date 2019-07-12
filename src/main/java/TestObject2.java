import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;

import java.awt.*;


public class TestObject2 extends GameObject {
	private int x = 50;
	private int y = 50;
	private int r = 50;

	@Override
	public void update() {
		if (KeyListener.isKeyPressed(Keys.I)) this.y -= 1;
		if (KeyListener.isKeyPressed(Keys.K)) this.y += 1;
		if (KeyListener.isKeyPressed(Keys.J)) this.x -= 1;
		if (KeyListener.isKeyPressed(Keys.L)) this.x += 1;
		if (KeyListener.isKeyPressed(Keys.Y)) this.r += 1;
		if (KeyListener.isKeyPressed(Keys.H)) this.r -= 1;
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawOval(this.x - Math.abs(this.r), this.y - Math.abs(this.r), 2 * Math.abs(this.r), 2 * Math.abs(this.r));
	}
}
