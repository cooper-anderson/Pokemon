package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.type.Type;

import static ninja.cooperstuff.pokemon.type.Type.Effectiveness.*;

public class Types implements Init.Initializer {
	public static final Type normal = new Type("Normal", "NOR", "#aa9");
	public static final Type fire = new Type("Fire", "FIR", "#f42");
	public static final Type water = new Type("Water", "WAT", "#39f");
	public static final Type electric = new Type("Electric", "ELE", "#fc3");
	public static final Type grass = new Type("Grass", "GRA", "#7c5");
	public static final Type ice = new Type("Ice", "ICE", "#6cf");
	public static final Type fighting = new Type("Fighting", "FIG", "#b54");
	public static final Type poison = new Type("Poison", "POI", "#a59");
	public static final Type ground = new Type("Ground", "GRO", "#db5");
	public static final Type flying = new Type("Flying", "FLY", "#89f");
	public static final Type psychic = new Type("Psychic", "PSY", "#f59");
	public static final Type bug = new Type("Bug", "BUG", "#ab2");
	public static final Type rock = new Type("Rock", "ROC", "#ba6");
	public static final Type ghost = new Type("Ghost", "GHO", "#66b");
	public static final Type dragon = new Type("Dragon", "DRA", "#76e");
	public static final Type dark = new Type("Dark", "DAR", "#754");
	public static final Type steel = new Type("Steel", "STE", "#aab");
	public static final Type fairy = new Type("Fairy", "FAI", "#e9e");

	@Override
	public void preInit() {}

	@Override
	public void init() {
		normal.addEffectiveness(NONE, ghost);
		normal.addEffectiveness(HALF, rock, steel);
		fire.addEffectiveness(HALF, fire, water, rock, dragon);
		fire.addEffectiveness(SUPER, grass, ice, bug, steel);
		water.addEffectiveness(HALF, water, grass, dragon);
		water.addEffectiveness(SUPER, fire, ground, rock);
		electric.addEffectiveness(NONE, ground);
		electric.addEffectiveness(HALF, electric, grass, dragon);
		electric.addEffectiveness(SUPER, water, flying);
		grass.addEffectiveness(HALF, fire, grass, poison, flying, bug, dragon, steel);
		grass.addEffectiveness(SUPER, water, ground, rock);
		ice.addEffectiveness(HALF, fire, water, ice, steel);
		ice.addEffectiveness(SUPER, grass, ground, flying, dragon);
		fighting.addEffectiveness(NONE, ghost);
		fighting.addEffectiveness(HALF, poison, flying, psychic, bug, fairy);
		fighting.addEffectiveness(SUPER, normal, ice, rock, dark, steel);
		poison.addEffectiveness(NONE, steel);
		poison.addEffectiveness(HALF, poison, ground, rock, ghost);
		poison.addEffectiveness(SUPER, grass, fairy);
		ground.addEffectiveness(NONE, flying);
		ground.addEffectiveness(HALF, grass, bug);
		ground.addEffectiveness(SUPER, fire, electric, poison, rock, steel);
		flying.addEffectiveness(HALF, electric, rock, steel);
		flying.addEffectiveness(SUPER, grass, fighting, bug);
		psychic.addEffectiveness(NONE, dark);
		psychic.addEffectiveness(HALF, psychic, steel);
		psychic.addEffectiveness(SUPER, fighting, poison);
		bug.addEffectiveness(HALF, fire, fighting, poison, ghost, steel, fairy);
		bug.addEffectiveness(SUPER, grass, psychic, dark);
		rock.addEffectiveness(HALF, fighting, ground, steel);
		rock.addEffectiveness(SUPER, fire, ice, flying, bug);
		ghost.addEffectiveness(NONE, normal);
		ghost.addEffectiveness(HALF, dark);
		ghost.addEffectiveness(SUPER, psychic, ghost);
		dragon.addEffectiveness(NONE, fairy);
		dragon.addEffectiveness(HALF, steel);
		dragon.addEffectiveness(SUPER, dragon);
		dark.addEffectiveness(HALF, fighting, dark, fairy);
		dark.addEffectiveness(SUPER, psychic, ghost);
		steel.addEffectiveness(HALF, fire, water, electric, steel);
		steel.addEffectiveness(SUPER, ice, rock, fairy);
		fairy.addEffectiveness(HALF, fire, poison, steel);
		fairy.addEffectiveness(SUPER, fighting, dragon, dark);
	}

	@Override
	public void postInit() {}
}
