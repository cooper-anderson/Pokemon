package ninja.cooperstuff.pokemon.util;

import ninja.cooperstuff.engine.util.RandomWeight;
import ninja.cooperstuff.pokemon.monster.Monster;

public class PokemonWeight {
	private RandomWeight<Monster> pokemon = new RandomWeight<>();
	private int defaultWeight;

	public PokemonWeight(int defaultWeight) {
		this.defaultWeight = defaultWeight;
	}

	public PokemonWeight() {
		this(10);
	}

	public PokemonWeight add(Monster pokemon, int weight) {
		this.pokemon.add(pokemon, weight);
		return this;
	}

	public PokemonWeight add(Monster pokemon) {
		return this.add(pokemon, this.defaultWeight);
	}

	public Monster get() {
		return this.pokemon.get();
	}
}
