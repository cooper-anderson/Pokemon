package ninja.cooperstuff.pokemon.monster;

import java.util.HashSet;

public class Monster {
	public static HashSet<Monster> monsters = new HashSet<>();

	public Monster() {
		Monster.monsters.add(this);
	}
}
