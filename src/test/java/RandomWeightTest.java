import ninja.cooperstuff.engine.util.RandomWeight;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomWeightTest {
	private static RandomWeight<Integer> rw = new RandomWeight<>();

	@BeforeClass
	public static void setup() {
		rw.add(0, 5);
		rw.add(1, 5);
		rw.add(2, 5);
		rw.add(3, 5);
	}

	@Test
	public void testManual() {
		assertEquals(Integer.valueOf(0), rw.get(0));
		assertEquals(Integer.valueOf(0), rw.get(1));
		assertEquals(Integer.valueOf(0), rw.get(2));
		assertEquals(Integer.valueOf(0), rw.get(3));
		assertEquals(Integer.valueOf(0), rw.get(4));
		assertEquals(Integer.valueOf(1), rw.get(5));
		assertEquals(Integer.valueOf(1), rw.get(6));
		assertEquals(Integer.valueOf(1), rw.get(7));
		assertEquals(Integer.valueOf(1), rw.get(8));
		assertEquals(Integer.valueOf(1), rw.get(9));
		assertEquals(Integer.valueOf(2), rw.get(10));
		assertEquals(Integer.valueOf(2), rw.get(11));
		assertEquals(Integer.valueOf(2), rw.get(12));
		assertEquals(Integer.valueOf(2), rw.get(13));
		assertEquals(Integer.valueOf(2), rw.get(14));
		assertEquals(Integer.valueOf(3), rw.get(15));
		assertEquals(Integer.valueOf(3), rw.get(16));
		assertEquals(Integer.valueOf(3), rw.get(17));
		assertEquals(Integer.valueOf(3), rw.get(18));
		assertEquals(Integer.valueOf(3), rw.get(19));
	}

	@Test
	public void testAuto() {
		int count = 1000000;
		double percent = 0.05;
		HashMap<Integer, Integer> results = new HashMap<>();
		for (int i = 0; i < count; i++) {
			int item = rw.get();
			results.put(item, results.getOrDefault(item, 0) + 1);
		}
		assertTrue(Math.abs(results.get(0) - count / 4) < count * percent);
		assertTrue(Math.abs(results.get(1) - count / 4) < count * percent);
		assertTrue(Math.abs(results.get(2) - count / 4) < count * percent);
		assertTrue(Math.abs(results.get(3) - count / 4) < count * percent);

	}
}
