package ninja.cooperstuff.engine;

import ninja.cooperstuff.engine.components.GameObject;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.graphics.Camera;
import ninja.cooperstuff.engine.graphics.Screen;
import ninja.cooperstuff.engine.util.Keys;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Game extends JFrame {
	public HashSet<GameObject> gameObjects = new HashSet<>();
	public LinkedList<GameObject> addQueue = new LinkedList<>();
	public LinkedList<GameObject> deleteQueue = new LinkedList<>();
	public boolean running = true;
	private Screen screen = new Screen(this);
	public Camera camera = new Camera(this);
	public boolean loading = true;

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
		RepaintManager.currentManager(this).markCompletelyClean(this.screen);
		if (KeyListener.isKeyHeld(Keys.ESC)) this.close();
		else {
			this.gameObjects.addAll(this.addQueue);
			this.addQueue.clear();
			for (GameObject gameObject : this.deleteQueue) this.gameObjects.remove(gameObject);
			this.deleteQueue.clear();
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
		KeyListener.clearKeys();
	}

	public void render(Graphics2D screen) {}

	public void loadingScreen(Graphics2D screen) {
		int width = this.getWidth();
		int height = this.getHeight();
		screen.setColor(new Color(51, 51, 51));
		screen.fillRect(0, 0, width, height);
		screen.setColor(Color.WHITE);
		screen.scale(10, 10);
		screen.drawString("Loading", (width - 450) / 20, (height + 45) / 20);
	}

	public void stopLoading() {
		this.loading = false;
	}

	public <T extends GameObject> T instantiate(T gameObject) {
		gameObject.game = this;
		this.addQueue.add(gameObject);
		return gameObject;
	}

	public <T extends GameObject> void destroy(T gameObject) {
		this.deleteQueue.add(gameObject);
	}
}
