package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.pokemon.monster.Monster;

public class Monsters implements Init.Initializer {
	public static final Monster bulbasaur = new Monster("Bulbasaur");
	public static final Monster ivysaur = new Monster("Ivysaur");
	public static final Monster venusaur = new Monster("Venusaur");
	public static final Monster charmander = new Monster("Charmander");
	public static final Monster charmeleon = new Monster("Charmeleon");
	public static final Monster charizard = new Monster("Charizard");
	public static final Monster squirtle = new Monster("Squirtle");
	public static final Monster wartortle = new Monster("Wartortle");
	public static final Monster blastoise = new Monster("Blastoise");
	public static final Monster caterpie = new Monster("Caterpie");
	public static final Monster metapod = new Monster("Metapod");
	public static final Monster butterfree = new Monster("Butterfree");
	public static final Monster weedle = new Monster("Weedle");
	public static final Monster kakuna = new Monster("Kakuna");
	public static final Monster beedrill = new Monster("Beedrill");
	public static final Monster pidgey = new Monster("Pidgey");
	public static final Monster pidgeotto = new Monster("Pidgeotto");
	public static final Monster pidgeot = new Monster("Pidgeot");


	@Override
	public void preInit() {}

	@Override
	public void init() {}

	@Override
	public void postInit() {}
}
