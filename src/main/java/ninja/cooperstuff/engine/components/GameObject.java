package ninja.cooperstuff.engine.components;

import ninja.cooperstuff.engine.Game;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class GameObject {
	public Game game = null;
	public Transform transform = new Transform(this);
	public HashMap<Class, Component> components = new HashMap<>();
	public int frame = 0;

	public <T extends Component> T addComponent(Class<T> componentType) {
		Component component = null;
		try {
			component = componentType.getDeclaredConstructor().newInstance();
			component.gameObject = this;
			this.components.put(componentType, component);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return (T) component;
	}

	public <T extends Component> T getComponent(Class<T> componentType) {
		return (T) this.components.get(componentType);
	}

	public <T extends Component> boolean hasComponent(Class<T> componentType) {
		return this.components.containsKey(componentType);
	}

	public void destroy() {
		this.game.destroy(this);
	}

	@SuppressWarnings("unused")
	public void update() {

	}

	@SuppressWarnings("unused")
	public void fixed_update() {

	}

	@SuppressWarnings("unused")
	public void late_update() {

	}

	@SuppressWarnings("unused")
	public void render(Graphics2D screen) {

	}
}
