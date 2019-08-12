package ninja.cooperstuff.pokemon.entity.particle;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.pokemon.util.Constants;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class StatModifier extends Particle {
	private HashSet<IntVector> lines = new HashSet<>();
	public int sign;
	public double scale;
	public int width;

	public StatModifier(World world, Color color, int sign, double scale) {
		super(world);
		this.sign = sign;
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), sign == 1 ? 255 : 0);
		this.scale = scale;
		this.width = (int) (Constants.statModifier.width * scale);
	}

	public StatModifier(World world) {
		this(world, Constants.statModifier.color.ATTACK, 1, 1.0);
	}

	private void spawnLines() {
		Random r = new Random();
		int count = Constants.statModifier.lineCount;
		for (int i = 0; i < count; i++) {
			this.lines.add(new IntVector(this.width * (-0.5 + (double) i / (double) (count - 1)), Constants.statModifier.height * (r.nextDouble() - (sign == 1 ? 0 : 3))));
		}
	}

	@Override
	public void behavior() {
		if (this.frame <= 40 && this.frame % 20 == 0) this.spawnLines();
		this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), Math.min(255, Math.max(0, this.color.getAlpha() - (this.sign == 1 ? 1 : (this.frame > 20 ? -5 : 0)))));
		for (IntVector line : this.lines) {
			line.y -= this.sign;
		}
	}

	@Override
	public void render(Graphics2D screen) {
		screen.setStroke(new BasicStroke(1));
		screen.setColor(this.color);
		for (IntVector line : this.lines) {
			int limit = (int) (this.scale * (5 - Math.pow(line.x, 2) / 40.0));
			if (line.y < limit) {
				screen.drawLine(line.x, line.y, line.x, Math.min(line.y + Constants.statModifier.lineLength, limit));
			}
		}
	}
}
