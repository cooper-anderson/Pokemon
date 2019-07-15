package ninja.cooperstuff.engine.events;

import java.awt.event.KeyEvent;
import java.util.HashSet;

public class KeyListener implements java.awt.event.KeyListener {
	private static HashSet<Integer> hashSet = new HashSet<>();
	private static HashSet<Integer> downSet = new HashSet<>();
	private static HashSet<Integer> upSet = new HashSet<>();
	private static HashSet<Integer> typedSet = new HashSet<>();

	public static boolean isKeyHeld(int key) {
		return hashSet.contains(key);
	}

	public static boolean isKeyDown(int key) {
		return downSet.contains(key);
	}

	public static boolean isKeyUp(int key) {
		return upSet.contains(key);
	}

	public static boolean isKeyTyped(int key) {
		return typedSet.contains(key);
	}

	public static void clearKeys() {
		downSet = new HashSet<>();
		upSet = new HashSet<>();
		typedSet = new HashSet<>();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!hashSet.contains(e.getKeyCode())) downSet.add(e.getKeyCode());
		typedSet.add(e.getKeyCode());
		hashSet.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (hashSet.contains(e.getKeyCode())) upSet.add(e.getKeyCode());
		hashSet.remove(e.getKeyCode());
	}
}
