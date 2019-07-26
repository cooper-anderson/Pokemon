package ninja.cooperstuff.pokemon.move;

import ninja.cooperstuff.pokemon.entity.MoveInstance;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.type.Type;

public class VineWhip extends Move {
	public VineWhip(String name, Type type, AttackType attackType, int power, int accuracy, int points) {
		super(name, type, attackType, power, accuracy, points);
	}

	@Override
	public MoveInstance behavior(Pokemon pokemon) {
		int count = 20;
		//for (int index = 0; index < count; index++) {
			//Projectile p = pokemon.game.instantiate(new VineWhipProjectile(pokemon, this, pokemon.facing.getAngle() + Math.PI, count - 1, count));

		//}
		return null;
	}

	/*private class VineWhipProjectile extends Projectile {
		private double angle;
		private int index;
		private int count;
		private int timer = 50;

		public VineWhipProjectile(Entity pokemon, Move move, double angle, int index, int count) {
			super(pokemon);
			this.angle = angle;
			this.index = index;
			this.count = count;
			//this.shadow.scale = 0.25;
		}

		@Override
		public void update() {
			double percent = (double) this.frame / (double) this.timer;
			double dist = 100 * ((double) this.index / (double) this.count) * Math.sin(percent * Math.PI);
			//this.transform.position = Vector.add(this.pokemon.transform.position, new Vector(dist * Math.cos(this.angle + percent * 2 * Math.PI), dist * Math.sin(this.angle + percent * 2 * Math.PI)));
			//if (this.frame > this.timer) this.destroy();
		}

		@Override
		public void behavior() {

		}

		public void render(Graphics2D screen) {
			if (this.index == this.count - 1) {
				Color c = screen.getColor();
				screen.setColor(this.color);
				screen.setStroke(new BasicStroke(5));
				//IntVector pos = Vector.sub(this.pokemon.transform.position, this.transform.position).getIntVector();
				//screen.drawLine(0, 0, pos.x, pos.y);
				screen.setColor(c);
			}
		}
	}*/
}
