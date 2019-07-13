package ninja.cooperstuff.engine;

import ninja.cooperstuff.engine.components.Component;
import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.events.Keys;
import ninja.cooperstuff.engine.graphics.Screen;

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Game extends JFrame {
	public HashSet<GameObject> gameObjects = new HashSet<>();
	public LinkedList<GameObject> deleteQueue = new LinkedList<>();
	public boolean running = true;
	private Screen screen = new Screen(this);

	public Game() {
		super();
		this.setVisible(true);
		this.addKeyListener(new KeyListener());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(this.screen);
	}

	public void close() {
		this.running = false;
		this.dispose();
	}

	public void update() {
		if (KeyListener.isKeyPressed(Keys.ESC)) this.close();
		else {
			for (GameObject gameObject : this.deleteQueue) this.gameObjects.remove(gameObject);
			for (GameObject gameObject : this.gameObjects) {
				gameObject.update();
				for (Class componentType : gameObject.components.keySet()) gameObject.components.get(componentType).update();
			}
			for (GameObject gameObject : this.gameObjects) {
				gameObject.late_update();
				for (Class componentType : gameObject.components.keySet()) gameObject.components.get(componentType).late_update();
				gameObject.frame++;
			}
		}
	}

	public <T extends GameObject> T instantiate(T gameObject) {
		gameObject.game = this;
		this.gameObjects.add(gameObject);
		return gameObject;
	}

	public <T extends GameObject> void destroy(T gameObject) {
		this.deleteQueue.add(gameObject);
	}
}
