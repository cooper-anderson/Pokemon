package ninja.cooperstuff.engine.util;

import java.util.Collection;
import java.util.Random;

public class RandomGet {
	public static <T> T get(Collection<T> collection) {
		if (collection.size() == 0) return null;
		int index = new Random().nextInt(collection.size());
		int i = 0;
		for (T item : collection) {
			if (i == index) return item;
			i++;
		}
		return null;
	}
}
