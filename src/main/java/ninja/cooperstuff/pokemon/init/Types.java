package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.type.Type;

import static ninja.cooperstuff.pokemon.type.Type.Effectiveness.*;

public class Types implements Init.Initializer {
	public static final Type NORMAL = new Type("Normal", "NOR", "#aa9");
	public static final Type FIRE = new Type("Fire", "FIR", "#f42");
	public static final Type WATER = new Type("Water", "WAT", "#39f");
	public static final Type ELECTRIC = new Type("Electric", "ELE", "#fc3");
	public static final Type GRASS = new Type("Grass", "GRA", "#7c5");
	public static final Type ICE = new Type("Ice", "ICE", "#6cf");
	public static final Type FIGHTING = new Type("Fighting", "FIG", "#b54");
	public static final Type POISON = new Type("Poison", "POI", "#a59");
	public static final Type GROUND = new Type("Ground", "GRO", "#db5");
	public static final Type FLYING = new Type("Flying", "FLY", "#89f");
	public static final Type PSYCHIC = new Type("Psychic", "PSY", "#f59");
	public static final Type BUG = new Type("Bug", "BUG", "#ab2");
	public static final Type ROCK = new Type("Rock", "ROC", "#ba6");
	public static final Type GHOST = new Type("Ghost", "GHO", "#66b");
	public static final Type DRAGON = new Type("Dragon", "DRA", "#76e");
	public static final Type DARK = new Type("Dark", "DAR", "#754");
	public static final Type STEEL = new Type("Steel", "STE", "#aab");
	public static final Type FAIRY = new Type("Fairy", "FAI", "#e9e");

	@Override
	public void preInit() {}

	@Override
	public void init() {
		NORMAL.addEffectiveness(NONE, GHOST);
		NORMAL.addEffectiveness(HALF, ROCK, STEEL);
		FIRE.addEffectiveness(HALF, FIRE, WATER, ROCK, DRAGON);
		FIRE.addEffectiveness(SUPER, GRASS, ICE, BUG, STEEL);
		WATER.addEffectiveness(HALF, WATER, GRASS, DRAGON);
		WATER.addEffectiveness(SUPER, FIRE, GROUND, ROCK);
		ELECTRIC.addEffectiveness(NONE, GROUND);
		ELECTRIC.addEffectiveness(HALF, ELECTRIC, GRASS, DRAGON);
		ELECTRIC.addEffectiveness(SUPER, WATER, FLYING);
		GRASS.addEffectiveness(HALF, FIRE, GRASS, POISON, FLYING, BUG, DRAGON, STEEL);
		GRASS.addEffectiveness(SUPER, WATER, GROUND, ROCK);
		ICE.addEffectiveness(HALF, FIRE, WATER, ICE, STEEL);
		ICE.addEffectiveness(SUPER, GRASS, GROUND, FLYING, DRAGON);
		FIGHTING.addEffectiveness(NONE, GHOST);
		FIGHTING.addEffectiveness(HALF, POISON, FLYING, PSYCHIC, BUG, FAIRY);
		FIGHTING.addEffectiveness(SUPER, NORMAL, ICE, ROCK, DARK, STEEL);
		POISON.addEffectiveness(NONE, STEEL);
		POISON.addEffectiveness(HALF, POISON, GROUND, ROCK, GHOST);
		POISON.addEffectiveness(SUPER, GRASS, FAIRY);
		GROUND.addEffectiveness(NONE, FLYING);
		GROUND.addEffectiveness(HALF, GRASS, BUG);
		GROUND.addEffectiveness(SUPER, FIRE, ELECTRIC, POISON, ROCK, STEEL);
		FLYING.addEffectiveness(HALF, ELECTRIC, ROCK, STEEL);
		FLYING.addEffectiveness(SUPER, GRASS, FIGHTING, BUG);
		PSYCHIC.addEffectiveness(NONE, DARK);
		PSYCHIC.addEffectiveness(HALF, PSYCHIC, STEEL);
		PSYCHIC.addEffectiveness(SUPER, FIGHTING, POISON);
		BUG.addEffectiveness(HALF, FIRE, FIGHTING, POISON, GHOST, STEEL, FAIRY);
		BUG.addEffectiveness(SUPER, GRASS, PSYCHIC, DARK);
		ROCK.addEffectiveness(HALF, FIGHTING, GROUND, STEEL);
		ROCK.addEffectiveness(SUPER, FIRE, ICE, FLYING, BUG);
		GHOST.addEffectiveness(NONE, NORMAL);
		GHOST.addEffectiveness(HALF, DARK);
		GHOST.addEffectiveness(SUPER, PSYCHIC, GHOST);
		DRAGON.addEffectiveness(NONE, FAIRY);
		DRAGON.addEffectiveness(HALF, STEEL);
		DRAGON.addEffectiveness(SUPER, DRAGON);
		DARK.addEffectiveness(HALF, FIGHTING, DARK, FAIRY);
		DARK.addEffectiveness(SUPER, PSYCHIC, GHOST);
		STEEL.addEffectiveness(HALF, FIRE, WATER, ELECTRIC, STEEL);
		STEEL.addEffectiveness(SUPER, ICE, ROCK, FAIRY);
		FAIRY.addEffectiveness(HALF, FIRE, POISON, STEEL);
		FAIRY.addEffectiveness(SUPER, FIGHTING, DRAGON, DARK);
	}

	@Override
	public void postInit() {}
}
