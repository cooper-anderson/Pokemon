package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Stats;

import java.awt.*;

public class Pokemon extends Entity {
	private Monster monster;
	private Stats valuesEffort = new Stats();
	private Stats valuesIndividual = new Stats();
	private Stats stats;

	public Pokemon(Monster monster) {
		this.monster = monster;

	}

	@Override
	public void render(Graphics2D screen) {
		screen.drawImage(this.monster.spriteLayout.down[0], 0, 0, 64, 64, null);
	}
}
