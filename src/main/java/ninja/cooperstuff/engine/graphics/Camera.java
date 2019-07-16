package ninja.cooperstuff.engine.graphics;

import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.components.Transform;
import ninja.cooperstuff.engine.util.Vector;

import java.awt.*;

public class Camera {
	public Game game;
	public Vector position = new Vector();
	private DrawMode drawMode = DrawMode.GAME;
	private PosMode posMode = PosMode.CENTER;

	public Camera(Game game) {
		this.game = game;
	}

	public void toGameCoords(Graphics2D screen) {
		if (this.drawMode != DrawMode.GAME) {
			double width = this.game.getWidth() - 16;
			double height = this.game.getHeight() - 16;
			this.drawMode = DrawMode.GAME;
			screen.translate(-this.game.camera.position.x + width / 2, -this.game.camera.position.y + height / 2);
		}
	}

	public void toScreenCoords(Graphics2D screen) {
		if (this.drawMode != DrawMode.SCREEN) {
			double width = this.game.getWidth() - 16;
			double height = this.game.getHeight() - 16;
			this.drawMode = DrawMode.SCREEN;
			screen.translate(this.game.camera.position.x - width / 2, this.game.camera.position.y - height / 2);
		}
	}

	public void toTopLeft(Graphics2D screen) {
		if (this.posMode != PosMode.LEFT) {
			double width = this.game.getWidth() - 16;
			double height = this.game.getHeight() - 16;
			this.posMode = PosMode.LEFT;
			screen.translate(-width / 2, -height / 2);
		}
	}

	public void toCenter(Graphics2D screen) {
		if (this.posMode != PosMode.CENTER) {
			double width = this.game.getWidth() - 16;
			double height = this.game.getHeight() - 16;
			this.posMode = PosMode.CENTER;
			screen.translate(width / 2, height / 2);
		}
	}

	public void follow(Vector position, Vector tolerance) {
		if (position.x - this.position.x > tolerance.x) this.position.x = position.x - tolerance.x;
		if (position.x - this.position.x < -tolerance.x) this.position.x = position.x + tolerance.x;
		if (position.y - this.position.y > tolerance.y) this.position.y = position.y - tolerance.y;
		if (position.y - this.position.y < -tolerance.y) this.position.y = position.y + tolerance.y;
	}

	public void follow(GameObject gameObject, Vector tolerance) {
		follow(gameObject.transform.position, tolerance);
	}

	public void follow(Transform transform, Vector tolerance) {
		follow(transform.position, tolerance);
	}

	public void lagFollow(Vector position) {
		double speed = 0.5;
		if (this.position.x < position.x) this.position.x += speed;
		else if (this.position.x > position.x) this.position.x -= speed;
		if (this.position.y < position.y) this.position.y += speed;
		else if (this.position.y > position.y) this.position.y -= speed;
	}

	private enum DrawMode {GAME, SCREEN}
	private enum PosMode {LEFT, CENTER}
}
