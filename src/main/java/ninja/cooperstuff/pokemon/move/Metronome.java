package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.engine.util.RandomGet;
import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.type.Type;

public class Metronome extends Move {
	public Metronome(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		return RandomGet.get(Move.moves).behavior(pokemon);
	}
}