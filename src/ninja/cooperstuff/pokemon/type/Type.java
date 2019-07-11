package ninja.cooperstuff.pokemon.type;

import java.util.HashMap;
import java.util.HashSet;

public class Type {
	public static final HashSet<Type> types = new HashSet<>();
	public enum Effectiveness {NORMAL, NONE, HALF, SUPER}

	public String name;
	public String abbreviation;
	public String color;
	public HashMap<Type, Effectiveness> effOut = new HashMap<>();
	public HashMap<Type, Effectiveness> effIn = new HashMap<>();

	public Type(String name, String abbr, String color) {
		this.name = name;
		this.abbreviation = abbr;
		this.color = color;
		Type.types.add(this);
	}

	public void addEffectiveness(Effectiveness eff, Type... types) {
		for (Type type : types) {
			this.effOut.put(type, eff);
			type.effIn.put(this, eff);
		}
	}

	public Effectiveness getEffectiveness(Type type) {
		if (this.effOut.containsKey(type)) {
			return this.effOut.get(type);
		}
		return Effectiveness.NORMAL;
	}

	public String toString() {
		return String.format("Type(%s)", this.name);
	}
}
