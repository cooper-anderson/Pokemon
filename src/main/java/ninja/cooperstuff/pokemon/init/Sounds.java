package ninja.cooperstuff.pokemon.init;


import ninja.cooperstuff.pokemon.sound.Sound;

public class Sounds implements Init.Initializer {
	public static final Sound shiny = new Sound("/pokemon/sounds/safeguard_light_jingle", 1.0);
	public static final Sound faint = new Sound("/pokemon/sounds/faint_no_hp", 0.9);

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}
}
