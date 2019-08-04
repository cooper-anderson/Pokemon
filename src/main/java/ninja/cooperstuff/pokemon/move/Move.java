package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.type.Type;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.util.Status;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public abstract class Move {
	public static HashSet<Move> moves = new HashSet<>();
	public enum AttackType {PHYSICAL, SPECIAL, STATUS}

	public HashMap<Stats.Stat, StatModification> modifiers = new HashMap<>();
	public HashMap<Stats.Stat, StatModification> selfModifiers = new HashMap<>();
	public HashMap<Status, Double> effects = new HashMap<>();
	public HashMap<Status, Double> selfEffects = new HashMap<>();

	public String name;
	public Type type;
	public int power;
	public int accuracy;
	public int points;
	public MoveDefault.AttackType attackType;
	public int projectileCountMin = 1;
	public int projectileCountMax = 1;
	public int projectileDelay = 10;
	public double recoilPercent = 0.0;
	public double recoilChance = 0.0;

	public Move(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		this.name = name;
		this.type = type;
		this.attackType = attackType;
		this.power = power;
		this.accuracy = accuracy;
		this.points = points;
		Move.moves.add(this);
	}

	/*public final boolean use(Pokemon pokemon) {
		return this.use(pokemon, new Vector(0, 1));
	}

	public final boolean use(Pokemon pokemon, double angle, double speed) {
		return this.use(pokemon, new Vector(speed * Math.cos(angle), speed * Math.sin(angle)));
	}*/

	public boolean use(Pokemon pokemon) {
		// TODO check status modifiers, accuracy
		if (pokemon.canUseMove()) {
			for (Stats.Stat stat : this.selfModifiers.keySet()) {
				StatModification data = this.selfModifiers.get(stat);
				pokemon.modifyStat(stat, data.modifier, data.sign, data.chance);
			}
			for (Status status : this.selfEffects.keySet()) {
				if (new Random().nextDouble() < this.selfEffects.get(status)) pokemon.setStatus(status);
			}
			this.behavior(pokemon);
			return true;
		}
		return false;
	}

	public abstract MoveInstance behavior(Pokemon pokemon);

	public Move addModifier(Stats.Stat stat, Stats.Modifier modifier, Stats.Sign sign, double chance) {
		this.modifiers.put(stat, new StatModification(modifier, sign, chance));
		return this;
	}

	public Move addSelfModifier(Stats.Stat stat, Stats.Modifier modifier, Stats.Sign sign, double chance) {
		this.selfModifiers.put(stat, new StatModification(modifier, sign, chance));
		return this;
	}

	public Move addEffect(Status status, double chance) {
		this.effects.put(status, chance);
		return this;
	}

	public Move addSelfEffect(Status status, double chance) {
		this.selfEffects.put(status, chance);
		return this;
	}

	public Move addRecoil(double percent, double chance) {
		this.recoilPercent = percent;
		this.recoilChance = chance;
		return this;
	}

	public final String toString() {
		return String.format("MoveDefault(\"%s\", %s, %s, %d, %d, %d)", this.name, this.type.name, this.attackType, this.power, this.accuracy, this.points);
	}

	public class StatModification {
		public Stats.Modifier modifier;
		public Stats.Sign sign;
		public double chance;

		StatModification(Stats.Modifier modifier, Stats.Sign sign, double chance) {
			this.modifier = modifier;
			this.sign = sign;
			this.chance = chance;
		}
	}
}
