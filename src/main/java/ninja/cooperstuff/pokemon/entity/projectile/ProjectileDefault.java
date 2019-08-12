package ninja.cooperstuff.pokemon.entity.projectile;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.move.Move;

public class ProjectileDefault extends Projectile {
	public ProjectileDefault(MoveInstance owner, Move move) {
		super(owner, move);
	}

	@Override
	public void behavior() {

	}
}
