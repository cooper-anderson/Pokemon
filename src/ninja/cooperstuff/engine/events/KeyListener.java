package ninja.cooperstuff.engine.events;

import java.awt.event.KeyEvent;
import java.util.HashSet;

public class KeyListener implements java.awt.event.KeyListener {
	private static HashSet<Integer> hashSet = new HashSet<>();

	public static boolean isKeyPressed(int key) {
		return hashSet.contains(key);
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		hashSet.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		hashSet.remove(e.getKeyCode());
	}
}
