import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.components.Rigidbody;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;

import java.awt.*;

public class TestObject extends GameObject {
	private static int count = 0;

	private int r = 50;
	private int id = 0;
	private Rigidbody rb;

	public TestObject() {
		this.id = TestObject.count++;
		this.rb = this.addComponent(Rigidbody.class);
	}

	@Override
	public void update() {
		if (KeyListener.isKeyPressed(Keys.W) && this.transform.position.y == 300) this.rb.velocity.y = -300;
		if (KeyListener.isKeyPressed(Keys.S)) this.transform.position.y += 1;
		if (KeyListener.isKeyPressed(Keys.A)) this.transform.position.x -= 1;
		if (KeyListener.isKeyPressed(Keys.D)) this.transform.position.x += 1;
		if (KeyListener.isKeyPressed(Keys.R)) this.transform.scale.x += .01;
		if (KeyListener.isKeyPressed(Keys.F)) this.transform.scale.x -= .01;
		if (KeyListener.isKeyPressed(Keys.T)) this.transform.scale.y += .01;
		if (KeyListener.isKeyPressed(Keys.G)) this.transform.scale.y -= .01;
		if (KeyListener.isKeyPressed(Keys.Y)) {
			this.transform.scale.x *= 1.01;
			this.transform.scale.y *= 1.01;
		}
		if (KeyListener.isKeyPressed(Keys.H)) {
			this.transform.scale.x /= 1.01;
			this.transform.scale.y /= 1.01;
		}
		if (KeyListener.isKeyPressed(Keys.E)) this.rb.angularVelocity += Math.PI / 24;
		if (KeyListener.isKeyPressed(Keys.Q)) this.rb.angularVelocity -= Math.PI / 24;
		if (KeyListener.isKeyPressed(Keys.C)) this.rb.angularVelocity = 1;
		if (KeyListener.isKeyPressed(Keys.X)) this.rb.angularVelocity = 0;
	}

	@Override
	public void late_update() {
		if (this.transform.position.y > 300) {
			this.transform.position.y = 300;
			this.getComponent(Rigidbody.class).velocity.y = 0;
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		//graphics.drawOval(-50, -50, 100, 100);
		//graphics.setColor(Color.getHSBColor((int) ((this.rb.angularVelocity * 360) % 360), 50, 100));
		//double shx = 0, shy = 0;
		//graphics.shear(shx, shy);
		//graphics.drawRect(-50, -50, 100, 100);
		//graphics.shear(-shx, -shy);
		graphics.drawRoundRect(-50, -50, 100, 100, 15, 15);
	}
}
