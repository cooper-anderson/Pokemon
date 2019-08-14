package ninja.cooperstuff.pokemon.monster;

import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.move.Move;
import ninja.cooperstuff.pokemon.type.Type;

import java.util.ArrayList;

public class Magikarp extends Monster {
	public Magikarp(String name, Type type1, Type type2, int health, int attackPhysical, int attackSpecial, int defensePhysical, int defenseSpecial, int speed) {
		super(name, type1, type2, health, attackPhysical, attackSpecial, defensePhysical, defenseSpecial, speed);
	}

	@Override
	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		moves.add(Moves.splash);
		return moves;
	}
}
