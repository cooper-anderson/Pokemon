package ninja.cooperstuff.engine.graphics;

import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.components.GameObject;

import javax.swing.*;
import java.awt.*;

public class Screen extends JComponent {
	Game game;

	public Screen(Game game) {
		this.game = game;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		for (GameObject gameObject : this.game.gameObjects) {
			g.translate(gameObject.transform.position.x, gameObject.transform.position.y);
			g.rotate(gameObject.transform.rotation);
			g.scale(gameObject.transform.scale.x, gameObject.transform.scale.y);
			gameObject.render(g);
			g.scale(1 / gameObject.transform.scale.x, 1 / gameObject.transform.scale.y);
			g.rotate(-gameObject.transform.rotation);
			g.translate(-gameObject.transform.position.x, -gameObject.transform.position.y);
		}
	}
}
