import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.pokemon.move.DefaultMove;
import ninja.cooperstuff.pokemon.type.Type;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class Init {
	@BeforeClass
	public static void setup() {
		Debug.level = 3;
		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();
	}

	@Test
	public void countTypes() {
		assertEquals(18, Type.types.size());
	}

	@Test
	public void countMoves() {
		assertEquals(165, DefaultMove.moves.size());
	}
}
