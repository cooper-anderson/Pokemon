package ninja.cooperstuff.pokemon.world;

import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.world.biome.Biome;

import java.awt.*;

public class World {
	Game game;
	double multiplier = 1.0;
	double multiplierElevation = 6.12;
	double multiplierMoisture = 5.05;
	public double scaleMap = .1;
	double scaleElevation = 1;
	double scaleMoisture = 5.56;
	double offsetElevation = -0.58;
	double offsetMoisture = 0.23;

	public World(Game game) {
		this.game = game;
	}

	private double getElevation(double x, double y) {
		double elevation = (Noise.noise(x / (this.scaleMap * this.scaleElevation * 100), y / (this.scaleMap * this.scaleElevation * 100)));
		return World.sigmoid(multiplier * elevation * multiplierElevation + offsetElevation) * 4;
	}

	private double getMoisture(double x, double y) {
		double moisture = (Noise.noise(x / (this.scaleMap * this.scaleMoisture * 50), y / (this.scaleMap * this.scaleMoisture * 50)));
		return World.sigmoid(multiplier * moisture * multiplierMoisture + offsetMoisture) * 4;
	}

	private Biome getBiome(int x, int y) {
		return this.getBiome(this.getElevation(x, y), this.getMoisture(x, y));
	}

	private Biome getBiome(double elevation, double moisture) {
		return null;
	}

	public void render(Graphics2D screen) {
		int width = this.game.getWidth() - 16;
		int height = this.game.getHeight() - 16;
		this.game.camera.toScreenCoords(screen);
		screen.translate(width / 2, height / 2);
		int x = (int) this.game.camera.position.x;
		int y = (int) this.game.camera.position.y;
		int screenOffsetX = ((x > 0) ? x % 32 : ((x % 32) + 32) % 32);
		int screenOffsetY = ((y > 0) ? y % 32 : ((y % 32) + 32) % 32);
		int tileOffsetX = Math.floorDiv(x, 32);
		int tileOffsetY = Math.floorDiv(y, 32);
		for (int i = 0; i - 3 < width / 32; i++) {
			for (int j = 0; j - 2 < height / 32; j++) {
				double n = this.getElevation(i + tileOffsetX - width / 64, j + tileOffsetY - height / 64) / 4;
				screen.drawImage((n < 0.33 ? Tiles.ground1 : (n < 0.66 ? Tiles.ground2 : Tiles.ground3)).getSprite(), (i - 1 - width / 64) * 32 - screenOffsetX, (j - 1 - height / 64) * 32 - screenOffsetY, 32, 32, null);
			}
		}
		screen.translate(-width / 2, -height / 2);
		this.game.camera.toGameCoords(screen);
	}

	private static double sigmoid(double z) {
		return 1.0 / (1.0 + Math.exp(-z));
	}
}
