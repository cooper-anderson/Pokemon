package ninja.cooperstuff.engine.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomWeight<T> {
	private ArrayList<WeightContainer> items = new ArrayList<>();
	private HashMap<T, Integer> itemsMap = new HashMap<>();
	private int size = 0;

	public RandomWeight<T> add(T item, int weight) {
		this.size += weight;
		if (itemsMap.containsKey(item)) {
			this.size -= this.items.get(this.itemsMap.get(item)).weight;
			this.items.get(this.itemsMap.get(item)).weight = weight;
		}
		else {
			int index = this.items.size();
			this.items.add(new WeightContainer(item, weight));
			this.itemsMap.put(item, index);
		}
		return this;
	}

	public T get(int index) {
		if (this.size == 0) return null;
		int current = 0;
		for (WeightContainer container : this.items) {
			if (index < current + container.weight) return container.item;
			current += container.weight;
		}
		return null;
	}

	public T get() {
		if (this.size == 0) return null;
		return this.get(new Random().nextInt(this.size));
	}

	private class WeightContainer {
		T item;
		int weight;

		WeightContainer(T item, int weight) {
			this.item = item;
			this.weight = weight;
		}
	}
}
