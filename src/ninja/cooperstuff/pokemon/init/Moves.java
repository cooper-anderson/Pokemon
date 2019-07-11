package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.pokemon.move.Bubble;
import ninja.cooperstuff.pokemon.move.Move;

import static ninja.cooperstuff.pokemon.init.Types.*;
import static ninja.cooperstuff.pokemon.move.Move.AttackType.*;

public class Moves implements Init.Initializer {
	public static final Move absorb = new Move("Absorb", grass, SPECIAL, 20, 100, 25);
	public static final Move acid = new Move("Acid", poison, SPECIAL, 40, 100, 30);
	public static final Move acidArmor = new Move("Acid Armor", poison, STATUS, 0, 0, 20);
	public static final Move agility = new Move("Agility", psychic, STATUS, 0, 0, 30);
	public static final Move amnesia = new Move("Amnesia", psychic, STATUS, 0, 0, 20);
	public static final Move auroraBeam = new Move("Aurora Beam", ice, SPECIAL, 65, 100, 20);
	public static final Move barrage = new Move("Barrage", normal, PHYSICAL, 15, 85, 20);
	public static final Move barrier = new Move("Barrier", psychic, STATUS, 0, 0, 20);
	public static final Move bide = new Move("Bide", normal, PHYSICAL, 0, 0, 10);
	public static final Move bind = new Move("Bind", normal, PHYSICAL, 15, 85, 20);
	public static final Move bite = new Move("Bite", dark, PHYSICAL, 60, 100, 25);
	public static final Move blizzard = new Move("Blizzard", ice, SPECIAL, 110, 70, 5);
	public static final Move bodySlam = new Move("Body Slam", normal, PHYSICAL, 85, 100, 15);
	public static final Move boneClub = new Move("Bone Club", ground, PHYSICAL, 65, 85, 20);
	public static final Move bonemerang = new Move("Bonemerang", ground, PHYSICAL, 50, 90, 10);
	public static final Move bubble = new Move("Bubble", water, SPECIAL, 40, 100, 30);
	public static final Move bubbleBeam = new Move("Bubble Beam", water, SPECIAL, 65, 100, 20);
	public static final Move clamp = new Move("Clamp", water, PHYSICAL, 35, 85, 10);


	public static final Move tackle = new Move("Tackle", normal, PHYSICAL, 40, 100, 35);

	@Override
	public void preInit() {}

	@Override
	public void init() {
		for (Move m : Move.moves) Debug.debug(m);
	}

	@Override
	public void postInit() {}
}
