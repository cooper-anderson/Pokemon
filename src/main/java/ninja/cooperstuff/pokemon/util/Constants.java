package ninja.cooperstuff.pokemon.util;

import java.awt.*;

public abstract class Constants {
	public static abstract class healthBar {
		public static abstract class color {
			public static final Color BORDER = new Color(128, 128, 128);
			public static final Color BACKGROUND = new Color(100, 100, 100);
			public static final Color HIGH = new Color(89, 246, 69);
			public static final Color MID = new Color(238, 227, 81);
			public static final Color LOW = new Color(249, 89, 66);
		}
		public static final int ANIMATION_STEP = 10;
		public static final int ANIMATION_DELAY = 120;

		public static Color getColor(double percent) {
			if (percent <= 0.2) return color.LOW;
			if (percent <= 0.5) return color.MID;
			return color.HIGH;
		}
	}

	public static abstract class statModifier {
		public static abstract class color {
			public static final Color ATTACK = new Color(255, 100, 100);
			public static final Color DEFENSE = new Color(100, 255, 100);
			public static final Color SPEED = new Color(100, 100, 255);
		}
		public static final int lineCount = 10;
		public static final int lineLength = 16;
		public static final int width = 32;
		public static final int height = 32;
	}

	public static final double projectileVelocity = 5.0;
	public static final int shadowOpacity = 128;
	public static final Color shadowColor = new Color(0, 0, 0, shadowOpacity);
	public static final int deathParticleCount = 10;
	public static final double maxBezierMagnitude = 500.0;
}
