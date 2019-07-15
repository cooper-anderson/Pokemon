package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Noise;

import java.awt.*;

public class MapGenLaunch {
	private static MapGenGame game = new MapGenGame();

	private static int x = 0;
	private static int y = 0;
	private static double viewScale = 5;

	public static void run() throws InterruptedException {
		//Debug.info("Starting Pokemon");
		game.setSize(816, 616);
		game.setTitle("Pokemon");

		while (game.running) {
			game.update();
			game.repaint();
			Thread.sleep((long) (1000.0 / 30.0));

			int d = 10;
			if (KeyListener.isKeyHeld(Keys.W)) y -= d;
			if (KeyListener.isKeyHeld(Keys.S)) y += d;
			if (KeyListener.isKeyHeld(Keys.A)) x -= d;
			if (KeyListener.isKeyHeld(Keys.D)) x += d;
			if (KeyListener.isKeyHeld(Keys.BRACKET_LEFT)) viewScale = Math.max(viewScale - 1, 5);
			if (KeyListener.isKeyHeld(Keys.BRACKET_RIGHT)) viewScale += 1;
		}
	}

	private static class MapGenGame extends Game {
		static double multiplier = 1.0; // 1
		static double multiplierElevation = 6.12;
		static double multiplierMoisture = 5.05;
		static double scaleMap = .1;
		static double scaleElevation = 1;
		static double scaleMoisture = 5.56;
		static double offsetElevation = -0.58;
		static double offsetMoisture = 0.23;
		static boolean debug = false;

		@Override
		public void update() {
			if (!KeyListener.isKeyHeld(Keys.SHIFT)) {
				if (KeyListener.isKeyHeld(Keys.F)) multiplier /= 1.1;
				if (KeyListener.isKeyHeld(Keys.R)) multiplier *= 1.1;
				if (KeyListener.isKeyHeld(Keys.G)) multiplierElevation /= 1.1;
				if (KeyListener.isKeyHeld(Keys.T)) multiplierElevation *= 1.1;
				if (KeyListener.isKeyHeld(Keys.H)) multiplierMoisture /= 1.1;
				if (KeyListener.isKeyHeld(Keys.Y)) multiplierMoisture *= 1.1;
			} else {
				if (KeyListener.isKeyHeld(Keys.F)) scaleMap /= 1.1;
				if (KeyListener.isKeyHeld(Keys.R)) scaleMap *= 1.1;
				if (KeyListener.isKeyHeld(Keys.G)) scaleElevation /= 1.1;
				if (KeyListener.isKeyHeld(Keys.T)) scaleElevation *= 1.1;
				if (KeyListener.isKeyHeld(Keys.H)) scaleMoisture /= 1.1;
				if (KeyListener.isKeyHeld(Keys.Y)) scaleMoisture *= 1.1;
			}
			double d = 0.025;
			if (KeyListener.isKeyHeld(Keys.J)) offsetElevation -= d;
			if (KeyListener.isKeyHeld(Keys.U)) offsetElevation += d;
			if (KeyListener.isKeyHeld(Keys.K)) offsetMoisture -= d;
			if (KeyListener.isKeyHeld(Keys.I)) offsetMoisture += d;
			if (KeyListener.isKeyTyped(Keys.TILDE)) debug = !debug;
			super.update();
		}

		private static Color getColor(int tileX, int tileY) {
			double elevationMultiplier = 100;
			double moistureMultiplier = 50;

			double elevation = (Noise.noise((double) tileX / (scaleMap * scaleElevation * elevationMultiplier), (double) tileY / (scaleMap * scaleElevation * elevationMultiplier))/* + 1*/);
			double moisture = (Noise.noise(100000 + (double) (tileX) / (scaleMap * scaleMoisture * moistureMultiplier), 100000 + (double) (tileY) / (scaleMap * scaleMoisture * moistureMultiplier)/* + 1*/));

			elevation = 1.0 / (1.0 + Math.exp(-(elevation) * multiplier * multiplierElevation - offsetElevation)) * 4;
			moisture = 1.0 / (1.0 + Math.exp(-(moisture) * multiplier * multiplierMoisture - offsetMoisture)) * 6;

			if (elevation < 1) {
				if (moisture < 1) {
					return new Color(233, 221, 200);
				} else if (moisture < 2) {
					return new Color(196, 211, 172);
				} else if (moisture < 4) {
					return new Color(170, 203, 165);
				} else {
					return new Color(137, 157, 255); //
				}
			} else if (elevation < 2) {
				if (moisture < 1) {
					return new Color(228, 231, 203);
				} else if (moisture < 3) {
					return new Color(196, 211, 172);
				} else if (moisture < 5) {
					return new Color(180, 200, 170);
				} else {
					return new Color(165, 195, 169);
				}
			} else if (elevation < 3) {
				if (moisture < 2) {
					return new Color(228, 231, 203);
				} else if (moisture < 4) {
					return new Color(196, 204, 188);
				} else {
					return new Color(204, 212, 188);
				}
			} else {
				if (moisture < 1) {
					return new Color(153, 153, 153);
				} else if (moisture < 2) {
					return new Color(187, 187, 187);
				} else if (moisture < 4) {
					return new Color(221, 220, 188);
				} else {
					return new Color(248, 248, 248);
				}
			}
		}

		@Override
		public void render(Graphics2D screen) {
			super.render(screen);
			for (int i = 0; i < game.getWidth() / viewScale; i++) {
				for (int j = 0; j < game.getHeight() / viewScale; j++) {
					//double n = (Noise.noise((double) (x + i / scaleMap), (double) (y + j / scaleMap)));// + 1) / 2;
					//double asdf = 1.0 / (1.0 + Math.exp(-n * multiplier));
					//screen.setColor(new Color(0, 0, 0, (int) (255 * (KeyListener.isKeyHeld(Keys.SPACE) ? (n + 1) / 2 : asdf))));
					screen.setColor(getColor((int) ((double) x * scaleMap) + i, (int) ((double) y * scaleMap) + j));
					screen.fillRect((int) (i * viewScale), (int) (j * viewScale), (int) (viewScale), (int) (viewScale));
				}
			}
			if (debug) {
				screen.setColor(Color.WHITE);
				screen.fillRect(0, 0, 280, 70);
				screen.setColor(Color.BLACK);
				screen.drawString("Multiplier: " + String.valueOf(Math.round(multiplier * 100) / 100.0), 5, 20);
				screen.drawString("Elevation: " + String.valueOf(Math.round(multiplierElevation * 100) / 100.0), 5, 40);
				screen.drawString("Moisture: " + String.valueOf(Math.round(multiplierMoisture * 100) / 100.0), 5, 60);
				screen.drawString("Scale: " + String.valueOf(Math.round(scaleMap * 100) / 100.0), 100, 20);
				screen.drawString("Elevation: " + String.valueOf(Math.round(scaleElevation * 100) / 100.0), 100, 40);
				screen.drawString("Moisture: " + String.valueOf(Math.round(scaleMoisture * 100) / 100.0), 100, 60);
				screen.drawString("Offset:", 195, 20);
				screen.drawString("Elevation: " + String.valueOf(Math.round(offsetElevation * 100) / 100.0), 195, 40);
				screen.drawString("Moisture: " + String.valueOf(Math.round(offsetMoisture * 100) / 100.0), 195, 60);
			}
		}
	}
}
