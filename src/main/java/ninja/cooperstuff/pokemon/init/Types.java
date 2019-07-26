package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.type.Type;

import java.awt.*;

import static ninja.cooperstuff.pokemon.type.Type.Effectiveness.*;

public class Types implements Init.Initializer {
	public static final int alpha = 200;
	public static final Type NORMAL = new Type("Normal", "NOR", new Color(170, 170, 153, alpha));
	public static final Type FIRE = new Type("Fire", "FIR", new Color(255, 68, 34, alpha));
	public static final Type WATER = new Type("Water", "WAT", new Color(51, 153, 255, alpha));
	public static final Type ELECTRIC = new Type("Electric", "ELE", new Color(255, 204, 51, alpha));
	public static final Type GRASS = new Type("Grass", "GRA", new Color(119, 204, 85, alpha));
	public static final Type ICE = new Type("Ice", "ICE", new Color(102, 204, 255, alpha));
	public static final Type FIGHTING = new Type("Fighting", "FIG", new Color(187, 85, 68, alpha));
	public static final Type POISON = new Type("Poison", "POI", new Color(170, 85, 153, alpha));
	public static final Type GROUND = new Type("Ground", "GRO", new Color(221, 187, 85, alpha));
	public static final Type FLYING = new Type("Flying", "FLY", new Color(136, 153, 255, alpha));
	public static final Type PSYCHIC = new Type("Psychic", "PSY", new Color(255, 85, 153, alpha));
	public static final Type BUG = new Type("Bug", "BUG", new Color(170, 187, 34, alpha));
	public static final Type ROCK = new Type("Rock", "ROC", new Color(187, 170, 102, alpha));
	public static final Type GHOST = new Type("Ghost", "GHO", new Color(102, 102, 187, alpha));
	public static final Type DRAGON = new Type("Dragon", "DRA", new Color(119, 102, 238, alpha));
	public static final Type DARK = new Type("Dark", "DAR", new Color(119, 85, 68, alpha));
	public static final Type STEEL = new Type("Steel", "STE", new Color(170, 170, 187, alpha));
	public static final Type FAIRY = new Type("Fairy", "FAI", new Color(238, 153, 238, alpha));

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
